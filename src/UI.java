import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UI extends BorderPane {
    
    public UI() {
      MenuBar menuBar = new MenuBar();

      MenuItem iNew = new MenuItem("New");
      iNew.setOnAction(e -> Controller.onNew());

      MenuItem iOpen = new MenuItem("Open");
      iOpen.setOnAction(e -> Controller.onOpen());

      MenuItem iSave = new MenuItem("Save");
      iSave.setOnAction(e -> Controller.onSave());

      MenuItem iSaveAs = new MenuItem("Save As");
      iSaveAs.setOnAction(e -> Controller.onSaveAs());

      MenuItem iExit = new MenuItem("Exit");
      iExit.setOnAction(e -> Controller.onExit());

      final Menu fileMenu = new Menu("File", null, iNew, iOpen, iSave, iSaveAs, iExit);

      MenuItem iUndo = new MenuItem("Undo");
      iUndo.setOnAction(e -> Controller.onUndo());

      MenuItem iRedo = new MenuItem("Redo");
      iRedo.setOnAction(e -> Controller.onRedo());

      MenuItem iCut = new MenuItem("Cut");
      iCut.setOnAction(e -> Controller.onCut());

      MenuItem iCopy = new MenuItem("Copy");
      iCopy.setOnAction(e -> Controller.onCopy());

      MenuItem iPaste = new MenuItem("Paste");
      iPaste.setOnAction(e -> Controller.onPaste());

      final Menu editMenu = new Menu("Edit", null, iUndo, iRedo, iCut, iCopy, iPaste);

      final Menu viewMenu = new Menu("View", null);
      final Menu helpMenu = new Menu("Help", null);

      
      menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);
      setTop(menuBar);

      TextArea textArea = new TextArea();
      setCenter(textArea);

    }

    
}
