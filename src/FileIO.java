import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileIO {

  private TextArea TextAreaUI;

  private File UserInputFile;

  private FileChooser Chooser;
  private Stage FileIOStage;

  public FileIO(Stage primaryStage, TextArea tf) {
    Chooser = new FileChooser();
    FileIOStage = primaryStage;
    TextAreaUI = tf;
  }

  public void OpenFile() {

    StringBuilder texttoshow = new StringBuilder();
    String temp = "";
    UserInputFile = Chooser.showOpenDialog(FileIOStage);
    // ไฟล์สามารถเปิดอ่านและเขียนได้
    if (UserInputFile != null) {
      if (UserInputFile.canRead() && UserInputFile.canWrite()) {
        try (

            FileReader Reader = new FileReader(UserInputFile);
            BufferedReader bf = new BufferedReader(Reader);) {

          while (temp != null) {

            texttoshow.append(temp);
            texttoshow.append(System.lineSeparator());
            temp = bf.readLine();

          }

          TextAreaUI.setText(texttoshow.toString());

        } catch (Exception e) {
          e.printStackTrace();
        }

      } else if (UserInputFile.canRead() && !UserInputFile.canWrite()) {
        // ไฟล์เปิดอ่านได้แต่เขียนไม่ได้

        try (

            FileReader Reader = new FileReader(UserInputFile);
            BufferedReader bf = new BufferedReader(Reader);) {

          Alert alert = new Alert(AlertType.INFORMATION);
          while (temp != null) {

            texttoshow.append(temp);
            texttoshow.append(System.lineSeparator());
            temp = bf.readLine();

          }

          TextAreaUI.setText(texttoshow.toString());
          TextAreaUI.setEditable(false);

          alert.setHeaderText(null);
          alert.setContentText("This file is read only");
          alert.show();

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }

  public void SaveFile() {

  }

}
