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


    //In addition, students will know about their current course list:
    private List<String> courses_taken;



    //METHODS

    public Student(String name_in, int age_in) {
        //Much like 'this' refers to the object we're currently on, 'super'
        //refers to the parent class' view of the object. If we use super()
        // as a function, we are referring to the constructor:
        super(name_in, age_in);


        this.courses_taken = new ArrayList<>();
    }

    public String generate_weekend_plans() {
        return "Study for exams!";
    }


    /*
    In addition to the methods defined on Person, we also want our student to
    be able to add a class and list their courses:
     */
    public void add_class(String class_name) {
        this.courses_taken.add(class_name);
    }

    public String list_classes() {
        return String.join(",", this.courses_taken);
    }



}
