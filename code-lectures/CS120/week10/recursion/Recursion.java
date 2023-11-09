
/*
Objectives for Today

By the end of today, you will be able to:
   * Define recursion.
   * Describe how recursion allows us to implement different types of 'loop-y' behavior.
   * Motivate recursion and explain why it exists.
   * Write recursive functions that call themselves.
 */


import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Recursion {
	public static void main(String[] args) {


        /*
        For several weeks, we've discussed how to use for loops and while loops to repeat tasks in programs.
        We've also spent several lectures practicing this, most recently with our various "looping patterns"...


        However, sometimes our loops end up looking a bit cumbersome....



        For instance, let's implement a program to try to find a file:
        */


        String looking_for = "Course.java";
        boolean file_found = false;
        String file_path = "NOT FOUND";

		File place_to_start_looking = new File("../.."); //< ".." says "go up one folder from where we are". It works like any other folder
		List<File> places_to_look = new ArrayList<File>(); //<- we might need to add more places to look once we check one
        places_to_look.add(place_to_start_looking); //<- in the beginning, the only place we know about is where we start



        //while the file is not found and we still have places to look
        while ( !file_found  &&  places_to_look.size() > 0 ) {

        	File currently_checking = places_to_look.remove(0);
        	//if the place we're checking is a folder
        	if ( currently_checking.isDirectory() ) {
        			//add all the things inside the folder as new "places to look"
        		for ( File next_file : currently_checking.listFiles() ) {
        			places_to_look.add(next_file);
        		}
        	}
        	// otherwise, if the next "place to look" is a file and its name matches the file we're looking for
        	else if ( currently_checking.isFile() && currently_checking.getName().equals(looking_for)) {
        		file_found = true;//the file was found, yay!
                file_path = currently_checking.getPath();
            }
        	//otherwise, this is an uninteresting "place to look" and we just continue (nothing even happens here, so we don't need an else)
        }



        System.out.println("Your file was found at: " + file_path);

        /*
        This is an ugly loop. We've got this list of places we're checking that we have to carefully maintain, adding
        and removing places as we check them. We've also got that nested loop we need to add each thing one at a time.
	
        What if we could break this down and only worry about this problem from the perspective of one "step" at a time?
         */


        File initial_location = new File("../..");
        File result = find_file(initial_location, "Course.java");

        System.out.println("Your file was found (recursively!) at: " + result);



        List<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(List.of(5, 12, 6, 3, 4, 9));

        int sum = sumArray(numbers);
        System.out.println("5+12+6+3+4+9=" + sum);

        print_next_number(0);

	}


	public static File find_file(File currently_checking, String looking_for) {

        // if the thing we're looking at is the file we want we're already done, return it!
		if ( currently_checking.isFile() && currently_checking.getName().equals(looking_for) ) {
			return currently_checking;
		}


		//if the thing we're looking at is a folder, check each file in it
		else if ( currently_checking.isDirectory() ) {
			for ( File child : currently_checking.listFiles() ) {
				File found_file = find_file(child, looking_for); //<- we already have a function that knows how to check a subfolder

                //if one of our checks found the file, return the file we found!
                if ( found_file != null ) {
                	return found_file;
                }
			}
		}

        //If the file was not found on this search then we're at a dead end, return 'nothing'
        return null;

	}



    /*
    In our find_file() function here, we're able to describe just one step of the file search process:
      - if we're looking at a file and it matches, return the file
      - if we're looking at a folder: check each file inside the directory with find_file again!
      - otherwise, say we couldn't find it



    Notice how our 'folder' option says "call find_file() again for the sub-file"? That's called recursion!

    Recursion follows all the normal rules for functions -- when we call a function, we stop everything we're doing,
    go complete that function, then come back and use the returned result. The only difference is that the function we're
    interrupting ourselves with is another copy of the same one we're already in.


    The benefit we get from using recursion  is that we don't have to worry about how each sub-check inside the folder
    is going to work: it's just going to follow our normal instructions for one step of finding a file.

	*/



    /* Let's practice writing another simple recursive function: one that can add together all the numbers in an array.
     */
	public static int sumArray( List<Integer> numbers_to_add ) {

		if ( numbers_to_add.size() == 0 ) {
			return 0; //if there's nothing in the array, I already know the answer and don't need to do any addition
		}

		//Summing an array is the same as summing the first number of the array, plus the sum of the rest of the array
		int next_number = numbers_to_add.remove(0);
		return next_number + sumArray(numbers_to_add);
	}


    /*
    Notice how there's always an if-statement somewhere in a recursive function that just returns something immediately?
    We call this branch the "base case" -- it's the simplest version of a single step.
    If our recursive function has no base case, then we end up with infinite recursion, just like an infinite loop
     */


public static void print_next_number(int current_number) {
	System.out.println("The current number is: " + current_number);
	print_next_number(current_number + 1);
}

    /* Unlike infinite loops, infinite recursion will actually crash Java (and most non-functional programming languages.)
      Let's look at the stack we get when we crash Java this way.

	
      To summarize: Recursion is a technique where we write a function that describes one "step" of an algorithm.
      As some part of this step, we call this same function to handle the next single step.


      Writing recursive functions lets us succinctly describe many types of problems that would be messy with loops.
      We'll learn how we can apply this to sorting problems next week!

     */

}
