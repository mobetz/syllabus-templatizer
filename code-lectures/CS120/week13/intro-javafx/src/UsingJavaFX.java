
/*
Objectives for Today:

By the end of today, you will be able to:
  * Describe how JavaFX uses "scenes" onto "stages" to create GUI applications.
  * Understand how events can be attached to JavaFX controls.
 */


/* Vocabulary for the Day

Stage - A "stage" is the window where a JavaFX program is displayed. A stage can be shown or hidden, and while
shown, can load a visible "scene" that contains the elements that should be displayed.

Scene - A "scene" is a group of JavaFX controls and their associated positioning information. Each scene has a single
"root" element at the top level of the scene, (usually a Pane that can hold child controls like buttons or labels.)

*/


/*
In lab today, we will run our very first JavaFX program!

To make a GUI application, we need a 'main' class, just like the main classes we've made in all our programs so far.

However, we need to add one extra thing to our class declaration: After we put the name of our class, we have to add
"extends Application" (and import javafx.application.Application, after we add JavaFX to our gradle build file!)


The "extends" keyword is a way of telling Java that our class is an example of a specific kind of class. In this case,
we're telling Java that the class we're making right now is a JavaFX Application.
 */


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;


public class UsingJavaFX extends Application {

  private int num_clicks;

    /*
    With our main method, we took in a String[] array of "args".

    The "args" in the main method were things that we could enter on the command line when starting the program.
    However... that doesn't make sense for a GUI application. What a GUI application's entry point cares about is
    being given the window it should be drawn in. Because of this, JavaFX creates a separate entrypoint function 
    called "start" that accepts this parameter. 

  JavaFX is smart enough to make a main() that calls start for us, but if we want, we can still write it out by hand:
    Our main method in this case will only do a single thing: call the "launch" function with our command line args, 
    which tells JavaFX to create the window and call "start".
     */
  public static void main(String[] args) {//<- in JavaFX, this function is actually optional!!!!
     launch(args);
  }


  @Override
  public void start(Stage stage) {//<- this will be our "entrypoint" to the whole program instead!!!
        /*
        The "stage" of a JavaFX application is the window that your program displays. You can think of a stage similar
        to the "stage" in a theatre where a play is being performed.

        Our stage is an object, with lots of helpful methods to do things like set the title bar, and change the
        dimensions of the window.
         */
    stage.setWidth(600);
    stage.setHeight(400);
    stage.setMinWidth(300);
    stage.setMinHeight(300);
    stage.setTitle("Intro to JavaFX");


    /*
    After "setting the stage", we need something we can show our users. The object in JavaFX that holds all the
    text and controls for the window is called the "scene".

    We create a new scene the same way we create any of our other objects: with the new keyword and a constructor!

    The Scene constructor takes a single parameter: a container that is going to act as the space to fit all our other
    controls. We call this top level container the "root" of our scene. For this example, let's use a VBox
    as our "root" element:
     */
    VBox vbox_pane = new VBox();
    vbox_pane.setAlignment(Pos.CENTER);

    Scene visible_scene = new Scene(vbox_pane); //NOTE: we can use other types of 'roots' here -- 
                                                //   Pane, AnchorPane, HBox are all other containers

    //Once we have our scene, we can set the active scene with our stage's "setScene" method:
    stage.setScene(visible_scene);

    /*
    Once our stage is set with a scene, we can show the stage by using the "show" method:
     */
    stage.show();


     /* -------------------------------------------------------- */


        /*
        Every control we could want to add to this window, whether it's a Button, or a text Label, or even something as
        complex as a DatePicker, is just another object whose class is given to us by JavaFX:
         */
     Label some_text = new Label();
     some_text.setText("This is my first JavaFX Application!!!!");


        /*
        To actually add our controls to the scene, we need to add them to a container. For the VBox, we just need
        to add the controls to the list of children for the pane, and the VBox will align them all vertically, one after
        the next:
         */

     List<Node> children_to_display_in_app = vbox_pane.getChildren();
     children_to_display_in_app.add(some_text);


     Button a_button = new Button();
     a_button.setText("Click Me!");
     children_to_display_in_app.add(a_button);


        /*
        To make our button do something, we need to set an action that will be performed when the button is clicked.
        An action isn't a string, or an integer, or even an object... an action is a group of instructions.

        
        To set the action, we just need to provide the name of a function to our setOnAction() method, WITHOUT calling it.
        This is just like when we wanted to map() or filter() a stream!
         */



     a_button.setOnAction(UsingJavaFX::onButtonClicked);


        /* 
        We can even use the same 'lambda function' notation we introduced back then:
        */
     num_clicks = 0;
     a_button.setOnAction( (ActionEvent click_event) -> {
        //Inside our 'lambda' function, we can still see the variables from the outer function. 
        // this means we can change our labels/children of the VBox.

        num_clicks = num_clicks + 1;
        some_text.setText("The button has been clicked " + num_clicks + " times!");


        boolean is_currently_normal = a_button.getTextFill() != Color.ORANGERED;
          if ( is_currently_normal ) {
                a_button.setTextFill(Color.ORANGERED);
          }  else {
            a_button.setTextFill(Color.BLACK);
          }
      });
  }


    /*
    In a static method like this, we can put all the code we want to happen when our button is clicked.

    This method acts like a little "mini-main" that executes when something happens. We call these types of methods
    "events".
     */
  public static void onButtonClicked(ActionEvent click_event) {
        System.out.println("Our button was clicked!");
  }


}