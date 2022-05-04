import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
  private static Stage stage;
  private static FileIO fileIO;
  private static UI ui;
  private static TextControl textcontroler;
  private static boolean must_save = false;

  @Override
  public void start(Stage primaryStage) {

    stage = primaryStage;

    ui = new UI();
    ui.getTextArea().setOnKeyPressed(e -> {
      if (!must_save) {
        isKeypressed();
      }
    });

    fileIO = new FileIO(ui.getTextArea(), primaryStage);
    textcontroler = new TextControl(primaryStage, ui.getTextArea());

    Scene scene = new Scene(ui, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Notepad--");
    try {
      primaryStage.getIcons().add(new Image(new FileInputStream("Picture/Icon.png")));
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
    setShortCutKey(primaryStage);
    primaryStage.show();
    primaryStage.setOnCloseRequest(event -> {
      event.consume();
      EventBeforeExit();
    });

  }

  public static void EventBeforeExit() {
    if (!ui.getTextArea().getText().equals("") && must_save && !fileIO.IsFileEditable()) {
      ButtonType savebtn = new ButtonType("Save");
      ButtonType donotsavebtn = new ButtonType("Don't Save");
      ButtonType cancelbtn = new ButtonType("Cancel");
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Alert");
      alert.setHeaderText("You're about to exit");
      alert.setContentText("Do you want to save your file before exit");
      alert.getButtonTypes().clear();
      alert.getButtonTypes().addAll(savebtn, donotsavebtn, cancelbtn);

      Stage window = (Stage) alert.getDialogPane().getScene().getWindow();
      window.setOnCloseRequest(e -> alert.close());
      try {
        window.getIcons().add(new Image(new FileInputStream("Picture/Information.png")));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }

      Optional<ButtonType> userinput = alert.showAndWait();
      userinput.ifPresent(e -> {
        if (e.equals(donotsavebtn)) {
          System.exit(0);
        } else if (e.equals(savebtn)) {
          if (fileIO.SaveFile() == 0) {
            System.exit(0);
          }
        } else if (e.equals(cancelbtn)) {
          alert.close();
        }
      });

    } else {
      System.exit(0);
    }
  }

  private void setShortCutKey(Stage stage) {
    stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
      final KeyCombination save = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
      final KeyCombination inew = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
      final KeyCombination font = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
      final KeyCombination find = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);

      public void handle(KeyEvent ke) {
        if (save.match(ke)) {
          fileIO.SaveFile();
          ke.consume();
        } else if (inew.match(ke)) {
          onNew();
        } else if (font.match(ke)) {
          textcontroler.TextControlEvent();
        } else if (find.match(ke)) {
          textcontroler.TextFinderEvent();
        }
      }
    });
  }

  public static void onNew() {

    if (fileIO.SaveFile() == 0) {
      stage.setTitle("Notepad--");
      ui.getTextArea().clear();
      ui.getTextArea().setFont(Font.font("System", 16));
    }
  }

  public static void onOpen() {
    fileIO.OpenFile();
  }

  public static void onSave() {
    fileIO.SaveFile();
  }

  public static void onSaveAs() {
    fileIO.SaveAsFile();
    fileIO.ChangeTitle(stage);
  }

  public static void onExit() {
    EventBeforeExit();
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

  public static void onFind() {
    textcontroler.TextFinderEvent();
  }

  public static void onAbout() {
    Stage alertStage;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setTitle("About");
    alert.setContentText(
        "This is a Notepad-- \nThis program is developed by \n64011106 ณรงค์พล กิจรังสรรค์ \n64011160 นนทัช มุกลีมาศ \n64011273 วิชชุ ศรีโยธี \n64011301 สิทธิ นวะมะวัฒน์");

    alert.getDialogPane().setStyle("-fx-font-size: 15;");
    alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
    try {
      alertStage.getIcons().add(new Image(new FileInputStream("Picture/Information.png")));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    alertStage.show();
  }

  public static void onHelp() {
    System.out.println("Help");
  }

  public static void onFormat() {

    textcontroler.TextControlEvent();

  }

  public void isKeypressed() {
    must_save = true;
  }

  public static void setSavestage(boolean in) {
    must_save = in;
  }

  public static void main(String[] args) {
    launch(args);
  }
}