/*  Objectives for Today

     By the end of today, you will:

        * Know how to declare and assign variables in JavaScript.
        * Use expressions to modify values.
        * Understand the difference between string and numeric representations of data.
        * Perform casting to change one type of data into another.
        * Identify how to provide and access command line arguments in a script.

 */


/*  Vocabulary for the Day:

  Statement - A statement is a single instruction that we want to give to the computer. We mark the end of a
       statement with a semi-colon, and typically write each statement on its own line.

  Expression - An expression is any computer code that "turns into" to a single value when the code is complete.
       'Literal' values (numbers, text, and other data we write out explicitly) are the simplest expressions.
       'Return' values (things we receive back from functions) are also expressions!

  Variable - A variable is a named space we have reserved to save data.

  Declaration - The act of reserving a name and space for data or instructions.

  Assignment - The act of saving an expression into a variable. We use the (equals sign: =) when we want to
      perform assignment.

*/


/*
Today, we are going to learn how to start writing our very first meaningful programs!


But first, a short side note: In JavaScript, if we want to write a "comment" to ourselves or someone else that
will be ignored by the computer, we can do so by putting a // at the start of the line. If we want a comment to
last for more than one line, we can start it with /*, and end it with * /.

Every other thing we type in the program must be valid javascript code, or the program will not run.
 */


/*
The most basic thing we can do in our computer programs is interact with data.

Whenever we want to save data from one part of a program and use it in another, we have to create a "variable".
A variable is just a slot we've created in the computer's memory that can remember our value for us.

When we want to create a new variable, we 'declare' that variable by using the "let" keyword, then writing the name
of the variable:
*/


let first_name;


/*
JavaScript will try to declare variables for you if you forget, but it is good practice to do this before we use our
variable for the first time, so we can easily see all the "slots" we've created in the memory of our computer! You can
think about this almost like creating a box that can hold a single piece of information:


           +-----+
           |     |
           |     |
           +-----+
          first_name


If we want to save data in our variable, we have to ASSIGN a value into our variable.
We do this with the equals sign =. For example:
 */


first_name = "Matthew";

/*

It's almost like we're saying "take this box, and put this value into it":

           +-----+
           |     |
           |    <--- "Matthew"
           +-----+
          first_name

We can even save the result of other work we want to do:
*/


let welcome_message;
welcome_message = "Hello, " + first_name;
console.log(welcome_message);


/*
In this sample code, we are creating a variable called "first_name", and we are assigning the value of the text
"Matthew" into it. Note: when we use a single equals sign like this, it doesn't mean we're saying we believe the
two things are the same (the way it does in algebra.) Instead, we're saying that we want to MAKE them be the same.



In general:
 - On the left of the equals sign, we put the place we want to save something.
 - On the right of the equals sign, we put the expression we want to save.



We can save more than just literal values into our variables. We can also use variables to save the result of work
we've done:
 */

let sum_of_odds;
sum_of_odds = 1 + 3 + 5 + 7 + 9;


console.log( "The sum of these odds is: " + sum_of_odds );


/*

Note: When we save an expression like this, we ONLY save the answer (not all the work).


           +-----+
           |     |
           |    <--- 25
           +-----+
          sum_of_odds

*/


let SumOfOdds = 17;


console.log( "After creating SumOfOdds, our original sum_of_odds is: " + sum_of_odds );
console.log( "The SumOfOdds is: " + SumOfOdds  );


/*
Reusing the same space does not require re-declaration....
 */
SumOfOdds = 9 + 11 + 13 + 15;


console.log( "After re-assigning, SumOfOdds is " + SumOfOdds );


/*
...however, ever time we re-assign a new value to an existing variable, all the old values are deleted completely.

           +-----+
           |     |
           |    <---  17
           +-----+
           SumOfOdds


           +-----+
           |     |
           |    <---  -----   48   //The seventeen gets deleted completely! Note: We only save the "final answer" of 
           +-----+                 // the right hand side, not all the math.
           SumOfOdds

*/


let sum_of_evens = 2 + 4 + 6 + 8 + 10;


/* When we want to actually use the value of a variable, we just put the name of that variable in the same place
we would put any other expression or value!
 */


let sum_of_both;
sum_of_both = sum_of_evens + SumOfOdds;  // this will use the value of 48, not the value of 17.
                                         // It also won't update if sum_of_evens or SumOfOdds changes later in the program!!


/*
This brings up one important point though: We need to give JavaScript a hint about what type of data we are creating.

If we want to write literal text in our program, we need to use double quotes:
 */

let course_name;
course_name = "CS118";

/*
Without quotes, JavaScript will look for a variable with a given name, *not* text.

 */


// NOTE: This means that the "word" 4 and the "number" 4 are two different things:

let the_number_four = 4;
let the_letter_four = "4";



/*
How are they different? We can do math with numbers, but "math" with text will work a little bit differently:
 */

let one_more = the_number_four + 1;
/* Just like we learned during lab zero, we can "print out" some text onto the console at the bottom of our screen
by using the "console.log()" command:
 */

console.log("When we do 4+1 with numbers, we get: " + one_more);


let what_will_this_be = the_letter_four + 1;
console.log("When we do 4+1 with text, we get: " + what_will_this_be);


/*
      When we do addition with text, we're actually performing an operation called "concatenation". Concatenation is just
      a fancy term for joining two things together. In this case, "4" + 1 will give us a longer string of text: "41".

*/


