/*
Objectives for Today

By the end of today, you will be able to:
    * Identify the consequences of left-associativity in the dot operator.
    * Understand how the dot operator can be used directly on the return of a method.
    * Describe how we can use the dot operator to:
          - chain together transformations to data
          - configure special types of objects.
    * Write classes that enable dot chaining.
 */



import java.io.File;


public class DotChaining {
    public static void main(String[] args) {

          /*
        This semester, we have worked extensively with objects. Each one of these objects is
        constructed with some data, and then provides a group of associated functions that are
        supposed to work with this data.


        Whenever we've wanted to actually use any of these parts of our object, we've needed
        to use the dot operator, which always takes the form:

        some_object  . something_I_want_that_object_to_do();



        This entire method call expression ultimately gets replaced with whatever the result of the
        method is, allowing us to save the result onto an object:
         */

        File some_file;

        some_file = new File("hello.txt");

        boolean is_there_a_hello_txt = some_file.exists();
                                     //   ^         ^
                                    // object    relevant piece of object
                                   // \_______ _________________________/
                                  //          + turns into just the return of exists() (a boolean.)




        /*
        However, occasionally we've seen syntax that looks a little bit funky:

        object . methodOne()  . someOtherMethodFromSomeOtherObject()

          Today, we will be exploring more about what is actually happening when we write code like this.

          In the simplest example, sometimes we need to access a sub-object:
         */


        File this_code = new File("./DotChaining.java");

        String   current_folder =  this_code.getParentFile().getAbsolutePath();

        /* In this case, we have two different dots on this line, and the combined meaning is slightly subtle.
           We need to think about which dot happens first.

           In Java (and most object oriented languages), the dot operator is "left associative".
           This means that the meaning doesn't change if we put parentheses around each pair of dot and method
           starting from the left:

        */

        current_folder =  ( ( this_code.getParentFile() )  . getAbsolutePath() );

        String first_letter_of_Folder =  ( ( this_code.getParentFile() . getAbsolutePath()  . substring(0,1) ) );



        /*

        Another way to think about this is that dots on the left "happen first":

                    a_file . getMeTheFolderYouAreIn()  . getMeThePath()
                    \______ _________________________/
                           + returns the 'parent_file'
                                         \____________  ________________/
                                                       + get path is used with the 'parent file',
                                                             not with the original object!



          This makes sense, because we're also allowed to break up those chained dots across two lines:
         */

        File parent_file = this_code.getParentFile();  
        current_folder = parent_file.getAbsolutePath();        
        first_letter_of_Folder = current_folder.substring(0,1);     
 

        /*
        All three of the ways we wrote this program mean the same thing, putting both  method calls on one line
        just saved a bit of space!                  
        */



/*
        Even in your lab project, we might want something from an Assignment, but need to get it from the
        Transcript first:

        Transcript grades = new Transcript("Prog1", "Joe Smith", 2023, "Spring");
        grades.recordLab(20);
        grades.recordLab(50);
        int score_on_lab_1 = grades.getAssignment("Lab", 1).getPointsPossible();

        When dot chaining really becomes space saving is when we can start chaining it indefinitely:
         */


        String result = "     I wish I had a cute brown fox"
                            .trim()   //<- "I wish I had a cute brown fox
                             .replace("cute", "quick")   //<- "I wish I had a quick brown fox"
                              .concat(" who could jump over lazy dogs.") //"I wish I had a quick brown fox who could jump over lazy dogs."
                               .toUpperCase(); 

        System.out.println("The final string is: " + result);



        /*
        But if we look at this code, nothing different is happening here:



              starting_string . trim()  . replace()  .  concat()  .  toUpperCase()
              \_______________ ______/
                       trimmed_string
                       \________________ _________/
                                  substituted_string
                                    \_______________ ________/
                                                  longer_string
                                                  \_______________  _____________/
                                                               capitalized_string



              Each method call starts from the left, and applies one transformation, returning
              the resulting string back. Since each of these methods returns the same type of
              object as what it was called on, it feels like all these transformations are happening
              to one string.


             This "pipelining" of operations is one common way to use dot chaining.

             The other is when we want to make "ad-hoc" settable objects. Let's say, as an example, I wanted
              a new DiceRoll class. DiceRoll should be able to use methods to set things like:
                 - the number of dice to roll.
                 - the number of sides each die has.
                 - the number of 'best rolls' to keep.
              

              And I'm deciding I want each of these properties to be settable, but I don't want the user to HAVE to
              set all three of these values.

                */



        DiceRoll my_dice = new DiceRoll()
                                 .withDice(4)
                                 .withSides(6)
                                 .keep(3);


        Integer[] resulting_rolls = my_dice.roll();


        int total = 0;
        for ( int next_roll : resulting_rolls ) {
            total = total + next_roll; 
            System.out.println("You rolled a: " + next_roll);
        }

        System.out.println("The total roll is: " + total);


        
        /*
        Dot method chaining is never required. However, designing methods that work with chaining can make our
        code "feel more ergonomic" to people who want to use it, and help us understand what is happening when
        we read code written by others!
         */

    }
}