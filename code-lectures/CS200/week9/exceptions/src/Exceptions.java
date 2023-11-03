
/*
Objectives for Today

By the end of today, you will:
   * Describe common Java exceptions and code that can produce them.
   * Practice using try-catch to gracefully handle exceptions.
   * Understand how try-catch affects control flow.
   * Identify the difference between checked and unchecked exceptions.
 */


import java.util.Scanner;

public class Exceptions {

    public static void main(String[] args) {

        /*
        Since the very first weeks we learned Java, we've had situations where our programs may crash:
         */

        //System.out.println("Before converting text to a number:");

        //int some_num = Integer.parseInt("apple");//<- after this, the program panics and does not know how to continue!

        //System.out.println("After conversion!");  //<- this line doesn't show, our program crashed during RUNTIME! (not compilation)

        /*
        When this happens, we receive a crash report that includes a call stack and an error message:

        Exception in thread "main" java.lang.NumberFormatException: For input string: "apple"
	         at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
	         at java.base/java.lang.Integer.parseInt(Integer.java:668)
	         at java.base/java.lang.Integer.parseInt(Integer.java:786)
	         at Exceptions.main(Exceptions.java:20)

        Today, I want to focus on one particular part of this message -- at the very beginning, we are told that the
        type of error in our example is a java.lang.NumberFormatException.

        Notice how this identifier looks very similar to what we would put next to an import statement? That's because
        in Java, an error that crashes a program is itself an object! These Exception objects hold all the details
        about what went wrong and where.

        Normally, this knowledge would not be too useful on its own, but it turns out we can take advantage of a
        special kind of code block to 'capture' exceptions and read information about them.


        Whenever we're writing a block of code that can fail, we can tell Java to be prepared for an error by writing
        a try-catch block. A try-catch block has two parts:
               - the group of statements you want Java to "try" to do
               - the types of errors you are prepared to "catch" and resolve yourself

         Take our example before, I can rewrite my parsing code inside a try-catch block:
         */
        int chosen_number;

        try { //<- here I'm telling java anything inside the curly braces may fail.
            Scanner user_input = new Scanner(System.in);
            System.out.print("Enter a number: ");
            String entered_text = user_input.nextLine();
            chosen_number = Integer.parseInt(entered_text);
            System.out.println("You chose: " + chosen_number);
        } /* after a "try" block, we must immediately follow with at least one "catch" block that describes a type of error:*/
        catch ( NumberFormatException e ) { //<-= this is declaring a variable with our error type!
            System.out.println("Uh oh, I wasn't able to understand that number!");
            System.out.println("The error message says:" + e.getMessage());
            System.out.println("Setting your number to 0 by default!");
            chosen_number = 0; //<- normally in a catch, we "fix" the error by doing a default version of what we were trying to accomplish
        }
        /*
        The power of try-catch is that it allows us to take something that would normally crash the program, and make it
        just another conditional block of behavior.

            You can think of a catch block almost like an "if-else" statement -- it declares a type of error object
            that it is watching for. If an unrecoverable error happens, instead of crashing the program, it jumps into
            this catch block.

            The object that we declare in the catch's parentheses is an object we can use like any other. It has methods
            to get the message, show the call stack of where the error occurred, or otherwise gather useful information
            for debugging why the problem occurred.

         */

        /*
        Notice that when I run my program now, even if I type a word that cannot be converted to a number, the program
        does not crash and show me a crash report. Instead, it runs the code in the catch, and then keeps going.

        However, when the catch happens, something is skipped -- notice that we don't see the "You chose:" line printed
        out in our program output.

        That's because a try is a single atomic block of code. If an error happens anywhere in the try, the rest of the
        try block is immediately aborted, and we skip everything to find a relevant catch. It's almost like we have
        an invisible "go to" after every single instruction in our block:

            1  Scanner user_input = new Scanner(System.in);       when NumberFormatException goto 7
            2  System.out.print("Enter a number: ");              when NumberFormatException goto 7
            3  String entered_text = user_input.nextLine();       when NumberFormatException goto 7
            4  chosen_number = Integer.parseInt(entered_text);    when NumberFormatException goto 7
            5  System.out.println("You chose: " + chosen_number); when NumberFormatException goto 7
            6  goto 9;
            7  NumberFormatException e = handledException();
            8  System.out.prinlnt("Uh oh, something went wrong...");
            9 //rest of program

         This can make it very difficult to reason about what happens in a try block. *Especially* because exceptions
         "bubble up" from functions that they occur in. Imagine we rewrote our number code into a separate guessing
         class:

         */


        NumberGuesser guesser =  new NumberGuesser();
        try {
            guesser.collectNumber();
            int result = guesser.showOneHigher();
            System.out.println("One higher than your number is: " + result);
        } catch ( NumberFormatException e) {
            System.out.println("Number guesser failed! You put in something that wasn't a number!");
        }

        /*
        This makes intuitive sense, because when our program crashes, normally it has to exit every single function
        it was in. However, it does mean we should try to handle exceptions as locally as possible -- suddenly finding
        yourself in a completely different part of the program can cause some very unintended consequences:
         */

        int result = guesser.showOneHigher();
        System.out.println("Outside the try, higher is: " + result);

        /*
        Here I assume my guesser has already collected a number. However, if my try-catch caught a number format error,
        then Guesser's this.number never saved a value.... so it's still null here, causing a NullPointerException when
        I try to use it!!

        If I had handled the NumberFormatException directly inside Guesser, I could have set number in a way that
        avoided creating this landmine for later.
         */


        /*
        One additional detail about try-catch blocks that's important to know: a try-catch can catch more than one
        problem! For example, let's say we had a block of code like this:
         */

        try {
            String input = "50,50,5a,50";
            String[] parts = input.split(",");
            String sixth_grade = parts[5];
            int grade = Integer.parseInt(sixth_grade);
            /*
            There are multiple things that could go wrong here: ArrayIndexOutOfBoundsExceptions, NumberFormatExceptions,
            maybe even a NullPointerException. I can be prepared to handle any of them by writing multiple catch blocks:
             */
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Too few grades provided to find the sixth! Cannot perform the calculation!");
        } catch ( NumberFormatException e ) {
            System.out.println("A grade entered was not provided as a number: " + e.getMessage());
        } catch ( NullPointerException e) {
            System.out.println("Tried using the input but it was still null!");
        } catch ( Exception e ) {
            System.out.println("Something unknown went wrong... :( ");
        }

        /*
        One important note about multiple catch blocks: Much like a chain of if-elseif-elseif-elseif... statements,
        only the FIRST error that is thrown will be handled (because we skip the rest of the try block), and
        only the FIRST catch that applies will be run.

         Also notice the type caught in that last catch block: Exception.
         Catch blocks are polymorphic! Exception is a base class, and every specific type of exception is a subtype
          that inherits it. Some are even subtypes of another subtype:

               Exception
                 + RuntimeException
                    + IllegalArgumentException
                        + NumberFormatException
                 + IOException
                    + FileNotFoundException
                    + FileLockException
                    + AccessDeniedException

               When I write a catch for "Exception", it will catch *any* type of exception, even if the actual
               exception is a subtype. (A Exception variable can hold any subtype because of polymorphism!)

               Note: This means if you are including Exception (or any more general supertype 'category' exceptions),
               you should always handle it *last*. Otherwise, you'll never go into any of the other catch blocks.
         */


        /*
        Some exceptions are "predictable". Next week, we will be learning all about ways we can accept input to
        Java programs. Activities like reading files are rife with common errors that Java knows programmers should
        expect and handle.

        These are called "checked" exceptions -- in our Exception type tree, any exception that does not inherit from
        RuntimeException counts as "checked". When one of these exceptions occurs, Java forces you to either write
        a try-catch, or include the exception name in the method signature. Let's write a FilePreview class
        to see an example of this!
         */

        FilePreview p = new FilePreview("somefile.txt");
        try {
            p.readAllLines();  //<- this function can throw a FileNotFoundException, so it must be caught or declared.
        } catch ( Exception e ) {
            throw new RuntimeException("readAllLines() didn't work"); //<- a fun trick for converting a checked exception
            // into a runtime exception.
        }

        try {
            p.addPositiveNumbers(4, -5);
        } catch( UsedItWrongException e ) {
            System.out.println("One of the numbers provided to addPositiveNumbers was negative");
        }


        /*
        Throwing exceptions should be used sparingly. This tool mostly exists for when you are designing code that will
        be used by someone else, where it makes no sense to continue because someone is using your code VERY WRONGLY
        and where crashing is an acceptable outcome.

         Checked exceptions represent foreseeable errors that can be expected to happen, so Java forces programmers
         to write solutions for them, while runtime exceptions represent unrecoverable problems in program logic.

         Writing exception handling code far from either type of error can make it very difficult to follow the
         flow of a program, so always try to handle an exception as close to where the error occurs as possible!

         */

    }
}
