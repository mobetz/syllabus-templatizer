

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class PickerController {

  @FXML
  private TextField selected_filepath;


  private File selected_file;


  @FXML
  public void pick_file(ActionEvent e) {

            /* When we click the file selection button, we want to open one of those fancy popups
            that has all the formatting that we're used to seeing when we select a file.

            It would take us a very long time to make one of those on our own. Fortunately, we don't have
            to -- there's a FileChooser class that does it for us! Using this class is as easy as making
            an object, then calling:

            chooser_variable.showOpenDialog(on_some_stage_variable);

            */

            FileChooser file_chooser = new FileChooser();

            /* If we want to be extra fancy and the user has selected a file before, we can even
            start our dialog pre-loaded on the folder of their previous selection:
             */
             if ( this.selected_file != null ) {
               file_chooser.setInitialDirectory(this.selected_file.getParentFile());
             } else {
               file_chooser.setInitialDirectory(new File("/home/mwobee/Desktop/CS120"));
             }



            //The return of "showOpenDialog" is going to be the file that the user selected:
            File dialog_result = file_chooser.showOpenDialog(null);
            if ( dialog_result != null ) { //<- the result of the dialog will be null if the user cancels the dialog / clicks the X
              this.selected_file = dialog_result;
              this.selected_filepath.setText(this.selected_file.getAbsolutePath());
            }
  }


  @FXML
  public void load_file(ActionEvent e) throws IOException {
    /*
    Our load event is a little bit more complicated than the others we've written so far...

    When someone loads, I actually want to change the scene that's rendered entirely.

    This means I need some way to access that other scene. I can do that by adding just
    a bit more code to our main.
    */


    DisplayController other_controller = Main.getDisplayer();
    Scene other_scene = other_controller.getScene();
    Stage main_window = (Stage) this.selected_filepath.getScene().getWindow();

/*
There's one complication in changing the scene.... We still need some of the information from this scene!
We want the file that gets loaded in the big textarea to be the file the user selected here.

To move this state from one scene to the other, I need a function on the "receiving" scene that allows me
to pass my state across.
*/


    other_controller.load(this.selected_file);

    main_window.setScene(other_scene);

  }


    public Scene getScene() {
        return this.selected_filepath.getScene();
    }

}
