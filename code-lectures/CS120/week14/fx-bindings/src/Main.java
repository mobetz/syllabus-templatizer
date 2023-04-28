

/*
Objectives for Today

By the end of today, you will:
    * Learn how to create grids and charts in JavaFX. 
    * Understand how some JavaFX controls can be bound to objects in the code.
    * Practice interacting with ObservableLists to display items in a JavaFX table.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class Main extends Application {


  public void start(Stage stage) throws IOException {

      /*
      So far, most of the controls we've been working with have been relatively
      standard --
             * textboxes start empty or with a short prompt
             * Buttons have a short description and an attached event
             * Labels just have whatever single line of text they should display

These controls are great for enabling interactivity, but they are really
     bad at displaying a lot of information. Many of the most useful applications
     we can create have some interaction with data, and so we need a way to
     present that information to our users.

     Today, we will be focusing on a few JavaFX controls that allow us to do this:
     The TableView and its TableColumns.

     First, we need a source of data, so let us quickly create some mock objects
     we want to show users in our code -- we'll use our Student class again
     for this.
             */

    FXMLLoader loader = new FXMLLoader(Main.class.getResource("Layout.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

  }

}