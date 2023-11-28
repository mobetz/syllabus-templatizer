
/*
Objectives for Today

By the end of today, you will:
    * Identify the purpose of Stream objects in Java.
    * Describe how we can use streams to process lists of data.
    * Apply the concepts of folding, mapping, and filtering to streams.
    * Understand how to pass functions as arguments.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Streams {

    public static void main(String[] args) {

        /*
        So far, whenever we've wanted to process a group of items, the tool we've reached for
        has been a loop. Loops are great for letting us travel through a list one item at a
        time, but they do have one fault -- it can be tricky to determine at a glance what a
        loop is supposed to do.

        We've studied many different patterns for our loops, and gotten some practice implementing
        those loops to solve real problems. Today, we are going to look at another object in Java
        that leverages these patterns to make it more clear what each 'step' of our changes is
        intending to do.

        */


        List<Integer> numbers = List.of(3, 1, 5,  8, 2,  7,  4);



        /*
        Let's say, for example, we wanted to filter our list to just odd numbers, double every
        number that's left over, then fold them into a single string separated by spaces.
        If we were using the list and loops as we've learned so far, that code might look
        something like this:
        */

        List<Integer> odd_nums = new ArrayList<>();
        for ( int next : numbers ) {
            boolean isOdd = next % 2 == 1;
            if ( isOdd ) {
                odd_nums.add(next); 
            }
        }


        List<Integer> final_list = new ArrayList<>();
        for ( int next : odd_nums ) {
            final_list.add(next * 2);
        }


        String final_string = ""; 
        for ( int next: final_list ) {
            final_string = final_string + next + " ";
        }


        System.out.println("Our filtered and doubled numbers are: " + final_string);



        /*
        We might even try to combine them into a single loop:
        */

        String all_at_once = "";
        for ( int next : numbers ) {
            if ( next % 2 == 1 ) {
                int new_num = next * 2;
                all_at_once = all_at_once + new_num + " ";
            }
        }

        System.out.println("Our filtered and doubled numbers are: " + all_at_once);



        /*
        This works, but what the loop is doing here is non-obvious. Someone might not
        recognize that there are three separate tasks we are trying to accomplish,
        so we would probably want to leave a comment or two to describe the purpose
        of the if statement and doubling.


        Really, what we're doing here in this example is mashing together three of the
        'looping patterns' into a single loop:
              we want to FILTER the odd numbers
              ...then we MAP the numbers to double their size
              ......then MAP each number to its string representation
              ......and FOLD the numbers into a single string.

        We could have even written it as many separate loops, though that would have
        been a bit slower and a lot longer to read.


        Imagine if instead we wrote functions for each one of our 'steps' of the
        conversion:
               checkIfOdd     - this will take an integer and decide whether or not it is odd
               doubleNumber   - this will take an integer and return back an integer twice the size
               appendNumToStr - this will take a string and a converted num, and append the num to the string.

        */


        String result = "";

        for ( int num : numbers ) {
            if ( checkIfOdd(num) ) {
                int doubled = doubleNumber(num);
                String converted = String.valueOf(doubled);
                result = appendNumToString(result, converted);
            }
        }

        System.out.println("Calling our functions, we ended up with: " + result);


        /*
        This puts names on the steps, which maybe makes some of our bits of logic more described, but this
        doesn't actually clear up exactly what's happening in this loop.

        It turns out, there is an object that is very good at applying a pipeline of functions
        to a group when all of those functions follow one of our "looping patterns" -- a Stream.

        When we have a Java collection like a List or Set, we can ask Java to give us
        that collection's related stream, by calling the .stream() method.
        */

        Stream<Integer> number_stream = numbers.stream();

        /*
        Much like Lists, Streams require us to use a template type to describe what they're
        a stream of, like a Stream<Integer> or a Stream<String>.

        However, instead of using loops to interact with this stream, we are going to instead
        use functions named after the looping patterns we've seen. There is a catch though....
        if we just called stream.map(), the stream wouldn't know *what* we want to do during that
        map.

        We need to pass it the function that we want it to perform, as if that function were a variable!
        */

         Stream<Integer> filtered_nums = number_stream.filter(Streams::checkIfOdd);
          Stream<Integer> doubled_nums = filtered_nums.map(Streams::doubleNumber);
           Stream<String> nums_as_text = doubled_nums.map(String::valueOf);  //<- we can even use builtin Java functions! valueOf(int) was our built-in function for doing a "parse" in reverse (int -> str)
                   String final_answer = nums_as_text.reduce("", Streams::appendNumToString);


        System.out.println("With streams, our final answer is: " + final_answer);



        /*
        There are a few important things to notice here:

        * First, we are NOT calling the inner functions directly. There are no parentheses after checkIfOdd()
            This is because we're not actually calling the function yet. Instead, we're telling
            the stream that *it* should call the function when it wants to do the filtering.

        * Second, we use :: instead of a . when we're referencing a method instead of calling it directly.
            This helps visually distinguish our intent, and makes it clear we want to use a function
            as a variable.


        * Third, most of our Stream functions return back another stream! The type of stream is always the type
            of whatever thing we should get back at the end of that 'stage' in the pipeline. (So the one exception
            is reduce, when we fold down the entire list into a single item.) This has an important consequence...
            because we're getting back an object of the same type, we can use method chaining!
        */

        number_stream = numbers.stream();


            final_answer = number_stream
                    .filter(Streams::checkIfOdd)
                    .map(Streams::doubleNumber)
                    .map(String::valueOf)
                    .reduce("", Streams::appendNumToString);

        System.out.println("With our chained example: " + final_answer);


        /*
         In this new version of the code, it's very clear that I'm starting with a group of numbers, then filtering some
         out, then doubling and converting them into strings, and finally appending them all into one final answer.
         
         A couple quick additional notes about working with streams:

         - If you've got a stream and you want to turn it back into a list, there are functions such as '.toList()'
         and '.collect()' that can help you do this:

         */


        number_stream = numbers.stream();



        List<Integer> just_the_doubled = number_stream
                    .filter(Streams::checkIfOdd)
                    .map(Streams::doubleNumber)
                    .toList();


        System.out.println("The list we got back from toList(): " + just_the_doubled);


        number_stream = numbers.stream();


        String another_way_to_concat = number_stream
                    .filter(Streams::checkIfOdd)
                    .map(Streams::doubleNumber)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));//<- concat with ", " between each element


        System.out.println("Using collect():  " + another_way_to_concat);


        /*
        - Additionally, notice that each stream can only be "consumed" once -- if you want to do
            a second pipeline of operations to your stream, you need to fetch a new copy of the stream
            from the original list.


            This is because the stream waits to perform its operations until it sees you .reduce(),
            .collect(), or .toList() the results into something that isn't a stream anymore. It builds all
            your different stages into a single loop, the way we did with our original example!


        - Finally, there is a more concise way to specify our inner functions to a stream. If you know
               you're never going to use the function anywhere else in your code, you can write a special shorthand
               that describes your function called a "lambda function".

        To write a lambda function, we distill the function down into its most basic parts: input parameters,
        and a function body.
         */

        List<Student> students = List.of(
                new Student("John", "Doe", 76),
                new Student("Janelle", "Cooper", 85),
                new Student("Carla", "Ortiz", 91)
                );


       Stream<Student> student_stream = students.stream();

       String final_letter_grades = student_stream
            .map((Student each_student) -> {  //<- parameters go inside parentheses, followed by an "arrow" -> and the normal body
                return each_student.getGrade();
            })
            .map((each_grade) -> each_grade + 10 ) // <- if your lambda is only one line you can even skip writing {} and return!
            .map((scaled_grade) -> {
                if ( scaled_grade > 90) {
                    return "A";
                } else if ( scaled_grade > 80 ) {
                    return "B";
                } else if ( scaled_grade > 70 ) {
                    return "C";
                } else {
                    return "F";
                }
            }).collect(Collectors.joining(", "));

            System.out.println("After scaling, the grades in the class were: " + final_letter_grades);
    }



    public static boolean checkIfOdd( int num ) {
        return num % 2 == 1;
    }

    public static int doubleNumber( int num ) {
        return num * 2;
    }


    public static String appendNumToString( String text_so_far, String converted_num ) {
        return text_so_far + converted_num + " ";
    }

}