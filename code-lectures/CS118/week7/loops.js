
import Prompt from 'prompt-sync';
const prompt = Prompt({});

/*
Objectives for Today

By the end of today, you will:

   * Describe how loops allow programmers to write repeating instructions in a script.
   * Compare the behavior of if-statements and while-loops.
   * Explain how for-loops let us write loops more efficiently when repeating over a known set of values.
   * Rewrite for-loops as while-loops and while-loops as for-loops (and understand when this is impossible.)
   * Practice common looping patterns.
 */


/*
Vocabulary for the Day

Loop - A loop is conditional behavior in a program that repeats itself UNTIL a condition is no longer met.

 */




/*
   Previously, we've learned about if statements...

   If statements let us describe something that should only happen 'if' a particular condition is met.

   Loops let us write the same type of conditional behavior as these branches, but with one important distinction; 
   at the end of a loop, we go back to the top and run our test again!

   The simplest kind of loop is a while loop -- they look exactly like if statements, but we use the word "while"
   instead of the word "if".

   */

let countdown_number = 5;


while ( countdown_number > 0 ) {
	console.log("Countdown: " + countdown_number);
	countdown_number = countdown_number - 1;
}
console.log("Time up!");



/*

 We can even draw a similar diagram to our branches to show what happens when we run a loop:


                                                       |
   Enter  |           _______________                  |      Enter  |           _______________
          |         /                 \                |             |         /                 \
          |        v                    \              |             |        v                    \
         +----------------+              |             |            +----------------+              |
         | Is a condition |        +--------------+    |            |  is the number |        +-----------------+
         |     met?       |        | do something |    |            | more than zero?|        | print countdown |
         +----------------+        +--------------+    |            +----------------+        +-----------------+
           No |       | Yes              ^             |              No |       | Yes              ^
              |       |                  |             |                 |       |                  |
              |        \ ______________ /              |                 |        \ ______________ /
              v                                        |                 v


*/

/*

  Note: it's VERY important that we do something somewhere in the loop that changes part of our loop condition.
  If we don't then if the answer was true the first time, it will be true every time, and the loop will never end....

*/

/*
countdown_number = 5;
while ( countdown_number > 0 ) {
	console.log("Countdown is now: " + countdown_number); //<- we'll never get past this line!
}

console.log("Time's up!");

*/


/*

  It's very common for us to know exactly how many times we want a loop to run, such as when we're counting
  or checking each thing in a group with a known size.

  JavaScript gives us a shortcut to write a loop that counts this way, called a "for" loop:

 */


for ( let i=0;  i<10;  i=i+1  ) {

    /*
        A for loop declaration has three parts, each separated by a semicolon:
             - declaring a new variable
             - testing a boolean expression
             - performing some "incrementor" statement to update the variable
     */
    console.log("The value of 'i' is now: " + i);
}


/*
Any time we have a for loop, we can make an equivalent while loop by:
   - moving the variable declaration above the loop
   - testing the exact same boolean expression
   - moving the "incrementor" statement to the bottom of the loop
 */


let i=0;
while ( i < 10 ) {
    console.log("In the while loop, the value of 'i' is now: " + i);
    i=i+1; 
}



/*
Sometimes, we have loops where the loop keeps repeating until a certain condition is met, 
but we don't necessarily know how many attempts it's going to take:
*/


let user_entered_correctly = false;

while ( !user_entered_correctly ) {
	let guess = prompt("Enter the secret password: ");
	if ( guess == "bumblebee") {
		console.log("You got it!!!");
		user_entered_correctly = true;
	} else {
		console.log("Try again!");
	}
}


/*
In addition to "validation loops", that keep repeating until a user gets something *right*, we
can also have "command entry loops", that keep repeating until a user enters the command to leave:
*/

let user_command = "";


while ( user_command != "quit") {

	user_command = prompt("Enter a command: ");

	if ( user_command == "add") {
		let first = prompt("Enter a number: ");
		let second = prompt("Enter a number: ");
		let sum = parseInt(first) + parseInt(second);
		console.log(first + " plus " + second + " = " + sum);


	} else if ( user_command == "subtract") {
		let first = prompt("Enter a number: ");
		let second = prompt("Enter a number: ");
		let difference = parseInt(first) - parseInt(second);
		console.log(first + " minus " + second + " = " + difference);


	} else {
		console.log("Invalid command!");
	}

}

/*
Note, because "input validation" and "command entry" loops don't have an incrementor, we can't
express them as for loops! There are many loops that test complex conditions, sometimes even involving
multiple different variables. All of these can be expressed ONLY with while loops.

Every for loop can be a while loop. Not every while loop can be a for loop.
*/