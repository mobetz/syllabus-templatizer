
/*
     Objectives for Today

     By the end of today, you will:
        * Understand the boolean data type and how it allows us to write programs that consider truthiness.
        * Build truth tables for common boolean operators.
        * Recognize the order of operations for boolean operators.
        * Compare different types of conditional behavior in Java and explain how they differ.
 */


/*
Vocabulary for the Day

  Boolean - A boolean is a type of data that specializes in storing the 'truthiness' of certain conditions. Boolean data
      can only have one of two possible values: true or false.

  Boolean operator - A boolean operator is a special symbol that lets us combine two booleans together. The three main
      boolean operators are NOT, AND, and OR.


  Conditional logic - We say instructions in a computer program are "conditional" if they only happen sometimes, (when
      a certain 'condition' is met.) Branches (if-statements) and Loops (for-loops and while-loops) are the most common
      types of conditional logic.


  Control flow - "Control flow" is a term we use to talk about any instructions we can write that change the order
      other instructions run. Conditional logic, functions, and exception handling are all control flow!
 
 */

import java.io.File;
import java.lang.Thread;

public class Booleans {
	

    public static void main(String[] args) throws Exception{

        /* So far, we've learned about a few important types of data:
            - numeric types, that let us perform calculations and store numbers in a space efficient way.
            - the String type, which lets us store, process, and display text.
            - Object types, which store all the data and functions related to a particular concept.

         This class, we are going to learn how to use one more type of data: boolean data. Booleans are declared
         similarly to all the other base types we've seen this semester:
         */

        int x = 4;
        String name = "Jim";



        /*
        A boolean stores information about the answer to a yes/no question, like "Is it sunny out today?"
           - If the answer is yes, we save the value true.
           - If the answer is no, then we save the value false.

           */


        boolean is_today_sunny;
        is_today_sunny = false; //<- no, it is not sunny

        /*
        Some of the objects we've already worked with this semester have useful boolean methods we can access to
        ask some common questions!
         */

        String a_word = "apple";
        boolean ends_with_ple = a_word.endsWith("ple");
        System.out.println("Does " + a_word + " end with 'ple'?: " + ends_with_ple);



        File a_file = new File("some_file_that_doesnt_exist.txt");
        boolean does_file_exist = a_file.exists();
        System.out.println("Does " + a_file.getPath() + " exist?: " + does_file_exist);




        /* Sometimes, we want to ask yes/no questions that have more than one part.


        For a rainbow to appear, we need weather that is both sunny AND ALSO rainy. We can ask these questions using
        special symbols called "boolean operators", which allow us to chain together simpler questions:
         */

        boolean is_today_rainy = true;

        boolean will_rainbow_appear = is_today_sunny && is_today_rainy;
        //will_rainbow_appear will only be true if is_today_sunny is true AND is_today_rainy is also true.


        System.out.println("Sunny: " + is_today_sunny + " Rainy: "  + is_today_rainy + 
         ", Will rainbow appear? " + will_rainbow_appear);




        /* There are three boolean operators we will talk about during this class: AND, OR, and NOT.

           For each boolean operator, we can create a "truth table". A truth table shows all possible combinations of
           inputs for a given boolean operator, and describes what the output will be.


           AND is the operator we use when we want to test if multiple conditions are ALL met. We express AND in Java
           using a double ampersand (&&).


                                                  Y
                             AND (&&) |   True    |   False   |
                                 -----+-----------+-----------+
                                 True |   True    |   False   |
                            X   -------------------------------
                                False |   False   |   False   |
                               --------------------------------



           OR is the operator we use when we want to test if ANY condition is met. We express OR in Java
           using a double pipe (||).



                                                  Y
                              OR (||) |   True    |   False   |
                                 -----+-----------+-----------+
                                 True |   True    |    True   |
                            X   -------------------------------
                                False |   True    |   False   |
                             ----------------------------------
                                   
                                   

           NOT is the operator we use when we want to test if a single condition is UNMET. We express NOT in Java
           using an exclamation point (!).   


                                    NOT (!)  |  Result   |
                                        -----+-----------+
                                        True |  False    |
                                  X     -----+-----------+
                                       False |   True    |
                                             +-----------+



             In addition to our three boolean operators, we also have a few operators that help us compare things and
             get a boolean answer. We call these the 'relational operators'.

              The relational operators we'll use in this class are:


                     ==                  (equality) - the equality operator tests if the things on both sides are the
                                                      same. For example: (2 + 2) == 4 will evaluate to true.
                                                      NOTE: for objects, we need to use the .equals() function instead
                     !=                (inequality) - the inequality operator tests if both sides are NOT the same.




                      >              (greater than)
                      <                 (less than)
                     >=  (greater than or equal to)
                     <=     (less than or equal to) - the four numeric relational operators test whether numbers on both
                                                      sides follow their respective mathematical property. For example:
                                                        4 < 5 will be true, but 4 >= 5 will be false.



             One important feature of boolean operators is that, just like with arithmetic operators like + and *,
             we can string multiple pairs together, and they even have their own order of operations!


                1) Parentheses ( ).
                2) NOT.
                3) relational operators like >, <, ==, and !=; left to right.
                4) AND, left to right.
                5) OR, left to right.


             Since parentheses are the highest precedence, we can use them the same way that we use them in math, to
             group things together to be done first.
         */



        /* Let's test out how well we understand our boolean operators and order of operations with a poll question:
             Will the "is_this_true" variable below be true or false?
         */
        boolean is_this_true = true && !true || !false == !(false || !false);
        /*
                is_this_true =  true && !true || !false == !(false || true)
                is_this_true =  true && !true || !false == !true
                is_this_true =  true && false || true == false
                is_this_true =  true && false || false
                is_this_true =  false || false
                is_this_true =  false


        */

        System.out.println("The answer is: " + is_this_true);



        /*
        Recording conditions is great, but the real power of booleans comes from their ability to change the way our
        programs execute. We have a few special statements we will be learning about this week that use a boolean
        expression to change whether parts of our program run at all.


        The two main types of conditional statements we have are loops and branches. Both of these statements are
        written similarly:



        KEYWORD ( boolean_expression ) {
              //stuff that only happens conditionally
        }

        The difference between our different types of conditional statements will be in what happens before/after we
        test our condition.


        The first type of control flow we will look at are branches.
        To make a branch, we use the keyword "if" to say "Only do these instructions IF a boolean condition is met":
         */

        if ( is_today_rainy ) {
        	System.out.println("Don't forget your umbrella!");
        }


		if ( is_today_sunny ) {
			System.out.println("Don't forget your sunscreen!");
		}



        /* In this example, we will only print a reminder about an umbrella when today is rainy. Otherwise, we will skip
        everything inside those curly braces entirely.

        We can even use branches with our compound boolean expressions:
         */

		if ( is_today_sunny && is_today_rainy ) {
			System.out.println("Look for a rainbow!");
		}



        /* On Wednesday we'll look at some of the fancy things we can add to our if statements to make them more
         expressive, but for today, we want to compare these if statements to one other type of conditional statement:
           a loop.


         A loop works just like an if statement, with one important difference: at the end of a loop, we'll go back to
         the top of our loop and ask the same question again!

         The simplest type of loop we can make is a 'while loop', which will look exactly like our if statement, but it
         will use the word "while" instead of if:
         */

		int some_counter = 0;
		while ( some_counter < 10 ) {
             System.out.println("Some counter is now: " + some_counter);
             some_counter = some_counter + 1;
             // Thread.sleep(1000);

		}

        /*
         Unlike our if statement, which just checks if something happened once, our while loop is going to check over
         and over and over again, until the condition isn't true anymore. Because of this, it's going to repeat the body
         of our loop and print out the value of "some_counter" several times!


         Because we will keep checking our condition until that condition is false, it is VERY IMPORTANT that something
         somewhere in our loop has the potential to change the value of our expression.

         */
    }

}