import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.util.List;

/*
Inside an FXML Controller class, each element with an fx:id becomes an attribute, and each
method name in setOnAction becomes a method:
 */
public class SceneController {

    /*
    A TableView is a control that specializes in displaying a 2D grid of information in rows and columns.
    This is ideal for representing lists of objects that have multiple attributes.


    Much as we'd expect of an object-based collection, we need to describe the generic type of TableView.
    In our case, our table is going to display information from Grocery objects, so we create a TableView<Grocery>
     */
    @FXML
    private TableView<Grocery> groceries_table;


    @FXML
    private TableColumn<Grocery, String> name_col;
    @FXML
    private TableColumn<Grocery, Integer> id_col;
    @FXML
    private TableColumn<Grocery, Double> price_col;

    @FXML
    private Button bread_button;

    @FXML
    private TextField bread_name_box;
    @FXML
    private TextField bread_barcode_box;
    @FXML
    private TextField bread_price_box;


    //Note: not ALL attributes of JavaFX Controllers *need* to be FXML Controls, you can still store state:
    private ObservableList<Grocery> groceryList;

    @FXML
    public void initialize() {
        /*
        This initialize method works sort of like a constructor, but it runs after all our attributes
        have been loaded in by SceneBuilder.

        In here, we might think to load our data into our grid (this could also happen as a result of
        a button/menu click event):
         */

        this.groceryList = this.groceries_table.getItems(); //<- because tables use observable lists,
                                                                            // they change in response to updates.


        groceryList.add(new Grocery("bread", 3.99, 41235));
        groceryList.add(new Grocery("peppers", 4.99, 4688));
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

        name_col.setCellValueFactory((this_row) -> this_row.getValue().getNameProperty());

        id_col.setCellValueFactory((this_row) -> this_row.getValue().getBarcodeProperty().asObject());

        price_col.setCellValueFactory((this_row) -> this_row.getValue().getPriceProperty().asObject());

        /*
        These lines are a little dense, but what they're saying is:
        "My column has a function that knows how to turn a row into the value for this column (a "cell value" factory.)"

            The neat thing about these connections to columns being bindings is that, even if the values in our Grocery
            objects change, the Grid knows immediately (like with our increment_bread_cost button method!)

        If we were feeling particularly clever, we could even make our Grocery itself observable with
        PropertyChangeSupport (this would let us make the rest of the class with "Plain Old Java Objects"
        and just write the bindings off the Grocery directly.)

        However, bindings aren't limited to one way. As long as both sides of the binding use observable
        properties, we can make a binding bidirectional!
         */


        Grocery bread = this.groceryList.get(0);
        //If I want to connect a object with a control, I can bind their two observable properties with the .bindBidirectional:
        bread_name_box.textProperty().bindBidirectional(bread.getNameProperty());

        //If we wanted to bind barcode, it's slightly more tricky.
        // A barcode is an Integer, but a Label is a String... fortunately, there's a Converter object that can help:


        Bindings.bindBidirectional( //<- we use this static method that can associate the converter
                bread_barcode_box.textProperty(),
                bread.getBarcodeProperty(),
                new NumberStringConverter()
        );

        //Our Bindings work with ANY two JavaFX "Property" observed attributes:
        Bindings.bindBidirectional(
                bread_price_box.textProperty(),
                bread.getPriceProperty(),
                new NumberStringConverter()
        );
        /*
        Because BOTH the Table and the TextBox are binding the same Grocery, when our Button updates our Grocery,
        it updates in both places at once.
         */


        /*
        Bindings let us create powerful UIs, where our elements are directly attached to
        Java objects that represent the current state of our program.
         */



    }



    //JavaFX events like button clicks just become methods that match the signature we saw when we created them directly in Java:
    @FXML
    public void increment_bread_cost(ActionEvent e) {

        for ( Grocery next : this.groceryList ) {
            if ( next.getName().equalsIgnoreCase("bread")) {
                next.increment_cost(1);
            }
        }

    }
}
