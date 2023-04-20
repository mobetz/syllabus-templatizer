
/*
Objectives for Today

By the end of today, you will:
   * Understand how we create and use object type variables.
   * Know the differences between classes and objects.
   * Identify the major features of a class: attributes and methods.
   * Identify dot notation in Javascript programs.
   * Use dot notation to access parts of objects.
   * Define Object-Oriented Programming.
 */



/*
Vocabulary of the Day

Class -- A class is a template that describes a collection of bundled data and functions. When we make data from that
         template, the created variables are called "objects".

Attributes - A attributes is a special form of variable that holds data an object "has". These are also called 'fields',
         'properties', or 'instance variables'.

Method - A method is a special function that holds instructions about something an object can "do".


Constructor - The constructor is a special method that helps set up an object. We call the process of creating an
               object "instantiation", and have to use the special "new" keyword.

Dot operator - We use the dot operator (a period: . ) to access some part of an object we've created
               (usually a method.) On the left of the dot we put an object; on the right of the dot we put
               the name of the part of the object we're interested in.
 */




/*
At the beginning of this semester, the first things we learned to do were create named 'slots' to save data (variables),
and created named groups of instructions we wanted our program to be able to follow (functions.)

However, sometimes, these variables and functions are related to each other. For example, we might want to save many
different details about a student.

Right now, we know how to do that with variables:
 */


let first_name = "John";
let last_name = "Smith";


/* This can be inconvenient and space-consuming, especially if I have more than one copy of this group of variables: */

let first_name2 = "Elena";
let last_name2 = "Wheeler";

let first_student = {
    first_name: "John",
    last_name: "Smith",
    final_grade: 95,
    courses: ["CS110", "CS118", "CS107"]
}


let second_student = {
    full_name: "Sarah Torres",
    grades: [89, 93, 101],
    courses_registered_for: ["CS110", "CS108", "CS116"]
}


/*
However, we had said that JavaScript dictionaries are just 'key-value' pairs, so we can't guarantee that every student we
make will have the same exact properties.
 */

let this_class = [first_student, second_student];

/*
This is bad, because we can't easily use all of our students as a group:
*/

for ( let student of this_class) {
   console.log("The next student is: " + student.first_name); // This will only work for one of our two students!
}


/*
 Instead, we might wish to group together all the related pieces of a student in one place. To do that, we can create
 a 'class'. A class is almost like a "recipe" for objects that describes all the parts that every single one of our
 students should have.

 To start a new class, we use the "class" keyword, followed by a pair of curly braces:
 */

class Student {

    /*
   Our Student class is going to work like a 'blueprint' for making a new student.

   Classes have two major parts.

     First, there are "attributes", which list all the data the class *has*.
     Afterward, there are "methods" which list all the things a class *can do*.

   Everything that's directly inside the {curly braces} of a class must be one of these two things.
     */

   //ATTRIBUTES
    /* Attributes work just like variables: they hold data of a specific type, and we have to declare them before
   we decide to start using them. However, since we know everything must be either a attribute or a method, we can
   skip the word 'let' for our attributes: */
   first_name;
   last_name;
   final_grade;
   courses;

   /*
   Attributes typically *do not* get assigned values right away when they're declared.

    This is because right now we're just describing a 'blueprint' of all the data a student has. However, every individual
    student is going to have a *different* name and grade.
     */



    //METHODS
    /*
    In addition to the data our class has, we also want to be able to describe a set of tasks our class knows how to do.

    Just like with our attributes, the class can assume that we're declaring a function if we put a name and then
    parentheses, so we can skip the word 'function' when we want to add a method:
    */
   register_for_class(name_of_class) {

        /*
        Inside a method, if we want to talk about one of our attributes, we can do it by using the special keyword "this".
        In this method, putting the word "this" will signify that we want to get the data about whichever student is currently
        registering for classes:
         */

      this.courses.push(name_of_class); //<- add the student's new class to their course list

   }



    generate_email() {
      let first_initial = this.first_name.charAt(0);
      let email = first_initial + "_" + this.last_name + "@massbay.edu";
      return email.toLowerCase();
    }


    /*
    The most basic thing every class needs to know to be able to do in Javascript is make a brand new object from the
    template. This special function always has the same name: "constructor":
     */
    constructor(given_first_name, given_last_name) {
        /*Often, most of what a constructor will be doing is 'setting up' the initial values of an object.

          Some of these values come from the input parameters to the constructor directly:
         */ 
      this.first_name = given_first_name;
      this.last_name = given_last_name;

        /*
        Others might have default values that start the same for every copy of the object:
         */
        this.courses = []; //all students start registered for 0 classes
        this.final_grade = 100; //all students start with a 100!
    }
}


/*
We've now created a template of all the things a student should have.

However, classes don't do anything on their own.  If a class is a "recipe", each object is like one specific meal you
make out of that recipe -- it has its own copy of every property of the class that represents the choices you made that
specific time.

In order to use the recipe, we call our constructor.

From outside the class, the name of our constructor is just the name of our class, but otherwise it works like any other
function, with one special difference:
 */

let templated_student = new Student("Caroline", "Reis");

/*
Before we call the constructor with the name of the class, we put the word "new". This lets Javascript know it should
be looking for a constructor inside a class with that name, not any other normal function.

Once we have our student, we can use the 'dot' operator to call any of the methods that are part of that object:
 */

let grade_from_templated_student = templated_student.final_grade;
console.log("The final grade of " + templated_student.first_name + " is: " + grade_from_templated_student);


/*
Each student gets their own copy of each variable!
*/



let another_student = new Student("Jesse", "Salvado");
console.log("There's a second student in addition to " + templated_student.first_name + 
   " who is called " + another_student.first_name);


   /* We can also use that same dot to call the 'methods' attached to each student:
   */
let student_email1 = templated_student.generate_email();
let student_email2 = another_student.generate_email();
console.log( "The two student emails are: " + student_email1 + ", " + student_email2);



/*
  Now, if we create multiple students, we can always be sure that each one of our students will know how to do the same
  things!

 */


 let many_students = [templated_student, another_student];


console.log("\nFull class roster:")
for ( let next_student of many_students ) {

    console.log("   " + next_student.first_name +" " + next_student.last_name + " email is: " + next_student.generate_email());
}


/*

One important thing to note: each different Student we make has its own copy of all the attributes in our class.

If we start registering our different students for classes, each one will have its own copy of that list:
 */

 templated_student.register_for_class("CS118");
 templated_student.register_for_class("CS107");


another_student.register_for_class("CS120");
another_student.register_for_class("CS241");


console.log("\nCourse registrations: ")
for ( let next_student of many_students ) {
    console.log("    " + next_student.first_name + ": " + JSON.stringify(next_student.courses) )
}


/*
We can see here that even though we're calling the same exact "register_for_class()" function on our two different students,
both students have their own independent course list.


This is not the first time this semester we've seen the 'dot' operator used to call a method.

It turns out, many of the other data types we've worked with this semester, like Strings and Arrays, are also objects
that Javascript created for us!

In addition to creating those objects the way we've seen already, we can also use the same 'new' keyword that we
used with our Student class:
 */
let some_list = new Array(5);  // <- this will make a new array with five "empty" spaces.
console.log(some_list);

some_list.fill(4); // this method puts '4' in every one of the empty spaces in our list!
console.log(some_list);