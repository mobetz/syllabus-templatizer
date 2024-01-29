

/*
Objectives for Today:

By the end of today, you will:
    * Identify different ways that Java code is distributed and executed.
    * Understand the concept of a "program entrypoint".
    * Use command line arguments in a Java program.
    * Describe how variables are scoped to functions.
 */



/*

Vocabulary for the Day

Entrypoint - The entrypoint of a program describes what instructions will run first when a program
is executed. For most compiled languages, the entrypoint is a special function called main().

Command Line Arguments - Command line arguments are extra details we provide to a program when it is
executed that are immediately assigned to variables at the entrypoint.

Scope - Scope is the "lifetime" of a variable. When a variable's scope is expired (usually
at the first unmatched } it finds), it will be cleaned up and no longer visible to the program.

*/


public class Entrypoints {

  /*
    Previously, we've learned that main() is a function: a group of instructions that is given a name,
    accepts some inputs, and returns some outputs.

    Most functions are like a recipe: if you don't "call" them to use the instructions, you don't get
    back any results.

    However, main is a special function -- unlike every other function in our program, we never call it
    elsewhere. This is because main is our "entrypoint" function; it is the one function that Java will
    call for us when we execute the program.
    */


    public static void main(String[] args) {

        /*
        Now that we recognize that main() is just the name of the function, we should take a moment to
        think about the signature of this function.

        Specifically, the return type of main() is "void" -- this is a special placeholder for a function
        that doesn't return anything at all.


                Execute java Entrypoints
                       .
                       .
                       V
                    implicitly
                    calls main() -------------------+
                                                    |
                                                    v
                                               +---------+
                                               |  main() |
                                               +---------+
                                                    |
                     program is  <------------------+
                       over...            return value

                    nowhere to
                 assign a variable



        main() doesn't return anything because by the time main() is complete, the program is over... there
        wouldn't be any other function to return that final value to!

        However, there is one more detail of main()'s signature that is vitally important --
        main() takes a function parameter!

       The type of function parameter that it accepts looks mostly like a string, but it has
       some weird brackets after it. This is because it is an 'array', a special variable we
        will learn about in a few weeks that holds more than one piece of data.


         As far as how we can pass something into it, the parameter to main() holds what are
         called "command line arguments".  These are pieces of information that someone provided
         right when they first ran the program.



        When we wrote our very first programs, we learned that we can build and run them using the
        Java Compiler and JVM commands:

      javac Entrypoints.java       #<- builds a computer-readable .class version of our code
      java Entrypoints             #<- executes the code and 'follows' the instructions


        */

        System.out.println("I'm in the main function!");


        String first_thing_entered = args[0];  //<- this grabs the first 'word' of text from the command line
        String second_thing_entered = args[1]; //<- this will grab the second

        System.out.println("Arguments to this program were: \n" + first_thing_entered
         + " \n" + second_thing_entered);

        /*

       /*
            Now, if I run my Entrypoints program with:

                    java Entrypoints "Some first argument" "more text"


            I get printed back:

                    Arguments to this program were:
                        - Some first argument
                        - more text


          These arguments will always be strings, because the only safe thing that Java can assume
          is that they were at one point typed on a keyboard, and they might include letters or other
          symbols.

      */

      String some_result = args[0] + args[1];

      System.out.println("The result of some_result is: " + some_result);

      /*


            If I want to use these arguments to pass in numbers, I will need to convert them.
            Fortunately, there are some helpful functions that let me turn text into numbers:
      */

      int first_num = Integer.parseInt( args[0] ); //<- takes a String, gives back an Integer
      String other_direction = String.valueOf(first_num); //This would take in anything, gives back a String


     double second_num = Double.parseDouble(second_thing_entered);  // there are "parse" functions for most base types,
                                                                    // with predictable names

      double sum;
      sum = first_num + second_num;

      System.out.println("The numeric result of adding these two together is: " + sum);

      /*
        Note: If you try to convert something impossible into a number (like the word "apple"), your program
        will crash, because Java won't know how to follow further instructions with your numeric non-number.

          */




           /*
          One last point I want to discuss about functions: Each function has its own separate list of variables!
          */

          double the_returned_value = AdderFunction(first_num, second_num);

          System.out.println("All of these are defined:" + first_num + ", " + other_direction + ", " + second_thing_entered);

          //System.out.println("The result variable: " + result);

    }



    public static double AdderFunction(double x, double y) {
      //System.out.println("All of these are defined:" + first_num + ", " + other_direction + ", " + second_thing_entered);

      // The variables "other_direction", "first_num", and "Second_thing_entered" all have a "SCOPE"
      // that only includes the function they were first declared in.
      double result = x + y;
      return result;
    }


}
