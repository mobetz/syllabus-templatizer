
/*
Objectives for today

By the end of today, you will:
   * Identify the concept of a key-value dictionary in scripting.
   * Understand how key-value dictionaries let us store associations between two different types of data.
   * Know the syntax for creating, accessing, and modifying dictionaries.
   * Evaluate when the use of dictionaries is appropriate and what functions exist for interacting with dictionaries.
*/


/*
Last class, we learned about lists.

Lists allow us to store groups of related data in a single variable:
 */


let students = ["Alejandro", "Billy", "Christie", "Dylan"];


/*
When we wanted to fetch something out of our list or put something in our list, we used
[square brackets] with a number to indicate which 'slot' of the list we wanted to access:
*/

let the_third_student = students[2];



/* Sometimes, we don't just have groups of things. Instead, we have pairs of related things.

    For instance, instead of just storing a list of students, we might have each student and the grade they have
    in the course.

*/

  students = ["Alejandro", "Billy", "Christie", "Dylan"];
let grades =          [97,      92,         89,      97];

/*

If I tracked this as two separate lists, when I wanted to talk about the grade for a particular student, I need to 
be careful to use the same index for each of my students....

*/


for ( let i=0; i<students.length; i++) {
   let next_student = students[i];  //<- this will get me each student one at a time
   let grade = grades[i]; //<- this will get me the grade from the same numbered slot as the student because I'm using the same 'i'

   console.log(" - " + next_student + "'s grade is: " + grade);

}



/*

However, this isn't great. If I have to maintain both of these lists it's easy to make a mistake and user the wrong index.
If someone drops the class, my grades and students might no longer 'line up' if I only removed one of the two values.

When we have two groups of related data like this, instead of using multiple lists, we can use a dictionary (also called a
    "map" or an "associative array") to store it:
*/


let grade_list = {
   "Allie": 98,
   "Quinten": 92,
   "Tae": 89,
   "Harold": 89
}
   

let word_definitions = {
   "apple": "a red fruit that grows on trees.",
   "programming": "the coolest part of STEM"
}

/* Our dictionary uses {curly braces} instead of the square braces that our array used. Each individual
   pair in our array has two parts -- a key, which is the thing that we will use to look up our data, and
   a value, which is the thing that we want to store about the key. We put a colon between the two, and a
   comma after each pair.


     To get things out of our dictionary, we use the exact same [square bracket] notation that we do for
     arrays, but instead of a number, we put a key:
   */


let allies_grade = grade_list["Allie"];
console.log("Allie got a " + allies_grade + " in the course!");


grade_list["Michael"] = 70;

/*

NOTE: The keys in  a dictionary HAVE to be unique. You cannot have two students with the same name, or Javascript wouldn't
know which person you're trying to look up and get a grade for.

*/


console.log("All the grades I know about are: " + JSON.stringify(grade_list, null, 2));


/*
However... if I try to add Quinten a second time:
*/

grade_list["Quinten"] = 100;


console.log("After trying to change an already existing key, I get: " + JSON.stringify(grade_list, null, 2));

/*
If we run this program, we can see the old 'value' for Quinten was thrown out entirely! We replaced the 92 with a 100.
*/


/*
      When we have a dictionary object, we can ONLY go from key->value, not from value->key, because our
      values aren't guaranteed to be unique...

      For example, in our grade_list, if two students both have a 97, and JavaScript wouldn't know which person
      we were talking about!
    */


/* Just like our lists, dictionaries also have some helpful functions we can use: */


//We can use the "in" keyword to check if a particular key is in our dictionary:
let does_gradelist_contain_zane = "Zane" in grade_list;
console.log("Is Zane in our grade_list?: " + does_gradelist_contain_zane);


if ( ! ("SomeFakeStudent" in grade_list) ) {
   console.log("Hey, SomeFakeStudent never registered for this course!");
}

if ( "Quinten" in grade_list ) {
   console.log("Quinten's grade is: " + grade_list["Quinten"]);
}

//We can use the Object.keys() function to get back just an array of the 'keys' from our dictionary:
let student_names = Object.keys(grade_list);
//..And likewise, the .values() function to get back an array of the 'values':
let all_grades = Object.values(grade_list);

console.log("The students in the grade_list are: " + student_names);
console.log("The grades in the class are: " + all_grades);



/* We also have JSON.stringify(), that lets us convert our object into something readable as text
   (which is very useful for debugging!):  */

let grades_as_text = JSON.stringify(grade_list);
console.log("All of our grades are: " +  grades_as_text);




/*

     We can also use an "in" version of our foreach loop with dictionaries:
       When we do this, JavaScript gives us each key in the dictionary, because we can use those keys to get the values:
 */



for ( let student in grade_list ) {
    let that_students_grade = grade_list[student];
    console.log(student + "'s grade was: " + grade_list[student]);
}




/* Just like arrays, our dictionaries can also be built of other dictionaries and arrays: */


let grade_roster = {
    "cs118": {
        "Allie": [97,95,93,96],
        "Michael": [89, 93, 91, 87],
        "Zane": [71,92, 78, 94]
    },
    "cs120": {
        "Quinten": [94, 92, 96, 93],
        "Carlos": [82, 85, 81, 82],
        "Susanne": [87, 0, 94, 100]
    }
};



/*
In this case, I have to use the keys first for a course, then for a student, to get back the whole group of grades:
*/
let allies_first_grade = grade_roster["cs118"]["Allie"][0]; //<- we use multiple pairs of of square brackets, 
                                                            //exactly the same way we did with our 2D arrays


/* Let's say we wanted to loop through each course, and for each student in that course, calculate a final grade
from this list of grades...
*/

for ( let course in grade_roster ) {
    let course_details = grade_roster[course]

    console.log("\nCourse -- " + course.toUpperCase());
    for ( let student in course_details ) {

        console.log("  " + student);
        let grades = course_details[student];


        let total_score = 0;
        let grade_count = 0;
        for ( let grade of grades ) {
            total_score = total_score + grade;
            grade_count = grade_count + 1;
        }

        console.log("     Final Grade: " + ( total_score / grade_count ) );
    }
}