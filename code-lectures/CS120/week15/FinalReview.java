

/*
Objectives for Today:
  * Review for the final exam!
 */


/*
Your final exam this semester is going to be the same format that we saw for the midterm: there will a handful of 
multi-part, short answer questions on topics from any part of the course.

Just like the midterm, you will have a full day to work on the exam. Our exam proctoring period will be May 8th,
from 10:15-12:15pm in the normal webex room for lecture.
*/

import java.io.*;
import java.util.*;
import java.util.stream.*; //<- Remember, we need to import when code lives in another package/module. 
                           // This lets us avoid needing the fully-qualified name of another class.
                           // When we put a *, we're saying "get every single class in that folder and bring them all".



public class FinalReview {
  public static void main(String[] args) throws IOException {


        /*
        COLLECTIONS

        Sometimes we want to store more than one of the same type of thing in a single place. List is a class that lets
        us do this.

        We declare a list like any other variable, but we need to include one extra piece of information -- the kind of
        thing we want to store:
         */
        List<String> a_list_of_names;


        /*
        Assigning a new list to this variable also works similarly to assigning new objects to any of our other variables:
         */
        a_list_of_names = new ArrayList<String>();

        /*
        Once we have our list, we can use the methods on the list to add many different objects that match the list's
        type:
         */
        a_list_of_names.add("Alice");
        a_list_of_names.add("Barret");
        a_list_of_names.add("Cherise");
        a_list_of_names.add("Derrick");

        /*
        The list's methods also let us do other things, like check whether something is in our list:
         */
        boolean is_name_in_list = a_list_of_names.contains("Alice");



        /*
        A special kind of for loop lets us loop over the things in the list:
         */
        for ( String one_individual_thing_from_list : a_list_of_names ) {
            //Each time we go through this loop, one_individual_thing_from_list will be another name that was 
            //saved in the list.
        }


        /*
        When we have a list (or any other type of collection), we can convert that list into a Stream.
        A Stream is an object that gives us many helpful functions for iterating through each individual element
        of the List:
        */

        Stream<String> stream_of_names = a_list_of_names.stream();


        /*
        Once we have our stream, we can call the methods that allow us to map, filter, reduce, or otherwise
        interact with each individual item on the stream.

        The special thing about these methods is that they all required "lambda functions" -- these were
        functions we sent into
        */

        
        Stream<String> just_names_with_A = stream_of_names
             .filter((String next_name) -> { //<- this function that takes a next name and checks the
                return next_name.startsWith("A");   //starting letter will be used on each name in the original list
              });


             //Streams are helpful when we have some quick operations that fit neatly into our looping "patterns"


             //When we're done processing, we need to turn the Stream back into a List with the .collect() method:
        List<String> list_names_starting_with_A = just_names_with_A.collect(Collectors.toList());
        System.out.println("The names that start with A are: " + list_names_starting_with_A);



        /*
        A set works exactly like a list, except sets do not have duplicates (and there is no numeric order to the things in
        the collection.)
         */
        Set<String> set_of_colors = new HashSet<>();

        /*
        We add things to a set exactly the same way we add things to a list:
         */

        set_of_colors.add("silver");
        set_of_colors.add("green");
        set_of_colors.add("blue");
        set_of_colors.add("red");
        set_of_colors.add("rose");
        set_of_colors.add("silver"); //<- there will still only be one copy of "silver", even though its added twice

        System.out.println(set_of_colors);


        /*
        Unlike lists, we can't use the .get() function to get back an individual thing by index:
         */
         // 

         //set_of_colors.get(0); //<-- this function does not exist

        /*
        Sets excel at representing "Venn-diagram" style relationships:
         */

        Set<String> words_beginning_with_r = new HashSet<String>();

        words_beginning_with_r.add("red");
        words_beginning_with_r.add("rose");
        words_beginning_with_r.add("rhubarb");
        words_beginning_with_r.add("reading");
        words_beginning_with_r.add("relegate");


        HashSet<String> result = new HashSet<>(set_of_colors);
        result.retainAll(words_beginning_with_r); // <-- the 'overlap' of our sets -- the middle of the venn diagram is now in result

        result = new HashSet<>(set_of_colors);
        result.removeAll(words_beginning_with_r); // <-- the 'difference' of our sets -- the left of the venn diagram is now in result


        result = new HashSet<>(set_of_colors);
        result.addAll(words_beginning_with_r); // <-- the 'union' of our sets -- everything in either part of the venn diagram




        /*

        Files 

        */


        /*
        A File object is a 'handle' that points to some location in file storage.
        To make a new File object, we need to pass in a String that represents the location we want to access:
         */

        File final_grades = new File("final_grades.txt");

        /*
        By default, a File starts looking in the project folder (a "relative" path), unless we prefix it with 
        C:\ (Windows) or a / (Mac/Linux) (an "absolute" path).

        File objects have a few useful methods that let us test whether the file exists and perform basic operations:
         */

        final_grades.exists();
        final_grades.getName();
        final_grades.getAbsolutePath();
        //final_grades.delete();
        //final_grades.renameTo(new File("final_grades.txt"));


        /*
        To read from a File, we use the Scanner class. This works exactly like reading input from the command line,
        but instead of using System.in we use the File object!
         */
        Scanner file_reader;

      /* Reading from a file has a chance of failing, so we need to handle its exceptions by putting 'throws' in the
      method signature. */
      
       file_reader = new Scanner(final_grades);

            /*
            To iterate over the lines of the file, we can use the standard methods of our Scanner:
              - hasNextLine() checks if there's another line waiting to be read
              - nextLine() *returns* that line and update's the scanner's "cursor" to the next line of the file
             */

       while ( file_reader.hasNextLine() ) {
                String next_line = file_reader.nextLine();

                String[] line_parts = next_line.split(",");
                String student_id = line_parts[0];
                String grade_as_text = line_parts[1];

                int grade = Integer.parseInt(grade_as_text); //<- everything that is read from the file is text
                                                             // if we need to use it as a number we need to convert it

                System.out.println("I predict that " + student_id + " will get a " + grade);
       }



        File output = new File("output.txt");

        /*
        To write to files, we can use the PrintWriter class.
         */
            PrintWriter file_writer = new PrintWriter(output);


            /*
             The most important method on the PrintWriter class is println(), which works exactly like
             println() in System.out.println()
             */
            file_writer.println("We wrote to a file!");

            //NOTE: This overwrites the old contents of the file. We need to use a "FileWriter" in append mode to not
            // overwrite the file:
            // PrintWriter file_writer = new PrintWriter(new FileWriter(output, true));



            //After we write to a file, we have to close our writer to make sure the changes are reflected!!!
            //This also frees up the file so another program can open it.
            file_writer.close();


            javadoc(1, "obetzm");
            searching();

  }


    /*
    We can create documentation for our methods by using JavaDoc comments. A JavaDoc comment is a block comment
    with an extra * in the beginning. @tags denote features of the documented method, such as arguments or the
    return value:
     */


    /**
     * 
     * Takes in an integer and a set of initials, and it returns the email address for the student with that numbered
     * count and those initials.
     *
     * @param number   An integer representing how many times a student has had these initials at MassBay.
     * @param initials The initials of the student we want to create an email address for.
     * @return Returns a formatted massbay email address as a String.
    */
    public static String javadoc(int number, String initials) {
        return initials + number + "@massbay.edu";
    }


    public static void searching() {
      /*
      When we want to search, we are looking for the presence or absence of an item in a group of items.
      In the easiest examples, we're looking for a single known value. (But we can also do a 'fuzzy' search where
      we only know some details -- trying to find "the first match" or "all the matches".)
      */

        List<Integer> numbers = new ArrayList<>();

        numbers.add(12);
        numbers.add(6);
        numbers.add(7);
        numbers.add(13);
        numbers.add(21);


        int number_we_want = 21;
        boolean we_found_it = false;


        /*
        The easiest way we can search through a list is with a loop. However, this means we have to do one
        check for each thing in the list. We said this had "linear complexity", because the number of steps was
        directly proportional to the count of things in our list:
         */

        for ( int number : numbers ) {
           if ( number == number_we_want ) {
            we_found_it = true;
           }
        }


        System.out.println("Did we find " + number_we_want + ": " + we_found_it);


        /*
        We discovered we could do better by repeatedly splitting the list in half. We called that approach Binary Search.

        In order to search the list this way, we first had to sort the list, because otherwise, we couldn't
        make judgments about things we had not directly seen.
        */
        Collections.sort(numbers);
        binary_search(numbers, number_we_want);



        /*
        The number of steps this would take is proportional to the log2(count_of_things), which
        is much, MUCH faster. We called this type of growth "logarithmic complexity".

         */
    }


    public static boolean binary_search(List<Integer> things_to_search, int looking_for) {


        if ( things_to_search.size() == 0 ) {
            return false; //there's nothing left to search, we can't find it.
        }


        int earliest = 0;
        int latest = things_to_search.size() - 1;
        int midpoint = (earliest + latest) / 2;


        int thing_at_midpoint = things_to_search.get(midpoint);


            if (thing_at_midpoint == looking_for) { 
              return true;
            } else if (thing_at_midpoint < looking_for) {
              earliest = midpoint + 1;
              List<Integer> new_things_left_to_search = things_to_search.subList(earliest, latest);
              return binary_search(new_things_left_to_search, looking_for);
            } else if (thing_at_midpoint > looking_for) {
              latest = midpoint - 1;
              List<Integer> new_things_left_to_search = things_to_search.subList(earliest, latest);
              return binary_search(new_things_left_to_search, looking_for);
            }

            return false;

    }




    public static void swap_two_numbers(List<Integer> list, int id1, int id2) {
        //We can use this pattern to swap two things in a list:
        int temp = list.get(id1);
        list.set(id1, list.get(id2));
        list.set(id2, temp);
    }


    public static void sorting() {

        /*
        We said that most sorting algorithms have us go through and compare each possible pair of things in
        a list, then swap them if they're out of place.

       */


        List<Integer> numbers = new ArrayList<>();

        numbers.add(12);
        numbers.add(7);
        numbers.add(21);
        numbers.add(13);
        numbers.add(6);


        for ( int first=0; first < numbers.size()) {
          for ( int second=first+1; second < numbers.size(); second++) {
                if ( numbers.get(first) > numbers.get(second)) {
                    swap_two_numbers(numbers, first, second);
                }
          }
        }

        System.out.println(numbers);


        /*


         This means that for each of our N things, we have to compare it to N-1 other things.


         12 has to be compared to 7, 21, 13, 6 (4 checks)
         7 has to be compared to 21, 13, 6 (3 checks)
         21 has to be compared to 13, 6 (2 checks)
         13 has to be compared to 6 (1 check)

         4+3+2+1 checks = 10 checks


         if we added just one more thing, we'd have to add 5 more checks!

         steps = (num_things * (num_things - 1) ) / 2

        After some fancy math, we said that this roughly estimated to count_of_things^2. We discovered this was
        much slower than linear or logarithmic problems -- we said problems like this had "polynomial complexity".


         */

        /* 
        We did come up with an improvement -- we could keep splitting the list in half, saying "we just need to sort 
        this half", and then *merging* those two sorted halves back together. We called this strategy merge sort!

        */


        mergeSort(numbers);


    }


    private static List<Integer> mergeSort( List<Integer> unsorted_list ) {

        //If we have a list that only has 1 or 0 things, the list is already sorted, and just return it.
        if ( unsorted_list.size() <= 1 ) {
            return unsorted_list;
        }

              int halfway_point = unsorted_list.size() / 2;
       List<Integer> first_half = unsorted_list.subList(0, halfway_point);
      List<Integer> second_half = unsorted_list.subList(halfway_point, unsorted_list.size());

         first_half = mergeSort(first_half);
        second_half = mergeSort(second_half);

        //finally, merge those two sorted halves together and return that as our answer
        List<Integer> full_sorted_list = merge(first_half, second_half);
        return full_sorted_list;
    }


        private static   List<Integer>  merge(List<Integer> a, List<Integer> b) {
          //The benefit here is that we're able to assume A and B are already sorted
          // ....this means we only have to care about the thing on 'top' of each list


        List<Integer> merged_list = new ArrayList<>();

        while ( a.size() > 0 || b.size() > 0 ) {
          if ( a.size() == 0 ) {
            int next_thing = b.remove(0);
            merged_list.add(next_thing);
          } else if ( b.size() == 0 ) {
            int next_thing = a.remove(0);
            merged_list.add(next_thing);
          }else if ( a.get(0) > b.get(0) ) {
            int next_thing = b.remove(0);
            merged_list.add(next_thing);
          } else  {
            int next_thing = a.remove(0);
            merged_list.add(next_thing);
          }
        }

         return merged_list;
        }




        /*

    We had said that recursive functions need a "base case".

    The base case is some branch of the function that returns a result immediately without doing more recursion.
    Without a base case, our function would recurse infinitely.
    */

        public static int recursion(int n) {

         if ( n == 1 ) {
            return 1;  // <-- this n=1 is our "base case" -- we don't want to keep multiplying smaller factorials to it!
                       // we return one because we already know for sure that 1! = 1
         }


         return n * recursion(n-1); //<- otherwise, some step of our function calls itself. That's what makes this function
                                    // recursive! When we call the function recursively, somehow we have to return that value
                                    // so that it can "bubble" back  up to the place it was originally called. 
        }



    public static void graphics() {
        /*
        We discussed a Java library called JavaFX that could create graphical user interfaces (GUIs).
        JavaFX had Stages that loaded Scenes, which we could describe with FXML. Each of our 'controls' 
        (buttons, text boxes) was represented as an object in the code!


        We had also discussed "events", which, much like our streams, were functions that accepted other functions
        that should run when a particular 'event' happens (like clicking a button, picking a menu option...)
         */
    }

}