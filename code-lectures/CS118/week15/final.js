
/*
Objectives for Today

By the end of today, you will:
    * Review the concepts we have learned this semester.
    * Understand the format and timing of the final exam.
 */

/*
Your final exam in this course will be on Tuesday, May 9th. It will follow the format of the midterm:
   - released early in the morning
   - multi-part short answer questions
   - cumulative over the entire semester
   - entire day to work on it, proctoring period from 2-4pm in the Webex room

*/



/*
Review Up to Midterms
*/

/* 
In Javascript, we learned about writing four different types of statements in our programs:
     - Assigning variables
     - 'If' statements/Branches
     - 'While'/'For' Loops
     - Functions
*/

/*
When we want to save information from one line of a program to another, we use a variable!

To use a variable, we first need to 'declare' that a variable with that name exists. In 
Javascript, we do this by writing the word 'let' in front of its name:
*/

let some_number; //<- this creates a "space" for us to store some data, and gives that space the name "some_number"

/*
To actually save something into it, we need to use the equals operator:
*/
some_number = 4; //<- this puts the value 4 into that space we created.

/*
We can even do both of these on one line:
*/

let persons_name = "Matthew"; //<- this will both create and store a value on one line

/*
When we're working with variables, one important consideration is the TYPE of data that we've got.

For a human, the "word" four and the "number" 4 mean the same thing -- that you've got four of something.

For a computer, 4 and "four" are entirely different -- 4 is a number (specifically an integer), which is
something that the computer knows how to use to do math and to store in a compact format. On the other hand,
"4" with double quotes is text -- this means its something that computer is going to know how to 'squish together'
with other text. Strings are the name that we give to lines of text stored on a computer:

*/

let a_number = 4;
let a_string = "4";

console.log(a_number + 1); //<- this will print out 5, because 4 + 1 will be the mathematical version of addition
console.log(a_string + "1"); //<- this will print out "41", because addition with strings of text "squishes them together"
                           // so we have "4" and "1" placed side by side (this is called concatenation.)


/*
There was even a third type of data in addition to numbers and strings -- sometimes, we a variable that
can hold the answer to a question. Specifically, a "yes or no" question, like: "does this text start with a particular
letter?" or "is one number bigger than another number?"

When we've got a question like this, we can store the answer as a 'boolean' -- booleans have exactly two values:
true or false. True when the answer to the question is 'yes', false when it is 'no'.
*/


let some_boolean = "apple".startsWith("a"); //<- this will save 'true', because apple DOES actually start with "a"
let some_falsehood = 4 < 1; //<- this will save 'false', because 4 is actually BIGGER than one (the statement isn't the 'truth')

/*
We can even combine multiple true or false values with the conjunctions && (AND) and || (OR) -- && is true when both sies are true,
|| is true when EITHER side is true, otherwise both are false.
*/

let some_example = "apple".startsWith("a") && "banana".startsWith("z"); //<- even though the first part is true, both parts aren't true 
                                                                        // together, so the answer is false.

let with_or = "apple".startsWith("a") || "banana".startsWith("z"); //<- this version will actually be true, because at least one part is true.


/*
We could also use ! to 'flip' a true value:
*/

let opposite = !true; //<- this will save false, because false is the opposite of true.




//When we want something to only happen when certain conditions are met, we can use an 'if' statement:

/*
With an if statement, we always need some "condition" that describes the thing we will test to see whether
or not our if statement should run the code in its body.
*/

let is_sunny = false;
let is_rainy = false;

if ( is_sunny && is_rainy ) {
    console.log("This line and everything inside the curly braces only happens if the 'condition' is true");
}

/*
When we want to keep repeating something as long as a condition is met, we use a loop:
*/

let countdown_number = 10;

/*
With our loops, we have a condition exactly like the one we had in an if statement. The only difference is
in a loop, when we get to the bottom, we go back to the top.
*/
while ( countdown_number > 0 ) {
    console.log("The countdown says: " + countdown_number);
    countdown_number = countdown_number - 1;
}


/*
For loops work very similarly to while loops -- they just let us declare a variable, check it, and change it all
on one line. This makes them very useful when we want to do something exactly a number of times:
*/

for ( let countdown=10; countdown>0; countdown=countdown-1 ) {
    console.log("This countdown works just like our while loop version: " + countdown);
}

/*
When we want to create a group of instructions that we can use or adjust based on some inputs, we write a function:
*/


function guess_number(player) { //<- this says there's a task I know how to do: ask a user to guess a number
                                // in order to do this task, you need to give me one thing: a player
    let valid_guess = false;
    let guess = -1;
    while ( !valid_guess) {
        guess = parseInt(prompt("Hello " + player + ", guess a number between one and ten")); //<- down here, 
             // I'm using the player to make custom prompt text even though I don't know who the player is yet.
        valid_guess = guess > 0 && guess < 11 
    }

    return guess;
}



/*
To use a function, we need to 'call' it by putting its name, followed by parentheses and actual values that
we want to put into our input parameters:
*/

a_good_guess = guess_number("Matt") //<- when a function "returns" a result, I can save it by assigning it
a_good_guess = guess_number("Logan")


/*
Lists
*/

/*
Lists were a way for us to store an entire group of variables with one name, by position. We create and access
lists using [square brackets]. When we create the list, we can put all the things we want in it, separated by commas:
*/

let classmates = ["Joe", "Bob", "Mary", "Carol"]; //<- this creates a group with four strings, all that share the same 
                                                  // list variable name.


/*
To retrieve something from the list, we need to use square brackets to indicate which person we want:
*/

let just_the_first_classmate = classmates[0]; //<- get the person in the "Zeroth" position (the beginning)

/*
We were also able to repeat for each thing in a group:
*/

for ( let each_student of classmates ) {
    console.log("This will repeat four times, once for each student: " + each_student); 
    //^ This prints out four lines, one with "Joe" at the end, one with "Bob" at the end...
}

/*
Lists also had some helpful functions for adding or removing elements, and doing some common 'group operations':
*/

classmates.push("NewPerson");
classmates.sort();





/* Files and Modules */


/*
We had learned that there are a couple functions related to working with files in Javascript.

Most of the file-related functions live in a third-party module called 'fs'. When we have code that lives outside
the default functions given to us, we need to first import it before we can use those functions:
 */

import fs from 'fs';



/*
Once we've imported our 'fs' module, we can tell it we want to read a file by calling the 'readFile' method:
 */

let file_contents = fs.readFileSync("./package.json", "utf-8");


/*
Once I've read the contents of the file, I can use it like any other string:
 */

console.log("The contents of package.json holds: " + file_contents);


/*
We had also seen two functions that let us create/add text to files:
 */

fs.writeFileSync("./some-example-file.txt", "Here is some text I can write to a file.");
fs.appendFileSync("./some-example-file.txt", "The appendFile lets us write to the end of a file without deleting what was already there.")


/*
In addition to our synchronous interface, the 'fs' module was special that it also gave us our first look at "asynchronous" functions:

Asynchronous functions require a callback, that describes work to be done when the task (of reading the file) is completed.
However, the readFile() function itself won't wait for the file to be finished -- the program will continue on, and it will
call the callback when the work is eventually complete.
 */

fs.readFile("./some-example-file.txt", "utf-8", (err, data) => {
    if ( err !== null ) {
        console.log(err);
    } else {
        console.log("The contents of the example file is: " + data);
    }
})



/*
Searching and Sorting
*/

let list_of_items = ["apple", "banana", "cherry", "durian", "elderberry", "fig", "grapes"]


/*
This semester, we learned about a couple different strategies for searching.

The easiest way we can search through a list is with a simple loop. However, this means we have to do one
check for each thing in the list.

We said this had "linear complexity", because the number of steps was directly proportional to the count of things in our list:
 */

let checking_for = "durian";
let fruit_found = false;
let num_steps = 0;


for ( let fruit of list_of_items ) {
    if ( checking_for === fruit ) {
            fruit_found = true;
    }
    num_steps = num_steps + 1;
}



console.log("Was item found?: " + fruit_found);
console.log("Steps taken: " + num_steps);


/*
We discovered we could do better by sorting the list first, repeatedly splitting the list in half, checking the thing in the
middle, and throwing away the entire half where we know the target thing couldn't be found. We had called this approach
Binary Search.


In order to search the list this way, we first had to sort the list, because otherwise, we couldn't
make judgments about things we had not directly seen.
 */

list_of_items.sort();
fruit_found = false;
num_steps = 0;


function binary_search(list_of_things, target_item) {

    if ( list_of_things.length === 0 ) {
        return false;
    }

    let earliest = 0;
    let latest = list_of_things.length - 1;


    let midpoint = Math.floor(latest/ 2);
    let thing_in_the_middle = list_of_things[midpoint];


    if ( thing_in_the_middle < target_item) {
        earliest = midpoint + 1;
    } else if ( thing_in_the_middle > target_item ) {
        latest = midpoint -1;
    }else if ( thing_in_the_middle === target_item ) {
        return true;
    }

    let sublist_left_to_search = list_of_things.slice(earliest, latest);
    return binary_search(sublist_left_to_search, target_item);
}

console.log("Found?: " + binary_search(list_of_items, "durian"));
console.log("Num steps: " + num_steps);


/*

The number of steps this would take is proportional to the log2(count_of_things), which
 is much, MUCH faster. We called this type of growth "logarithmic complexity".

 */




/*
We said that most sorting algorithms have us go through and compare each possible pair of things in
a list, then swap them if they're out of place.
*/



function swap_two_places(list, first, second) {
    let temp = list[first];
    list[first] = list[second];
    list[second] = temp;
    return list;
}


list_of_items = ["durian", "apple", "fig", "banana", "cherry", "grapes", "elderberry"]
num_steps = 0;


for ( let first=0; first < list_of_items.length-1; first++) {
    for ( let second=first+1; second < list_of_items.length; second++) {
        if ( list_of_items[first] > list_of_items[second]  ) {
            swap_two_places(list_of_items, first, second);
        }
        num_steps = num_steps + 1;
    }
}

console.log("Sorted list: " + list_of_items)
console.log("Steps taken: " + num_steps)


/*

For each of our N things, we have to compare it to N-1 other things.
After some fancy math, we said that this roughly estimated to count_of_things^2. We discovered this was
much slower than linear or logarithmic problems.

We said problems like sorting had "polynomial complexity".

 */



/*
Classes and Objects
 */

/*
In addition to sorting and searching, we also learned about how to make classes.

A class is a bundle of related data and functions.
However, a class is just a 'recipe', we can't actually use a class. To 'use' the elements of our class,
we have to instantiate an object.
 */




class Exam {

    /*
    ATTRIBUTES / PROPERTIES / FIELDS / INSTANCE VARIABLES
     */
    questions;
    time_limit;
    due_date;


    /* METHODS */
     constructor(minutes_available, due_date) {
        /* The constructor is a special function that tells Javascript how to create a brand
        new object of our class (in this case, a new Exam.) */
        this.questions = [];
        this.time_limit = minutes_available;
        this.due_date = due_date;
        /* Inside our class, we're able to use the word 'this' to talk about one of the 'properties' of
        our class.
         */
    }


    add_question(text, correct_answer) {
        this.questions.push({
            "question_text": text,
            "answer": correct_answer
        });
    }


    suggest_minutes_per_question() {
        return this.time_limit / this.questions.length;
    }

}



/*

To actually use our class, we need to create an object of that class by calling the constructor.

To call the constructor, we provide the name of the class as the function name, and also use the special
keyword 'new'.
 */


let midterm_exam = new Exam(180, "3/9/2023");
let final_exam = new Exam(180, "5/9/2023");


/*
To access properties and methods on my class, I can use a dot operator:
   - on the left of the dot, we put the name of the object we're using
   - on the right of the dot, we put the method/property that we want to access
 */


final_exam.add_question("What is your favorite class this semester?", "CS118");
final_exam.add_question("What grade should you get on the final?", 100);


let time_per_question = final_exam.suggest_minutes_per_question();
console.log("You should spend approximately " + time_per_question + " minutes per question if there were only " + final_exam.questions.length + " questions.")

/*
A lot of existing objects in Javascript have their dot methods indexed on the Mozilla Developer Network:

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array
*/



/*
Browser Javascript: see web-example.js
 */

