

/*
Objectives for Today

By the end of the day, you will:
    * Understand how to create an abstract model of a real world problem.
    * Identify the object-oriented and function-oriented approaches to software design.
    * Discover how to analyze the quality of a solution in terms of modularity, correctness, and robustness to change.
    * Practice data modeling on a real world problem.
 */



/*
Vocabulary for the Day 

Domain - In programming, a domain refers to the real-world subject of a program and the associated knowledge and assumptions
that come with subject expertise. 

Model - A model refers to the simplified view of a domain we use when writing code. Models list the relevant data and 
behaviors for solving a problem programmatically, but are not always fully representational of their real-world counterparts. 


Abstraction - Abstraction is the process of taking a real world domain and simplifying it into a model that can be used
in code.

*/





public class Main {


    public static void main(String[] args) {

        /*
        Even experienced programmers talk about "analysis paralysis" -- the feeling that having too many choices
        when you want to solve a new problem can be almost paralytic. Today, we are going to investigate how to do
        some of this reasoning before we code to select the right tools for the job!

        To help with this process, we are going to introduce the concept of 'factoring': every program, no matter
        how complex, can be described as just a single main function held in just a single class! Factoring is the
        process of taking that one big sequence of instructions, and repeatedly breaking it down into smaller and smaller
        parts. (Sort of like how factoring in math is breaking a big number up into smaller factors we can multiply together.)

        There are two dimensions on which we can factor a program:
            - we can determine how to break data up into objects
            - we can determine how to break instructions up into smaller functions


        Today, we are going to focus on the first of these: object-oriented factoring. Last time (with our Restaurant
        program, we did Top Down Object-Oriented Factoring -- we started with a word problem, identified ideas, then
        wrote classes and decided on attributes for each of these. This time, we'll look at Bottom Up Refactoring, starting
        from the data we have.)


        In order to factor our data, we must first identify what our goal is with a program, and what data we have that
        matters for solving that goal. This process of taking a real world thing and stripping away everything to create
        a model for our program is called "abstraction".

            The first step of abstraction is summarizing -- try to take your goal, and put it into a single sentence:

               "I want to make a program that calculates student grades for assignments."
               "I want to make a program that lets a user enter colors into locations to print a Painting."
               "I want to make a program that finds members of congress based on the terms they were elected for."
               "I want to make a program that can index the payroll for state employees."



            The next step of abstraction is elaborating: now that we have a clear goal, we want to brainstorm all the
            SIMPLE pieces of data that might be part of this problem -- note: it should be immediately clear what Java type
            each piece is. If it isn't, try breaking that piece of information down further until you get something that
            is clearly a string, a number, a boolean, or one of our other known data types (e.g LocalDate.)

            Work up from the constraint of "these are the types I know how to represent", not down from "this is the
            type of data I think I have."


           There are two crucial features of abstracting a model:
               - First, the model is less detailed/simpler than the original problem (so that it can be used efficiently)
                  + "just the important bits" are kept in the model
               - Second, it is going to be composed from the data/types I have on hand (so that it can be made quickly)


        When we perform abstraction, there are a few things to consider throughout the process:
            - Does this piece of information matter for the problem I'm trying to solve?
            - What is the simplest way to express that piece of information?
            - What other data does this piece of information get used with?

                    For a Congress program with the original goal of "I want to make a program that finds members of 
                    congress based on the terms they were elected for.":

              - String: a congressperson has a name
              - String: a congressperson has a named party 
              - String: a congressperson belongs to a state with a name (can this also change over the course of their tenure?)
              - String: a congressperson has a named position (But this only lasts a certain amount of time... can they have more than one?)
              - LocalDate: a congressperson has a birthday
              - LocalDate: a congressperson was elected on a particular date  
              - LocalDate: a congressperson has a term end on a particular date
              - Number: a position has a number of years it lasts for after an election
              - String: a position is for a chamber of congress with a name 
              - String: a position serves for a district
              - String: a party has a named chairperson
              - String: a party holds a number of seats 
              - String: a congressperson has a committee assignment

            Often when we're using the Bottom Up approach, we're starting from an actual data file that's giving us
            many/most of the pieces of information we can identify.



            NOTE: you can express the same idea at different levels of detail. A state could an entire object with
            a great deal of information about the state, or it could be just a single name that's a string. When thinking
            about this abstraction, review whether each piece of data you've come up with helps with the single sentence
            problem you're trying to solve.




            Once you've brainstormed all the pieces of data relevant to your problem, start grouping those pieces. When
            considering grouping, ask the following questions:
                 - Are these two pieces of information related to the same real world 'thing' (a person, an object, an event or idea?)
                 - Are these two pieces of information likely to be used together? (two numbers or dates used in the same calculation...)

                 - Does an entire one of these 'groups' get used by another group? (Do you need access to all the details?)



                 NOTE: This process is *iterative* -- much like a rough draft of an essay, you will
                 keep revisiting and refining until you are happy with the result.


                 Don't be constrained by the way you originally phrased the data in your brainstorming. You may
                 find out that something you thought was part of one of your "groups" of data is actually part of another
                 group *used* by that data.  When you're doing the brainstorming this time, feel free to use an entire one of
                 these groups as a type, the way you used Strings or Numbers previously.



            CongressPerson
              - String: a congressperson has a name
              - LocalDate: a congressperson's birthday
              ----------------BEFORE-------------------
              - String: a congressperson has a named party  //<- when you notice that one of your groups relates to one of the attributes
                                                            // you're adding here, that might be a sign that the field of this "thing"
                                                               is actually an object of the other type
              - String: a congressperson has a named state
             -----------------AFTER-----------------------------
              - Party: a congressperson has an associated Party
              - State: a congressperson has an associated State
              ----------------------------------------------


             - String: a congressperson has a named position  
             ----------------BEFORE------------------
             - LocalDate[]: a congressperson was elected on a particular date   //<- when you notice yourself making 'parallel arrays', that might be a sign that
             - LocalDate[]: a congressperson has a term end on a particular date //I'm actually building a group of objects
             ----------------AFTER-------------------
              - Term[]: the terms a congressperson was elected for //<- we recognize you could be elected more than once, and all the details of that election are here


              Party
              - String: a party has a name
              - String: a party has a named chairperson
              - String: a party holds a number of seats

              State
              - String: each state has a name
              - String: each state has a named governor
              - Number: each state has a population count
              - String: each state has a named state flower 



              Term
              - LocalDate: a congressperson was elected on a particular date  
              - LocalDate: a congressperson has a term end on a particular date




          ------------ AFTER ONE ROUND OF DRAFTING ----------------------


          CongressPerson
              - String: a congressperson has a name
              - LocalDate: a congressperson's birthday
              - String: a congressperson has a named position  
              - Term[]: the terms a congressperson was elected for
              ----------BEFORE-----------------------------
              - Party: the details about a party           //<- wait... can this change election-by-election. If so, maybe this isn't a detail of a PERSON, but a TERM
              ----------AFTER, we move it to Term-------------------------------

              Party
              - String: a party has a name
              - String: a party has a named chairperson
              - String: a party holds a number of seats

              Term
              - LocalDate: a congressperson was elected on a particular date  
              - LocalDate: a congressperson has a term end on a particular date
              ---------AFTER realizing some of our details change each election ----------
              - State: the state the congressperson was elected for
              - Party: the details about a party   
              ------------------------------------



    --------------- AFTER TWO ROUNDS OF DRAFTING ------------------------------

          CongressPerson
              - String: a congressperson has a name
              - LocalDate: a congressperson's birthday
              - String: a congressperson has a named position  
              - Term[]: the terms a congressperson was elected for


             ////DELETED AFTER WE REMOVED UNNECESSARY ATTRIBUTES////////
             State
              - String: each state has a name
              ----- REALIZATION: state biographical info doesn't help me find congresspeople --------------
              - String: governor ///<- does this actually solve the problem?
              - String: flower ///<- do we need this directly any way to find people?
              - Number: population  ///<- is this ever relevant? 
              ----- AFTER: we may delete all these attributes --------



              Party
              - String: a party has a name
              - String: a party has a named chairperson
              - String: a party holds a number of seats

              Term
              - LocalDate: a congressperson was elected on a particular date  
              - LocalDate: a congressperson has a term end on a particular date\
              -------- BEFORE: We condensed state down to a single attribute ----------------
              - State: the state the congressperson was elected for
              --------- AFTER: We can delete the state class and make it just a string property here ----------
              - String: two character name of a state
              ---------------------
              - Party: the details about a party   


            ------------ AFTER THREE ROUNDS ----------------



          CongressPerson
              - String: a congressperson has a name
              - LocalDate: a congressperson's birthday
              - Term[]: the terms a congressperson was elected for


          Term
              - LocalDate: a congressperson was elected on a particular date  
              - LocalDate: a congressperson has a term end on a particular date
              - String: the name of the state they ran for
              - String: the name of the party that a candidate ran under
              - String: a congressperson has a named position  





 
             As we do our iterative brainstorming, we're looking for attempts to simply. In particular, we're looking
             for a few things in this round of simplification:
                  - Is the same piece of information represented in two different places? (If I have one piece of
                   information, can I figure the other one out without storing anything extra?)
                       + If this is the case, maybe one of the two is not actually a new piece of data, but a behavior (like a specialized getter!)
                         == Make sure you note these down so you can implement them later!
                  - Do any of my groups not get used by any other groups?
                       + If I defined a whole group, but I'm not using that group anywhere, maybe it doesn't actually help
                         me solve the problem, or I might be missing a bullet point that connects two ideas!
                  - Does one of these groups have a single relevant detail that uniquely identifies it?
                       + If this is the case, maybe I can simplify by removing the whole group and replacing it where
                          used with just the one important detail.


        When we go through and do all of this, we're left with just a few groups with simple attributes.

        Once we've done this, we have objects and it's time to make classes!
            Each piece of data becomes a class attribute, each 'specialized behavior' I can figure out from other details becomes a method!


        */

    }
}


