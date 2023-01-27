
/*
Objectives for Today:

By the end of today, you will:
    * Identify different ways that Java code is distributed and executed.
    * Understand the concept of a "program entrypoint".
    * Use command line arguments in a Java program.
    * Describe the advantages of using a build tool to compile and execute code.
 */



/*

Vocabulary for the Day 

Entrypoint - The entrypoint of a program describes what instructions will run first when a program
is executed. For most compiled languages, the entrypoint is a special function called main().


Command Line Arguments - Command line arguments are extra details we provide to a program when it is
executed that are immediately assigned to variables at the entrypoint.

Build Tool - A build tool is a program that helps simplify the steps to run a program. For Java,
the most popular build tools are Ant, Maven, and Gradle.

*/


public class Entrypoints {


    /*
    Previously, we've learned that main() is a function: a group of instructions that is given a name,
    accepts some inputs, and returns some outputs.


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


        main() doesn't return anything because by the time main() is complete, the program is over... there
        wouldn't be any other function to return that final value to!


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

                       javac Entrypoints.java    #<- builds a computer-readable .class version of our code 
                        java Entrypoints         #<- executes the code and 'follows' the instructions


         When we use the 'java' command, we can  provide extra strings of text, and each string 
         gets assigned to a number counting up in our arguments array, starting from zero:
        */

        String first_thing_entered = args[0]; //<- this grabs the first 'word' of text from the command line
        String second_thing_entered = args[1]; //<- this will grab the second


        System.out.println("Arguments to this program were:\n   - " + first_thing_entered + 
            "\n   - " + second_thing_entered);

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

            If I want to use these arguments to pass in numbers, I will need to convert them.
            Fortunately, there are some helpful functions that let me turn text into numbers:


        */

        int first_num = Integer.parseInt(first_thing_entered);  //<-takes a String, gives back an Integer
        String other_direction = String.valueOf(first_num);  //This would take an Integer, give back a String



        double second_num = Double.parseDouble(second_thing_entered);  // there are "parse" functions for most base types, 
                                                              // with predictable names



        double sum = first_num + second_num;

         System.out.println("The sum of " + first_thing_entered + " + " + second_thing_entered + " = " + sum);


        /*
        Note: If you try to convert something impossible into a number (like the word "apple"), your program
        will crash, because Java won't know how to follow further instructions with your numeric non-number.

         */



         /*


        Now that we've learned more about how main works and the arguments we can pass in, let's also take a 
        moment to talk more about the way we build our programs.


        So far, every time we've wanted to run our java code, we've needed to compile, and then 
        separately execute our programs. This is great for understanding the steps the computer
        takes, but it's mildly inconvenient. 

        We can use a build tool to help expedite this process -- a build tool is exactly what it sounds like:
        a tool, (in this case another program,) that helps us build our software.

        The build tool we'll be looking at today is called "gradle" ( https://gradle.org/install/ )



        In order to tell gradle the information it needs to build our Java code, we must create one additional
        file: a "build.gradle" file. Let's make that file now.

         */

    }


    public static double AdderFunction(double x, double y) {
        return x + y;
    }
}