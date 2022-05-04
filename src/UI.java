import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

public class UI extends BorderPane {
  private MenuBar menuBar;
  private TextArea textArea;

  public UI() {
    initMenuBar();
    setTop(menuBar);

    textArea = new TextArea();
    setCenter(textArea);

  }

  public TextArea getTextArea() {
    return textArea;
  }

  private void initMenuBar() {
    menuBar = new MenuBar();

    MenuItem iNew = new MenuItem("New");
    iNew.setOnAction(e -> Main.onNew());

    MenuItem iOpen = new MenuItem("Open");
    iOpen.setOnAction(e -> Main.onOpen());

    MenuItem iSave = new MenuItem("Save");
    iSave.setOnAction(e -> Main.onSave());

    MenuItem iSaveAs = new MenuItem("Save As");
    iSaveAs.setOnAction(e -> Main.onSaveAs());

    MenuItem iExit = new MenuItem("Exit");
    iExit.setOnAction(e -> Main.onExit());

    final Menu fileMenu = new Menu("File", null, iNew, iOpen, iSave, iSaveAs, iExit);

    MenuItem iUndo = new MenuItem("Undo");
    iUndo.setOnAction(e -> Main.onUndo());

    MenuItem iRedo = new MenuItem("Redo");
    iRedo.setOnAction(e -> Main.onRedo());

    MenuItem iCut = new MenuItem("Cut");
    iCut.setOnAction(e -> Main.onCut());

    MenuItem iCopy = new MenuItem("Copy");
    iCopy.setOnAction(e -> Main.onCopy());

    MenuItem iPaste = new MenuItem("Paste");
    iPaste.setOnAction(e -> Main.onPaste());

    MenuItem iFind = new MenuItem("Find");
    iFind.setOnAction(e -> Main.onFind());

    final Menu editMenu = new Menu("Edit", null, iUndo, iRedo, iCut, iCopy, iPaste, iFind);

    MenuItem iAbout = new MenuItem("About");
    iAbout.setOnAction(e -> Main.onAbout());

    MenuItem iHowto = new MenuItem("วิธีใช้");
    iHowto.setOnAction(e -> Main.onHowto());

    final Menu helpMenu = new Menu("Help", null, iAbout,iHowto);

    MenuItem iformat = new MenuItem("Edit Format");
    iformat.setOnAction(e -> Main.onFormat());

    final Menu format = new Menu("Format", null, iformat);

    menuBar.getMenus().addAll(fileMenu, editMenu, format, helpMenu);
  }
}
