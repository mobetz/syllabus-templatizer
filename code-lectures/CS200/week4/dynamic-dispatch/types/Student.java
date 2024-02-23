package types;
import java.util.ArrayList;
import java.util.List;

/*
To make our student a "subtype" of Person, we add two words to the declaration
of the class: extends Person. This tells Java that Student should "inherit"
all the information that Person has.
 */
public class Student extends Person {
    
    //ATTRIBUTES

    //Our student needs to have everything a Person has:
    /* 
    private String name;
    private int age;

    Once we extend Person, all of Person's attributes and methods are implied!

   */
    private List<String> courses;



    //METHODS

    public Student(String name_in, int age_in) {

        //Much like 'this' refers to the object we're currently on, 'super'
        //refers to the parent class' view of the object. If we use super()
        // as a function, we are referring to the constructor:

        super(name_in, age_in);

        this.courses = new ArrayList<>();


    }



    /*
     * Similarly to attributes, all the methods on Person are implied in Student
    public void has_birthday() {
        this.age = this.age + 1;
    }
    */


    public void add_course(String class_name) {
        this.courses.add(class_name);
    }


    public String list_courses() {
        return String.join(",", this.courses);
    }


    @Override
    public String generate_weekend_plans() {
        return "Study for exams!";
    }


    public String generate_student_id() {
        return "-----------\n"
              + this.name + "\n" +
              "------------";
    }

}
