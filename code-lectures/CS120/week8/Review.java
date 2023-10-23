/*
Objectives for Today

Today, we will:
   * Review what we have learned this semester in CS 120, including:
     - data types
     - declaration
     - assignment
     - control flow (loops and branches)
     - classes and objects (including methods and attributes)
     - arrays
 */



public class Review {

    public static void main(String[] args) { //<- remember, the "args" here are command line arguments -- things that come in from the terminal when you type "java <program_name> <arg1> <arg2> <arg3>"


        /* Declaration is the process of creating a new variable. A variable is a named slot that lets us store
        data so that we can use it more than once.

        When we declare a variable, we need to tell Java two things:
         */


        int some_number;

        /* We need to tell Java what kind of value we want to save, and then we need to give that value a name.

        In Java, we have to declare variables before we use them because Java is statically-typed. This means that
        every variable is only ever allowed to hold one kind of value, and Java needs to know what type of value that
        will be.

        The simplest types are base types, also called "primitives". These are types that are included in Java by default:
        */


        boolean a_bit;       //stores one bit. Typically used to represent "True" or "False"
        byte a_byte;      //stores 8 bits numerically. Can represent values from -128 to 127.
        char a_letter;    //stores two bytes (one character) of text. Can represent ASCII, lower order Unicode...

        short small_num;   //stores two bytes of numeric data. -32768 to 32767.47
        int medium_num;  //stores four bytes of numeric data. -21483648 to 2147483647.
        long big_num;     //stores eight bytes of numeric data. -9223372036854775808 to 9223372036854775807.
        float small_dec;   //stores four bytes of numeric data *with a decimal point*. Enough for ~7 digits.
        double big_dec;     //stores eight bytes of numeric data *with a decimal point* Enough for ~16 digits.



        /* However, variables can hold more kinds of values than just our primitives. We can also make a variable to
        hold a whole bundle of related data in a single place.


        We call these bundles of data "objects".


        The type for these variables is the name of the object's "class", but otherwise object
         variables are declared just like any other variable: */

        File a_file_handle;
        String some_text;
        Scanner input_reader;
        Assignment some_graded_work;



           /* Assignment is the process of saving a value into a variable. We perform assignment with the 
           equals operator (=).


        Assignments have two parts:
         - To the left of the equals sign, we put the place we want to save something.
         - To the right of the equals sign, we put the thing we want to save.


        The simplest assignments are those where we assign a "literal value" into a variable:
         */

         medium_num = 14;
         a_letter = 'x';
         some_text = "Hello world!";


        /*
        Anywhere that we can use a literal value in a program, we can also use any "expression" that will
        turn into a value when it's finished:
         */


        some_number = 4 + 6;
        a_bit = (true || !(false && true));
        some_text = "Hello, " + "World!";
        medium_num = some_number + 1;


        /* When we have one of these value expressions, Java starts on the right hand side, does all that work, then
         * saves the final answer on the left. */



        /* A method is a group of instructions that complete a specific task. Java methods that aren't part of a class
        are sometimes called "functions".

        Methods have a name, a list of things they need from the programmer in order to run, and a thing they give back
        to the programmer when they are finished.

                                +-----------------+
        called with ----------> |  some_function  | --------->   assigned to a variable
         arguments              +-----------------+    return

            To call a method, we put the name of that method, then all the arguments that method needs inside parentheses:
         */

         System.out.println("This text is the single argument to the println() function.");


        /* When a method gives back a return value after it finishes, it is an expression just like any other work.
        This means we can save that return value in a variable. */

        big_dec = Math.power(2, 4);


        /* Just like with our assignment above, any argument to a method that can be a literal value can also be a
         value expression: */

         some_text = some_text.repeat(some_number + 1);

        /* Even method calls themselves count as value expressions: */
         System.out.println( some_text.repeat(Math.abs(-4)) );


        /* When we want to create one of our objects that bundles a group of data, we call the constructor for that object.
          This works like any other method call, but it has one extra keyword, new:
         */

         a_file_handle = new File("some/path/to/the/file.txt");
          some_graded_work = new Assignment(50, 50, "lab"); 
          input_reader = new Scanner( System.in );
          ///the "new" keyword tells Java to save room for all the attributes!


        /*
        OBJECTS AND CLASSES


        An object is a bundle of variables with functions created from a class "blueprint". 
        Once we have an object saved in a variable, the main way we interact with those variables is by calling
        its methods/functions.

        We can tell that object to do something with the dot operator.

        On the left of the dot, we put the object we are talking to.
        On the right of the dot, we put the thing we want to use from an object (usually a method.)
         */


          some_text = a_file_handle.nextLine();
          medium_num = some_graded_work.getPointsPossible();
          a_file_handle.renameTo(  new File("some/new/path/to/a/different/file.txt")  );


        /*
        When the method of an object returns another object, we can even use the dot operator to get a specific thing
        from that returned object. When we do this, the dots are resolved from left to right:
         */

          String shortened_replaced_line_from_user = input_reader.nextLine().replace(" ", "_").substring(0, 20);

          String answer = "This is a very long line of text".substring(0,5); //<- this will save just the first five letters
          System.out.println(answer); //<- this will print out "This ";
          // This is the same as writing:
          String text_from_user = input_reader.nextLine();
          String replaced_text = text_from_user.replace(" ", "_");
          String shortened_text = replaced_text.substring(0, 20);


        /* Java comes with many different classes of objects, but often we'll have to make our own to solve the specific
        real-world problems we are writing a program for.

         Let's practice making a Dog class that stores information about a Dog!

        Creating Dog.java also creates a brand new data type for our variables: Dog
         */

          Dog a_dog = new Dog("Spot", "Beagle");
          a_dog = new Dog("SecondDog", "Husky"); //<- I can still reassign into a object variable like any other
                                                 // just like with base types, this "deletes" everything previously there
          a_dog.changeName("Tricksy");


        /*
        CONDITIONALS


        Sometimes, we want to only execute instructions when a particular condition is met.

        When this is the case, we can use an if statement.
         */

        boolean some_condition_is_met = true; //this could also be from a function or one of our boolean operators...

        if ( some_condition_is_met ) {
            //The code inside these curly braces will only run when the value expression inside the parentheses is true.

            //Just like with our assignments and method calls, the value expression can be anything that will eventually
            // become either "true" or "false", whether it's a variable, or a comparison with < or ==, or a method call.

            //REMINDER: you can only use the == with base types. For objects (including "Strings",) you need to use .equals()
        } else {

            //things inside an "else" statement will only happen when the things in their preceding if statement DON'T run
            // (when the conditional expression is "false".)
        }

        /*
        LOOPS

        Sometimes, we want to keep repeating some instructions AS LONG AS a condition is met, over and over.

        When this happens, we can use a loop. We have two different keywords for loops:


        A WHILE loop lets us repeat instructions until a particular condition is no longer true:
         */

        while ( some_condition_is_met ) {

            /*
            The first time a program reaches a while loop, it behaves just like an if statement --
            if the condition is met, the code inside the curly braces executes.
             */

            //However, when we reach the end curly brace, we will jump back up to the top of the loop, and check the
            // condition again. This will repeat until the condition inside the parentheses is false.

            some_condition_is_met = false;
            //This means something inside the loop needs to change one of the variables being used the condition, or
            // the loop will repeat forever.
        }

        /*
        A FOR loop lets us repeat instructions a certain number of times.

           To make a for loop, we need to:
              - declare a variable that will keep count of which numbered time we are executing the loop.
              - write a condition (just like our while loop)
              - write a statement that updates our variable keeping count (usually using ++ to add 1 to the variable we declared.)

        */

        for ( int number_of_runs=0;    number_of_runs < 5 ;  number_of_runs = number_of_runs + 1 ) {

            //Our variable keeping count of the loop is a variable, just like any other.
            // We need to declare the loop variable, and assign it an initial value.

            // When this for loop executes, the variable is declared first. Then the condition is checked immediately.
            // At the very end of the loop, the update statement runs, then the condition is checked again.
        }

        /*
        It's possible to rewrite any for loop as a while loop by declaring the variable before the loop ourselves, and
        update the variable at the end of the loop:
         */

        int number_of_runs=0;
        while ( number_of_runs < 5 ) {
          //Whatever happens in the body goes here...

          number_of_runs = number_of_runs + 1;
        }


        /* While loops written using this pattern can also be converted back into for loops (but not all while loops
             follow this pattern)! Some might execute an unknown number of times (like repeatedly asking for input
             until it's correct.) */




        //ARRAYS


        /*
        Arrays let us store groups of things that are all the same type, such as multiple datapoints:
        */

        Dog[] a_whole_kennel_of_dogs; //<- the type of an array is the same as one thing in the array, with []



        /*
        When we want to use this group, we need to describe how many 'slots' we have for data:
        */

        a_whole_kennel_of_dogs = new Dog[10]; //<- we have to say how many things 'fit' in our array

        /*
        To put things in our get things out of an array, we use square brackets:
         */

        a_whole_kennel_of_dogs[0] = a_dog;
        a_whole_kennel_of_dogs[1] = new Dog("Roody", "Lab");



        /*
        We use the same notation to get things back from the array:
         */

        Dog the_second_dog = a_whole_kennel_of_dogs[1];



        /*
        We even have a special type of loop to traverse each item in this array:
        */
        for ( Dog one_dog : a_whole_kennel_of_dogs ) {
            /*
            A For-each loop has two parts, separated by a colon:
            - On the left of the colon, we put a new variable that can hold one item from the group
            - On the right of the colon, we put the group that we want to pull things from

            The loop repeats once for each thing in the array!
            */
            System.out.println(one_dog);
            one_dog = new Dog("Spot", "Dalmation"); //<- THIS WILL NOT ACTUALLY GET SAVED INTO A_WHOLE_KENNEL_OF_DOGS.
                                                    // one_dog is a Dog variable, completely separate from the kennel array.
                                                    // when we reassign it, we're "deleting" the value that was pulled from the array, 
                                                    // ...but this still isn't assigning it to the array.
            /*

              whole_kennel:
             +----+----+----+----+----+----+----+----+----+----+
             |    |    |    |    |    |    |    |    |    |    | 
             +----+----+----+----+----+----+----+----+----+----+
                    ^
                    The dog in slot two
                     \
                     \
                     \
                    +---+
                    |   | <- if I reassign what's in this box, I'm "scratching out" the arrow above and overwriting the one_dog variable,
                    +---+       losing any association with the array at all.
                   one_dog

          If you actually want to permanently change the array, you need to use a for loop like the one below
          and assign to the kennel[slot].
            */
            /*
            Remember: we will still loop through "empty" array slots. The value in one_dog will be null for each empty slot.
            */
        }



        /*
        Just like how for loops are just an abbreviation for while loops, 
        all for-each loops can be rewritten as for loops:
        */
        for ( int index=0; index<a_whole_kennel_of_dogs.length; index++) {
            Dog one_dog = a_whole_kennel_of_dogs[index];

            System.out.println(one_dog); //<- this does the same thing as the previous example!!!
        }



        /*
        Scanners


        A scanner is an object that knows how to read from an input source.

        Scanners are typically used to read from one of two things: either a File object, or the command line.

        In both cases, they have exactly the same methods:
        */

        Scanner in = new Scanner(System.in); //<- the constructor is told what it is going to read from. System.in is the terminal input.

        String next_line = in.nextLine(); //<- this will pause and wait to read one line up to a carriage return (the enter key), or one line of a file.

        /*
        One common way that these objects are used is reading "as long as" there is something left to be read:
        */

        while ( in.hasNext() ) { //<- keep reading as long as there are lines remaining. Mostly used with Files, not input.
          String next_line = in.next_line(); 
        }

    }

}

