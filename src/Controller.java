import java.io.IOException;

public class Controller {
  public static void onNew() {
    System.out.println("New");
  }

  public static void onOpen() {
    FileIO fileio = new FileIO(null, null);
  }

  public static void onSave() {
    System.out.println("Save");
  }

  public static void onSaveAs() {
    System.out.println("Save As");
  }

  public static void onExit() {
    System.exit(0);
  }

  public static void onUndo() {
    System.out.println("Undo");
  }

  public static void onRedo() {
    System.out.println("Redo");
  }

  public static void onCut() {
    System.out.println("Cut");
  }

  public static void onCopy() {
    System.out.println("Copy");
  }

  public static void onPaste() {
    System.out.println("Paste");
  }

  public static void onZoomIn() {
    System.out.println("Zoom In");
  }

  public static void onZoomOut() {
    System.out.println("Zoom Out");
  }

  public static void onZoomReset() {
    System.out.println("Zoom Reset");
  }

  public static void onAbout() {
    System.out.println("About");
  }

  public static void onHelp() {
    System.out.println("Help");
  }
}
