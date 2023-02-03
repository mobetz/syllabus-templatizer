

/*
Objectives for Today

By the end of today, you will:
  * Identify the syntax for declaration, assignment, and control flow in JavaScript.
  * Understand the ramifications of dynamic typing.
  * Be able to create objects and access properties on objects.
 */



/* Vocabulary for the Day

Dynamic Typing - A programming language that is 'dynamically typed' does not prevent you from changing the type of data
saved in a variable after that variable is created. The opposite of dynamically typed languages are statically typed
languages (like Java), that force you to only ever save one kind of data in a given variable.

Control Flow - Control flow is any instructions we give to a program that change the order other instructions will run.
Functions, Branches, Loops, and Exception Handling are all examples of control flow.

 */



/*
One of the simplest things we can do in our code is declare a variable. Since JavaScript is dynamically typed,
we don't need to say what *kind* of variable we are using, just that one will exist.  To declare a variable, we
put the word 'let' before our variable name:
 */

  let first_name;


/* Note: JavaScript will not stop you if you forget to declare a variable. It will try to silently create a variable
for you wherever it can. However, this can lead to unexpected behavior in your program, so it's best practice to
always declare a variable before the first time you use it (or on the same line that you use it for the first time.)

You may also see the word 'var' used instead of 'let' -- var is an older keyword we used to declare variables --
when variables are declared with var, JavaScript won't stop you from 're-declaring' them a second time, and they
will get "hoisted" up to the start of whatever function they're declared in. Let will behave much more similarly to
block-scoped variables from Java, Python, and other programming languages.
 */


/* Assignment, the act of saving data in a variable, works exactly like it does in other languages:
*    - the place you want to save data goes on the left of the =
*    - the data you want to save goes on the right of the =
*/

   first_name = "Matthew";



/* Unlike in Java, JavaScript won't stop us if we try to change what type of thing is stored in our variable
after we make it: */

  first_name = 4;
  first_name = true;


/* I recommend you still pick a type for your variables when you create them, and try your best not to change the
type of data a variable stores. */


/*  Different data types still exist, even if JavaScript doesn't enforce them. Fortunately, we have functions to
convert one type of data into another:
 */
let the_word_four = "4";
let the_number_four = parseInt(the_word_four);



/* JavaScript is a scripting language; this means that, like in Python, we don't need to define a main function or a
class to hold our code -- we can start writing instructions immediately!


 In JavaScript, we can declare a function by using the "function" keyword, followed by the function's name and
 parameter list. Unlike Java, we don't have to worry about return types (but we should still keep good track of them for
 our own sanity!):
 */



function  add_two_numbers( first,  second ) {
    //to send something back from a function, we use the return keyword:
    let sum = first + second;
    return sum;
}

let returned_value = add_two_numbers(4, 6);
console.log("The sum of two numbers is " + returned_value);



/* To store a group of things in a single variable, we can use a list: */
    let grocery_list = [];

/* I can even create a list with multiple pre-populated values: */
    grocery_list = ["apples", "bread", "milk"];


/* A list offers some standard methods for working with data.

We can 'push' things onto the end of a list to add them to the list:
*/

grocery_list.push("chicken");
grocery_list.push("broccoli");
grocery_list.push("eggs");

console.log("After adding things, our list has: " + grocery_list);


/* We can access elements of a list using [square bracket] notation: */
let third_thing = grocery_list[2];
console.log("The third thing on our list is: " + third_thing);


/* We can also do things like sort: */
grocery_list.sort();
console.log("After sorting, our list has: " + grocery_list);

/* We can 'pop' to remove things from the end of the list, or 'shift' to remove things from the beginning: */
let first_removed_thing = grocery_list.shift();
let last_removed_thing = grocery_list.pop();


console.log("The list now has: " + grocery_list + ", because we removed: "
    + first_removed_thing + " and " + last_removed_thing);



/* We have a lot of the same tools available to us to control the flow of our programs that we do in other languages.

We can create branches to conditionally execute statements:
 */

let today_is_sunny = true;
let today_is_rainy = false;

if ( today_is_sunny === true ) {
    console.log("Don't forget sunscreen!");
} else if ( today_is_rainy === true ) {
    console.log("Don't forget your umbrella!");
} else {
    console.log("Just another cloudy day...");
}


/* One important note: In JavaScript, when we use ==, the language will attempt to do coercion to test equality.
This means that some unexpected comparisons will evaluate to true. If we want to skip this behavior, we can use
=== instead:
 */

console.log("Does 0 == False?: " + (0 == false));
console.log("Does 0 === False?: " + (0 === false));



/* We can also use loops to repeat a block of code as long as a condition is met: */

let current_number = 0;

console.log("\nCounting with a while loop:")
while ( current_number < 10 ) {
    console.log("Counting: " + current_number);
    current_number = current_number + 1;
}



console.log("\nCounting with a for loop:")
/* We can write loops that are guaranteed to execute a certain number of times with for: */
for (let i=0; i<10; i++) {
    let text_to_be_outputted = "Counting: " + i;
    console.log(text_to_be_outputted);
}



/* If we're looping over a group of things, we can use a foreach loop: */
console.log("\nGrocery List: ")
for ( let next of  grocery_list ) {
    console.log("   - " + next);
}

console.log("\nGrocery List (with basic for):")
for ( let i=0; i<grocery_list.length; i++ ) {
    let next = grocery_list[i];
    console.log("   - " + next);
}



/* If we want an associative array between keys and values, we can create a dictionary object: */

let students_to_grade = {
    "Michael": 89,
    "Kaelya": 94,
    "Xander": 92,
    "Kenrith": 84,
    "Michelle": 89,
    "Xander": 98   //NOTE: keys are unique. If we put the same key more than once, we 'overwrite' the old value
}

/* To fetch things out of the dictionary, we can use the same [square bracket] notation we used for lists: */

let xanders_grade = students_to_grade["Xander"];
console.log("Xander's grade is: " + xanders_grade);


/* We can also use dot notation to refer to values of keys that are strings: */
xanders_grade = students_to_grade.Xander;

/* We can also modify or add keys with the same notation: */
students_to_grade["Anya"] = 95;
console.log("After the change, our dictionary has: " + students_to_grade); //NOTE: objects only print [object Object]
console.log("After the change, our dictionary has: " + JSON.stringify(students_to_grade)); // This lets us "peek inside"
                                                                                     //By converting the object to text

let json_string = "{\"apple\": 0.49, \"banana\": 0.30 }";
let parsed_object_out_of_text = JSON.parse(json_string); //This works in the other direction!
console.log("After converting our prices from text, we have apples costing: " + parsed_object_out_of_text["apple"]);

/* Note: In JavaScript, all objects are just dictionaries. This means the dot operator and [square brackets] are
both doing the same thing!
 */

/*

Keep in mind that we can only access and store things from key -> value. If we wanted to go in the other direction,
we would need our value to be a group of all the values that match that key:

*/

let grade_to_students = {
    89: ["Michael", "Michelle"],
    94: ["Kaelya"]
};




/* Strings are objects, just like in Java. They have many of the same methods you might expect: */
let colors = "red,orange,yellow,green,blue";
let first_color = colors.substring(0, 3);  //Note: substr's arguments are first index and LENGTH, not end.
let many_first_color = first_color.repeat(5);

let list_of_individual_colors = colors.split(",");




/* Classes can be defined with the 'class' keyword.
*
* Just like in Java, attributes go at the top, and methods go below. However, we don't need to use any visibility
* keywords like 'public' or 'private', and our methods don't need return types:
*/


class Rectangle {
    //ATTRIBUTES
    height;
    width;
    color;

    //METHODS
    constructor(given_height, given_width, given_color) {
        this.height = given_height;
        this.width = given_width;
        this.color = given_color;
    }

    calculate_area() {
        return this.height * this.width;
    }

 } //end of Rectangle class


/* Once we have a class, we can create objects of it with the constructor, just like we'd expect: */

let a_rectangle = new Rectangle(5,4, "red");

/* And call methods with the dot operator: */
let area = a_rectangle.calculate_area();

console.log("Our rectangle's area is: " + area);

console.log(JSON.stringify(a_rectangle));


let second_rectangle = new Rectangle(7,3, "blue");



console.log("First rectangle's area was: " + a_rectangle.calculate_area());
console.log("Second rectangle's area was: " + second_rectangle.calculate_area());