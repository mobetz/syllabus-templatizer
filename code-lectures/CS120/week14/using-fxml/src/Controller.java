
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;


/*
When we wrote our FXML file, we made a "promise" that some of our controls would have home in our Java code. This is what lets us attach
those controls to behaviors (like functions that are called when a button is clicked.) We need to make a class that JavaFX can find
with the same name as the class that we wrote into the "Controller" pane in Scenebuilder, with all the attributes we set on fx:id in the Code
pane for each control.
*/

public class Controller {

    // Inside our "controller" class, we can create attributes to hold each of our controls, annotated with @FXML to let
    // Java know that these are FXML properties.
    @FXML
	private Button click_button;

	@FXML
	private Label some_text;

	@FXML
	private MenuItem close_menu;


	private int num_clicks; //<- not every attribute NEEDS to be a JavaFX control from SceneBuilder, this class can also hold any state.



    @FXML
    public void initialize() {
        //You can do stuff here similarly to a constructor
        this.num_clicks = 0;
    }



    // And we can create the methods we hooked up to control events in the "code" panel:
    @FXML
	public void when_button_clicked(ActionEvent e) {
    	this.num_clicks = this.num_clicks + 1;
    	this.some_text.setText("This button has been clicked " + this.num_clicks + " times.");

		
	}


	@FXML
	public void close_the_program(ActionEvent e) {
        Platform.exit();
	}
	
}