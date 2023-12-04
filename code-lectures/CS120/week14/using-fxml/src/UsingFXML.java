

/*
Objectives for Today:

By the end of today, you will be able to:
  * Identify how FXML allows us to succinctly describe and load a JavaFX scene.
  * Identify the different panels in SceneBuilder and use them to generate an FXML project.
  * Understand how Controllers allow us to connect to objects that appear in a JavaFX scene.
  * Understand the @FXML "binding" annotation.
 */

/*
Vocabulary for the Day

Controller - In Java, a "controller" class is the code that makes the objects of our visual interface (the
"view") accessible to the data we store (the "model".)
 */



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class UsingFXML extends Application {
	

    public void start(Stage stage) throws IOException {
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setMinWidth(300);
        stage.setMinHeight(300);
        stage.setTitle("Using FXML");


         /*
             Last class we introduced manually adding elements to a Scene.
             However, it's very hard to position elements without being able to see them,
             and we have no way to easily design everything at once.


            This is where FXML comes in. An FXML file is a special document that describes the layout
            for an entire scene, all at once.

            FXML files are not .java code, so instead of going in our source root, we're going to add
            them as a resource (similar to our data files from earlier labs.)


            Once we've made a Scene we're happy with in SceneBuilder, we can load it with an FXMLLoader:
         */

        FXMLLoader loader = new FXMLLoader( UsingFXML.class.getResource("/Scene.fxml"));
        AnchorPane root = loader.load(); //<- this finds every Control in the FXML file, creates objects for each, and gives us back the "root"


        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        

        /*
        When we build a scene with FXML, we don't actually have any of the objects that we created to do
        things like setOnAction for our Button. We *could* fish them out from the AnchorPane by getting its
        list of children, but that would be really messy.

        Instead, our FXML lets us describe a class to 'link' to this Scene. We can even describe those details
        directly in the "Controller" and "Code" tabs of SceneBuilder 
        */
    }
}