import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TextControl {

    private TextArea textarea;
    private static Stage textStage;

    public TextControl(Stage inputstage, TextArea inputTextArea) {

        this.textarea = inputTextArea;
        this.textStage = inputstage;

    }

    public static void TextControlEvent() {

        Label label = new Label("Test New Windod for textControler");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(label);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // สร้าง stage ใหม่
        Stage textcontrolstage = new Stage();
        textcontrolstage.setTitle("Second Stage");
        textcontrolstage.setScene(secondScene);

        // ตั้งให้ว่าถ้าไม่ปิดwindow นี้ก็จะทำอะไรไม่ได้
        textcontrolstage.initModality(Modality.WINDOW_MODAL);

        // ตั้งให้เป็น window ลูก
        textcontrolstage.initOwner(textStage);

        // เซทตำแหน่งให้อยู่ซ้ายบนเสมอ
        textcontrolstage.setX(textStage.getX());
        textcontrolstage.setY(textStage.getY());

        textcontrolstage.show();

    }
}