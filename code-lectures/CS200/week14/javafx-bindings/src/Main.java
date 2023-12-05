/*
Objectives for Today

By the end of today, you will:
   * Identify the concept of a data binding
   * Understand how data bindings can be used to link model objects to views
   * Practice linking model objects to a JavaFX table.
 */


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;
import java.util.List;

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

        In order to show you the full picture of what's happening, I'm going to code our bindings
        fully in Java, but the princple works the same in a Controller class loaded with FXML (you
        would just need to give the property you are binding to an fx:id in SceneBuilder.)
         */

        /*
        As always, I'm going to start my scene by creating a root element, then create a Scene from
        that root and send it to my stage.
         */
        AnchorPane pane = new AnchorPane();
        Scene s = new Scene(pane);
        primaryStage.setScene(s);
        primaryStage.show();


        /*
        A pane's observable list of children is attached to events, so later controls will appear
        even if I attach them here. A TableView is a control that specializes in displaying a 2D
        grid of information in rows and columns. This is ideal for representing lists of objects
        that have multiple attributes.
        */

        ObservableList<Grocery> groceries = FXCollections.observableArrayList();

        groceries.add(new Grocery("bread", 3.99, 41235));
        groceries.add(new Grocery("peppers", 4.99, 4688));
        groceries.add(new Grocery("broccoli", 4.25, 3082));
        groceries.add(new Grocery("bananas", 1.99, 4011));


        /*
        Much as we'd expect of an object-based collection, we need to describe the generic type of TableView:
         */

        TableView<Grocery> groceries_table = new TableView<>();

        pane.getChildren().add(groceries_table);
        AnchorPane.setLeftAnchor(groceries_table, 5.0);
        AnchorPane.setRightAnchor(groceries_table, 5.0);
        AnchorPane.setTopAnchor(groceries_table, 5.0);
        /*
        I want this table to be a table of Groceries, but there's a problem... even if I add those groceries
        to my table's observed collection, the table won't display them:
         */

        groceries_table.setItems(groceries);

        /*
        We could even define columns on the table...
         */

        TableColumn<Grocery, String> name_col = new TableColumn<>();
        TableColumn<Grocery, Integer> id_col = new TableColumn<>();
        TableColumn<Grocery, Double> price_col = new TableColumn<>();

        name_col.setText("Name");
        id_col.setText("Barcode");
        price_col.setText("Price");

        groceries_table.getColumns().addAll(name_col, id_col, price_col);

        /*
        But if we just ran the program now, we'd see all the columns, but no values!

        This is because even though we have the table and its columns, the table has no concept of how
        a Grocery object is structured. What I want is to "bind together" an attribute of my Grocery
        with a column of my table, so that whenever one changes, the other is immediately made aware
        of that change and updates accordingly.

        This sounds a lot like what we did with events. In fact, we could do this with events if we
        wanted to using "simple property" javaFX attributes. These simple properties are wrappers
        around our primitive value types like double and integer that can register listeners and
        fire events whenever a value changes. This lets them wire them up directly to our grid:
         */


        name_col.setCellValueFactory((this_row) -> this_row.getValue().getNameProperty());

        id_col.setCellValueFactory((this_row) -> this_row.getValue().getBarcodeProperty().asObject());

        price_col.setCellValueFactory((this_row) -> this_row.getValue().getPriceProperty().asObject());


        /*
        These lines are a little dense, but what they're saying is:
        "My column has a function that knows how to turn a row into the value for this column (a "cell value" factory.)"

         For each row, that cell's column is determined by getting the Grocery object for that row, then getting the
         observable attribute from that Grocery. Because this attribute is observable, it can be wired directly into the
         grid!

         Now if we run our program again, we can finally see all the rows! The neat thing about this being a binding
         is that, even if the values in our objects change, the grid will update automatically:
         */


        groceries.get(0).getPriceProperty().set(99.99); //<- suddenly we have some very expensive bread


        /*
        If we were feeling particularly clever, we could even make our Grocery itself observable with
        PropertyChangeSupport (this would let us make the rest of the class with "Plain Old Java Objects"
        and just write the bindings off the Grocery directly.)

        However, bindings aren't limited to one way. As long as both sides of the binding use observable
        properties, we can make a binding bidirectional!
         */

        Grocery current_grocery = new Grocery("apples", 3.99, 4135);

        Label name = new Label();
        pane.getChildren().add(name);
        AnchorPane.setBottomAnchor(name, 10.0);
        AnchorPane.setLeftAnchor(name, 10.0);

        name.textProperty().bindBidirectional(current_grocery.getNameProperty()); //<- if our grocery name changes, so does this!

        current_grocery.getNameProperty().set("Gala Apple"); //<- this will change the label, even though I'm setting the grocery


        //If we wanted to bind barcode, it's slightly more tricky.
        // A barcode is an Integer, but a Label is a String... fortunately, there's a Converter object that can help:
        Label barcode = new Label();
        pane.getChildren().add(barcode);
        AnchorPane.setBottomAnchor(barcode, 10.0);
        AnchorPane.setLeftAnchor(barcode, 100.0);

        Bindings.bindBidirectional( //<- we use this static method that can associate the converter
                barcode.textProperty(),
                current_grocery.getBarcodeProperty(),
                new NumberStringConverter()
        );


        //These methods even work with editable text fields:
        TextField field = new TextField();
        field.setPromptText("Apple Price...");

        pane.getChildren().add(field);
        AnchorPane.setBottomAnchor(field, 10.0);
        AnchorPane.setRightAnchor(field, 100.0);

        Bindings.bindBidirectional(
                field.textProperty(),
                current_grocery.getPriceProperty(),
                new NumberStringConverter()
        );


        /*
        Now, any time we change the grocery's price in the UI, our object will also change!

         We can prove this by attaching a listener!
         */

        current_grocery.getPriceProperty().addListener((property, old_val, new_val) -> {
                System.out.println("Apple price changed from " + old_val + " to " + new_val);
        });

        /*
        Bindings let us create powerful UIs, where our elements are directly attached to
        Java objects that represent the current state of our program.
         */


    }
}
