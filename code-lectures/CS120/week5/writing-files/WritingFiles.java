/*
Objectives for Today

 By the end of today you will be able to:
     * Review the mechanisms use to interact with files in Java.
     * Describe how the PrintWriter class lets us append new information to a file.
     * Understand how to convert datetime strings into Java's LocalDate class.
 */


import java.io.*;  //<- putting a * says "give me every class in that folder"
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.*; //DateTimeFormatter and FormatStyle both live here
import java.time.temporal.ChronoUnit;

public class WritingFiles {
    public static void main(String[] args) throws IOException {

        /*
        Last class, we learned how to take a file and read its information into
        Strings in our program:
        (How?)
         */

        File lectures_file = new File("resources/lectures.csv");
        Scanner lecture_reader = new Scanner(lectures_file);

        int lecture_count = 0;
        Lecture[] lectures_collection = new Lecture[45];
        while (lecture_reader.hasNext() ) {
            String nextLecture = lecture_reader.nextLine();
            lectures_collection[lecture_count] = Lecture.fromFile(nextLecture);
            lecture_count = lecture_count + 1;
        }

        /*
        The Scanner class was a powerful object that knows how to do reading from
        all types of "input streams", including from Files!

        Sometimes, we also want to be able to write information TO a file:
               - to save a permanent record of actions performed
               - to persistently store the result of calculations
               - to reflect changes a user made


         When we want to perform such an action, fortunately Java gives us another
         helpful object: the PrintWriter. PrintWriter, much like Scanner, will
         also accept a File object when it is created.
         */
        File lecture_comments = new File("output/comments.csv");
        lecture_comments.createNewFile();
        PrintWriter comments_writer = new PrintWriter(lecture_comments);

        /*
        (Ideas on how we use this thing?)
        As its name would suggest, the print writer uses functions with the same
        name as printing. In fact, System.out.println() is just using a PrintWriter
        on the virtual file System.out, the same way Scanner can read from System.in!

         */
        Scanner input_reader = new Scanner(System.in);

        for ( Lecture lecture : lectures_collection ) {
            if ( lecture != null ) {
                String prompt_name = lecture.getPromptName();

                System.out.print("How did you feel about the lecture " + prompt_name + ": ");
                String comments = input_reader.nextLine();
                lecture.setComments(comments);

                String new_file_line = lecture.toCSV();
                comments_writer.println(new_file_line); //<- we're adding one line to the end of the file!
            }
        }

        /*
        Note: We need to "close()" our file when we're done writing to it. You can think of
        this like hitting the save button -- it "flushes the buffer" of all the pending changes,
        making sure they actually get saved. If you don't, YOUR WRITTEN FILE MIGHT NOT BE SAVED!

        (Also remember: without tricks, only one program is normally allowed to open a file at once,
        so it's "greedy" to keep our file open longer than we need it)
         */

        comments_writer.close();

        /*
        Now the comments file suddenly exists, and it has all the comments we wrote!

        ..However, there's a problem. Watch what happens if we try to append to the file again:
         */

       /*
        comments_writer = new PrintWriter(lecture_comments); //<- we need a new writer, our old one is "closed"

        System.out.println("\nHow did you feel about today's lecture?");
        String comments = input_reader.nextLine();
        comments_writer.println("2023-02-15,Today's Lecture," + comments);
        comments_writer.close();
*/


        /*
        This deleted all the first things we wrote! If we want to *append* to an existing
        file, we must first wrap up our File in a "fileWriter", which is a class that
        knows about the difference between appending and overwriting:
         */
        FileWriter wrapper = new FileWriter(lecture_comments, true); //<- this true says we should append
        comments_writer = new PrintWriter(wrapper);
        comments_writer.println("This line will appear at the very end of the file!");
        comments_writer.close();



        /*
        One last quick topic I want to introduce today is another Java object that helps us
        with dates and times. So far, we've been using a String to talk about dates. However,
        this is kind-of clunky:
         */

        String project_deadline = "Feb 29, 2023";
        String current_date = "Feb 15, 2023";


        /*
        Right now, it's hard to compare these two dates. We're not able to use greater than / less than, and if we
        try using String's compareTo function, our dates will be compared *alphabetically*, not by actual date.

        If we tried storing date as three different numbers for month, day, and year we could solve this problem,
        but our boolean expression would be three miles long:
         */

        int project_month = 2;
        int project_day = 28;
        int project_year = 2023;

        int current_month = 2;
        int current_day = 15;
        int current_year = 2023;

        boolean deadline_passed = current_year > project_year ||  current_year == project_year &&
                ( current_month > project_month || current_month == project_month && current_day > project_day );

        /*

        Instead of managing all of this manually, we can use objects that have already been created for us.
        Specifically, we have the "LocalDate", "LocalTime", and "LocalDateTime" classes, which allow us to
        record dates, times, and dates+times respectively.

        Dates are created with a special static method on LocalDate, called "of()":
         */

        LocalDate deadline = LocalDate.of(project_year, project_month, project_day);
        LocalDate today = LocalDate.now(); //<- the now() function gives us today automatically!

        /*
        We need to use a static method because constructors ALWAYS return an object, but creating a date
        might sometimes fail (For instance, there is no Febtober 62nd, and if you try to make one the
        program should stop you!)

        We can even make dates from Strings:
         */

        LocalDate end_of_semester = LocalDate.parse("2023-05-15"); //<- it's parse(), just like integer!

        /*
        Note: Dates are always parsed by default in YYYY-MM-DD (because anything else is ambiguous.)

        If we want to specify a different format, we can do so, using a "date format":
         */

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate US_end_of_semester = LocalDate.parse("05-15-2023", format);


        /*
        So what's the benefit of dates? Dates have convenient methods that let us compare two dates and calculate
        the days between them:
         */

                 boolean is_work_late = today.isAfter(deadline);
        boolean currently_in_semester = today.isBefore(end_of_semester);
              long how_many_days_left = ChronoUnit.DAYS.between(today, end_of_semester);

        /* Dates also use the same formatter objects to let us easily and prettily print out a date: */
        DateTimeFormatter display_format = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL); //<- uses this computer's
                                                                                                // default date format
        String pretty_today_text = today.format(display_format);
        System.out.println("Today is: " + pretty_today_text);


        /*
        LocalTime and LocalDateTime work similarly for times, and times + dates! Files often need to use dates to
        represent important information, so make sure to check out the JavaDocs for all three of these classes!
         */


    }
}
