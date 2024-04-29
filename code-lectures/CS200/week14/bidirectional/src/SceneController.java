import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Inside an FXML Controller class, each element with an fx:id becomes an attribute, and each
method name in setOnAction becomes a method:
 */
public class SceneController {
    
    @FXML
    private TableView<Grocery> groceries_table;

    @FXML
    private TableColumn<Grocery, String> name_col;
    @FXML
    private TableColumn<Grocery, Integer> id_col;
    @FXML
    private TableColumn<Grocery, Double> price_col;



    @FXML
    private TextField new_name;
    @FXML
    private TextField new_barcode;
    @FXML
    private Spinner<Double> new_price;
    @FXML
    private Button add_grocery_button;

    @FXML
    private Button bread_button;


    private Grocery grocery_in_progress;

    @FXML
    public void initialize() {
        /* 
        This initialize method works sort of like a constructor, but it runs after all our attributes
        have been loaded in by SceneBuilder.

        In here, we might think to load our data into our grid (this could also happen as a result of
        a button/menu click event):
         */


         ObservableList<Grocery> groceryList = this.groceries_table.getItems();

         groceryList.add(new Grocery("bread", 3.99, 41235));
         groceryList.add(new Grocery("pepper", 4.99, 4688));
         groceryList.add(new Grocery("broccoli", 4.25, 3082));
         groceryList.add(new Grocery("bananas", 1.99, 4011));

        /*

        I want this table to be a table of Groceries, but there's a problem... even if I add those groceries
        to my table's observed collection, the table won't display them.

        Even after our table has columns, we see those columns, and we see the rows that are clickable,
        but the columns have no concept of how a Grocery object is structured. What I want is to "bind together"
        an attribute of my Grocery with a column of my table, so that whenever one changes, the other is
        immediately made aware of that change and updates accordingly.

        Now, for each of my columns, I can tell it how to populate its value by listening to the
        object for that cell:
         */
        ArrayList l = new ArrayList<>();


         name_col.setCellValueFactory((this_row) -> this_row.getValue().getNameProperty());
         id_col.setCellValueFactory((this_row) -> this_row.getValue().getBarcodeProperty().asObject());
 
         price_col.setCellValueFactory((this_row) -> this_row.getValue().getPriceProperty().asObject());


        /*
        These lines are a little dense, but what they're saying is:
        "My column has a function that knows how to turn a row into the value for this column (a "cell value" factory.)"

            The neat thing about these connections to columns being bindings is that, even if the values in our Grocery
            objects change, the Grid knows immediately (like with our increment_cost_of_bread button method!)
            */


            this.grocery_in_progress = new Grocery("", -1, -1);

            /*
             * If I want an object in my model to be linked to controls in the UI, I can use bindings!
             * 
             * Since a textfield is editable, we will need a bidirectional binding. Fortunately, JavaFX
             * gives us a handy helper function to do exactly that:
             */

             Bindings.bindBidirectional(
                  this.new_name.textProperty(), 
                  this.grocery_in_progress.getNameProperty()
            );

            Bindings.bindBidirectional(
                this.new_barcode.textProperty(), 
                this.grocery_in_progress.getBarcodeProperty(),
                new NumberStringConverter() //<- if our two attributes are different types, then we need to provide a third argument to convert between the two
                                             //<- this third object can be anything that extends StringConvert<T>, where T is the other type of attribute
            );

            


          Bindings.bindBidirectional(
            this.new_price.getValueFactory().valueProperty(), 
            this.grocery_in_progress.getPriceProperty().asObject()
          );

    }

    @FXML
    //JavaFX events like button clicks just become methods that match the signature we saw when we created them directly in Java:
    public void increment_cost_of_bread(ActionEvent e) {
        Grocery bread = this.groceries_table.getItems().get(0);
        bread.getPriceProperty().set(bread.getPrice() + 1);

    }

    @FXML
    public void load_groceries(ActionEvent e) {

        //If we want to clear out the old table, all we need to do is empty its array:
        this.groceries_table.getItems().clear();


        //We can ask for the file that the user wants to use with a filechooser dialog
        FileChooser file_chooser = new FileChooser(); 
        File groceries_file = file_chooser.showOpenDialog(null);

        if ( groceries_file != null ) { //<- if they actually picked a file 

            try { 
                Scanner s = new Scanner(groceries_file);
                while ( s.hasNext() ) {
                    String next_grocery_text = s.nextLine();
                    String[] parts = next_grocery_text.split(",");
                    Grocery new_grocery = new Grocery(parts[0], Double.parseDouble(parts[2]), Integer.parseInt(parts[1]));
               
                    this.groceries_table.getItems().add(new_grocery);
                }
            } catch(IOException error ) {
                error.printStackTrace();
            }
        }

    }


    @FXML
    public void add_grocery(ActionEvent e) {

        this.groceries_table.getItems().add(this.grocery_in_progress.copy());
    }
}
