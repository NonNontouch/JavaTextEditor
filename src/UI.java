import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class UI extends BorderPane {
  private static TextArea textArea;
  private static Stage stage;
  private static MenuBar menuBar;

  public UI(Stage stage) {
    UI.stage = stage;

    initMenuBar();
    setTop(menuBar);

    textArea = new TextArea();
    setCenter(textArea);

    display();
  }

  private void initMenuBar() {
    menuBar = new MenuBar();

    MenuItem iNew = new MenuItem("New");
    iNew.setOnAction(e -> onNew());

    MenuItem iOpen = new MenuItem("Open");
    iOpen.setOnAction(e -> onOpen());

    MenuItem iSave = new MenuItem("Save");
    iSave.setOnAction(e -> onSave());

    MenuItem iSaveAs = new MenuItem("Save As");
    iSaveAs.setOnAction(e -> onSaveAs());

    MenuItem iExit = new MenuItem("Exit");
    iExit.setOnAction(e -> onExit());

    final Menu fileMenu = new Menu("File", null, iNew, iOpen, iSave, iSaveAs, iExit);

    MenuItem iUndo = new MenuItem("Undo");
    iUndo.setOnAction(e -> onUndo());

    MenuItem iRedo = new MenuItem("Redo");
    iRedo.setOnAction(e -> onRedo());

    MenuItem iCut = new MenuItem("Cut");
    iCut.setOnAction(e -> onCut());

    MenuItem iCopy = new MenuItem("Copy");
    iCopy.setOnAction(e -> onCopy());

    MenuItem iPaste = new MenuItem("Paste");
    iPaste.setOnAction(e -> onPaste());

    final Menu editMenu = new Menu("Edit", null, iUndo, iRedo, iCut, iCopy, iPaste);

    final Menu viewMenu = new Menu("View", null);

    MenuItem iAbout = new MenuItem("About");
    iAbout.setOnAction(e -> onAbout());

    final Menu helpMenu = new Menu("Help", null, iAbout);

    menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);
  }

  private void display() {
    Scene scene = new Scene(this, 800, 600);
    UI.stage.setScene(scene);
    UI.stage.setTitle("Notepad--");
    UI.stage.show();
  }

  public static void onNew() {
    System.out.println("New");
  }

  public static void onOpen() {
    FileIO fileIO = new FileIO(stage, textArea);
    fileIO.OpenFile();
  }

  public static void onSave() {
    System.out.println("Save");
  }

  public static void onSaveAs() {
    FileIO fileIO = new FileIO(stage, textArea);
    fileIO.SaveFile();
    /*FileChooser fileChooser = new FileChooser();
    
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
    FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("All Files", "*.*");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.getExtensionFilters().add(extFilter2);

    File file = fileChooser.showSaveDialog(UI.stage);
    if(file != null) if(file.exists()) {
      textArea.getText();
  } else {
      try { 
        file.createNewFile(); 
      }
      catch(Exception e) { 
        return; 
      }
      textArea.getText();
  }*/

  }

  public static void onExit() {
    System.exit(0);
  }

  public static void onUndo() {
    System.out.println("Undo");
  }

  public static void onRedo() {
    System.out.println("Redo");
  }

  public static void onCut() {
    System.out.println("Cut");
  }

  public static void onCopy() {
    System.out.println("Copy");
  }

  public static void onPaste() {
    System.out.println("Paste");
  }

  public static void onZoomIn() {
    System.out.println("Zoom In");
  }

  public static void onZoomOut() {
    System.out.println("Zoom Out");
  }

  public static void onZoomReset() {
    System.out.println("Zoom Reset");
  }

  public static void onAbout() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setTitle("About");
    alert.setContentText("This is a Notepad--");
    alert.show();
  }

  public static void onHelp() {
    System.out.println("Help");
  }
}
