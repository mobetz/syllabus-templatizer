
/*
Objectives for Today

By the end of today, you will:
  * Understand how we can use third party libraries to help us solve problems.
  * Define a "package repository", and identify the Maven repository.
  * Describe the parts of the build.gradle manifest.
  * Practice using third party libraries to solve real problems.
 */



/*
Vocabulary for the Day

Software Repository - In computer science, a repository is a collection of software that has been indexed to be easily
searchable by automated tools.


Dependency - A 'dependency' is the name for code that is required for another piece of code to run (i.e if my code
'depends' on libraries A, B, and C, then A, B, and C are 'dependencies' of my project.)


Manifest - A manifest file is a special file included in the root directory of a project, which describes its contents,
as well as any external dependencies and build steps required to run the program.

*/

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;



public class ThirdParty {
    public static void main(String[] args) throws IOException {


        /*
        So far this semester, all of the code we have written has been fairly self-contained.

        However, often when writing a program, we don't want to do all of the work ourselves.

        Fortunately, Java is a language that has been around for many years, and as a result, many people have already
        written code that solves most common problems.


        Today, we are going to learn how to use this code in our own projects.

        We call these packages of code that other people have written "third party" code ("first party" being code we
        have written ourselves.) In order to find what "third party" libraries of code exist, we need to check a
        software repository.

        For Java, the most popular repository is Maven Central: https://mvnrepository.com/

        Let's try searching this repository to see if we can find a solution to help us figure out how to read a CSV file 
        with weather reports.

       Let's go with the Apache Commons CSV-Parser.


       To use these libraries, we must import them the same way we do all the other classes we want to use from Java.

       However, unlike the normal Java imports that we can just import immediately, we need to tell our program where
       to find this code (because remember, it's not shipped with Java by default.)
        */





      /*


       One way to do this would be to download the files ourselves and add them to Java's "classpath" when we run 
       java. To do this, we would put all the "third party" code into a folder, then run javac and java with the
       -cp option specifying that folder:

           javac -cp ".;lib/*" Main.java


        This example says "to find the classes I use, first look in the . folder (the current directory), and 
        then afterward check everything in the ./lib/ directory." Running Java works the same way.
        (Note: In windows these commands will use semi-colons to separate folders, but on some Mac and Linux systems 
        they will use colons instead.)

        However, writing this extra command line option when compiling our code can be a hassle. Another option we 
        have is to use a 'build configuration tool'.


        If you ever hear about programs called "Ant" or "Maven", these are two of the longest-lived build configuration
        tools. Today, we're going to learn about their newer sibling: Gradle.



        To turn our project into a "Gradle" project, two things have to be true:
           - we need to add a build.gradle file to the root of the project
           - Our source code root has to live in src/main/java (or you have to set the source root in build.gradle)

         Let's go visit the build.gradle file to see all the things we add.


        To use this build.gradle, we just need to run gradle with the 'gradle run' command on our command line.

         The nice thing about gradle is, in addition to running our code, it will also download and manage our
         third party libraries for us!

         Now, let's try out the CSVParser we found on Maven Central:
       */



              List<CSVRecord> list_of_reports = getRecordsFromPath("resources/boston_weather.csv");

              CSVRecord first_report = list_of_reports.get(0);
              String temp = first_report.get("TMAX");
              String date = first_report.get("DATE");

            System.out.println("Here's the first report: " + temp + "F on " + date);


            /*
            The powerful thing about libraries is that they let me spend more time working on the unique problems I want to solve:
            
            Say my actual task was finding the weather for a particular day:
            */

            Scanner s = new Scanner(System.in);
            System.out.print("Enter a date (YYYY-MM-DD): ");
            String entered_date = s.nextLine();
            LocalDate user_date = LocalDate.parse(entered_date);
            /*
            I don't have to waste time parsing the file, figuring out how to read values out, doing all the 'fiddly bits' before we 
            get to the real problem solving. I advance immediately to writing that specific logic:
            */

            for ( CSVRecord next_record : list_of_reports ) {
              String date_as_text = next_record.get("DATE");
              LocalDate next_date = LocalDate.parse(date_as_text);

              if ( next_date.isEqual(user_date) ) {
                String that_days_high = next_record.get("TMAX");
                System.out.println(" On " + user_date + ", the weather was: " + that_days_high );
              }

            }




  }



  public static List<CSVRecord> getRecordsFromPath(String file_location) throws IOException {


    File loaded_file = new File(file_location);

    CSVFormat format = CSVFormat.newFormat(',').withHeader().withQuote('\"');
    CSVParser a_parsed_csv_file = CSVParser.parse(loaded_file, StandardCharsets.UTF_8, format);

         /*
            This CSVParser replaces all the 'loader' code we wrote with a few short function calls!

            This is the power of libraries -- they allow us to solve common problems quickly so that
            we can focus on the tasks that make our program unique.
        */

    return a_parsed_csv_file.getRecords();

  }
}