

/*
Objectives for Today

By the end of today, you will:
    * Identify common patterns we can use to solve problems with a group of data.
    * Practice loops that iterate over groups of objects.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



public class LoopPatterns {
    public static void main(String[] args) {


        /*
        This semester, much of the code we've written has required us to distill many data points down
        into a single answer. For instance, several times we've needed to take a group of numbers
        and calculate a total:
         */


    	List<Integer> some_numbers =  Arrays.asList(5, 3, 7, 1, 6, 4, 12, 9, 8);


        int total = 0;
        for ( int next_num : some_numbers ) {
        	total = total + next_num;
        }

        System.out.println("The total is: " + total);



        /*
        Interestingly, when we've wanted to take an average the process has been very similar --
        visit each number in the group, fold it into a variable we're building up, then continue to the next
        point. The only difference is that we needed to track slightly different information: both a total, and a
        count:
         */

        total = 0;
        int count = 0;
        for ( int next_num : some_numbers ) {
        	total = total + next_num;
        	count = count + 1;
        }

        int average = total / count; //<- we are taking our two 'aggregates' and mashing them together. We could also use some_nums.size()
        System.out.println("The average is: " + average);


        /*
        It turns out this "folding" style of loop is one of a handful of very common ways that loops and arrays interact.
        Today we are going to get practice with this pattern, a few others, and develop strategies for identifying which
        type of loop we want to write to solve a problem.


        NOTE: The names I am giving to these loops today are not technical terms. They are based on related terms in other
        programming languages, but are mostly a convenient shorthand way to refer to the style of problem solving.

        */




        /*
        "Folding"/"Reducing" Loops

        Perhaps the style of loop we have had the most practice with are 'folding' loops. With a folding loop, the general
        pattern we follow is:

             - Before the loop, declare some variable that shares its type with our final answer, given an initial default.
                 (As an example, if we want the "total" that will be a number, so we declared 'int total' and set it to 0, because the total
                     of nothing is zero.)

             - Afterward, we loop through each item in a group, and modify the answer based on that item.
                 (For total this was adding the value; for counting, it would be adding one... The operation doesn't matter here as much
                    as the idea that each new item gets "folded into" the answer.)

             - At the end of the loop, we have our final answer already in the right type.


        Math is a very common reason to need to write this type of code, but even many of the toString() functions we've written have
        had examples of folding:

        */

        List<Student> students = new ArrayList<Student>();
          students.add(new Student("John", "Doe", 78));
          students.add(new Student("Ursula", "Myers", 81));
          students.add(new Student("Leanne", "Chu", 94));
          students.add(new Student("Carol", "Briggs", 87));


        String first_names = "";//<- when I'm done building a string representation, I should have text. 
                                //  The representation of nothing is blank text, so I'll start with an empty string.
        for ( Student next_student : students ) {
        	first_names = first_names + " " + next_student.getFirstName(); //<- we're saving back into my 'final answer', adding one thing
        	// in a "folding"-style loop, the previous value of the thing we're building generally gets used *somewhere* in the 
        	 //calculation of the next value.

        }


        String same_thing = "";
        for ( int i=0; i<students.size();i++) {
        	Student next_student = students.get(i);
        	same_thing = same_thing + " " + next_student.getFirstName();
        }


        System.out.println("The first names are: " + first_names);


        /*
		Even our loops where we've tried to find the "best" / "worst" / "oldest" / "youngest" of a group have followed this same general pattern.

		For example, if we wanted to find the student who comes alphabetically first, then our 'final answer' will be a student:
		*/

		Student alphabetically_first;
        //Since we don't know who comes first, a good 'initial value' is a random student from the group, maybe whoever is currently in front:
		alphabetically_first = students.get(0);


		for ( Student next_student : students ) {
			//In Java, the way we compare strings alphabetically is with the .compareTo() method:
			int comparison_of_names = next_student.getLastName().compareTo(  alphabetically_first.getLastName() );
			//We're using the "old best" in our calculation by comparing the new student. 
			//compareTo() gives us a number less than 0 if the second thing should come first:
			if ( comparison_of_names < 0 ) {
				alphabetically_first = next_student; //Here we're doing the "reassignment" to our final answer, but only when the new student is "better"
			}
		}


        System.out.println("The alphabetically first student is: " + alphabetically_first.getLastName() + ", " 
        	+ alphabetically_first.getFirstName());


        /*

        Folding loops are incredibly flexible -- generally, we can identify them because the answer will be a single thing whose type is often entirely
        unrelated to the type of the group we are working with, which is built up by 'folding' in each value of the list one at a time.


        However, they are not the only common looping pattern... Another incredibly common problem we want to solve is "filtering out" results from
        a big group:



        "Filter" Loops

        Filter loops are useful when we have a large group of data, but only some of the data points are relevant. They allow us to create a new
        group that has only those relevant objects. The pattern of a filter loop is:

             - We first declare a new, empty group whose type exactly matches the type of our existing group.
             - For each item in the original group, we perform some check. If the check succeeds, we copy the object over to our new group.
  			 - At the end of the loop, the new group contains just the 'filtered' results.

        As an example, say we wanted to write a loop that selected out only the students who got at least an 80 in the course:
 		*/

        List<Student> high_performers;//<- The students are a List<Student>, so our final answer will also be a (smaller) List<Student>.
        high_performers = new ArrayList<Student>(); //<- before we've looped, we don't know about any high performers yet.

        for ( Student next_student : students ) {//<- we're going to look at each student from the original list...
            if ( next_student.getGrade() > 80 ) { ///<- if they meet our conditions...
                high_performers.add(next_student); //<- ...then they get "filtered" into the new group!
            }

        }

        System.out.println("At the end, the high performing students are: " + high_performers);

        /*
        By the time we finish our loop, high_performers has collected everyone relevant from the original list, without modifying it.

		
        Just like with our "folding" loops, "filtering" loops can ask many different types of questions by modestly changing our "if"
        statement:
		*/


        List<Student> even_lettered_first_names;  //<- The students are still a List<Student>
        even_lettered_first_names = new ArrayList<Student>();


        for ( Student next_student : students ) {
        	if ( next_student.getFirstName().length() % 2 == 0 ) { ///<-the only thing that changed is what condition adds things to our group!
				even_lettered_first_names.add(next_student);
        	}
    	}

        System.out.println("The students with an even number of letters in their first name are: " + even_lettered_first_names);


        /*

        Filter loops are great at trimming down the data we need to work with. The easiest way to identify them is by recognizing that what
        we want to end with is a portion of what we began with, without any changes in type.

        When we *do* want to change types, we may instead be dealing with our third type of loop:



        "Mapping" Loops

         Whenever you find yourself saying "I have a group of X, but I *actually* want a group of Y...", this may be a sign you want a mapping
        loop. Mapping loops look very similar to filter loops, but with some small differences: they don't end with the same type they started
        with, and they often don't work conditionally. To construct a mapping loop:


            - You start by declaring an empty group of things in the type of your final answer.
            - For each item in the original group, perform the transformation, then append the result to your new collection.
            - At the end, you'll have a collection of things in the requested type.


        Say, for example, I had a list of student objects, but I really wanted just a list of final averages:

        */

        List<Integer> just_the_grades = new ArrayList<Integer>(); //<- my "final answer" will be a group of grades, and each grade is a number.

        for ( Student next_student : students ) {
        	int the_grade = next_student.getGrade(); //<- I transform each student into the kind of thing I want...
            just_the_grades.add(the_grade); //<- ...and then I can add it to my new group.
        }

        System.out.println("Just the grades of the students are: " + just_the_grades);



        /*
        Mapping loops are great as an 'intermediate' step. For instance, doing a 'map' first and then a 'fold' allows us to easily ask complex questions
        like: "What was the average grade for this group of students":
         */

        int student_total = 0;
        for ( int grade : just_the_grades ) {
            student_total = student_total + grade;
        }
        System.out.println("The average was: " + (student_total / just_the_grades.size()));

        /*
        Note: in a real program, it's perfectly acceptable to merge several of these steps together into a single loop. It's especially common to do
        a 'map' and a 'filter' or a 'map' and a 'fold' back to back in a single loop,  first transforming the data into a new type, and then doing some
        calculation or condition to decide whether you want to keep the result. In the coming weeks, we'll be looking at more convenient ways to conveniently
        compose these different looping strategies together!
         */


        /*
        Note: The patterns we covered above can get you through the vast majority of programs like the ones we write in this class.
        However, they are not the only looping patterns that exist. For example, sometimes we might need to "peek back" at previous values
        when looping:


		
        "Peeking" Loops

        A classic example of this is the "fibonnaci sequence" -- the fibbonaci sequence is a group of numbers where each number is the
        sum of the two numbers that precede it:

             -- + --=---|
             |    |     v
        1    1    2     3     5      8      13
        |    |    ^
        -- + --=--|

        No amount of filtering, mapping, and reducing will make it easy to do this. Instead, we will need to "peek" at the old values.
        To solve this problem, we will:

                - First declare the type of the group we're making, and 'seed' it with enough values so that our peeking works.
                - Use a basic loop to repeat a certain number of times and keep track of the current index we're building.
                - Use math on the index to peek backwards to earlier numbers (this is why we needed seed values!)

                */


        List<Integer> fibonnaci = new ArrayList<>();
        fibonnaci.add(1);
        fibonnaci.add(1); //<- if we don't put numbers in to start, we won't have anything to look back at!


        for ( int i=2; i<20; i++) { //<- NOTE: We are NOT starting at 0 here. We're starting forward the number of slots we peek back.
            int two_old = fibonnaci.get(i-2); //<- when we want to 'peek back', we do math directly on our "i"
            int one_old = fibonnaci.get(i-1);
            int new_num = two_old + one_old;
            fibonnaci.add(new_num);
        }

        System.out.println(fibonnaci);


        /*
        If we were solving a problem where we were "peeking ahead" to future values, we would instead subtract from the final value we are
        looping to (to make sure there's always something later for us to check.)

        Peeking loops are powerful, but much, much harder to reason about. Because they are manipulating the index of the group, they are
        usually written with a for loop instead of a for-each loop. "Peeking" can even be combined with strategies like mapping and reducing
        to build up complex outputs!

		*/
    }
}