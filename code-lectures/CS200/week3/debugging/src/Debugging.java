
/*

  Objectives for Today

By the end of today, you will:
   * Define "debugging", and identify how debugging helps us understand what steps
       programs perform when they execute.
   * Describe how breakpoints allow us to track the path through a program.
   * Practice debugging in our favorite IDE.
   * Look at how we can write unit tests to check that our code continues to work as we make changes.
 */


/* Vocabulary for the Day

Debugger - A special version of the Java runtime that allows us to pause programs in the middle of execution
      to observe data.

Breakpoint - A marker we have placed in our code to indicate that we wish to pause the program.
 */




public class Debugging {


    public static void main(String[] args) {

        /*
        Today, we are going to talk about some tools we can use to help check our program and test that it's working
        correctly.

         Right now, the main tool we have available is "print debugging", where we run our entire program
         print out values and see what they are during different parts of the program.

            However, this has a few limitations:
                1) Runtime errors can be hard to read or understand.
                2) We need to recognize where we should be looking, and what variables we need to inspect before execution.


         Sometimes, when a program calls multiple functions in a row, a mistake in the first function doesn't recognizably
         change behavior until the last function. We might even go down a completely different set of functions than we
         expected, and adding many print statements can make our code more cumbersome.


         This can make it difficult to trace all the steps a computer is actually taking.  For example:
         */

        GreetingFormatter formatter = new GreetingFormatter(" john");
        System.out.println(formatter.prettify_greeting());


        /*
        With print debugging we can slowly narrow down where an issue is, but it takes many tries to find the right
        variable and function where an error is occurring.

        We could figure out what's going on by putting a bunch of print statements, but today, we're going to try out
        a new tool: the Java Debugger. A "debugger" is a special piece of software that lets us 'pause' code halfway
        through an execution, and see the value of all our variables at each step.

        Programs execute pretty fast -- to be able to pause halfway through, we have to mark the points where we want
        to pause before we even start running the program. We can do this by setting a "breakpoint", a mark that states
        we want to stop on a certain line of a program.


        To set a breakpoint, we can click in the 'gutter' on the left of our IDE, right next to the line number.
        When we click here, we'll see a big red circle. This tells us a breakpoint has been set.

        Let's try it out!

        Once we click the bug icon, we see the Debug panel open where we would normally see the Java console, and the
        line where we're currently paused is highlighted.

        This panel has two parts:
        - On the left, it shows us what method we are currently in, and how we got there.
        - On the right, it shows all the variables that can currently be seen by the program on the current line of code,
           and their values.

        (Does the thing on the left remind you of anything? Pay close attention to it as we go into more methods.)
        (Is there anything missing that you'd expect?)
        One important note: a breakpoint pauses *before* a line happens. This is why the result is not yet a variable
        we can see.

        Once we're paused, we get a few helpful buttons up in the corner of the debug panel:
        - The bent arrow "step over" line takes us to the next line *in the same method*, skipping over any other methods.
        - The down arrow "step into" line takes us to the next instruction that runs. If a method is being called, we go
            "into" that method.
        - The up arrow "step out of" line immediately takes us back to wherever the current function was called.
        - The play button on the left will unpause the whole program until our next breakpoint.

        We might have been tempted to think something is wrong with our "capitalize_first_letter" method. After all,
        the problem we see is that the first letter in the resulting string isn't capitalized. However, once we debug,
        we see that the capitalize_first_letter function isn't executing at all.

        This shows us an example of how debugging can help us go step by step and figure out where a mistake was made.


        In addition to debugging live programs step by step, sometimes we also want to code that can test our code.

        This can give us insight on how functions work (for instance, if we give it several different inputs, we can
        test all of them without needing to run the program 20+ times for all our different examples.) It can also
        give us confidence that changes in one part of the code haven't broken any other part of the code.

        To do this type of testing, we write what are called unit tests.

        Unit tests are special classes that don't exist as a normal part of our program. Instead, they provide a
        battery of static methods we can call to see that some example problem that runs just a small part of our code
        does exactly what we would expect with known inputs and outputs.

        In an IDE, we usually have a button to create these methods automatically.


         */
    }
}
