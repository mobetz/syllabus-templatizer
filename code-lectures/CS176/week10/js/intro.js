

/* Inside Javascript, we write comments similarly to how we write them in CSS -- with a slash and a star to begin, and
then a star and a slash to end.

Just like with our other files, outside of comments, every line of the file must be a valid 'statement'. In Javascript,
all statements end with a semi-colon (just like the end of our declarations in CSS!)
 */




/*
VARIABLES AND ASSIGNMENT
 */


/*
In Javascript, the most basic thing we can do is create a 'variable'. A variable is just a named space we use to save
some information. To create a variable, we write the word 'let', then write the name for our variable:
 */

let x;
let sum_of_numbers;
let first_name;


/*
Once we've created one of these variables, we can 'assign' it a value with the equals sign:
   - on the right of the equals sign, we have some work to be done
   - on the left of the equals sign, we have a declared variable where we want to save the result (so we can use it later)
 */

sum_of_numbers = 3 + 4;
x = 1 + sum_of_numbers; //<- this uses the "saved" sum_of_numbers so that we can retrieve it and do more math

/*
We can save more than just numbers, we can also save text:
 */
first_name = "Matthew"; //<- notice: text always has double quotes " " surrounding the text itself

let full_name;

full_name = first_name + " " + "Obetz"; //<- when we "add" text, we're squishing the text together end-to-end (we call this concatenation)
/*
Keep in mind, this means the TEXT "1" and the NUMBER 1 are two different things, and addition works differently on them.
 */


/*
Note: Because instructions happen in order, we can't use a variable before we make it:
 */
// let big_number;
// big_number = 10000000000 + small_number; //<- small_number doesn't exist yet here, the program will crash! We read from top-to-bottom
// let small_number;
// small_number = 4;

let more_text = "We can even 'declare' and 'assign' a variable on the same line!";



/*
FUNCTIONS

Sometimes, we might want to write a bunch of instructions that we know we will be repeating, or that we might want to
use with slight changes. To do so, we can write a function:
 */

function add_two_numbers( number_1, number_2 ) {
    /*
    To 'declare' a function, we just put the word function, then give it a name, then our 'parameters' to the function
    inside some parentheses. Afterward, we put a block of instructions that is marked by curly braces, just like the
    'block' of declarations we used in CSS!
     */
    let sum;
    sum = number_1 + number_2;

    /*
    At the end of the function, we can choose to 'return' one 'final answer':
     */
    return sum;

}


/*
Then, after we've described all of the instructions in our function, we can 'call' that function by putting its name
again, and putting the values we want into parentheses immediately afterward:
 */

add_two_numbers(5, 10);

/*
When I call the function, I can even save its returned value by combining the call with an assignment:
 */

let my_answer = add_two_numbers(3, 4); //<- this will give 3 and 4 to the function, do all the work, give me back sum, and save it in my_answer

console.log("The final answer of my_answer is: " + my_answer);



/*
Each time we 'call' the function, we can supply different values to the parameters, and the function will run with
those versions of 'x' and 'y'. If we want to 'save' the returned value, we can do that with assignment, just the same
way we did for our normal arithmetic expressions. We can even write functions that work with text:
 */

function generate_a_greeting( name ) {
    let greeting = "Hello " + name + ", how are you today?";
    return greeting;

}

let johns_greeting = generate_a_greeting("John");
let marys_greeting = generate_a_greeting("Mary");

console.log("When I gave it John, I got back: " + johns_greeting);
console.log("When I gave it Mary, I got back: " + marys_greeting);


/*
Javascript comes with many functions that were written for us. For example, we can use the console.log() function to
'write' some text to the developer console:
 */

let highest = Math.max(4, 5, 6); //<- this will find the biggest number in all the parameters, and return back 6
let some_element = document.getElementById("objectives"); //<- this gives us a whole "object" that is an entire section of HTML!



/*
LOOPS AND BRANCHES
 */

/*
Sometimes, we want to give instructions in our program that only execute sometimes. There's a special type of value in
Javascript that lets us store the answer to yes/no questions: a boolean value. A boolean value is created with just the
word 'true' or 'false':
 */
let it_is_sunny = false;
let it_is_rainy = true;


/*
We can even create boolean values by comparing two other variables or values together:
 */
let is_vibe_more_than_four = 5 > 4;

let chosen_fruit = "apple";
let words_are_the_same = chosen_fruit == "orange"; //a double equals asks "is this equal?", a single equal says "these are equal."


/*
Then, to make a 'conditional' statement, we just put the word if next to a boolean value:
 */

if ( words_are_the_same ) {
    console.log("We won't see this print statement, because we're comparing apples and oranges!");

} else {
    console.log("After an if, we can optionally put an 'else' block that says what to do when a condition is NOT met.")
}



if ( it_is_sunny ) {
    console.log("Don't forget sunscreen!");
} else {
    console.log("Just another cloudy day...");
}

let rainbow_is_possible = it_is_sunny && it_is_rainy;


/*
   We can even combine multiple booleans together with the conjunctions AND (&&) and OR (||). They work much the same
   way they do in English. if A AND B will only be true if BOTH are true. A OR B will be true if EITHER is true.

           AND is the operator we use when we want to test if multiple conditions are ALL met. We express AND in Java
           using a double ampersand (&&).

                                                  Y
                             AND (&&) |   True    |   False   |
                                 -----+-----------+-----------+
                                 True |   True    |   False   |
                           X     -----+-----------+-----------+
                                False |   False   |   False   |
                                      +-----------+-----------+

           OR is the operator we use when we want to test if ANY condition is met. We express OR in Java
           using a double pipe (||).
                                                  Y
                              OR (||) |   True    |   False   |
                                 -----+-----------+-----------+
                                 True |   True    |   True    |
                           X     -----+-----------+-----------+
                                False |   True    |   False   |
                                      +-----------+-----------+

           NOT is the operator we use when we want to test if a single condition is UNMET. We express NOT in Java
           using an exclamation point (!).


                                    NOT (!)  |  Result   |
                                        -----+-----------+
                                        True |  False    |
                                  X     -----+-----------+
                                       False |  True     |
                                             +-----------+

 */


if ( it_is_sunny && it_is_rainy ) {
    console.log("When it's sunny AND rainy, there can be a rainbow!");
}



/*
A 'loop' works just like a branch, but using the word 'while'. After the word while, we again put a boolean condition:
 */

let countdown = 10;
while ( countdown > 0 ) {
    console.log("Countdown is now: " + countdown);
    countdown = countdown - 1; //<- it's very important that SOMETHING in a loop changes the condition at the start (our countdown>10 in this case)
                          // otherwise, it will keep repeating forever.
}


/*
I do have one last bonus data type for you all. When we have a "group" of things, we can put that group in square brackets:
 */

let group_of_students = ["Jane", "Helen", "Bob", "Victor"]; //<- all four of these names are stored in one variable

/*
There is a special kind of loop that lets us look at each thing in a group one at a time:
 */

for ( let student of group_of_students ) {
    console.log(student + " is going to do great!");
}
