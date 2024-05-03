
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;



/*
Objectives for Today

By the end of today, you will be able to:
  * Create JavaFX applications that use more than one scene to switch between different "views" in a program.
  * Generate modal dialogs for prompting users and choosing files.
  * Set the values of JavaFX controls from within code.
 */



public class Main extends Application {


  private static PickerController picker;
  private static DisplayController displayer;


  public void start(Stage primaryStage) throws IOException {

//When I have a JavaFX application with more than one scene, I need to load all of them:
    FXMLLoader picker_loader = new FXMLLoader(getClass().getResource("/picker.fxml"));
    FXMLLoader display_loader = new FXMLLoader(getClass().getResource("/displayer.fxml"));

    Parent picker_root = picker_loader.load();
    Scene picker_scene = new Scene(picker_root);

    Parent display_root = display_loader.load();
    Scene display_scene = new Scene(display_root);


//But at the end, I'll only set the scene the one that I want to be open when the app first starts
    primaryStage.setScene(picker_scene);
    primaryStage.show();


    this.picker = picker_loader.getController();
    this.displayer = display_loader.getController();



  }


  public static PickerController getPicker() {
    return Main.picker;
  }

  public static DisplayController getDisplayer() {
    return Main.displayer;
  }



}
