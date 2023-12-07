import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.stage.FileChooser;

import java.util.List;
import java.io.*;


public class Controller {

	@FXML
	private Button load_button;



    /*
    When we go to make the TableColumn attributes in our Controller, we must
    be careful of one small detail -- TableViews and TableColumns are actually
    templated types!
    
    A TableView works by storing an internal list of all the objects you want
    it to display. This means that, just like a list, you have to tell it what
    kind of objects its storing! For this example, our table will be a table of
    Students.
    
    Likewise, each Column needs to track both what type of object the data
    comes from, AND what type of data is stored in this particular column.
    */
    

	@FXML
	private TableView<Student>   students_table;
	@FXML
	private TableColumn<Student, String> first_name_col;
	@FXML
	private TableColumn<Student, String> last_name_col;
	@FXML
	private TableColumn<Student, String> major_col;
	@FXML
	private TableColumn<Student, Integer> grade_col;


	private Stage constructed_stage;


	@FXML
	public void load_button_clicked(ActionEvent e) {

        /*
        Now, there is one final step we must perform -- actually loading the
        data into the grid!
        
        The TableView makes loading students easy -- all we need to do is
        set the 'items' of the TableView to be our list. However... the table
        wants a special kind of list called an "ObservableList". Luckily, it's
        easy to move our data from the loader into this list:
         */


		List<Student> students = StudentLoader.load();
		List<Student> students_in_grid = students_table.getItems();

        students_in_grid.addAll(students);


	}


	@FXML
	public void initialize() {

        /* But wait: if we run our code right now, we still can't see anything
         in the table.... That's because we still haven't told the columns where 
         they should be pulling their data from!


         To do this, we have to create a "ValueFactory" for each column that
         tells it the name of the attribute it should be attached to. A
         PropertyValueFactory sounds scary, but it's just an object that stores
         the name of the method we can call on our Student to find the data we want
         to display.


         A ValueFactory has one parameter, a String that gets used to look for methods
         called:

             public Property <EnteredString>Property() //<- this is a JavaFX "Property" object  
             public <Type> get<EnteredString>()
             public <Type> is<EnteredString>()
             
             Since I have a method called "getFirstName", I'm going to plug in
             the string "firstName" to my PropertyFactory so it finds "getFirstName"

             NOTICE: this uses the method name, not the attribute name
         
		*/



        PropertyValueFactory<Student, String> first_name_getter = new PropertyValueFactory<>("FirstName");


        /*
        Then, I just need to give this 'factory' to my column:
         */
        first_name_col.setCellValueFactory(first_name_getter);

        PropertyValueFactory<Student, String> last_getter = new PropertyValueFactory<>("LastName");
        PropertyValueFactory<Student, String> major_getter = new PropertyValueFactory<>("Major");
        PropertyValueFactory<Student, Integer> grade_getter = new PropertyValueFactory<>("Grade");

        last_name_col.setCellValueFactory(last_getter);
        major_col.setCellValueFactory(major_getter);
        grade_col.setCellValueFactory(grade_getter);


	}


	@FXML
	public void addRandomStudent(ActionEvent e) {
		System.out.println("Adding a student!");

		Student new_student = StudentLoader.generateRandomStudent();
		System.out.println("Random student's name is: " + new_student.getFirstName());
		this.students_table.getItems().add(new_student);

	}


	@FXML
	public void load_from_file(ActionEvent e) throws FileNotFoundException {

		FileChooser chooser = new FileChooser();
		File selectedFile = chooser.showOpenDialog( this.constructed_stage );
 		if (selectedFile != null) { //<- we check for null here in case the user clicked "Cancel" or X on the dialog 
    		List<Student> students = StudentLoader.load_from_file(selectedFile);
			List<Student> students_in_grid = students_table.getItems();


        	students_in_grid.addAll(students);
 		}


	}



	public void setStage(Stage s) {
		this.constructed_stage  = s;
	}



}