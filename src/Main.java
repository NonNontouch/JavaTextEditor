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
  final static private ButtonType savebtn = new ButtonType("Save");
  final static private ButtonType donotsavebtn = new ButtonType("Don't Save");
  final static private ButtonType cancelbtn = new ButtonType("Cancel");
  final static private Font defultfont = new Font("System", 16);
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
        must_save = true;
      }
    });

    fileIO = new FileIO(ui.getTextArea(), primaryStage);
    textcontroler = new TextControl(ui.getTextArea(), primaryStage);

    Scene scene = new Scene(ui, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Untitled - Notepad--");
    try {
      primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Picture/Icon.png")));
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    setShortCutKey(primaryStage);
    primaryStage.show();
    primaryStage.setOnCloseRequest(event -> {
      event.consume();
      EventBeforeExit();
    });

  }

  public static Alert CreateAlertBeforeExit(String bannertext) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Notepad--");
    alert.setHeaderText("You're about to " + bannertext + ".");
    alert.setContentText("Do you want to save your file before " + bannertext + "?.");
    alert.getButtonTypes().clear();
    alert.getButtonTypes().addAll(savebtn, donotsavebtn, cancelbtn);

    Stage window = (Stage) alert.getDialogPane().getScene().getWindow();
    window.setOnCloseRequest(e -> alert.close());
    try {
      window.getIcons().add(new Image(Main.class.getResourceAsStream("Picture/Information.png")));
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return alert;
  }

  public static void EventBeforeExit() {
    if (!ui.getTextArea().getText().equals("") && must_save && !fileIO.IsFileEditable()) {
      Alert tempAlert = CreateAlertBeforeExit("exit");
      Optional<ButtonType> userinput = tempAlert.showAndWait();
      userinput.ifPresent(e -> {
        if (e.equals(donotsavebtn)) {
          System.exit(0);
        } else if (e.equals(savebtn)) {
          if (fileIO.SaveFile() == 0) {
            System.exit(0);
          }
        } else if (e.equals(cancelbtn)) {
          tempAlert.close();
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
      final KeyCombination open = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);

      public void handle(KeyEvent ke) {
        if (save.match(ke)) {
          fileIO.SaveFile();
          ke.consume();
        } else if (inew.match(ke)) {
          onNew();
          ke.consume();
        } else if (font.match(ke)) {
          textcontroler.TextControlEvent();
          ke.consume();
        } else if (find.match(ke)) {
          textcontroler.TextFinderEvent();
          ke.consume();
        } else if (open.match(ke)) {
          fileIO.OpenFile();
          ke.consume();
        }
      }
    });
  }

  public static void onNew() {
    if (must_save) {
      Alert tempAlert = CreateAlertBeforeExit("create a new file");
      Optional<ButtonType> userinput = tempAlert.showAndWait();
      userinput.ifPresent(e -> {
        if (e.equals(donotsavebtn)) {
          stage.setTitle("Untitled - Notepad--");
          ui.getTextArea().clear();
          ui.getTextArea().setFont(defultfont);
          fileIO.setboolReadOnly(false);
        } else if (e.equals(savebtn)) {
          if (fileIO.SaveFile() == 0) {
            stage.setTitle("Notepad--  Untitled");
            ui.getTextArea().clear();
            ui.getTextArea().setFont(defultfont);
            fileIO.setboolReadOnly(false);
          }
        } else if (e.equals(cancelbtn)) {
          tempAlert.close();
        }
      });

    } else {
      stage.setTitle("Untitled - Notepad--");
      ui.getTextArea().clear();
      ui.getTextArea().setFont(defultfont);
      fileIO.setboolReadOnly(false);
    }

  }

  public static void onOpen() {
    if (must_save) {
      Alert tempAlert = CreateAlertBeforeExit("open a new file");
      Optional<ButtonType> userinput = tempAlert.showAndWait();
      userinput.ifPresent(e -> {
        if (e.equals(donotsavebtn)) {
          fileIO.OpenFile();
        } else if (e.equals(savebtn)) {
          if (fileIO.SaveFile() == 0) {
            fileIO.OpenFile();
          }
        } else if (e.equals(cancelbtn)) {
          tempAlert.close();
        }
      });

    } else {
      fileIO.OpenFile();
    }
  }

  public static void onSave() {
    fileIO.SaveFile();
  }

  public static void onSaveAs() {
    fileIO.SaveAsFile();
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
      alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("Picture/Information.png")));
    } catch (Exception e) {
      e.printStackTrace();
    }
    alertStage.show();
  }

  public static void onHelp() {
    Stage alertStage;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setTitle("วิธีใช้");
    alert.setContentText(
        "1.การเปิดไฟล์\n  - notepad-- สามารถเปิดไฟล์ Code .asm .cpp .c .cs .go .html .java .js .kt .php .py .rb .rs เเละไฟล์เอกสาร .txt\n  - สามารถทำได้โดยเข้าไปที่เมนู File -> Open\n"
            +
            "2.การบันทึกไฟล์\n  - สามารถทำได้โดยการกดที่เมนู File -> Save,Save as\n  - ใช้ shortcut ในการบันทึก \"CTRL + s\"\n"
            +
            "3.การใช้คำสั่งเมนู Edit\n  - การใช้เมนูคำสั่ง Undo หรือใช้ \"CTRL + z\" ในการเลิกทำสิ่งที่ทำ\n  - การใช้เมนูคำสั่ง Redo หรือใช้ \"CTRL + y\" ในการทำซ้ำสิ่งที่ทำ\n  - การใช้เมนูคำสั่ง Cut หรือใช้ \"CTRL + x\"  ในการตัดข้อความ\n  - การใช้เมนูคำสั่ง Copy หรือใช้ \"CTRL + c\"  ในการคัดลอกข้อความ\n  - การใช้เมนูคำสั่ง Paste หรือใช้ \"CTRL + v\"  ในการวางข้อความ\n  - การใช้เมนูคำสั่ง Find หรือใช้ \"CTRL + f\"  ในการค้นหาข้อความ\n"
            +
            "4.การเปลี่ยน Font เเละขนาดของตัวอักษร\n  - ทำได้จากเเถบเมนู Format -> Edit Format หรือใช้ \"CTRL + t\"\n  - เมื่อเข้าสู่ Edit Format จะเห็นเมนู Font Size ที่ใช้ในการปรับขนาดของตัวอักษรเเละ Font Family ใช้ในการเปลี่ยนรูปเเบบของ font\n"
            +
            "5.ทางลัดในการใช้ Notepad--\n  - \"CTRL + s\" => Save\n  - \"CTRL + z\" => Undo\n  - \"CTRL + y\" => Redo\n  - \"CTRL + x\" => Cut\n  - \"CTRL + c\" => Copy\n  - \"CTRL + v\" => Paste\n  - \"CTRL + f\" => Find\n  - \"CTRL + n\" => New\n  - \"CTRL + t\" =>Font edit");

    alert.getDialogPane().setStyle("-fx-font-size: 12;");
    alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
    try {
      alertStage.getIcons().add(new Image(Main.class.getResourceAsStream("Picture/Information.png")));
    } catch (Exception e) {
      e.printStackTrace();
    }
    alertStage.show();
  }

  public static void onFormat() {
    textcontroler.TextControlEvent();
  }

  public static void setSavestage(boolean in) {
    must_save = in;
  }

  public static void main(String[] args) {
    launch(args);
  }
}