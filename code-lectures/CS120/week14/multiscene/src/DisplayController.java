
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import java.io.*;
import java.util.Scanner;


public class DisplayController {


  @FXML
  private TextArea file_contents;

  public void save_file(ActionEvent e) {

  }


  public void close_file(ActionEvent e) {
    PickerController picker = Main.getPicker();
    Scene other_scene = picker.getScene();
    Stage main_window = (Stage) this.getScene().getWindow();
    main_window.setScene(other_scene);



  }


  public void load(File target_file)  throws IOException {

    String contents = "";

    Scanner s= new Scanner(target_file);

    while ( s.hasNext() ) {
      contents = contents + s.nextLine() + "\n";
    }

    this.file_contents.setText(contents);
  }


  public Scene getScene() {
      return this.file_contents.getScene();
  }


}
