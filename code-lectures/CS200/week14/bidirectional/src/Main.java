
/*
Objectives for Today

By the end of today, you will:
   * Learn how to make a bidirectional binding so that changes in the UI can also update our objects
   * Practice other common features of JavaFX.
 */

 import javax.swing.table.TableColumn;

import javafx.application.Application;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
 import javafx.stage.Stage;

 
public class Main extends Application {

  @Override

  public void start(Stage primaryStage) {


    /*
    As always, I'm going to start my scene by creating a root element, then create a Scene from
    that root and send it to my stage. We could create all those objects manually here in Java,
    but programs like SceneBuilder let us declaratively create them all at once and load them
    in with a FXMLLoader:
     */

     try { 

      FXMLLoader loader = new FXMLLoader(Main.class.getResource("/layout.fxml"));
      VBox root = loader.load();


      Scene scene = new Scene(root);
      primaryStage.setScene(scene);


      primaryStage.show();



        /*
        One of the nice things about a FXML file, is it gives us an easy way to create a class representing
        all the controls related one scene. This is called a "controller" class (and it will actually be a
        major theme of our wednesday lecture), but for today, we just need to know that in SceneBuilder we
        can also set a Controller and then give any relevant controls an fx:id to denote them as attributes,
        or set their actions to method names under the method name in the Code panel on SceneBuilder.

        Our SceneController class will hold all these attributes.


         */

     } catch ( Exception e ) {
      e.printStackTrace();
     }


     /* 
    AnchorPane pane = new AnchorPane();

    TableView<Grocery> groceries_table = new TableView();

    TableColumn<Grocery, String> name_col = new TableColumn();
    TableColumn<Grocery, Integer> id_col = new TableColumn<>();
    TableColumn<Grocery, Double> price_col = new TableColumn<>();

    name_col.setText("Name");
    id_col.setText("Barcode");
    price_col.setText("Price");

    groceries_table.getColumns().addAll(name_col, id_col, price_col);

    pane.getChildren().add(groceries_table);

    Scene s = new Scene(pane);
    primaryStage.setScene(s);
    primaryStage.show();
*/


  }

}