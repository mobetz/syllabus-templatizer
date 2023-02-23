
/*
Objectives for Today

By the end of today, you will be able to:
    * Define "top-down design"
    * Describe how to break a programming task down into smaller sub-tasks
    * Create design plans for functions before writing code
 */


/*
Vocabulary for the Day

Top Down Design - Top down design is a problem solving strategy in programming where we 
start with a high level verbal description of a problem, and work on breaking that problem
up into small, manageable pieces.

*/


/*

So far, we've learned a few types of instructions that help us communicate with our computer:
    * Variables let us store information from line to line
    * If statements let us describe things that may or may not happen depending on conditions
    * Functions let us create groups of instructions we can easily refer to later


However... it isn't always obvious how we can take the description of an entire process, and distill
it down into a sequence of these simple steps.


Today, we are going to focus on practicing these instructions and using them to solve real problems.

*/



/*

As an example, let us start with a 'word problem' of a program we might want to write:


   "
    I want a program that checks a user's age and tells them what they are allowed to do. 

    When the user runs the program, it will pause and ask them for their name and their
    birthday. Once the program receives these details, it will then take that birthday, 
    and try to split off a "year" part. Then, if the birthyear is....

         -  more than 21 years ago, tell the user they are allowed to visit a club.
         -  more than 18 years but less than 21 years, tell them they can drive, but not drink.
         - less than 18 years ago, tell them they are still a minor!
   "


On its surface, it looks likle this program is doing a lot! There's talk about user inputs,
manipulating text, doing comparisons, and printing results... While trying to solve the whole
problem at once is overwhelming, we can break this down into successively smaller pieces.


First, we can set a "main goal" for ourselves:


   Goal: My program needs to ask for name and age, and print back permissions.



Afterward, we can split this goal into some subgoals:


  Subgoal 1: I need to ask for inputs from a user.
  Subgoal 2: I need to figure out how to turn a birthday into a birth year.
  Subgoal 3: I need to compare a birth year/age to some target dates/ages, and decide on the permissions they have.




I can even split each of these goals further!:

  Subgoal 1: I need to ask for inputs from a user.
      Subgoal 1.1: I need to prompt the user for a name with a function.
      Subgoal 1.2: I need to save the name the user entered.
      Subgoal 1.3: I need to prompt the user for a birthday a name with a function.
      Subgoal 1.4: I need to save the birthday the user entered.
  ...



Taking a whole problem and breaking it down into smaller steps can sometimes make it much
more obvious how those instructions can be written in code. This strategy of problem is
called "top down design", because we start with one main goal at the 'top', and then
keep breaking that problem down into smaller and smaller pieces.



However... not all subgoals are equally helpful. There are some qualities to keep in mind when picking
a subgoal:

     - How would I describe the "inputs" and "outputs" of this goal? In other words... 
              + what do I need to start working on this subgoal? (Maybe nothing, maybe details from a previous goal...)
              + What do I end with when I've accomplished it? (Is it a single piece of data, a whole group of variables...?)
     - How does this subgoal relate to the instructions I know how to write?
              + Near the "top" of our design, goals should be verbal descriptions of a real world problem.
              + Near the "bottom" of our design, goals should be very close to the code we know how to write.


Right now, we know how to write just a few types of instructions:

        -           Assignment: Save <some value> into <some variable>.                        (e.g  x = 4 )
        -        Function call: Call <some function> and save the result into <some variable>. (e.g  result = prompt("Enter:") )
        -         Conditionals: If <condition is true>, then <follow some instructions>.       (e.g  if (sunny==true) { ... )
        - Function declaration: When I mention <function> do <some instructions>.              (e.g  function add_numbers(x,y) { ... )


If we look at our sub-sub-goals from before, we can see how they match up to these types of instructions:

  Subgoal 1.1: I need to prompt the user for a name.          <- the prompt() function can help me with this
  Subgoal 1.2: I need to save the name the user entered.      <- assign the result of prompt() into "user_entered_name"
  Subgoal 1.3: I need to prompt the user for a birthday.      <- the prompt() function again!
  Subgoal 1.4: I need to save the birthday the user entered.  <- assign the answer from 1.3 into "user_entered_birthday"



This is the beauty of top-down design -- when followed correctly, it gives us near exact instructions on each line of
code that we want to write! We can even take our goals and subgoals, and turn them into one giant outline to help
us plan:



   Goal: My program needs to ask for name and age, and print back permissions.

         = I need to ask for inputs from a user.       <- I start with: nothing. I end with: "user_name" and "user_birthday" variables.
              + I need to prompt the user for a name.  
              + I need to save the name the user entered.
              + I need to prompt the user for a birthday.
              + I need to save the birthday the user entered.

         = I need to figure out how to turn a birthday into a birth year.  <- start: user birthday as text (e.g "01-16-2020") End: birth year number
              + I need to find the last time a dash appears in the birthday.
              + I need to save everything from that last dash to the end of the string into a birthyear_as_text.
              + I need to convert birth year to a number and save it in a final birthyear.

         = I need to compare a birth year to some target dates and get some permissions.  <- Start: birth year number   End: Permissions text
              + I need to save 2023 minus the birthyear as a number of years old.
              + If the number of years old is more than 21:
                  - save the club text.
              + Otherwise, if the number of years old is more than 18:
                  - save the driving text.
              + Otherwise:
                  - save the minor text.

         = Display the permissions back to the person using the program.  <- Start: permissions text   End: Goal is met!!!



 Now, we have an outline for our entire program! Each one of our smallest bullet points represents ~1 line of code,
 and each gets us one step closer to solving the big main problem we were originally given.





 However, the benefits of top down design don't end here. Now that we've got this "outline", we can also use it to 
 identify ways to make our code more organized. Each subgoal is describing a task that helps us solve one part 
 of a bigger problem. If any of those parts look reusable, particularly complicated, or isolated from the rest of
 what we need to do, then we can describe those instructions as a small, separate group.


 This is a perfect fit for a function!


 Take for example our subgoal #2:

        = I need to figure out how to turn a birthday into a birth year. <- start: user birthday as text   End: birth year number
              + I need to find the last time a dash appears in the birthday.
              + I need to save everything from that last dash to the end of the string into a birthyear_as_text.
              + I need to convert birth year to a number and save it in a final birthyear.


This outline says we need one thing to solve this subgoal: the birthday text.
When the whole subgoal is finished, we have one final answer: the birth year as a number.


As a function, we can write that as:
*/

function birthday_text_to_birth_year(birthday_text) { //<- parameters come from the "what do we need to solve this"

    let birth_year;  //<- my final answer is going to be a birth year, so I know I need a variable for that right away.


    //Now, our function just has to implement all the sub-subgoals! We can even paste in the points of our template:


    //+ I need to find the last time a dash appears in the birthday, and save that position. (find the last dash in "04-16-2020")
        let last_dash_position = birthday_text.lastIndexOf("-"); //<-- The lastIndexOf() function solves this problem for us!


    //+ I need to save everything from that last dash to the end of the string into a birthyear_as_text.
        let total_number_of_letters = birthday_text.length; //<- the length attribute tells us how many letters are in text!
        let year_text = birthday_text.substring(last_dash_position+1, total_number_of_letters); //<- substring gets part of a string from 
                                                                                              //some start position to the ending position

    //+ I need to convert birth year to a number and save it in a final birthyear.
        birth_year = parseInt(year_text);



    return birth_year;  //Since "birth_year" was my intended final answer for this subgoal, I will return it now.
}



/*
Writing functions this way can help make it more obvious what the parameters and return values of a function should be:
they map to the "inputs" and "outputs" of our subgoal!

We can even write our final program from the top down, leaving things 'stubbed out' as function calls until we are ready
to work on them later:
*/


import Prompt from 'prompt-sync'
const prompt = Prompt({});


function main() {

   //Goal: My program needs to ask for name and age, and print back permissions.

     //SG1: I need to ask for inputs from a user.  
        let name = prompt("What is your name: ");
        let bday = prompt("Enter your birthday as MM-DD-YYYY (e.g 04-16-2004): ");


     //SG2: I need to figure out how to turn a birthday into a birth year.
        let birth_year = birthday_text_to_birth_year(bday) //<- here I give the function the details that 'subgoal' requires
                                                           // and save the 'answer' into a variable


     //SG3: I need to compare a birth year to some target dates to get back permissions.
        let permissions_text = birth_year_to_permissions(birth_year); //<- I already know what my third goal will require
                                                                     // and return, even though I haven't written it yet!


       console.log("----------------------------");
       console.log("Thank you " + name + ".\n"
               + "Since your birth year is " + birth_year + ", your permissions are:\n " 
               + permissions_text);
       console.log("----------------------------");
}





/*
Then later, when I'm ready, I can fill in those functions for the other subgoals:
*/

function birth_year_to_permissions(year_num) {
    let permissions = "";


   // + I need to save 2023 minus the birthyear as a number of years old.
    let years_old = 2023-year_num;

    if ( years_old >= 21 ) {
        // + If the number of years old is more than 21:
         // - save the "allowed in" text.
        permissions = "You are allowed to enter the club!";
    } else if ( years_old >= 18 && years_old < 21 ) {
       // + Otherwise, if the number of years old is more than 18: 
        //  - save the driving text.
        permissions = "You can drive, but you cannot enter.";
    } else {
        // + Otherwise:
         //  - save the minor text.
        permissions = "You are still a minor! You cannot even drive without a permit."
    }


    return permissions;
}




main();