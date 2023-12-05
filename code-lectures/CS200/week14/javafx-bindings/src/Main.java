/*
Objectives for Today

By the end of today, you will:
   * Identify the concept of a data binding
   * Understand how data bindings can be used to link model objects to views
   * Practice linking model objects to a JavaFX tables and editable controls.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        /*
        Last class, we talked about the concept of events.

        Events allowed other parts of a program to become aware when a change occurred.

        We saw many practical uses for this, both in our own code (as an internal "messaging" system
        to move information from one place to another), and also some ways that this was used in JavaFX,
        like automatically updating a Scene when a control is added to that Scene's observable list of
        children.

        Today, we're going to take our events a step further, and introduce the concept of a binding.

        As always, I'm going to start my scene by creating a root element, then create a Scene from
        that root and send it to my stage. We could create all those objects manually here in Java,
        but programs like SceneBuilder let us declaratively create them all at once and load them
        in with a FXMLLoader:
         */
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/GroceryProgram.fxml"));

        VBox root_element = loader.load();

        /*
        This would be the same as us doing:

        AnchorPane pane = new AnchorPane();

        TableView<Grocery> groceries_table = new TableView<>();
        pane.getChildren().add(groceries_table);


        TableColumn<Grocery, String> name_col = new TableColumn<>();
        TableColumn<Grocery, Integer> id_col = new TableColumn<>();
        TableColumn<Grocery, Double> price_col = new TableColumn<>();

        name_col.setText("Name");
        id_col.setText("Barcode");
        price_col.setText("Price");

        groceries_table.getColumns().addAll(name_col, id_col, price_col);
         */

        /*
        To make these elements actually render, we need to add them to a Scene and then give that Scene
        to a Stage:
         */
        Scene s = new Scene(root_element);
        primaryStage.setScene(s);
        primaryStage.show();

        /*
        One of the nice things about a FXML file, is it gives us an easy way to create a class representing
        all the controls related one scene. This is called a "controller" class (and it will actually be a
        major theme of our wednesday lecture), but for today, we just need to know that in SceneBuilder we
        can also set a Controller and then give any relevant controls an fx:id to denote them as attributes,
        or set their actions to method names under the method name in the Code panel on SceneBuilder.

        Our SceneController class will hold all these attributes.


         */



    }
}