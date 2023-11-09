
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.concurrent.Executors;

/*
Objectives for Today

By the end of today, you will:
   * Understand how watchers allow us to detect when files change.
   * Write code that responds to file modification events.
   * Get more practice with file access objects.
*/



public class Main {
    public static void main(String[] args) {

		/*
		Last class, we learned about several different ways to interact with files on our computer:

		    * Using a File object and classes like Scanner and PrintWriter
		    * Using a Path object and the static methods in Files
		    * Using an InputStream from class resources

		 Today, we are going to stretch our knowledge of files to its limits and find some fun and creative
		 new ways to use files!

		 One common desire when working with files is to have your program be notified when one of those files
		 changes. There are many different motivations to want to do this, such as:
		     * Having a text editor "automatically update" when you overwrite a file a new version of the document
		     * Having an event log monitoring multiple different components
		     * Having your program detect when uploads or other long running tasks are making progress


		  All of these tasks require that our program know of a way to "watch" a file, and "listen" for changes.
		  Today we are going to see one way we could implement such behavior, by creating a FileListener class:
		 */

        File target_file = new File("watched_file.txt"); //<- if I decide I want to watch this one specific file

        FileListener l = new FileListener(target_file); //<- I can give that file to the FileListener I've written in FileListener.java

        //l.run(); //<- if I write this, I'm saying "stop everything and go watch for changes to the file"


		/*
		Since our FileListener implements Runnable, we can tell it to go off on its own and "run" in its own
		context. If you recall the event-driven programming we did with GUIs in Programming I, this works in a similar
		way: We can give the Runnable object to an "Executor", and that part of our program will run in its own time.

		This is actually a very basic example of creating programs with multiple "threads" of execution!
		 */

        Executors.newSingleThreadExecutor().submit(l);

		/*
		Now, I can make a small block of code to write to my file, and watch those changes get announced in my Listener:
		 */

        Scanner s = new Scanner(System.in);
        System.out.print("Enter a username: ");
        String username = s.nextLine();

        while ( true ) {
            String input = s.nextLine();
            try {
                String file_addition = username + " >> " + input + "\n";

                Files.writeString(target_file.toPath(), file_addition, StandardOpenOption.APPEND);
            } catch ( IOException e ) {
                System.err.println("Failed to write to file: " + e.getMessage());
            }
        }

			/*
			If I were feeling extra creative, I could even run two copies of this program at once and have them
			both watch the same file to "chat" between each other. In fact, this is the basis of most inter-process
			communication on some operating systems (like Linux!)
			 */


    }
}