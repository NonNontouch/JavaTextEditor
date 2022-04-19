import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileIO {

  private TextArea TFPane;

  private File Fileopen;

  private FileChooser Chooser;
  private Stage FileIOStage;

  public FileIO(Stage primaryStage, TextArea tf) {
    Chooser = new FileChooser();
    FileIOStage = primaryStage;
    TFPane = tf;
  }

  public void OpenFile() {

    StringBuilder texttoshow = new StringBuilder();
    String temp = "";
    Fileopen = Chooser.showOpenDialog(FileIOStage);
    // ไฟล์สามารถเปิดอ่านและเขียนได้
    if (Fileopen!=null) {
      if (Fileopen.canRead() && Fileopen.canWrite()) {
        try (

            FileReader Reader = new FileReader(Fileopen);
            BufferedReader bf = new BufferedReader(Reader);) {

          while (temp != null) {

            texttoshow.append(temp);
            texttoshow.append(System.lineSeparator());
            temp = bf.readLine();
            
          }

          TFPane.setText(texttoshow.toString());

          bf.close();
          Reader.close();

        } catch (Exception e) {
          e.printStackTrace();
        }

      } else if (Fileopen.canRead() && !Fileopen.canWrite()) {
        // ไฟล์เปิดอ่านได้แต่เขียนไม่ได้

      }
    }

  }

  public void SaveFile() {

  }

}
