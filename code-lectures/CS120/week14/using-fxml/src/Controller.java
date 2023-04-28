

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Controller {

    // Inside our "controller" class, we can create attributes to hold each of our controls, annotated with @FXML to let
    // Java know that these are FXML properties.

    @FXML
	private Button click_button;
    @FXML
	private Label some_text;

	
    private int num_clicks;



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

}