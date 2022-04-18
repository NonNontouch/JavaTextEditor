import java.io.DataInputStream;
import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileIO {

    private DataInputStream InputFile;
    private FileChooser chooser;
    private Stage FileIOStage;

    public FileIO(Stage primaryStage) {
        chooser = new FileChooser();
        FileIOStage = primaryStage;
    }

    public void OpenFile(){
      File fileopen = chooser.showOpenDialog(FileIOStage);
    }

    public void SaveFile(){

    }

}
