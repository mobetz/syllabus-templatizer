
/*
     Objectives for Today

     By the end of today, you will:

        * Understand the difference between statically typed and dynamically typed languages.
        * Know how to declare and assign variables in Java.
        * Identify the primitive types in Java.
        * Understand the difference between string and numeric representations of data.
 */


/*  Vocabulary for the Day:

  Statement - A statement is a single instruction that we want to give to the computer. We mark the end of a
       statement with a semi-colon, and typically write each statement on its own line.

  Expression - An expression is any computer code that "turns into" to a single value when the code is complete.
       'Literal' values (numbers, text, and other data we write out explicitly) are the simplest expressions.
       'Return' values (things we receive back from functions) are also expressions!


  Variable - A variable is a named space we have reserved for a specific type of data.

  Declaration - The act of 'announcing' the existence of data or instructions to Java. Declarations must include
      syntax describing the type of thing you are creating, and the name you are giving that thing.

  Assignment - The act of saving a value expression into a variable. We use the (equals sign: =) when we want to
      perform assignment.

*/


public class DeclarationAssignment {

   public static void main(String[] args) {


        /*

        When we write a computer program, we are writing a series of instructions we want the computer to follow. 
        Much like writing instructions to another human, we need to break these instructions down into a series 
        of complete thoughts that should be followed in order. Each one of these complete thoughts is called a
        "statement".


        While in verbal communication there are nearly infinite types of statements that you could describe, the
        tricky thing about computer programming is that the entire solution to our problem must be reduced to just
        a few limited kinds of statements the computer knows how to handle:


           * Create a space to store some information called __<name>__. 
           * Save __<info>__ into the space called ___<name>___. 

           * Use the other instructions called __<name>___.
           * If __<something is true>__, then: ___<do this stuff>___.
           * As long as __<something is true>__, repeatedly: ___<do this stuff>____.

        When you are writing a program, a good first step is using these sentence templates to figure out a verbal
        description of the solution to your problem, then figuring out how to translate *that* into Java.


        Today, we are going to focus on those first two types of statements -- creating spaces to store information,
        and saving something into them. When we want to save information for later in a computer program, we use a 
        "variable".


        Back in Python, when you wanted to create a new variable, you likely wrote code like this:
            first_name = "Matthew"


            This code saves the text "Matthew" into the variable first_name.


           You were able to declare a new variable just by assigning a new value to it, even if Python had never
           heard of that value before.


           Let's see what happens when we try to do the same thing in Java:
        */

      //first_name = "Matthew";


        /*

        Note: Just like how a sentence ends with a period, in Java a statement ends with a semi-colon!


        When we run this code, we get an error saying:
              java: cannot find symbol
              symbol: variable first_name


           This is telling us that Java is looking for the "symbol" for a variable called first_name. However, we
           never declared to Java that such a variable exists.

           In Java, we need to declare that a variable exists before the first time we use it. To declare that
           we wanted to use first_name, we would write something like this:
         */

      String first_name;


/*
        This declaration statement has two parts:
            First, we write what type of thing we're saving (in this case, a string of text.)
            Second, we give the space we are reserving a name (in this case, "first_name".)



        Once we do that, we've reserved a small space that's just the right size for some text.
        You can think of declaration like creating an empty box with a label on it:


           +-----+
           |     |
           |     |
           +-----+
           first_name

        We can then reference that variable later to assign a value to it, exactly the way we did in Python:
         */
        first_name = "Matthew";



        /*

        This act of assignment is like putting a value into the box:

           +-----+
           |     |
           |    <--- "Matthew"
           +-----+
           first_name



        Once we have declared a variable once, we can assign new values to it as many times as we want:
         */


          first_name = "Logan";
          first_name = "Serah";
          first_name = "Caleb";


        /*

        Note: This erases whatever was in the box before:

           +-----+
           |     |
           |    <--- "Matthew"
           +-----+
           first_name


           +-----+
           |     |
           |    <--- ---------  "Logan"
           +-----+
           first_name


           +-----+
           |     |
           |    <--- ---------  ---------   "Serah"
           +-----+
           first_name

      */

        System.out.println("The value saved in first_name is: " + first_name);


        /*
        If we want a second box, we need to make a second variable:
        */
        String second_name = "John"; //<- now we can save a second name without deleting the first.


        /* What's going to happen if I do this? */
        //first_name = 456;  //DeclarationAssignment.java:186: error: incompatible types: int cannot be converted to String



        /*
        Remember, we told Java that "first_name" should be a String. If we try to assign a number into it, Java won't
        have the right amount of space saved. This means our variables must always hold the same type of data from the
        time they are declared to the time they are cleaned up. 



        You can think of this like each type of box we 'declare' having a specific shape:

                 +-----+             +
                 |     |           /   \
                 |     |          /     \
                 +-----+         +-------+
               first_name       some_number
                 String           integer


        You can't fit a square peg into a round hole, and likewise you can't fit a numeric expression into a String variable!
 

        We call languages that force this restriction on you "statically typed". Languages like Python where you were
        allowed to write these kinds of programs are called "dynamically typed". Though it feels inconvenient now,
        static typing helps you learn how to write much safer programs!

        */


        /*
        If you are trying to save space, declaration and assignment can even be performed at the same time:
         */
        String last_name     = "Obetz";


        /* Even when we do this, we should only put that declaration keyword the FIRST TIME: */
        last_name = "Moralez";
        last_name = "McKinnon";
        last_name = "Smith";


        /*

        Declaration always has to happen before assignment (even if just earlier on the same line), but declaration
        only needs to happen once! Remember -- any time you put a type keyword like String or int, you're trying to make 
        a brand new box. If we're reusing the same name, we're reusing the same box!

        */


        /*
        So what types are available? By default, it's mostly text and numbers:
         */

        char a_letter;      //stores two bytes (one character). Can represent letters or some common symbols 
                                 //("Unicode" translates your letter or symbol into a number)...
        String text;        //not technically a base type (but honorary mention) -- 
                                // stores many characters back to back. Identified by double quotes.

        boolean a_bit;       //stores one bit of information (like the value 0 or 1). 
                                 //Typically used to represent "true" or "false"
        byte a_byte;        //stores 8 bits/one byte numerically. Can represent values from -128 to 127.
        short small_num;      //stores two bytes of numeric data. -32768 to 32767.
        int medium_num;      //stores four bytes of numeric data. -2147483648 to 2147483647. This is the sweet spot we normally use as programmers!!
        long big_num;       //stores eight bytes of numeric data. -9223372036854775808 to 9223372036854775807.


        float small_dec;   //stores four bytes of numeric data *with a decimal point*. Enough for ~7 digits of precision.
        double big_dec;     //stores eight bytes of numeric data *with a decimal point* Enough for ~16 digits of precision.



        /*
        Fun Fact:
           Floating point numbers are called 'floating' because they store their information as details about a 
           'place', and the most significant digits from that place:
        
              9.76349  x 10 ^ -6


        You can think of this almost like storing numbers in scientific notation! Anything past the point of 
        precision will get 'truncated' / rounded off.

         */


        /* All these types of values above are called our "primitive types" (sometimes also called the "base types".) They're the
        most basic types of data we have access to in Java. However, they are not the only types of variables that exist.

        Next week, we will learn about "object types". For now, you just need to know that they can be declared
        in exactly the same way as any other type:
         */

        //File    some_file;
        //Scanner some_input_readers;
        //Button  a_clickable_button;




        // Whenever we have a variable. We can store a literal value into it, but we can also store any 'expression'
        // that will ultimately give us a value -- the computer will do the work first, then save the final answer.

        int a_num = 3;


        int the_number_four = a_num + 1;

        // The computer will find the box labeled "a_num", pull the value out of it, do the math, and then
        // save that final result into "the_number_four".


        a_num = 100;

        System.out.println("Even after changing 'a_num', 'the_number_four' is: " + the_number_four);

        /*
         Remember: we only had enough 'space' in our the_number_four for the integer 'final answer' of our math.
         This means that even though 3 is 'crossed off' later, the 4 doesn't change (the_number_four doesn't "remember"
         it was related to a_num):

          +-----+
          |     |
          |    <---   ////3//// 100
          +-----+
          a_num

            +-----+
            |     |
            |    <---  4
            +-----+
            the_number_four

            */


        /*
        It is important to note that we can represent some types of information with multiple types of data.

        For instance, we can represent numbers both numerically and textually.
         */
        char the_letter_four = '4';

        // NOTE: in Python, ' and  " were interchangeable! In Java, single quotes are for chars (single letters as numbers),
       // and " are for strings (whole lines of text).
       

        // this character looks like a 4, but a char actually stores a number that represents a text character.
        // In this case, '4' will be saved as 52, the number we use to represent "4"!

        // Say we wanted to add one our 'letter' 4: 

        int should_be_five = 1 + the_letter_four;
        System.out.println("1 + '4' = " + should_be_five);



        /* When we have text with more than one digit, we'll be using a String instead of a char:  */
        String the_word_one_hundred_twelve = "112";
        //int should_be_one_hundred_thirteen = the_word_one_hundred_twelve + 1;  // incompatible types: String cannot be converted to int

        String should_be_one_hundred_thirteen = the_word_one_hundred_twelve + 1;
        System.out.println("\"112\" + 1 = " + should_be_one_hundred_thirteen);

         //with text, 'addition' is actually an operation called "concatenation" -- we 'squish' both bits of text end to end.


         String greeting = "Hello, " + first_name;

   }

}