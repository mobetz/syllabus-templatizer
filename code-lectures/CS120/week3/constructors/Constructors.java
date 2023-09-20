/*
     Objectives for Today

     By the end of today, you will:
        * Understand how constructors allow us to run code during the initialization of an object.
        * Identify how constructor syntax differs from other method signatures.
        * Override default methods of objects such as toString and equals.
        * Describe what it means when an object is passed and compared by "reference", and identify how this may
           be a source of bugs.
 */


/*

Vocabulary for the Day

Constructor - A constructor is a special method that runs right when an object is created. This method is generally
put at the start of the class that defines that object (right below the attributes), and often sets its inputs to 
the attributes of the class.

Override - An override is a declared function that changes a function that automatically exists for objects (such
as toString()). These functions work like any other normal function, but are prefixed with @Override.


Reference - A reference is something that "points to" data that exists in only one place. Objects are not copied every
time we assign them to a variable, and as a consequence, they are passed "by reference" into functions (this means if
we change the attributes of an object in the method, they change everywhere!)

*/



public class Constructors {


    public static void main(String[] args) {


        /*
         Let's take another look at our code from last class. We made a class to represent a Course, and populated it
         with properties such as course name, meeting days... However, there is one thing that is still
         clunky about our code:
         */

    	//Course first_course = new Course();  /// <--- at this point, we have an object, but what does this object represent?

    	//String first_day_this_course_meets = first_course.getFirstMeetingOfTheWeek();
        //System.out.println(first_day_this_course_meets);  //<- this crashes with some error about "meeting days being null"

/*
         Setting attributes after we've created the object is a problem -- all of our methods assume that this object
         has its attributes populated, but there's a moment in time when the object exists but isn't fully functional.

         We want objects to represent some ACTUAL data from the moment that they exist. I want this so that I can call
         any method in any order without the object breaking and crashing my program.


        Instead of setting the attributes *after* we create the object, we can use another tool at our disposal to make
        setting the attributes of the class a necessary step during initialization -- the constructor.

        Let's improve on the design of Course by adding a constructor.

        Using our new constructor, we can create a Course that already has all its attributes populated:
        */

        Course first_course = new Course("Programming I", "MWF", "M", "11:00 AM", 1);
         //Now, when I make the thing, we already know exactly what course this variable is holding.
         String received_details = first_course.getDetailsText();
         System.out.println(received_details);



        /*
         In addition to the constructor, there are a few other methods that are included in every class that we can write to
         augment our objects with special behavior. The first we will discuss is toString.

         Every object in Java automatically has a method implemented called "toString", that allows us to see a textual
         representation of the object. By default, this representation isn't very useful (it just tells us the class name
          and memory address of the object):
        */

         System.out.println(first_course);//<- prints out "Course@4f47d241" in the default implementation
         //This is telling us where in the computer's memory the course exists. That is interesting, but not useful.


         /*
         The equals() method is also provided to all objects. By default, it checks whether they are literally the same
         object created at the same time with the same memory address. This is also what happens when you use ==.

         However, that isn't useful if we make a second course that actually is the same:
         */
         Course second_course = new Course("Programming I", "MWF", "M", "11:00 AM", 1);

         System.out.println("Are first course == second course the same?: " + (first_course == second_course));
         //This is comparing REFERENCES (where the object lives), so it incorrectly tells us that Programming I is not the same
         // in both these objects.

         System.out.println("Does first course .equals() second course: " + first_course.equals(second_course));
         //Our custom .equals() knows how to check if they're the same.

    }
}