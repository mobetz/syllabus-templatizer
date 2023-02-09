
/* Objectives for Today

By the end of today, you will be able to:
   * Identify how functions allow programmers to create named, reusable groups of instructions.
   * Describe how functions define parameters whose values can change each time a function is called.
   * Explain how arguments are passed into a function to become function parameters.
   * Understand how functions can return a single value back to their calling context.
   * Define the pipeline operator, and understand how piping allows us to chain multiple commands in a shell script.
   * Analyze how functions help organize code and isolate individual parts of a problem.

 */


/*

Vocabulary for the Day

Function - A  named, reusable groups of instructions. Functions require some "input parameters" in order to run, and
they give back a "return value" when they're finished.

 */


/* Sometimes, it can be a lot of work to write out all the steps we want a computer to take. It would be inconvenient if
we had to copy and paste all that work every time we wanted to get the answer. For example, what if we wanted to find the
distance between two points:

 (4, 4)     (12, 16)

 */




let x1 = 4;
let y1 = 4;

let x2 = 12;
let y2 = 16;

let difference_of_Xs = x2 - x1;
let difference_of_Ys = y2 - y1;


let square_of_Xs = difference_of_Xs ** 2;   //  difference_of_X²
let square_of_Ys = difference_of_Ys ** 2;


let sum_of_both;
sum_of_both = square_of_Xs + square_of_Ys;


let distance = Math.sqrt( sum_of_both );



/* If we were writing a game or a program that could work with geometry, we might need to use this formula dozens or
even hundreds of times!


If we were going to copy-paste this code, we might make a mistake somewhere. Or even worse, if there's a mistake in the
code we copied, we have to remember to change it every single place we copied the code.



Fortunately, JavaScript gives us a tool to avoid repeating ourselves.

We can save all of our code in a "function". A function is just a group of instructions that we give a name.

To declare a new function in JavaScript, we use the special keyword "function":

 */


function  distance_between_points(x1, y1, x2, y2) {

    /* This declaration has a few parts:

     First, we use the function keyword to say we're making a new function.
     Next, we give our function a descriptive name. This name must not have spaces, but we can use underscores.

     After the name, we put a pair of parentheses. Inside these parentheses, we list all of the data we are going
     to need in order to run our function. We call these input variables "parameters".

     Finally, after our list of input parameters, we put a pair of {curly bracces}. All of the instructions we put
     inside the curly braces count as part of our function!
   */

    let squared_distance = (x2-x1) ** 2 + (y2-y1) ** 2;
    let distance = Math.sqrt( squared_distance );


    /*
    However, any variables that we make inside of our function will not be visible outside of it! If we want to use a
    value we've computed outside our function, we have to add one more statement to the end of our function: the
    "return" statement.  The return statement takes one value from inside our function, and sends that value back to anyone
    who uses our function:
     */
    return distance;
}


/*

So, how do we actually use this function? We need to call it! To call a function, we just put its name with parentheses
right after, exactly the same way we declared.

However, this time, instead of giving names to all the values we require, we're going to put those actual values we want
to run the function for:

*/


let problem1_answer = distance_between_points(1, 1, 5, 5); 

console.log(  "The distance between (1,1) and (5,5) is: " + problem1_answer);


/*
The nice thing about functions is that we can now reuse this same code for any point we can think of!
 */
let problem2 = distance_between_points(6, 12, 8, 16);
let problem3 = distance_between_points(4, 7, 1, 5);
let problem4 = distance_between_points(9, 9, 1, 6);

console.log("Problem four give us: " + problem4 + " as the distance.");


/*
What's actually happening here is that when JavaScript reaches the line with the
function call, it goes and finds a function with that name, follows ALL those
instructions, comes back with the return value, then replaces the function
call with that value and continues on.


                                         +------------------+
    Start of program  |             7    |    func start    |
                      | 1          /     +------------------+
                      |           / 2              |
                      v          /                 |     complete
                +--------------+                 3 |    function body
                |  func call   |                   |  using arguments
                +--------------+ < ___             |  from this call
                      |               \  4         |
                   5  |  replace       \           v
                      |  function call    +------------------+
                      v  with return      |    func return   |
                                          +------------------+


The nice thing about functions is that they allow us to "abstract" complicated
sets of instructions. Another programmer using our code doesn't need to remember
how to find distance mathematically, they just need to remember that all the
instructions for finding distance live in our function called "distance_between_points".


We can think of functions kind of like a black box: the only thing we need to
know is that they take in something, do some work, and then give us back the
answer we want:

                  +------------------+
----- inputs ---> |   some_function  | ---- returned value --> (usually saved in
                  +------------------+                             a variable)


JavaScript comes with many functions that have already been written for you.
We already saw console.log(), which lets us print out text:
*/



console.log("Functions are great!");




/*
There are also many functions whose name starts with "Math" that let us calculate useful things:
 */
let square_root = Math.sqrt(4);
let keep_bigger_of = Math.max(1, 3, 5);
let keep_smaller_of = Math.min(1, 3, 5);
let find_log_of = Math.log10(10000);


/* Finally, there are some useful functions that let us turn one type of data into another type of data!) */

let the_word_four = "4";
let the_number_four = parseInt(the_word_four);

console.log("Now that we have the number, 4 + 1 is: " + (the_number_four+1) );


/* Occasionally, if our function is 'attached' to a particular piece of data, we may need to use a . that connects
the name of our variable to the function we want to call on it. We'll learn more about this is a few weeks, but for now,
one common example of this is performing operations on a string of text:
 */


the_word_four = the_number_four.toString();

let many_fours = the_word_four.repeat(3);
the_word_hour = the_word_four.replace("f", "h");


/* One last important point about functions: The returned value of a function call is an expression, just like any other.
This means we can even use the output of one function as the input of another function!
 */


 let smallest_of_the_biggest = Math.min(  Math.max(1,3),   Math.max(2,5) );

 