
/*
Objectives for Today

By the end of today, you will:
  • Define a 'manifest' for programs.
  • Understand the role package.json plays in configuring NodeJS programs.
  • Practice passing functions as callbacks into other functions.
  • Describe the consequences of asynchrony in callbacks.
  • Identify the APIs for reading files and collecting user input in NodeJS.
 */


/*
Vocabulary for the Day

Manifest -- A manifest is a special configuration file that is attached to a computer program to
  provide information about that program. In NodeJS, the manifest file is named package.json.


Dependency -- A piece of software that another program depends on in order to run. Each module that
  we import in our code is a dependency for our code.

Callback -- A callback is a function that is given to another function and run somewhere in the
   outer function. A function that can accept another function is called a "higher order function".

Asynchrony -- A piece of code is "asynchronous" if it happens outside the normal flow of a program.
    In JavaScript, most functions that use callbacks are asynchronous. If code is not asynchronous,
    it is "synchronous" by default.
 */



/* As software projects grow in size, we need to keep track of some important information about them.
  To keep track of this information, we create one more file called a "manifest".

  The manifest holds a great deal of information, such as:
      - authorship
      - licensing rights
      - version number
      - human-readable program name
      - included library 'dependencies'
      - run and build configurations
      - program entrypoint (!!!!)


   In NodeJS, the manifest file that is included in every project is called 'package.json'.

   Let's go take a look!

   Some of the important fields in package.json:

    "main" -- this field points to the entrypoint of your program. The file pointed to by main
     is the file that will be run when NodeJS tries to run the whole project from its root folder.

    "type" -- NodeJS has two different ways to import code. Setting "type" to "module" will let
     us use the import syntax we will be learning about in this class.

    "dependencies" -- Dependencies has module name and version information for every piece of code
     that needs to be downloaded in order to run our program.

     "scripts" -- A list of run configurations that we want to define for our project.

  */


/* Like in other programming languages, not all the code available to us is loaded by default
when we create a new script.


   When we want to import a module to make it available in our project, we can use an import statement:
 */

import fs from 'fs';
import readline from 'readline';
import promptSync from 'prompt-sync';


/* Import statements are structured as IMPORT object FROM "place_as_a_string". We'll learn a bit more about
making and using imports next week, but for now, lets focus on seeing what we can do with these objects once
they're created.
 */


/* The first module we're going to look at is the 'fs' module. The fs module allows us to work with files, similarly
to the File/Path/Scanner classes from Java, or the 'open' function in Python. To read from a file, we will use the
'readFile' method on fs.

'readFile' takes three arguments:
   - a path to a file that we want to open
   - a character set that file was written in (usually the string "utf-8")
   - a function that we want to run when that file is opened.


 Remember when we talked about 'functions' just being objects in JavaScript. Here's our first chance to see some of
 the consequences:
 */
let totalCost = 0;

function parse_groceries(error, file_contents) {
    if ( error != null ) {
        console.log(error)
    } else {
        let grocery_lines = file_contents.split("\n");
        let total_bill = 0;
        for ( let item of grocery_lines ) {
            let [name, price] = item.split(",");
            total_bill = total_bill + parseFloat(price);
        }
        totalCost = total_bill;
        console.log("You will need: $" + total_bill + ".");
    }
}


fs.readFile('groceries.csv', "utf-8", parse_groceries);  //<- note: I'm not CALLING the function, just NAMING it like a variable

console.log("Total cost (as a global): " + totalCost); //<- THIS IS BUGGED! TotalCost won't be done calculating yet.
/*
Notice that this means that we're writing all the code that explains what we want to do with a file before we
have even opened the file.


What's more, we can't get any information back from this function, because it's a function that's being called
*inside* another function. To show you what's happening here, let's try writing a function on our own that uses
a callback.

 */


function do_math(operation, first, second) {
    return operation(first, second);
}


function addition(f,s) {
    return f+s;
}


function subtraction(f,s) {
    return f-s;
}


/*
 Now, watch how we can call do_math:
 */

let answer1 = do_math(addition, 5, 10);
console.log("When we call do_math with the addition callback and the numbers 5 and 10, we get: " + answer1);


let answer2 = do_math(subtraction, 5, 4);
console.log("When we call do_math with the subtraction callback and the numbers 5 and 4, we get: " + answer2);

/* Callbacks let us give pieces of code to another function, so that that function can work in many different
programs. Very many JavaScript functions use callbacks.

Notice something else... in our output, our do_math statements were printed before our grocery list print statement,
even though we wrote the file code way earlier.

Reading from a file in JavaScript is "asynchronous" -- this means it happens on a completely separate 'thread' of
execution than the normal flow of our program. If our programs get complex enough, we can even see 'interleaving',
where output from the callback and output from the rest of our program gets mixed together.

Try your best not to mix accessing the same data from inside callbacks and outside of callbacks!

If you don't want to deal with callbacks and the problems they introduce, many JavaScript methods also include
"synchronous" versions that you can identify by the word 'Sync' at the end of their name:
 */

let groceries_json = fs.readFileSync('groceries.json', 'utf-8');
let groceries_as_object = JSON.parse(groceries_json);
console.log("Here's how much an apple costs: $" + groceries_as_object["apples"]);


/*
Getting input from stdin (the command prompt) in JavaScript is also done in an asynchronous callback.

To do so, we can use the 'readline' module. Readline has a 'createInterface' method that we can give a handle
to our stdin and stdout file descriptors (almost like creating a new Scanner object in Java):
 */

let scanner = readline.createInterface(
  process.stdin,
  process.stdout
  );


/* Once we have that 'scanner', we can use the 'question' method to give the user a prompt and collect input: */

function parse_favorite_color(favorite_color) {
    console.log("Wow, I love " + favorite_color + " too!");
}

/*
scanner.question("What is your favorite color?: ",  (favorite_color) => {
    console.log("Wow, I love " + favorite_color + " too!");
});
*/

/*
Notice how I'm passing something into the callback there that isn't a name of a function.
If we don't want to define a whole new function for the callback, we can write the callback code 'in-line'
by putting the arguments in (parentheses) then using an arrow operator => to point to a block of code.

You could also define an anonymous function with function(arguments) {   }.  The three of these are all
(mostly) equivalent in JavaScript:
 */


function someFunctionClassic(x, y) {
    return x*y;
}

let someFunctionVariable = function(x,y) {
    return x*y;  
}

let someFunctionLambda = (x,y) => {
    return x*y;
}


/*
   Say we want to ask a user for more than one thing, and have access to all of those things at once.


   To do this, we have to use a callback inside a callback inside a callback....
 */


/*
scanner.question("What is the first number?: ",  (first) => {

  scanner.question("What is the second number?: ",  (second) => {

    scanner.question("What is the third number?: ",  (third) => {
      console.log("The sum of your three numbers is: " + (parseInt(first)+parseInt(second)+parseInt(third)));
    });
  });
});
*/

/* This is a common pain point in JavaScript code. It's a pattern you should get used to working with in JavaScript
(at least until we learn about Promises in another lecture!)
*/




/*
That library prompt-sync that we declared as a dependency at the start of class helps us create a synchronous version
of our 'scanner':
*/


let scannerSync = promptSync();

let answer = scannerSync("Is it sunny today?: ");
console.log("You indicated" + answer);






/*
Review of For Loop Syntax 
*/

/*
Basic while loops work the same as in every language:
*/

int i=0;
while ( i<10) {
  console.log("i is now: " + i);
  i=i+1;
}



/*
Basic Javascript for loops work like for loops in most programming languages:
*/
/*
for ( initial-value;   condition;   incrementor ) {

  //stuff to repeat
} 
*/


for ( let i=0; i<10;  i++) {
  console.log("I is now: " + i);
}


/*
One common thing we want to do is use this index to access each thing in a list:
*/

let students = ["Joe", "Mary", "Shannon"]


for ( let i=0; i<students.length; i++) {
  let next_student = students[i];
  console.log("Next student is: " + next_student);
}

/*
For-each loops in java allow us to loop over each element in an array:

for ( declared-variable   of    group   ) {
  
}

*/
console.log("\nAll students, for-each version:")
for ( let next_student of students ) {
  console.log("Next student is: " + next_student);
}


/*
NOTE: There's another kind of for-each loop in Javascript: a for...in loop:

for ( declared-variable   in   group ) {
  
}

This will give me the INDEXES of each element (or the 'keys' in a dictionary), NOT the VALUES.
*/


console.log("\nAll students, for...in version:")
for ( let next_student in students ) {
  console.log("Next student is: " + next_student); //<- prints 0, 1, 2...
}






