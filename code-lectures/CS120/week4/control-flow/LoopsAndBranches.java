
/*
     Objectives for Today

     By the end of today, you will:
        * Understand how branches allow us to express diverging paths in programs.
        * Compare different types of branching in Java and explain how they differ.
        * Understand how loops allow us to express repeating concepts in programs.
        * Compare the differences between while loops and for loops.
 */

import java.time.LocalTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class LoopsAndBranches {

    public static void main(String[] args) throws InterruptedException {


        /* Last class, we had started to preview the concept of branching in our Java programs.

        We start a branch with the "if" keyword, followed by a boolean expression that we want to test, and then
        a block of instructions to run when our test passes.

        For example, say we were writing a quiz program:
         */


        int score = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("Who was the first president?");
        System.out.print("Enter your guess: ");

        String guess = in.nextLine();


        if ( guess.equals("George Washington")) {
            System.out.println("Very good!");
            score = score + 1;
        }


        /*
        We can visualize our path through the quiz program something like this:
                                                          |
                    Enter  |                              |              Enter  |
                           |                              |                     |
                          +----------------+              |                    +----------------+
                          | Is a condition |              |                    |  was guess     |
                          |     met?       |              |                    |    correct?    |
                          +----------------+              |                    +----------------+
                       Yes   |          |  No             |                 Yes   |          |  No
                   +--------------+     |                 |             +--------------+     |
                   | do something |     |                 |             | add score    |     |
                   +--------------+     |                 |             +--------------+     |
                             |         /                  |                       |         /
                              \      /                    |                        \      /
                               \   /                      |                         \   /
                                 V                        |                           V
                                 |                        |                           |
                                 v                        |                           v

        We're only going to do that block of extra instructions when the answer to the question was right, otherwise
        we'll skip them entirely and continue on.


        However, sometimes we want to do something not just when a single condition is true; we might want to create
        many different possible paths. Fortunately, our if statements give us the tools to do that!
         */


        System.out.println("What year was the U.S Constitution ratified?");
        System.out.print("Enter your guess: ");
        guess = in.nextLine();


         if ( guess.equals("1776")) {
            System.out.println("Close, but that's when the Declaration of Independence was written.");

        /*In addition to our first branch, we can create another possible path by putting "else if" and a new condition
          after our first block of instructions: */
         } else if ( guess.equals("1787") ) {
            score = score + 1;
            System.out.println("Very good!");
        /* At the end, we can also put an "else" block that will only happen if no other path was taken: */
         } else {
            System.out.println("Uh oh, you might need to go back and study!");
        }

        /* If we were to extend our visualization from before, this second quiz program would look like:



                               Enter  |
                                      |
                                     +-----------------------+
                                     |   Was guess 1776?     |
                                     +-----------------------+
                                  Yes   |          |  No
                              +--------------+    +-------------------+
                              |  Print hint  |    | Was guess correct?|
                              +--------------+    +-------------------+
                                        |              | Yes        \ No
                                         \        +---------------+    +----------------+
                                          \       |  add to score |    |  Print uh-oh.  |
                                           \      +---------------+    +----------------+
                                             \         |                 /
                                               \       |               /
                                                 \     |             /
                                                   \   |           /
                                                     \ |________ /
                                                       |
                                                       |
                                                       V

            We can keep adding as many else-ifs as we want onto the same if-elseif-elseif-elseif... chain.


            However, one important note: we will only ever go down the FIRST branch that matches!

            What time will be saved in "wakeup_time" at the end of this program:
         */

        boolean its_snowy = true;
        boolean its_a_workday = true;


        // if we normally sleep until 8AM
        LocalTime wakeup_time = LocalTime.of(8,0);

        // we might change that if....
        if ( its_snowy ) {
            //we sleep in on snow days!
            wakeup_time = LocalTime.of(10,0);
         } else if ( its_snowy && its_a_workday ) {
            //we have to wake up early to scrape off the car!
            wakeup_time = LocalTime.of(6, 30);
        } else if ( its_a_workday ) {
            //we have to get up for work!
            wakeup_time = LocalTime.of(7, 0);
        }

        System.out.println("We will wake up at: " + wakeup_time);




        /*
        This program has a bug! Even though its_snowy and its_a_workday are both true, the program is telling us to sleep
        in.


        We can fix it by reordering the branches, so that our most specific/important branch is tested first, or by using
        nested branches.

        */


        /*
        Last class, we had also looked at something very similar to branches...


        Loops let us do the same type of conditional behavior as branches, but with one important distinction; at the
        end of a loop, we go back to the top and run our test again!

        Last class, we had previewed loops using the 'while' keyword:
         */

        int seconds_left = 5;

        while ( seconds_left > 0 ) {
            System.out.println("Seconds left: " + seconds_left);
            TimeUnit.SECONDS.sleep(1);
            seconds_left = seconds_left - 1;
         }
        System.out.println("Time up!");


        /*
        We can even draw a similar diagram to our branches to show what happens when we run a loop:


                                                               |
           Enter  |           _______________                  |      Enter  |           _______________
                  |         /                 \                |             |         /                 \
                  |        v                    \              |             |        v                    \
                 +----------------+              |             |            +----------------+              |
                 | Is a condition |        +--------------+    |            |  is there      |        +--------------+
                 |     met?       |        | do something |    |            |    time left?  |        | subtract one |
                 +----------------+        +--------------+    |            +----------------+        +--------------+
                   No |       | Yes              ^             |              No |       | Yes              ^
                      |       |                  |             |                 |       |                  |
                      |        \ ______________ /              |                 |        \ ______________ /
                      v                                        |                 v


              It's very common for us to know exactly how many times we want a loop to run, such as when we're counting
              or checking each thing in a group with a known size.

              Java gives us a shortcut to write a loop that counts this way, called a "for" loop:
         */

        for ( int i=0;   i<10;   i=i+1 ) {

            /*
                A for loop declaration has three parts, each separated by a semicolon:
                     - declaring a new variable
                     - testing a boolean expression
                     - performing some "incrementor" statement to update the variable
             */
            System.out.println("The value of 'i' is now: " + i);

        }

/*       
      Any time we have a for loop, we can make an equivalent while loop by:
           - moving the variable declaration above the loop
           - testing the exact same boolean expression
           - moving the "incrementor" statement to the bottom of the loop
         */

        int i=0;
        while ( i<10 ) {
            System.out.println("The value of 'i' is now: " + i);
            i=i+1;
        }


        /*
        NOTE: We cannot always go from while to for:
             - We might have multiple conditions we are testing/incrementing
             - We might have variables that aren't 'incrementing' each step!

       While loops are more flexible than for loops, but it can often be easier to think about your problem 
       in terms of counting, which is a natural fit for for loops.
        */



        /*
         We can even combine our loops and if statements to build up complex logic,
         like giving a user three guesses to get the right answer!
        */


        for (int guess_count=0;  guess_count < 3;   guess_count=guess_count+1  ) {

            System.out.println("What is the name of the branching keyword in Java?");
            System.out.print("Enter your guess: ");
            guess = in.nextLine();

            if ( guess.equals("if") ) {
               guess_count = 3; //<- they don't need to guess again, make ourselves the loop
               score = score + 1;
               System.out.println("Very good!");
            } else if ( guess_count < 2 ) {
               System.out.println("Try again!");
            } else {
               System.out.println("Sorry, the correct answer is if!");
            }
        }



        

        




    }

 }