


/*
Objectives for Today

By the end of today, you will:
   * Describe how arrays allow us to store groups of related variables in a single place.
   * Recognize the syntax for initializing, accessing, and mutating data stored in an array.
 */



/*
Vocabulary for the Day

Compound Data - Compound Data is a type of data that allows us to store multiple pieces of information in a
   single variable.

Array - An Array is a collection of same-type variables that we collect in a single variable and refer to using a
        numeric index and [square brackets]. Arrays are one of the simplest forms of compound data.

 */


public class Arrays {


    public static void main(String[] args) {


        /*
            Sometimes when writing a program we want to work with a collection of related data points.

            Say, for instance, we wanted to represent every course in a schedule. Working with all of these courses as
            loose variables can quickly become unwieldy:
         */

    	Course course1 = new Course("Programming I", "MWF", "M", "11:00 AM", 1);
    	Course course2 = new Course("Web Design", "TR", "R", "10:00 AM", 2);
    	Course course3 = new Course("Programming II", "TR", "T", "11:30 AM", 2);

    	/*
    	With how this is written, I can use each of these courses individually: 
    	*/
    	System.out.println("My first course of the week is: " + course1);

    	/*
    	However... operations that require looking at *every* object become unwieldy:
    	*/
    	if ( course1.getCourseName().equals("Programming I")) {
    	   System.out.println("The variable that holds CS120's details is: course1");
    	} else if ( course2.getCourseName().equals("Programming I")) {
    	   System.out.println("The variable that holds CS120's details is: course2");
    	} else if ( course3.getCourseName().equals("Programming I")) {
    	   System.out.println("The variable that holds CS120's details is: course3");
    	}


        /*
        As written, it's difficult to do something for each member of our group, and it's very easy to lose track of one
        or more of the groups members, typo a name, or copy-paste incorrectly.


            When we have a group of related data that we want to store and access as a group, we can use arrays. In Java,
            an array is a same-typed collection of elements accessed by a numeric array index. You can think of arrays
             as working similarly to lists in Python.

            We declare a new array similarly to the way we declare any other variable. However, after the normal type
            declaration, we add square braces:
        */

            Course[]  fall_2023_schedule;

        /*    ^    ^         ^
             type  []     variable name



             This array acts like a container that holds a bunch of objects in individual slots.
             Extending our 'box' analogy from before, it's almost like we're creating a 'box of boxes':

             +-----+-----+-----+-----+-----+-----+-----+-----+-----+
             |     |     |     |     |     |     |     |     |     |
             |     |     |     |     |     |     |     |     |     | .....
             +-----+-----+-----+-----+-----+-----+-----+-----+-----+
        
             \                                                      /
              ------- ---------------------------------------------
                    v
               course_roster



              However, before we can actually *use* the array, we have to initialize it, similarly to the way we
              initialize objects. Specifically, we need to say *how many* boxes there will be:
         */

            fall_2023_schedule = new Course[5];
        /*                       ^       ^   ^
                                new    type   [size_of_array]


            Instead of calling a constructor, when we initialize an array we put our square brackets after the type
            name. Inside the brackets, we put the maximum number of slots the array can hold. This is us telling 
            Java how much space it needs to save, or "how many boxes to make".

             +-----+-----+-----+-----+-----+
             |     |     |     |     |     |
             |     |     |     |     |     |
             +-----+-----+-----+-----+-----+
                0     1     2     3     4

             \                             /
              ------- --------------------
                    v
               course_roster



          Once the array has been initialized, each 'slot' in the array acts as a different variable we can access and
          mutate, just like any other variable. To do so, we just put brackets after the name of the array, and
          put the index for the variable inside the braces:

            */

            fall_2023_schedule[0] = course1;

        /*

        This is like storing something in to one box from our long group:


              CS120   
             +--|--+-----+-----+-----+-----+
             |  v  |     |     |     |     |
             |     |     |     |     |     |
             +-----+-----+-----+-----+-----+
               0      1     2     3     4   
            \                                  /
             ------- -------------------------
                    v
            course_roster


            */


        //we can assign from previous variables of the same type, or we can assign new objects directly:

            fall_2023_schedule[1] = new Course("Web Development", "TR", "R", "9:00 AM", 1);


        /*
              CS120 CS241  
             +--|--+--|--+-----+-----+-----+
             |  v  |  V  |     |     |     |
             |     |     |     |     |     |
             +-----+-----+-----+-----+-----+
               0      1     2     3     4   
            \                                  /
             ------- -------------------------
                    v
            course_roster

            */

        /* Note: the index for an array starts at 0, NOT 1. This means ordinal numbering is going to be one higher than
        the index we use.
.

        To access the elements of our array, we use the same square brackets, but this time on the right of the equals: 
      */

            Course cs120 = fall_2023_schedule[0];
            Course cs241 = fall_2023_schedule[1];


        /*
        We can declare and initialize all the values of an array on the same line:
        */
        Course[] spring_2024_roster = { cs120, cs241, course3, new Course("Scripting", "W", "W", "1:45pm", 3) };


        /*
        Note: this gives exactly as many slots as the number of things you add initially -- there's no room to grow!

        /*
              CS120 CS241  course3  ______(the fourth one has no other name outside its array box)
             +--|--+--|--+--|----+--|--+
             |  v  |  V  |  v    |  v  |
             |     |     |       |     |
             +-----+-----+-------+-----+
               0      1     2       3
            \                           /
             ------- ------------------
                    v
            spring_roster

        */



        //We can even use our dot notation directly with a referenced object from the array:

        String first_courses_details = fall_2023_schedule[0].getDetailsText();
        System.out.println("Fall 2023's first course's details are: " + first_courses_details);


        /*

        When we access a field in the array that has never been assigned a value, it's similar to what happens when
        we access a declared variable that has never been assigned a value:
         */
        System.out.println("The fifth course you are assigned this semester is: " + fall_2023_schedule[4] );
                //We get back 'null', the special placeholder for when there's an object-typed variable with no data.

        Course some_nonexistent_course = fall_2023_schedule[4];
        //What we're doing here is the same as:
        some_nonexistent_course = null;

        System.out.println("A Object Variable with 'nothing' assigned to it: " + some_nonexistent_course);

        /*
        This means in the beginning, our array ACTUALLY looks like: 

              null  null  null  nul   null  
             +--|--+--|--+--|--+--|--+--|--+
             |  v  |  V  |  v  |  V  |  v  |
             |     |     |     |     |     |
             +-----+-----+-----+-----+-----+
               0      1     2     3     4   
            \                                  /
             ------- -------------------------
                    v
            course_roster


		This does lead us to a few errors to keep in mind when working with arrays:
			*/

        //fall_2023_schedule[4].getDetailsText();//this will crash with a "java.lang.NullPointerException" -- it's trying to use 
                                               // an object that doesn't exist

        //fall_2023_schedule[20].getDetailsText(); //java.lang.ArrayIndexOutOfBoundsException: Index 20 out of bounds for length 5



    	// What if I wanted to print out each course's details in my group?

    	// I can do this using a loop!


        int number_of_courses = fall_2023_schedule.length; // array.length is a special variable that shows us how many slots 
                                                           //    exist in one array!

        System.out.println("\nShowing your entire fall schedule: ");
    	for ( int array_slot = 0; array_slot < number_of_courses; array_slot++ ) {
    		Course next_course = fall_2023_schedule[array_slot];
    		if ( next_course != null ) {  // if there was actually a value saved in this course:
    			System.out.println("   - " + next_course.getDetailsText());
    		}

    	}


    	// Just like how for loops are a "shortcut" for a common looping pattern we can do with while loops,
    	//    This is also a common looping pattern, and we have a tool to make it easier to write:

        System.out.println("\nShowing your entire fall schedule (with a for-each loop!!!): ");
    	for( Course next_course   :   fall_2023_schedule ) {
    		// This is called a for-each loop! We're saying "For each Course in the group of Courses[]..."
    		if ( next_course != null ) { // if there was actually a value saved in this course:
    			System.out.println("   - " + next_course.getDetailsText());
    		}
    		
    	}






    }
}