import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;
import java.util.List;

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
    private Button bread_button;

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
    }

    @FXML
    //JavaFX events like button clicks just become methods that match the signature we saw when we created them directly in Java:
    public void increment_cost_of_bread(ActionEvent e) {
        Grocery bread = this.groceries_table.getItems().get(0);
        bread.getPriceProperty().set(bread.getPrice() + 1);

    }
}
