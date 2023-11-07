

/*
Objectives for Today

By the end of today, we will:
    * Review the basics of interacting with files via Scanner.
    * Introduce the Files API.
    * Practice reading, writing, and parsing files.
 */


import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*
        When we learned about exceptions, we saw a simple example of working with Files.

        Java makes working with Files convenient -- first we create a File object to represent
        the file:
         */

        String relative_path_to_file = "resources/some_file.txt";
        File some_file = new File(relative_path_to_file);

        /*
        This File object has methods that let us perform several common file operations like
        moving/"renaming", copying, checking the size, or checking permissions:
         */

        boolean canRead = some_file.canRead();
        String full_path = some_file.getAbsolutePath();
        long filesize_in_bytes = some_file.length();
        //some_file.delete();
        //some_file.renameTo("new/relative/path");


        /*
        However, if we want to actually *read* a File, then we need to give it to a Scanner.
        A Scanner given a file works exactly like a Scanner given a stream like System.in:
         */
        try {
            Scanner reader = new Scanner(some_file); //<- remember to give the File, not a String name! (or you'll just read the string)

            String full_file = "";
            while ( reader.hasNext() ) {
                String next_line = reader.nextLine();
                System.out.println("Scanner'd file: " + next_line);
                full_file = full_file + "\n" + next_line;
            }

        } catch ( FileNotFoundException e ) {
            System.out.println("Scanner could not find the file: " + e.getMessage());
        }



        /*
        Writing a file is similarly easy: We give a File object to a PrintWriter:
         */

        try {
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            //As the name suggests, writer works just like System.out:

            writer.println("When writing a file this will REPLACE any old content completely.");
            writer.println("You can wrap a File object in a FileOutputStream to set an 'append' to add to files.");

            //FileOutputStream f = new FileOutputStream(new File("output.txt"), true); <- the true here says "append", instead of "overwrite"
            //PrintWriter w = new PrintWriter(f);

            //When we're done with a writer, we must remember to close it to "flush" the file:
            writer.close();


        } catch (IOException e ) {
            System.out.println("PrintWriter failecd to make file: " + e.getMessage());
        }


        /*
        However... these are not the only tools we can use to read and write files in Java!

        There is another object that we can use to represent a file location: the Path class.
         */

        Path file_location = Path.of("resources/some_file.txt");

        //file_location.resolve("starting/from/file_location/find/this/path"); //<- a relative path... relative to file_location


        /*
        Unlike a File, Path only has methods related to location, not file operations
        like moving or copying or the ability to check metadat. However, when we have a Path,
        we can use it with Java's "non-blocking IO" (java.nio) classes:
         */
        try {
            //Files.delete(file_location);
            //Files.createDirectory(Path.of("new_folder"));
            long size_of_file = Files.size(file_location);

            /*
            Most operations that use Paths are found right on the Files static class.
            We can even read and write files all in one line:
             */
            List<String> all_lines_of_file = Files.readAllLines(file_location);
            System.out.println("File contents: " + all_lines_of_file);

            for ( String line : all_lines_of_file ) {
                String[] parts = line.split(",");
                String pet_name = parts[0];
                String breed_of_pet = parts[1];
                int age = Integer.parseInt(parts[2]);
                System.out.println("Parsing this line, the pieces are: \nPetName: " + pet_name + ", Breed: " + breed_of_pet + ", age: " + age);

            }

            /*
            We can even get the entire file as one contiguous string:
             */
            String the_entire_file_including_newlines = Files.readString(file_location);
             System.out.println("\nThe entire file from Files: " + the_entire_file_including_newlines);


             /*
             To write, I just use Files.writeString(), and can even specify which writing mode on the same line:
              */
            Files.writeString(Path.of("output.txt"), "An extra line", StandardOpenOption.APPEND);

        } catch ( IOException e ) {
            System.out.println("Path-related code failed: " + e.getMessage());
        }


        /*
        So why two different versions of File methods? The answer is history!

        The original File/Scanner interface for working with files is a strongly object-oriented
        design. Files and Scanners encapsulate the behavior in a minimalistic, reusable interface
        that works for a variety of different input types.

        However, as thinking around files has changed, there were some gaps in the idea of a File --
        as Java packages became more complex, being able to specify a location in a virtual filesystem
        was useful, and many common operations were cleaned up to be more efficient.

        You can read Java's original release notes about Path here: https://docs.oracle.com/javase/tutorial/essential/io/legacy.html

        Fortunately, when we have one of these file object types, we can produce the other with little effort:
         */

        File file_from_path = file_location.toFile();
        Path path_from_file = some_file.toPath();

        /*
        One last quirk of working with files. When we are turning our code into a .jar, we have to
        be careful about how we read the file. In fact, none of our previous methods will work for
        a file that gets shipped *with* the JAR.

        To do this, we need to read the file location as a URL from Java's "class loader", and
        then use its stream:
         */
        URL location = Main.class.getResource("/some_file.txt");//<- our location is relative to the resource folder!

        try {
            InputStream stream = location.openStream(); //<- an InputStream is any source we can read data from
                Scanner s = new Scanner(stream);//<- ...this means we can use Scanner with it again!
            while ( s.hasNext() ) {
                System.out.println("Embedded contents: " + s.nextLine());
            }

            /*
            Or if we want to get even fancier and get a ".lines()"-like interface for our stream,
            we can give the InputStream to an InputStreamReader (a class that reads any input in the format specified),
            which we can then give to a BufferedReader (a class that can read and store a queue of text),
            which we can then ask for its lines():
             */
            stream = location.openStream();
            InputStreamReader stream_reader = new InputStreamReader(stream, StandardCharsets.UTF_8); //<- UTF_8 is "standard text"
            BufferedReader buffered_reader = new BufferedReader(stream_reader);
            List<String> lines = buffered_reader.lines().toList();
            System.out.println("file contents: " + lines);

            /*
            This trick works with ANY InputStream (even Files!)
             */
            //FileInputStream f = new FileInputStream(some_file);
            //InputStreamReader isr = new InputStreamReader(f);
            //BufferedReader r = new BufferedReader(isr);
            //List<String> lines = buffered_reader.lines().toList();




        } catch ( IOException e ) {
            System.out.println("getResource version failed: " + e.getMessage());
        }
    }
}
