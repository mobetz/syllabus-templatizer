

/*
     Objectives for Today

     By the end of today, you will:
         * Identify boolean data and list a few types of common expressions that produce boolean values.
         * Describe how we can use boolean operators (and, or, and not) to chain and invert boolean expressions.
         * Explain the concept of branching using if-statements in scripting.
         * Understand the role of else-if and else in creating multiple separate paths through a script.
         * Identify common pitfalls when writing and ordering if and else-if conditions.

 */


/*
Vocabulary for the Day

  Boolean - A boolean is a type of data that specializes in storing the 'truthiness' of conditions.
      Boolean data can only have one of two possible values: true or false.

  Boolean operator - A boolean operator is a special symbol that lets us combine booleans together.
      The three main boolean operators are NOT, AND, and OR.

  Branch - A conditional branch is a path through a program that will only sometimes happen. To create a branch,
          we use an "if statement" that starts with the keyword if.

  */



/* Last time we met, we talked about saving data in variables and calling functions.


   Sometimes, when we are giving instructions, we need to think about more than one possibility.

   The same is true of our computer programs! Sometimes, we want to tell the computer to do something different
   depending on whether or not the answer to a question is true.

   For example, say we were writing a program to tell our user what they need to bring with them as they're leaving
   the house. We might want to tell them  something different if it is raining or sunny.

   To store the answers to questions like this, we are going to use a new type of data, a boolean:
 */

  let today_is_rainy = false;
  let today_is_sunny = true;


/*  A boolean stores information about the answer to a yes/no question, like "Is it sunny out today?"
       - If the answer is yes, we save the value true.
       - If the answer is no, then we save the value false.

    A lot of functions in JavaScript can give us back a boolean when we ask questions about our data:
 */

  let some_text = "apple";
  let  does_apple_end_with_ple = some_text.endsWith("ple");


  console.log("Does " + some_text + " end with 'ple'?: " + does_apple_end_with_ple);


/* Sometimes, our questions might have more than one part.

   For example, what conditions do we need for a rainbow to appear?
           - It must have rained recently.
           - The sun must be out.

      We made some 'rainy' and 'sunny' boolean variables before -- each one of these represents just half of
      what we need to check for a rainbow. Fortunately, JavaScript gives us a way to combine them, with
      "boolean operators".
    */

let rainbow_is_possible = today_is_rainy && today_is_sunny;
//rainbow_is_possible will only be true if today_is_rainy is true AND today_is_sunny is also true.




/* There are three boolean operators we will talk about during this class: AND, OR, and NOT.

   For each boolean operator, we can create a "truth table". A truth table shows all possible combinations of
   inputs for a given boolean operator, and describes what the output will be.


   AND is the operator we use when we want to test if multiple conditions are ALL met. We express AND in JavaScript
   using a double ampersand (&&).

                                          Y
                     AND (&&) |   True    |   False   |
                         -----+-----------+-----------+
                         True |   True    |   False   |
                   X     -----+-----------+-----------+
                        False |   False   |   False   |
                              +-----------+-----------+


   OR is the operator we use when we want to test if ANY condition is met. We express OR in JavaScript
   using a double pipe (||). (Pipe is the key right above ENTER, shared with \.)

                                          Y
                      OR (||) |   True    |   False   |
                         -----+-----------+-----------+
                         True |   True    |    True   |
                   X     -----+-----------+-----------+
                        False |   True    |   False   |
                              +-----------+-----------+



   NOT is the operator we use when we want to test if a single condition is UNMET. We express NOT in JavaScript
   using an exclamation point (!).


                            NOT (!)  |  Result   |
                                -----+-----------+
                                True |  False    |
                          X     -----+-----------+
                               False |   True    |
                                     +-----------+


  In addition to our three boolean operators, we also have a few operators that help us compare things and
  get a boolean answer. We call these the 'relational operators'. The relational operators we'll use in this
  class are:

          ==                  (equality) - the equality operator tests if the things on both sides are the
                                           same. For example: (2 + 2) == 4 will evaluate to true.
          !=                (inequality) - the inequality operator tests if both sides are NOT the same.
           >              (greater than)
           <                 (less than)
          >=  (greater than or equal to)
          <=     (less than or equal to) - the four numeric relational operators test whether numbers on both
                                  sides follow their respective mathematical property. For example:
                                    4 < 5 will be true, but 4 >= 5 will be false.


  One important feature of boolean operators is that, just like with arithmetic operators like + and *,
  we can string multiple pairs of booleans together, and they even have their own order of operations!

  In JavaScript, the order of operations for booleans is:
     1) Parentheses ( ).
     2) NOT.
     3) relational operators like >, <, ==, and !=; left to right.
     4) AND, left to right.
     5) OR, left to right.


  Since parentheses are the highest precedence, we can use them the same way that we use them in math, to
  group things together to be done first.

 */


/* Let's test out how well we understand our boolean operators and order of operation:
     Will the "is_this_true" variable below be true or false?
 */
let  is_this_true = true && !true || !false == !(false || !false);
     is_this_true = true && !true || !false == !(false || true);
     is_this_true = true && !true || !false == !true;
     is_this_true = true && false || true == false;
     is_this_true = true && false || false;
     is_this_true = false || false;
     is_this_true = false;

console.log("The answer is: " + is_this_true);


/*
Recording conditions is great, but the real power of booleans comes from their ability to change the way our
programs execute. We have a few special statements we will be learning about this week and next that use a
boolean expression to change whether parts of our program run at all.

The two main types of conditional statements we have are loops and branches. Both of these statements are
written similarly:

        KEYWORD ( boolean_expression ) {
              //stuff that only happens conditionally
        }

  This week, let's start by taking a look at the simplest form of conditional statement: the if statement.

  The if statement says "only do these instructions IF some condition is true":
 */

if ( today_is_rainy ) {
  console.log("Don't forget your umbrella!");
}


/* In this example, we will only print a reminder about an umbrella when today is rainy. Otherwise, we will skip
   everything inside our curly braces entirely.
 */

if ( today_is_sunny ) {
  console.log("Don't forget your sunscreen!");
}

/*

    We can visualize our path through the program here something like this:
                                                          |
                    Enter  |                              |              Enter  |
                           |                              |                     |
                          +----------------+              |                    +----------------+
                          | Is a condition |              |                    |  Is it sunny   |
                          |     met?       |              |                    |     today?     |
                          +----------------+              |                    +----------------+
                       Yes   |          |  No             |                 Yes   |          |  No
                   +--------------+     |                 |             +--------------+     |
                   | do something |     |                 |             | Sunscreen!   |     |
                   +--------------+     |                 |             +--------------+     |
                             |         /                  |                       |         /
                              \      /                    |                        \      /
                               \   /                      |                         \   /
                                 V                        |                           V
                                 |                        |                           |
                                 v                        |                           v


        We're only going to do that block of extra instructions when the weather is rainy, otherwise
        we'll skip them entirely and continue on.

        We can even use if statement branches with our compound boolean expressions:
    */

if ( today_is_rainy && today_is_sunny ) {
  console.log("Look for a rainbow!");
}


/*
  Sometimes, we want to do something not just when a single condition is true; we might want to create
      many different possible paths. Fortunately, our if statements give us the tools to do that!

      If we want to test for a second thing if our first didn't happen, we can put "else if" and a second
      condition right after the end of our original "if" statement.

      If we want to run some instructions only when no other path was taken, we can put an "else" statement
      after our last if or else if.
 */



console.log("What year was the U.S Constitution ratified?");
let student_guess = 1783;

if ( student_guess == 1787 ) {
  console.log("Very good!");
} else if ( student_guess == 1776 ) {
  console.log("Close, but that's when the Declaration of Independence was written.");
} else {
  console.log("Uh oh, you might need to go back and study!");
}


/* If we were to extend our visualization from before, this quiz program would look like:


                       Enter  |
                              |
                             +-----------------------+
                             |   Was guess correct?  |
                             +-----------------------+
                          Yes   |          |  No
                      +--------------+    +------------------+
                      |  Very good!  |    | Was guess close? |
                      +--------------+    +------------------+
                                |              | Yes        \ No
                                 \        +---------------+    +----------------+
                                  \       |  Print hint   |    |  Print uh-oh.  |
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
 */


let its_snowy = true;
let its_a_workday = true;

// if we normally sleep until 8AM
let wakeup_time = "8 AM";

// we might change that if....
if ( its_snowy ) {
  //we sleep in on snow days!
  wakeup_time = "10 AM";
} else if ( its_snowy && its_a_workday ) {
  //we have to wake up early to scrape off the car!
  wakeup_time = "6:30 AM";
} else if ( its_a_workday ) {
  //we have to get up for work!
  wakeup_time = "7 AM";
}


console.log("We will wake up at: " + wakeup_time);


/*
This program has a bug! Even though its_snowy and its_a_workday are both true, the program is telling us to sleep in.

We can fix it by reordering the branches, so that our most specific/important branch is tested first.

 */

if ( its_snowy && its_a_workday ) {
  //we have to wake up early to scrape off the car!
  wakeup_time = "6:30 AM";
}  else if ( its_a_workday ) {
  //we have to get up for work!
  wakeup_time = "7 AM";
} else if ( its_snowy ) {
  //we sleep in on snow days!
  wakeup_time = "10 AM";
}

