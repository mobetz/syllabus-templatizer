

/*
Objectives for Today

By the end of today, you will be able to:
    * Explain the comparative benefits of collecting input from the command line,
        standard input, and files.
    * Describe how classes are organized around solving a real world problem (which
      does not often include receiving input.)
    * Design classes that avoid coupling their design to their source of input.
 */



/*
Vocabulary for the Day

Coupling - In computer science, "coupling" refers to a decision made when designing a
program that connects two components in a way that prevents future flexibility.

 */





import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class InputSources {


    public static void main(String[] args) throws FileNotFoundException {


        /*
        So far, we've talked about three different ways we can send data to a program
        when it runs:
            - We can send "command line arguments" in the terminal when launching a program (these get populated into args in main)

            - We can also collect additional inputs from the terminal using a Scanner
               connected to the System.in buffer.

            - Finally, we can read and parse data coming from a file.


       Each of these three different means of collecting inputs has its own strengths and weaknesses.


        = Command line arguments are quick and easy to implement (in fact, you are forced to support them
        when you write the method signature for main(),) but they must be known right when a program starts.
        We also only have them available *in main*.

        = Inputs fed through System.in can be supplied by a user as a program runs, but this pauses the program
        and forces the user to be present the entire time it runs. This means System.in is not really practical
        when we are processing LARGE batches of data.


        = Files allow us to store large quantities of data without requiring intervention, but parsing them
        can get tricky and we need to know where the file lives.

        The reality is that often, we end up combining one or more of these systems, such as writing data
        in a file, then supplying the location of that file through System.in while the program runs.

       Significantly, when we are designing complex programs or supporting them over long periods of time,
        we often end up changing the source of input. Where originally we might have thought it was "good enough"
        to collect input from System.in or from the command line arguments, later we might decide it would be
        better to read this information from a file!

        This means that it's important to design our classes to solve a real-world problem while being "agnostic"
        to the way they are getting the data to solve that problem.


        For example, let's say we wanted to make a grocery shopping program.

        We might decide that we've got a Grocery class that holds details about each item available for purchase,
        and a ShoppingList class that groups purchased items together and can provide a total cost.

        */

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a name: ");
        String received_dynamic_name = input.nextLine();

        System.out.println("Searching for " + received_dynamic_name);


        String groceries_database_file = "resources/groceries.csv";
        File groceries_database = new File(groceries_database_file);
        Scanner groceries_reader = new Scanner(groceries_database);

        while ( groceries_reader.hasNextLine() ) {
            String grocery_text = groceries_reader.nextLine();

            String[] parts = grocery_text.split(",");
            String grocery_name =parts[0];
            String grocery_price = Double.parsedouble(parts[1]);
        }


        /*
        Our goal with class design is to figure out a way to minimize the chances we have to rewrite code
        in the future.

        By favoring function parameters for our classes, we're able to write programs that work with a variety
        of inputs and take advantage of the strengths of whatever input type is most appropriate for the problem
        we're trying to solve!


        As a general rule: 
            * reading data from a user should happen in its own class or in main
            * moving data around from place to place in a program or using data to solve a problem
              should happen as function parameters that are already in the correct type
         */


    }
}