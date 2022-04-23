import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {
  private static Stage stage;
  private static FileIO fileIO;
  private static UI ui;
  private static TextControl textcontroler;

  @Override
  public void start(Stage primaryStage) {

    stage = primaryStage;

    ui = new UI();
    fileIO = new FileIO(ui.getTextArea());
    textcontroler = new TextControl(primaryStage, ui.getTextArea());

    Scene scene = new Scene(ui, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Notepad--");
    primaryStage.show();
    primaryStage.setOnCloseRequest(event -> {
      event.consume();
      EventBeforeExit(stage);
    });
  }

  public static void EventBeforeExit(Stage stage) {
    if (!ui.getTextArea().getText().equals("")) {
      ButtonType savebtn = new ButtonType("Save");
      ButtonType donotsavebtn = new ButtonType("Don't Save");
      ButtonType cancelbtn = new ButtonType("Cancel");
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Alert");
      alert.setHeaderText("You're about to exit");
      alert.setContentText("Do you want to save your file before exit");
      alert.getButtonTypes().clear();
      alert.getButtonTypes().addAll(savebtn, donotsavebtn, cancelbtn);

      Optional<ButtonType> userinput = alert.showAndWait();
      userinput.ifPresent(e -> {
        if (e.equals(donotsavebtn)) {
          stage.close();
        } else if (e.equals(savebtn)) {
          fileIO.SaveFile();
          stage.close();
        } else if (e.equals(cancelbtn)) {
          alert.close();
        }
      });

    } else {

      stage.close();
    }
  }

  public static void onNew() {
    System.out.println("New");
  }

  public static void onOpen() {
    fileIO.OpenFile();
    fileIO.ChangeTitle(stage);
  }

  public static void onSave() {
    fileIO.SaveFile();

  }

  public static void onSaveAs() {
    fileIO.SaveAsFile();
    fileIO.ChangeTitle(stage);
  }

  public static void onExit() {
    EventBeforeExit(stage);
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