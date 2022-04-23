import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
  private static Stage stage;
  private static FileIO fileIO;
  private static UI ui;
  private static TextControl textcontroler;

  @Override
  public void start(Stage primaryStage) {

    stage = primaryStage;

    ui = new UI(primaryStage);

    Scene scene = new Scene(ui, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Notepad--");
    primaryStage.show();

    fileIO = new FileIO(ui.getTextArea());
    textcontroler = new TextControl(primaryStage, ui.getTextArea());

  }

  public static void onNew() {
    System.out.println("New");
  }

  public static void onOpen() {

    fileIO.OpenFile();
  }

  public static void onSave() {
    System.out.println("Save");
  }

  public static void onSaveAs() {
    fileIO.SaveFile();
    FileChooser fileChooser = new FileChooser();

    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
    FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("All Files", "*.*");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.getExtensionFilters().add(extFilter2);

    File file = fileChooser.showSaveDialog(stage);
    if (file != null)
      if (file.exists()) {
        // ตรงนี้ต้องเขียนโปรแกรมให้รับตัวหนังสือจาก text editer
      } else {
        try {
          file.createNewFile();
        } catch (Exception e) {
          return;
        }
        // ตรงนี้ต้องเขียนโปรแกรมให้รับตัวหนังสือจาก text editer
      }

  }

  public static void onExit() {
    System.exit(0);
  }

  public static void onUndo() {
    ui.getTextArea().undo();
  }

  public static void onRedo() {
    ui.getTextArea().redo();
  }

  public static void onCut() {
    ui.getTextArea().cut();
  }

  public static void onCopy() {
    ui.getTextArea().copy();
  }

  public static void onPaste() {
    ui.getTextArea().paste();
  }

  public static void onZoomIn() {
    textcontroler.ZoomIn();
  }

  public static void onZoomOut() {
    textcontroler.ZoomOut();
  }

  public static void onZoomReset() {
    textcontroler.ZoomReset();
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

  public static void onFormat() {

    textcontroler.TextControlEvent();

  }

  public static void main(String[] args) {
    launch(args);
  }
}