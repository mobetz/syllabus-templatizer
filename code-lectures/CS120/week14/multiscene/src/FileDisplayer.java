
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

import java.io.File;


public class FileDisplayer {
	
	@FXML
	private TextArea textarea;
	@FXML
	private MenuItem close_option;
	@FXML
	private MenuItem save_option;
	@FXML
	private MenuItem quit_option;



	public Scene getScene() {
		return this.textarea.getScene();
	}

	public void loadText(File some_file) {
		this.textarea.setText("In here, I could use a Scanner to set the text to the contents of the file located at: " + some_file.getAbsolutePath());
	}
}