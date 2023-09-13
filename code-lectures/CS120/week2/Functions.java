
/*
Objectives for Today

By the end of today, you will:
  * Define how functions organize statements into reusable groups.
  * Identify the syntax for declaring and calling functions.
  * Understand how a single value can be returned from a function and used as an expression where
       the function was called.
  * Describe how input parameters allow functions to work for a variety of different values.
 */


/*
Vocabulary for Today

Function - A function is a named group of statements. In order to instruct a program to follow all the statements in
            a function, we "call" that function by putting its name followed by parentheses.


Input Parameters - Functions may accept one or more values at the time they are called. These function 'arguments' are
                    each assigned to a special variable called a 'parameter' immediately at the top of the function.


Returned Values - A function may optionally be declared to return a single type of variable. When a function has a
                    return value, a "call" of that function also acts as an expression of that type.

*/


public class Functions {


  public static void main(String[] args) {

        /*
        Last class, we learned that all programs are constructed from a sequence of statements that are followed top
        to bottom. For example, I might write a series of statements to help me find a square root. The way we can
        find a square root 'by hand' would be to:

           - Save an original value for some starting number 'x'
           - Save a first 'guess' for the square root, equal to half of x.
           - Save a calculated 'error' based on how incorrect the square root is when re-multiplied.
           - As long as that error is too high:
                          - Save a new guess, which is the average of the old guess with the original number divided by the guess.
                          - Save a new error by calculating it with the same formula from before.

        */


    double x = 16;
    double guess_root = x / 2;

    double error = (guess_root * guess_root) - x;
    while ( Math.abs(error) > 0.0000001) {
        guess_root = ( guess_root + (x / guess_root) ) /2;
        error = (guess_root * guess_root ) - x;
    }

    System.out.println("The square root of " + x + " is approximately " + guess_root);

     /* We've now written a program that solves a very important task -- square roots are used constantly in math to
          do everything from calculating distances and areas to estimating return on investments.


          However, there's a problem... when a program needs a square root, it often needs a LOT of square roots.
          Especially in graphics programming, these square root lines of code might be used hundreds of times to help
          determine which elements are touching, what the user was clicking, and more...

          We don't want to cut and paste this code every time.

          Not every square root is going to be calulcated on the same number. We want the flexibility to not have to keep
          changing bits of our code. It would also make it very hard to read what anything else was doing, because
          these repeated calculations would clutter every part of our code.

           If we did that and decided to make a change later, we would have to update *every single place* that we
          wrote these lines.


          This is where functions come in -- a function lets us give this group of instructions a name we can use
          as shorthand for all these steps! Let's scroll down and make a new function called findSquareRoot. */

        /*
            Once we have declared our function "findSquareRoot" below, let us compile and run our code.

            Notice: this code isn't doing anything yet. Even if we add print statements to
            "findSquareRoot", those print statements never show up.

             A function declaration DESCRIBES instructions, but it doesn't perform them. The only code that runs when the
            program starts is one special function (the 'main' function.) Much like you need to use a recipe to produce
            an edible meal, you need to call your function to get a usable result. To call our function, we put its name,
            followed by parentheses:
        */

          findSquareRoot();


        //However, we have a problem.... We can't actually use any of the variables from our function:
          //System.out.println(guess); //<- "guess" doesn't exist here!!!

        /* This is because every variable has a 'scope' -- it only exists until the end of the block
        where it was created. For local function variables, this means they stop existing once
        the function is over.

        If we want to save our 'final answer', we need to store it in a newly named variable:
         */

          double answer = findSquareRoot(); // <- remember how we said our function 'returns' a double as a final answer?
                                           // This means we can use the function the same way we use any other double
                                           // expression and assign it to a double variable.


          System.out.println("When we called the function, we got back: " + answer);



        /*
        This is great! We can now find the square root of 16 as many times as we want, in just a single line!


        However, what if we wanted to make our function more general. Sometimes I might want the square root of other
        numbers, like 4, or 25, or literally anything else I can express on a computer. It would be really inefficient
        to make a different function for each of these.


        However, if we analyze our function... what parts of our function really need to change if we're using a different
        number?


        I could express all those differences just by assigning a different value to "x" -- everything else stays the
        same, no matter what number we're using. This means we can express our function with an "input parameter".
        Let's make a new version of findSquareRoot that accepts one parameter.


        We need to be mindful of parameters when we call a function. A function's signature is like a conditional
        promise that says "If you give me these details, I'll give you back this kind of answer." We need to fulfill
        our end of the promise:
        */


          double root_of_twentyfive = squareRootVersionTwo(25, 0.000001);
        System.out.println("The root of 25 is " + root_of_twentyfive);


          double root_of_four = squareRootVersionTwo(4, 0.000001);
        System.out.println("The root of 4 is " + root_of_four);



        System.out.println("The root of 100 is " + squareRootVersionTwo(100, 0.0000001));


        /*
        Another way to think about this is that, by the time our code runs, "original_number" needs to have an actual
        value, or our instructions make no sense. I can't divide "original_number" if it's not a number yet when my code
        runs. Putting a numeric expression with a value in the parentheses when we call the function ensures that
        something gets assigned to original_number.


        Functions are a great way to create reusable code. In fact, Java ships with thousands of pre-written functions
        by default. For example, many math functions exist with names that start with Math:

        */

        root_of_twentyfive = Math.sqrt(25);  // Math.sqrt does the same thing as our findSquareRoot!
        int absolute_value = Math.abs(-7);   // Math.abs finds the "absolute_value" of a number.
        int higher_number  = Math.max(3, 5); // Returns the greater of two numbers.


        /*
        Even System.out.println() is a function that takes one input parameter: a string that we want to display.


        This is the most powerful feature of functions -- they allow us to perform tasks that we don't even understand,
        as long as we can identify the name of the function:


       Start of program
              |
              v
        normal instructions
              |
              v                           +------------------+
         function call------------------> |     function     |   (Note: this means our code might execute 'out of
                      input parameters    +------------------+    order' compared to the visual ordering of statements
                         copied over                |                in the file -- we 'jump' to the function, then
                                                    |                'jump' back!)
       [usually assigned  <------------------------ +
         to a variable]          returned value
              |                   copied back
              v
     original code continues

        */
  }


    /*
    If I wanted to name those instructions "findSquareRoot", I would do so like this:
     */


  public static double findSquareRoot() {

        /*
        Notice: I did this *outside* the main() method -- each function is its own isolated block of instructions!


        We wrote a lot of words, but they follow a simple pattern:

            - the first word is always either public or private. This "visibility keyword" determines whether we can
               only use this function in this file, or if we want it to be usable everywhere in our program.

           - the second word 'static' means our function isn't related to an object. We don't know what objects are yet,
              so all our functions will be static for now.   

           - the third word is a type. Here, we're declaring what type our "final answer" will be when we're done with
               these instructions. In this case, a square root is a decimal number, so we return a double. When we *don't*
               want to give anything back, we can put the word 'void' to say that nothing gets returned.


           - Finally, we have the name we want to give the function. This can be anything that starts with a letter, but
           we've chosen "findSquareRoot".


           After this we have parentheses, and then a pair of curly braces -- curly braces anywhere in Java create a 
           "block". A block lets us attach a group of statements in a single location.


           Now, we can write all those instructions from before:
        */

        double x = 16;
        double guess = x / 2;
        double error = x - (guess * guess);
        while ( Math.abs(error) > 0.000001 ) {
            guess = (guess + (x / guess)) / 2;
            error = x - (guess * guess);
        }

        //System.out.println("INSIDE MY FUNCTION, square root is: " + guess_root);


        /*
        However, unlike last time, we don't want to print out our answer here. Functions only rarely do printing
        directly. Instead, we want to give back the final answer to the code that asked for these instructions.
        We do that with a "return" statement:
        */


        return guess;   // <- this says that "guess" is my final answer
        //Note: the type of the variable "guess" matches the return type when I declared my function!!

  }



  public static double squareRootVersionTwo( double original_number, double precision ) {

        /*
        Our function's "signature" (all the details we declared it with) looks almost identical to last time, but we've
        added one extra detail: we've put two words inside the parentheses.

        The words we've put look identical to a variable declaration! We've added a type of data, then a variable name.

        When we declare a variable in the parentheses of a function, we're creating a 'parameter' -- this special type
        of variable says "You need to give me this information in order for me to give you back an answer." However,
        inside the function we can now use that variable exactly like any other, even though we don't necessarily know
        what's in it yet:

        */
        double x = original_number; // <- I'm using "original_number" here, even though it could be anything!
        double guess_root = x / 2;
        double error = x - (guess_root * guess_root);
        while ( Math.abs(error) > precision ) {
            guess_root = (guess_root + (x / guess_root)) / 2;
            error = x - (guess_root * guess_root);
        }

         return guess_root;
  }



}