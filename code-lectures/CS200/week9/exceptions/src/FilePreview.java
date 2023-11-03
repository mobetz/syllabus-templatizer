import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilePreview {

    private File target_file;

    public FilePreview(String filename) {
        this.target_file = new File(filename);
    }


    public List<String> readAllLines() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Scanner s = new Scanner(this.target_file); //<- this can predictably fail when the File doesn't exist!
        while ( s.hasNext() ) {
            lines.add(s.nextLine());
        }

        return lines;

        /*
        Because Java knows that sometimes files don't exist, it forces us to acknowledge this possibility.
        The most straightforward thing we can do is write in the method signature that readAllLines can fail --
        to do this, we add "throws FileNotFoundException" after the parameters. We can even include multiple
        exception types if necessary (separated by commas.)

        However.. if we do this, then we also need to include that throws in any method that calls this method,
        because if the crash happens here, it will bubble up through the whole call stack!
         */

    }


    /*
    A more elegant solution to this problem is a try-catch right in our method:
     */

    public List<String> readAllLinesSafe() { //When I use a try-catch to handle a checked exception, I no longer need "throws" in the signature

        List<String> lines;

        try {
            lines = new ArrayList<>();
            Scanner s = new Scanner(this.target_file); //<- this can predictably fail when the File doesn't exist!
            while (s.hasNext()) {
                lines.add(s.nextLine());
            }
        } catch ( FileNotFoundException e ) {
            lines = new ArrayList<>(); //<- if I can't read the file, I'll just create an empty array here.
        }

        return lines; //<- note that we have to be careful with returns in a try-catch block -- a return inside a try
        // might not happen when we catch, so often variable declarations and returns have to be hoisted.

    }



    /*
    One final thing about exceptions: if you *want* your program to crash, you can induce a crash with the "throw"
    keyword. Much like "return", the throw keyword is immediately followed by an object. In throw's case, that object
    must be an Exception. This tells the program to crash on this line, offering the provided Exception to any try-catches
    above it!
     */

    public int addPositiveNumbers(int first, int second) throws UsedItWrongException {
        if ( first < 0 || second < 0 ) {
            UsedItWrongException error = new UsedItWrongException("You provided negative numbers when I asked for positive ones!!");
            throw error;
        }

        return first + second;
    }
}
