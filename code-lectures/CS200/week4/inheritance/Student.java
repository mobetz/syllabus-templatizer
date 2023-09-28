import java.util.*;

/*
To make our student a "subtype" of Person, we add two words to the declaration
of the class: extends Person. This tells Java that Student should "inherit"
all the information that Person has.
 */
public class Student extends Person {
    
    //Our student needs to have everything a Person has:
    /*
    private String name;
    private int age;
    
    Once we extend Person, all of Person's attributes and methods are implied!
    
   */
    
    //In addition, students will know about their current course list:
    private List<String> courses_taken;
    
    public Student(String name_in, int age_in) {
        //Much like 'this' refers to the object we're currently on, 'super'
        //refers to the parent class' view of the object. If we use super()
        // as a function, we are referring to the constructor:
        
        super(name_in, age_in);
        
        /*
        We could also call super.methodName() to call any other method on the
        superclass.
         */
        
        this.courses_taken = new ArrayList<>();
        
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
    
    
    /*
    The student may have different weekend plans that we can fetch with the
    same method:
     */
    public String generate_weekend_plans() {
        return "Studying for exams!";
    }
    
    /*
    Since student is a Person, I would think that I should be able to use this.name
    to access a Student's name...
     */
    
    public String print_id() {
        // But if Person.name is private, this fails:
        // .\Student.java:61: error: name has private access in Person
        
        /*
        Remember the definition of PRIVATE: a private field can only be accessed
        from the same file. This file is not Person.java, so the field is not
        visible here.
        
        If we want a field to be hidden from someone holding an object, but
        visible to any subclass, then instead of private we use 'protected'.
         */
        return "---------\n| "
               + this.name + " ".repeat(7 - this.name.length())
               + "|\n---------";
    }
    
    
}