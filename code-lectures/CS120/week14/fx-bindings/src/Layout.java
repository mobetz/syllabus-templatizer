
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;

import java.util.List;


public class Layout {
	
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
	private TableView<Student> students_table;
	@FXML
	private TableColumn<Student, String> first_column;
	@FXML
	private TableColumn<Student, String> last_column;
	@FXML
	private TableColumn<Student, String> major_column;
	@FXML
	private TableColumn<Student, Integer> grade_column;

	public void load_from_dummy(ActionEvent e) {
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
        first_column.setCellValueFactory(first_name_getter);


        PropertyValueFactory<Student, String> last_getter = new PropertyValueFactory<>("LastName");
        PropertyValueFactory<Student, String> major_getter = new PropertyValueFactory<>("Major");
        PropertyValueFactory<Student, Integer> grade_getter = new PropertyValueFactory<>("Grade");

        last_column.setCellValueFactory(last_getter);
        major_column.setCellValueFactory(major_getter);
        grade_column.setCellValueFactory(grade_getter);
        
        
	}

	@FXML
	public void addRandomStudent(ActionEvent e) {

        /*
        One of the especially powerful things about our TableView is that we can
        change the contents of its "items" list at any time, and the list will
        update automatically!
         */
		Student new_student = StudentLoader.generateRandomStudent();
		this.students_table.getItems().add(new_student);

        /*
        This makes it easy to implement editing and filtering on our tables!
         */
	}





}