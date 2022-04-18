import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileIO {

  private DataInputStream InputStream;
  private DataOutputStream OutputStream;

  private TextField TFPane;

  private File Fileopen;
  private Scanner ReadFile;

  private FileChooser Chooser;
  private Stage FileIOStage;

  public FileIO(Stage primaryStage, TextField tf) {
    Chooser = new FileChooser();
    FileIOStage = primaryStage;
    TFPane = tf;
  }

  public TextField OpenFile() throws IOException {
    StringBuilder sb = new StringBuilder();
    String temp = new String();
    Fileopen = Chooser.showOpenDialog(FileIOStage);
    FileReader Reader = new FileReader(Fileopen);
    BufferedReader bf = new BufferedReader(Reader);

    if (Fileopen.canRead() && Fileopen.canWrite()) {
      while (temp != null) {
        temp = bf.readLine();
        sb.append(temp);
        sb.append(System.lineSeparator());
      }
      TFPane.setText(sb.toString());
      // ไฟล์สามารถเปิดอ่านและเขียนได้
      return TFPane;

    } else if (Fileopen.canRead() && !Fileopen.canWrite()) {
      // ไฟล์เปิดอ่านได้แต่เขียนไม่ได้
      return TFPane;
    }

    return TFPane;
  }

  public void SaveFile() {

  }

}
