
/*
Objectives for Today

By the end of today, you will:
  * Describe how the final keyword allows us to values that cannot be changed later.
  * Understand how static methods allow us to write methods that work even when we don't have an object.
  * Observe how static properties allow us to share a single variable across all of our objects of a given class.
  * Practice writing code that uses static attributes and methods.
 */



/*
Vocabulary of the Day

Constant - A constant is a value defined in a computer program that can never change. Many programming languages include
   several mathematical constants by default, such as Math.PI. We create constants using the "final" keyword.

Static - Static fields of a class are those which are shared by all the objects of that class. When we create a static
   variable, it is only instantiated once, at the very start of the program.

*/



public class StaticAndFinal {

    public static void main(String[] args) {


        /* Sometimes, when we're writing a program, we need to use values that we know will never change:
              * there are mathematical constants that we need to use in formulas (for example, PI, PHI, Euler's number...)
              * there might be strings we use for formatting (for example,  all MassBay email addresses always end in "@massbay.edu".)

        I could just use literals to express these values:
              */

      String first_username = "mobetz";
      String one_email = first_username + "@massbay.edu";

      String second_username = "smoussavi";
      String second_email = second_username + "@massbay.edu";//<- what if I typo this at some point?

      /*
      Doing this is error-prone (I can make a mistake when I'm typing at some point), and it's inherently inflexible -- if in the
      future I want to change this code to work with umass email addresses, I have to fish out every single place I used this string.

      Constant values let us create variables that cannot change to solve this problem. Fortunately, Java gives us a special keyword 
      we can use when we're declaring variables that gives us exactly this kind of protection:
         */

      final String email_suffix;
        /* We just add the word "final" right before our normal declaration to say "this value can never change". */
      email_suffix = "@massbay.edu";
      System.out.println("Email_suffix is saved as: " + email_suffix);
      //email_suffix = "@umass.edu";//<- I'm trying to change this value somewhere after it's been assigned. This will be a compiler error!

        /*
         Java comes with many 'final' constants by default. For instance, in Math, we've got a constant representing PI:
        */

        System.out.println("What is the value of PI?: " + Math.PI);
        //Math.PI = 3; //Unlike the Indiana State Senate, Java doesn't let us change the value of Pi.



        /*
          Final variables let us securely store values that should never change.


          In addition, let's revisit another topic we discussed earlier this semester: static variables. Let's
          start by making a class that can hold details about a Student.
        */

        Student first = new Student("Jason", "Malolez", 24, 2);
        Student second = new Student("Karen", "Schmidt", 26, 1);


        int number_of_students = first.getEnrolledCount();
        System.out.println("Jason tells me there are: " + number_of_students + " students.");

        Student third = new Student("Adam", "Penguin", 18, 1);

        System.out.println("Now Jason says there are " + first.getEnrolledCount() + " students.");

        System.out.println("Now Adam says there are " + third.getEnrolledCount() + " students.");


        /*
        If we create a static method version of getEnrolledCount(), then we call it not with an individual
        object, but with the class name, just like our static attributes: */
        System.out.println("Now Adam says there are " + Student.getEnrollmentCount() + " students.");


        number_of_students = Student.getEnrollmentCount(); //<- because this is a static method, I can call it directly from the class. I don't need an object
        System.out.println("After creating one more, there are: " + number_of_students + " students.");


        Student.addFakeStudent();
        Student.addFakeStudent();
        Student.addFakeStudent();

        Student.addFakeStudent();

        number_of_students = Student.getEnrollmentCount();
        System.out.println("After padding our enrollment for more grant money, we have: " + number_of_students + " students.");

        /*
        The last note I want to leave you with about static and final:

        Both of these keywords are not truly "object oriented" -- static and final are great for creating "pure" functional
        code that accepts inputs and returns outputs. However, anything written in a static context is really more like
        just declaring free-floating functions (the functions are not attached to state.)

        For this reason, both static and final should be used very sparingly when we are writing object oriented code.
        */

    }

}

