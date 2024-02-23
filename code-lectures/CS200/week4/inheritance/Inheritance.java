

/*
Objectives for Today

By the end of today, you will:
    * Define the concept of inheritance in object oriented programming.
    * Identify the extends, super, and protected keywords.
    * Define dynamic dispatch.
    * Understand how Java selects the implementation of a method when called.
 */



/*
Vocabulary for the Day

Inheritance - Inheritance refers to the act of creating a "subtype" of an existing
type using the extends keyword. When we create a subtype, we refer to its
origin class as the "supertype".

Dynamic Dispatch - Dynamic Dispatch refers to the rules Java uses to discover
what function to execute when a method is called.
 */

import types.Person;
import types.Student;

public class Inheritance {

    public static void main(String[] args) {
        
        /*
        One of the earliest things we learned in Java was how to create classes
        that bundle state and behaviors. When we create a class, we are defining
        a new type that we can use in the rest of our program:
         */

         Person someone = new Person("John", 20);



        /*
        However, what if we wanted a class that works very similarly to this one,
        like a Student that is a special version of a Person.

        Right now, we would need to cut-and-paste all of the existing code
        to serve as the "starting point" for our Student.
         */


         Student a_student = new Student("Jane", 22);

         System.out.println(a_student);

         a_student.has_birthday();
         a_student.add_course("CS200");
         System.out.println(a_student + ", courses taken: " + a_student.list_courses());


         a_student.name = "asdfasdf";


    }
}