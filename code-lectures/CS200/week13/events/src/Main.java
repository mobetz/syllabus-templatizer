import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
Objectives for Today

By the end of today, you will:
    * Understand the event observer pattern.
    * Identify ways that events are implemented in JavaFX.

 Vocabulary of the Day

 Event - In computer science, an event is a behavior which triggers related "listener"
 functions when it occurs.

 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        /*
        We have taken a deep dive this semester exploring how object references are stored in memory and shared between
        variables across functions. However, we have not yet explored one of the most common applications of sharing
        references: event systems.

        Today, we are going to learn about this very valuable tool for controlling flow in our programs.

        When we write large programs, one of the biggest architectural decisions we have to make is how to inform
        different components when a change occurs. Say, for instance, we have ShoppingCart and Grocery classes. The
        ShoppingCart tracks a total price, and holds a list of Groceries with individual prices.
         */

        ShoppingCart cart = new ShoppingCart();
        cart.addGrocery("bananas", 1.99);
        cart.addGrocery("apples", 4.99);

        //Our class works as long as we use it correctly....
        System.out.println("Total of bananas + apples: " + cart.getTotalPrice());

        //...But what happens if a grocery changes price?
        cart.getGroceries().get(0).setPrice(3.99); //<- banana shortage! Price went up!

        System.out.println("After bananas increased in price, the total is now: " + cart.getTotalPrice());
        //When we originally run this code, the price doesn't change!

        /*
        In this case, we could solve the problem by computing the total dynamically every time it's asked for, but
        if our cart has hundreds of items, we might not want to iterate the whole cart every addition.

        In this case, we can use events! In Java, we can implement this behavior with two types:
           * a PropertyChangeSupport object, which knows how to register items and broadcast changes
           * the PropertyChangeListener interface, which describes what happens when a value we're listening to changes


           Let's go implement a design using these two types in our Grocery and ShoppingList.

           Now, when we re-run our code, Java automatically updates the price in the cart the second time.

           This event/listener architecture allows us to be much more flexible in our designs -- we could do things like
           create one "canonical" copy of each grocery, and add that same grocery to every cart, and the price would be
           updated everywhere at once!

           One time this pattern is particularly useful is when we're doing GUI programming. In JavaFX, many objects
           implement their own listener and event objects. For instance, JavaFX gives us an Observable List:
         */

        ObservableList<String> names = FXCollections.observableArrayList();

        /*
        Observable lists still implement List, so they have all the expected methods:
         */
        names.add("Joe");
        names.add("Mary");
        names.add("Timothy");

        /*
        But they also have support for Listener events with an .addListener() method. addListener() normally takes
        an object that implements the ListChangeListener interface. We *could* go implement a class with that name,
        but when an interface has only a SINGLE method, we can use a "lambda function" to write that method right inline
        as part of our current function:
         */

        ListChangeListener<String> listener =
                (ListChangeListener.Change<? extends String> changes) -> { //<- inside the parentheses we put method params, then an arrow to the function body
                  while ( changes.next() ) { //<- we can process each change from the Observed List...
                      for (String name : changes.getAddedSubList()) {          // if it was an added name, show it:
                          System.out.println("Observed adding: " + name);
                      }

                      for (String name : changes.getRemoved()) {  // if it was a removed name, show it:
                          System.out.println("Observed removing: " + name);
                      }
                  }
        };

        names.addListener( listener );

        //Now, any time we add or remove a name, our function will fire!
        names.add("Ryan");
        names.add("Helen");
        names.remove("Ryan");

        /*
        This would give us yet another trick for implementing our ShoppingCart, so that each added or removed item immediately
        recomputes the total! As we will see in another lecture, JavaFX uses ObservableLists for controls like data grids
        so that changes to the data immediately triggers an update to our graphics.

        For today, we will look at one more application of events: events are used in JavaFX to let code listen to UI
        controls like Buttons, TextFields, or MenuItem clicks!
         */

        Button javafx_button = new Button();
        javafx_button.setText("Click me!");

        /*
        JavaFX controls all support emitting one or more types of "ActionEvents". Like our other event listeners,
        EventHandlers for ActionEvents are an interface with a single method. This means we can once again write them as
        a lambda:
         */

        EventHandler<ActionEvent> when_clicked =
                (ActionEvent e) -> {
                    System.out.println("Button clicked!");
                };

        javafx_button.setOnAction(when_clicked);

        // If we wanted to be even more glib, we could write the lambda right inside the method handler:
        var counter = new Object() {
            public int value = 0;
        };

        Label text_label = new Label();
        text_label.setText("Button clicked 0 times.");

        javafx_button.setOnAction((e) -> {
            counter.value++;
            text_label.setText("Button clicked " + counter.value + " times.");
        });


        //Now, if we add these controls to a scene and attach that to our method's stage:
        AnchorPane pane = new AnchorPane();
        ObservableList<Node> children = pane.getChildren(); //<- an observable list, so when we add things the scene updates!!
                                                            // Every JavaFX control is a subtype of a Node, so we can add them all
        children.add(text_label);
        children.add(javafx_button);


        //Now let's layout our children, make a scene for them, and load that scene on our stage:
        pane.setPrefHeight(200);
        pane.setPrefWidth(300);

        AnchorPane.setLeftAnchor(text_label, 100.0);
        AnchorPane.setLeftAnchor(javafx_button, 125.0);
        AnchorPane.setTopAnchor(text_label, 40.0);
        AnchorPane.setTopAnchor(javafx_button, 80.0);

        //(Note, we can also do this layout graphically with SceneBuilder and load the resulting FXML with an FXMLLoader)


        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();



    }
}