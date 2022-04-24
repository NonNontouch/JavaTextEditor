import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileIO {

  private TextArea TextAreaUI;
  private TextArea saveTextArea;

  private File UserInputFile;

  private FileChooser Chooser;
  private Stage FileIOStage;

  public FileIO(TextArea tf, Stage primaryStage) {
    FileIOStage = primaryStage;

    Chooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Document (*.txt)", "*.txt");
    FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("All Files", "*.*");
    Chooser.getExtensionFilters().add(extFilter);
    Chooser.getExtensionFilters().add(extFilter2);

    TextAreaUI = tf;
    saveTextArea = new TextArea();
  }

  public void OpenFile() {

    StringBuilder texttoshow = new StringBuilder();

    UserInputFile = Chooser.showOpenDialog(FileIOStage);

    if (UserInputFile != null) {

      boolean isuserinputnontextfile;
      try {
        isuserinputnontextfile = IsBinaryFile(UserInputFile);
      } catch (IOException e1) {
        // เปิดไฟล์ไม่ได้ ให้จบการเปิดไฟล์เลย
        e1.printStackTrace();
        return;
      }

      if (UserInputFile.canRead() && UserInputFile.canWrite() && !isuserinputnontextfile) {
        // ไฟล์สามารถเปิดอ่านและเขียนได้
        try (

            FileReader Reader = new FileReader(UserInputFile, StandardCharsets.UTF_8);
            BufferedReader bf = new BufferedReader(Reader);) {

          String temp = bf.readLine();

          while (temp != null) {

            texttoshow.append(temp);
            texttoshow.append(System.lineSeparator());
            temp = bf.readLine();

          }

          TextAreaUI.setText(texttoshow.toString());
          TextAreaUI.setEditable(true);

          saveTextArea.setText(texttoshow.toString());

        } catch (Exception e) {
          e.printStackTrace();
        }

      } else if (UserInputFile.canRead() && !UserInputFile.canWrite() && !isuserinputnontextfile) {
        // ไฟล์เปิดอ่านได้แต่เขียนไม่ได้

        try (

            FileReader Reader = new FileReader(UserInputFile, StandardCharsets.UTF_8);
            BufferedReader bf = new BufferedReader(Reader);) {

          String temp = bf.readLine();
          Alert alert = new Alert(AlertType.INFORMATION);
          while (temp != null) {
            // loop อ่านซ้ำจนกว่าจะเป็น null(ถึงตัวสุดท้าย)
            texttoshow.append(temp);
            texttoshow.append(System.lineSeparator());
            temp = bf.readLine();

          }

          TextAreaUI.setText(texttoshow.toString());
          TextAreaUI.setEditable(false);

          alert.setHeaderText(null);
          alert.setTitle("Information");
          alert.setContentText("This file is read only.");
          alert.show();

        } catch (Exception e) {
          e.printStackTrace();
        }
      } else if (isuserinputnontextfile) {
        // เปิดไฟล์ Binary ขึ้นมาแจ้งผู้ใช้ว่ามันเปิดไม่ได้
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("This program can't open Binary file.");
        alert.show();
      }

      // เมื่อ user กด open อีกรอบจะเป็น directory ที่อยู่ล่าสุด
      Chooser.setInitialDirectory(new File(UserInputFile.getParent()));
      Main.setSavestage(false);
      ChangeTitle(FileIOStage);
    }
  }

  public void SaveFile() {
    if (UserInputFile != null) {
      // ถ้าเปิดไฟล์อยู่ก็ให้เซฟทับ
      if (UserInputFile.exists()) {

        try (FileWriter fileWriter = new FileWriter(UserInputFile, StandardCharsets.UTF_8)) {

          fileWriter.write(TextAreaUI.getText());
          Main.setSavestage(false);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } else {
      // ถ้าไม่ก็ให้เด้งไป save as
      SaveAsFile();
    }
  }

  public void SaveAsFile() {

    File file = Chooser.showSaveDialog(FileIOStage);

    if (file != null)
      if (file.exists()) {
        try (FileWriter fileWriter = new FileWriter(UserInputFile);) {
          fileWriter.write(TextAreaUI.getText());
          fileWriter.close();
          UserInputFile = new File(file.getPath());
          Main.setSavestage(false);
        } catch (Exception e) {
          e.printStackTrace();

        }

      } else {
        try (FileWriter fileWriter = new FileWriter(file);) {
          file.createNewFile();

          fileWriter.write(TextAreaUI.getText());
          fileWriter.close();
          
          UserInputFile = new File(file.getPath());
          Main.setSavestage(false);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    ChangeTitle(FileIOStage);

  }

  public boolean IsBinaryFile(File F) throws IOException {

    String type = Files.probeContentType(F.toPath());
    if (type == null) {
      // เป็นอะไรก็ไม่รู้ถือว่าเป็น Birnary เลย
      return true;
    } else if (type.startsWith("text")) {
      return false;
    } else {
      // ไม่ใช่ไฟล์ text
      return true;
    }

  }

  public void ChangeTitle(Stage inputStage) {
    if (UserInputFile != null) {
      inputStage.setTitle(UserInputFile.getName());
    }
  }

  public TextArea getSaveTextArea() {
    return saveTextArea;
  }

  public File getuserinputfile() {
    return UserInputFile;
  }
}
