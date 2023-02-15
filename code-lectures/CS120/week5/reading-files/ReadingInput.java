
/*
Objectives for Today

By the end of today, you will be able to:
   * Understand file handles and how computer programs interact with persistent storage.
   * Recognize how file operations are separated into different classes in Java programs.
   * Identify the Scanner and File interfaces for interacting with files in Java.
   * Describe how we can process text from a file to read it into Java objects.
 */



/* Vocabulary for the Day

Persistent Storage - A place on a computer where we can store data long term, beyond just the execution of a single
     program. Hard Disk Drives (HDDs), Solid State Drives (SSDs), flash drives, and SD cards are all examples of
     persistent storage.

File Path - A file path is the combination of folders we must traverse to find a file. File paths can be "relative"
     (starting from our current folder), or "absolute" (starting from the 'root'/top of a storage device.)

File Handle - A file handle is a special kind of object given to us by an operating system that grants us permission
     to read and edit a file. Some operating systems can only grant a handle for a given file to one program at a time.
 */


//These imports are classes that Java writes for us, but that don't get included manually. We have to describe
// where they live:
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadingInput {

   public static void main(String[] args) throws FileNotFoundException {


      /*
      Sometimes when we are writing a program we don't want to require all the information be provided right 
      when it runs. It's possible that:
            - It's too much information to type out manually.
            - No human is there to watch the program run (e.g it runs automatically overnight.)
            - It's in a format that would be difficult or error-prone for a human to enter manually.



      In these situations, rather than using command line arguments, we can extend our programs to interact 
      with files!


      In Java, we require a special File object in order to interact with a file. However, unlike the other
      classes we wrote ourselves, Java doesn't automatically give us this class for every program we write.


      We need to "import" the file by declaring where it lives at the very top of our code. In this case, 
      the File class lives in [JAVA'S INSTALL FOLDER]/io/File.java, so we write "import java.io.File;" on
      line 1 of our program. 
      

      Once we've done that, the File class can be used exactly like any other object we've constructed this 
      semester: 
      */ 

      String place_where_file_lives = "resources/course_list.csv"; //<- file paths are represented as normal text!

      File course_list = new File(place_where_file_lives);  //<- this is a "relative path" to the File
                                                            // starting from where we're running the program,
                                                            // look for a resources folder, then the courses file.
                                                            // We could use a hard slash to start from 'root'


      /* Creating a file has one parameter: the 'path' where the file lives. This tells Java to go and get a 
      "handle" on that file -- you can think of the handle like a permission slip -- it lets Java know it has
      exclusive access to read and edit the file for as long as our File object exists!

      Just like any other object, File has dot methods that let us interact with its various features:

      */

      boolean does_the_file_exist =  course_list.exists();
      String full_file_location = course_list.getAbsolutePath(); // This would return the C:\Users\name\... path.
      System.out.println("The full location is: " + full_file_location);


      String filename = course_list.getName();
      long size_of_file = course_list.length(); // <- how many bytes is the file?
      boolean is_file_readable = course_list.canRead();
      boolean is_file_writeable = course_list.canWrite();


      /*
          The File class doesn't have the ability to read or write to files on its own! To do that, we need to
          give our file object to another object. File is just the "permission slip"; we need to give that slip
          to another object that knows about reading!


          In our case, the class that knows about reading things is java.util.Scanner!

      */

      Scanner course_reader = new Scanner(course_list); // <- we give the file handle to the reader

            /* However... even though we've followed all the rules for declaring an object correctly, watch what
          happens when we run this code.

          error: unreported exception FileNotFoundException; must be caught or declared to be thrown

          This is telling us that trying to read a file is dangerous!

          It's telling us Java wouldn't know what to do if we gave it a file that doesn't exist!

        When we encounter code like this, we have to tell Java we understand the risks. For now, let's 
        put a warning in main's method signature that says "we're aware that the file might not be found.


        We do this by adding two words after everything else: throws FileNotFoundException.

        Any function that reads a file, or function that calls a function that reads a file, will need
        this warning. There are more elegant ways to handle this type of problem locally, but for now,
        our program doesn't crash. (NOTE: FileNotFoundException is also an object we need to import!)

      
        Now that we have our Scanner, we again have methods we can use on it:
          */
         boolean has_more_text =  course_reader.hasNext(); //<- is reading the file 'over'?
         String next_line_of_file =  course_reader.nextLine(); // read just the next line of text

        /*
        Note: there are also other reader functions like nextInt() and nextDouble() that just read
          a single number from the line. I'm going to strongly recommend you NOT use these functions, 
          because mixing different types can break your Scanner. 

          Instead, if you have text that represents a number, convert it using our 'parse' functions!
        */


         // These two functions represent everything we need to read the entire file!
         // We just need to combine them with a loop!

         while ( course_reader.hasNext() ) {
            String next_line = course_reader.nextLine();
            System.out.println("\nThe next line of the file is: " + next_line);


            // Let's take it one step further, and write a function that turns this one line of text
            // into a course object!

            Course next_course = fromText(next_line);
            System.out.println("As a course object, this course meets on: " + next_course.getMeetingDays());

            boolean isAfternoon = isAfternoonCourse(next_line);
            if ( isAfternoon ) {
               System.out.println("This is an afternoon course!!!");
            } else {
               System.out.println("Ugh... this course meets too early...");
            }
         }//end of "until we reach end of file" while loop


         // One last note about Scanner -- Scanner can read from anything that supplies text.
         // While often this is a file, it can also read directly from the command line itself!
         // To do so, we just give a special 'virtual' file called System.in to Scanner:

         Scanner terminal_reader = new Scanner(System.in); //<- the 'file' we read here is the terminal

         // Now, if I read a nextLine() from the reader...

         System.out.print("\n\nHey user, what's your favorite color?: ");
         String entered_text = terminal_reader.nextLine();
         System.out.println("Wow, I love " + entered_text + " too!\n");

         // ...the program pauses until the user types a line!
         // reading from the "standard input" is a helpful way to let us write interactive programs.
         
   }//end of main



   public static Course fromText(String text) {
      //Input text will be a line from our file, like "Programming I,MWF,F,10:00AM,1"

      Course created_course = null;

      /* Right now we just have one string that holds ALL our course data, but String has some helpful 
         functions that can let us split this file up into the pieces we need. For example, 
         string.split(pattern) will turn a string into an array, where each new entry starts after the pattern.

      For example:
      "Programming I,MWF,F,10:00AM,1".split(",") would give us an array with:

         {"Programming I", "MWF", "F", "10:00AM", "1"}

         Let's do exactly that!
      */

      String[] course_details = text.split(",");

      // Now we can read out each individual field from the file:

      String name = course_details[0];
      String meeting_days = course_details[1];
      String lab_days = course_details[2];
      String time = course_details[3];
      String hours = course_details[4]; //<- NOTE: everything from our original string is still
                                                // text. We may need to convert it!


      // String also has some other functions that can help us 'clean up' text:

                name = name.trim();      //<- trim() removes extra spaces on either end of our string
        meeting_days = meeting_days.trim(); 
            lab_days = lab_days.trim();
                time = time.trim();
               hours = hours.trim();

      int numeric_hours = Integer.parseInt(hours); //<- always trim() before you parse()! 

         /*
        There's one important note about strings -- no string method ever *changes* the string. Instead,
         they always *return* a new string that you need to save. This is called "immutability".
         Im- means 'not', and 'muta-' means changing. 

         Strings are non-changing; once a String object is created, that's the value it has for life! 
            (Unless we reassign a new object to the same variable.)
         */


      created_course = new Course(name, meeting_days, lab_days, time, numeric_hours);



      return created_course;

   }



    public static boolean isAfternoonCourse(String text) {

      String[] course_details = text.split(",");
      String time = course_details[3];

      /*
       There are some other helpful String methods that exist:
       */

        //To find out how many characters are in a String:
         int length_of_time = time.length();      
      
      int start_of_meridian = length_of_time-2;
        int end_of_meridian = length_of_time;


        //To get just 'part' of a longer line of text from (starting character) to (ending character):
       String meridian = time.substring(start_of_meridian, end_of_meridian); 


       //To check if a string has a value anywhere inside itself:
       boolean is_afternoon_class = meridian.contains("PM");


       // You can see a full list in the online documentation for String!
       // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html

       // Check out other helpful methods like startsWith(), endsWith(), indexOf(), and toLowerCase()!

       return is_afternoon_class;
      
    }

}