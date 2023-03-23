
/*
Objectives for Today

By the end of today, you will:
    * Describe how lists allow programmers to store groups of related variables in a single place.
    * Identify the syntax for creating, accessing, and modifying lists in Javascript, PowerShell, and Bash.
    * Understand different ways that for loops and for-each loops can be used in conjunction with lists.
    * Rewrite for loops as for-each loops and for-each loops as for loops.
    * Explain why lists work best with same-typed elements.
 */


/*
Vocabulary for the Day

  Array/List - An array (also called a "list") is a special variable type that lets us refer to many related
       pieces of data with a single name. The different entries in an array are often accessed using a
       numerical index and [square brackets].

 */



/*
Sometimes when writing a program we want to work with a collection of related data points.

Say, for instance, we wanted to represent every student in a class. Working with all of these students as
loose variables can quickly become unwieldy:
 */


let student1 = "Matt";
let student2 = "Konstantine";
let student3 = "Casey";
let student4 = "Regina";


/*
   If I wanted to print out every name in the class, I would need to keep track of all these variables and how many
   there are:
 */

console.log("Students in CS118: " + student1 + ", " + student2 + ", " + student3 + ", " + student4);


/* Say student2 dropped the class; we would need to keep track and remember to only print out students 1, 3, and 4
   from then on!


   When we have a set of related data that we want to store and access as a group, we can use arrays. If each
   variable is like a single locker for some data, we can think of an array almost like a filing cabinet: a single
   drawer that can hold many different objects of the same type.

   +------+
   |      <- "Matt" 
   |      |     student1 can fit one student  
   +------+      
    student1


   +------+------+------+------+------+------+------+------+------+------+------+
   |      |      |      |      |      |      |      |      |      |      |      |
   |      |      |      |      |      |      |      |      |      |      |      |
   +------+------+------+------+------+------+------+------+------+------+------+   
   array_of_students



   To make an array, we declare a variable like normal, then use a pair of [square braces]:
   */

   let cs118_class;
   cs118_class = [student1, student2, student3, student4];


   /* Inside the square braces, we put each 'thing' that is part of our group. For our code, the items of our group
    are each of the students we declared before. The array acts as a container that holds all the students.

  "Matt"  "Konst" "Casey" "Regina"
     |       |      |       |
   +-v----+--v---+--v---+---v--+
   |      |      |      |      |
   |      |      |      |      |
   +------+------+------+------+   
      0      1       2      3
   cs118_class



     Once the array has been created, each 'slot' in the array acts almost like a different variable we can access
     and mutate, just like any other variable. To do so, we put brackets after the name of the array, and
      put the index for the variable inside the braces:
 */

  let first_students_name  =  cs118_class[0];   //<- this says "go to the whole group, but then bring back just the thing in slot #0"

  /*


  "Matt"  "Konst" "Casey" "Regina"
     |       |      |       |
   +-v----+--v---+--v---+---v--+
   |      |      |      |      |       cs118_class
   |      |      |      |      |
   +------+------+------+------+   
      0      1       2      3
      |
      |
      |
      |
  +---v--+
  |      |
  |      |
  +------+  
  first_students_name
*/


console.log("The first student in our class is: " + first_students_name );
console.log("The second student in our class is: " + cs118_class[1] );


/* Note: the index for an array starts at 0, NOT 1. This means ordinal numbering is going to be one higher than
the index we use (the "first" thing is in slot 0, the "second" thing is in slot 1...) */


/* In addition to reading things from the array, we can also overwrite things in the array. */
/* If I wanted to change the name of the third student in the class:*/


cs118_class[2]  = "Allie";
console.log("After substituting in Allie, my list now has: " + cs118_class);



  /*

                  "Allie"
  "Matt"  "Konst" "/////" "Regina"
     |       |      |       |
   +-v----+--v---+--v---+---v--+
   |      |      |      |      |       cs118_class
   |      |      |      |      |
   +------+------+------+------+   
      0      1       2      3
                     ^
                     |




 Our array also comes with several helpful functions that let us do many common things we might want to do to our
 group:
 */


let a_new_student = "Yulia";
cs118_class.push(a_new_student); // array.push() lets us add another thing onto the end of our list:
console.log("After adding another student, our class has: " + cs118_class);

let starting_student = "Dylan";
cs118_class.unshift(starting_student); //array.unshift() adds something to the BEGINNING of the list:
console.log("Our student added to the front gives us: " + cs118_class);


// We can also array.pop() and array.shift() to remove things from the back or front of our list, respectively:
let removed_from_end = cs118_class.pop();
let removed_from_start = cs118_class.shift();

console.log("We removed " + removed_from_end + " from the back, and " + removed_from_start + " from the start.")
console.log("Now we have: " + cs118_class);


/*
We can even add or remove from the middle using a special method splice:
 */

let removed_position_middle = cs118_class.splice(2, 1);
console.log("After removing one thing starting in slot 2, we have: " + cs118_class);

cs118_class.splice(2, 0, "George");
console.log("After splicing one additional student into slot 2: " + cs118_class);



//There are also properties to show us how long our list is, and functions to sort or reverse the
//order of our things.

let size_of_class = cs118_class.length; //the array.length property shows us how many things are in our group!
console.log("Our class has " + size_of_class + " students!");


cs118_class.sort();
console.log("Our students sorted in alphabetic/numeric order are: " + cs118_class);
cs118_class.reverse();
console.log("In the opposite order, they will be: " + cs118_class);


/* The important thing to remember with all of these functions is that they all use the same dot notation.  
All of them need some group to already exist, and you put the name of the function after a dot when you use
the name of the group.

We've only scratched the surface of all the things lists can do. There's a complete "list" of all the list
functions available at: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array 
*/

/*
Groups are great, but the real benefit of the group is helping us avoid repetition. Specifically, we want a good
way to avoid having to do what we did back when we had "student1", "student2", "student3" all as separate variables.

Lists have the ability to repeat something for each thing in the list.
We could even loop over each item in our group with a special kind of for loop: the foreach loop.

To make a foreach loop, we use the same loop keyword "for", but something slightly different after:
*/

for ( let next_student of cs118_class ) {
    console.log("    The next time this loop runs, the next_student is: " + next_student);
}


/*
Any loop that we can express with a foreach loop, we can also express with a normal for loop, by using the length
of the list as part of our condition:
 */
 for (let i=0;   i < cs118_class.length; i=i+1) {
    let next_student = cs118_class[i]; //<- the first time, this will get cs118_class[0], the next time cs118_class[1]...
    console.log("  With a for loop, next_student is: " + next_student);
 } 



/* Say we wanted to represent a whole course list with a student roster for each.


    In this case, we wouldn't just have a group of things: we would have a group of groups of things!
    Fortunately, we are allowed to make lists where each thing in our list is also a list:
 */


let spring_2023_courses = [
    ["Quinten", "Tae", "Harold", "Casey"], //CS118 students  <- class 0
    ["Victor", "Brennan", "Sara", "Thom"], //CS120 students  <- class 1
    ["Gemry", "Ursula", "Francis", "Bob"]  //CS241 students  <- class 2
/*     ^         ^         ^         ^
   student0   student1  student2    student3
*/
];


/*
 When we have a group of groups, we have to be careful when trying to get out one individual item:
 */

let class_num=1;
let student_num=2;

let student_name = spring_2023_courses[class_num][student_num]; //<- I need to supply BOTH "which group", and THEN "which thing in that group"

console.log("The student we get from [" + class_num + "][" + student_num + "] is " + student_name);


let course_id = 1;



for ( let course of spring_2023_courses ) {
    console.log("Course " + course_id + ":");
    /*
    When I loop over a group of groups, each thing I get back is itself an array, so we can use it in its own "nested" inner loop:
    */
    for ( let student of course ) {
        console.log("  - " + student);

    }
    course_id=course_id+1;

}