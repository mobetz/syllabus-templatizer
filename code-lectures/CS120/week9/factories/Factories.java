
import java.time.LocalDate;
import java.io.FileNotFoundException;

/*
Objectives for Today

By the end of today, you will be able to:
    * Describe how the static factory methods let us safely create objects with the possibility of failure.
    * Analyze common concerns that might indicate the need for a factory method.
    * Write static factory methods for our own classes.
    * Identify existing Java classes that use static factory methods.
*/

/*
  
 Factory - A 'factory' in programming refers to a group of instructions that are responsible for the creation
 of other objects, often hiding or replacing a normal constructor. Factories can be implemented either as
 static methods on a class, or as entirely separate classes (sometimes called "builders".)

 */


public class Factories {

    public static void main(String[] args) throws FileNotFoundException {

        /*
         Whenever we've wanted to create an object in Java, the constructor has been our go-to tool for 
         bundling all the required details into a single variable:
         */

        Student some_student = new Student("Jae", "Freeman", 19, 1);


        /*
         As our objects get more complex, we often desire the ability to load objects from some external source,
         or supply all the details in a single compact format. As an example, maybe we want to be able to parse
         a single string with all the student details.

         Our first strategy might be to try and overload the constructor with a second pattern for creating students:
         */ 


        String student_as_text = "Carol,Chu,20,1";
        Student parsed_student = new Student(student_as_text);

        System.out.println("Carol's email is: " + parsed_student.generateEmail()); 


        String bad_data = "Harold,Levin,apple,3.2132,100,100,100";
        Student what_does_this_create = new Student(bad_data);  //<- when I call this constructor with 'junk' data, I still get a student


        //...However, just like when we were learning about constructors, this student might break if I try to use him:
        //String bad_email = what_does_this_create.generateEmail();  //java.lang.NullPointerException: "this.first_name" is null
        //System.out.println("The fake student's email address is: " + bad_email);


        /*
        Since constructors must ALWAYS succeed, we should only use them to do a version of construction that always succeeds.

        If we want to do something that only *might* produce an object, we should make a separate function to do that, and call
        the constructor internally as part of that process.

        This is the idea behind the 'Factory' pattern -- my function will work like a factory that should produce working Students
        when given the proper inputs, but it has the flexibility to return a dummy value or null when creation fails.

        We need to decide where to put this 'factory' method. Since it's still related to students, Student.java seems like a good
        fit. Since we won't have a student yet when we're making a new student, we should declare this new factory as static! 
        */

        Student possible_student = Student.parse(bad_data);


        /*
        Now, when student creation fails, the entire object doesn't get created:
        */
        if ( possible_student != null ) {
            //In here, I know my student was produced with valid data, and it will only run when the factory succeeded:
            String email = possible_student.generateEmail(); 
            System.out.println("The fake student's email address is: " + email);
        }


        /*
        There are a couple common reasons to create factories:
            - When we want to convert a string into another type of information (these factories often use "parse" in their name...)
            - When we want to read from a File or external data source (these factories often use "load" in the name...)
            - When we want to build an object from parts or another type of object (these factories often use "of" or "from" in the name...)

         We've actually worked with several examples of factories already:
         */

        LocalDate date_now = LocalDate.now();
        LocalDate date = LocalDate.parse("2023-01-01"); //<- this is a static method on the LocalDate class!
        LocalDate date2 = LocalDate.of(1990, 12, 30); //<- so is this!
        //LocalDate date3 = new LocalDate(2020, 13, 99);  //<- we don't just want to use a constructor for Dates, because we can give numbers that
                                                         //  that don't make sense!
        int some_num = Integer.parseInt("4"); //<- even this is a static method on a class named "Integer"!



        /*
        We can even create multiple specialized factories that let us build objects from different sources, or build whole
        arrays of objects at once:
        */
        Student load_from_file = Student.fromFile("student.sdt");

        if ( load_from_file != null ) {
            System.out.println("The student who was loaded from file has the email address: " + load_from_file.generateEmail());
        }

        Student non_existent_student = Student.fromFile("asdflkhjaskldrfjahjksdf.sdt");
        if ( non_existent_student != null ) {
            System.out.println("The student who was loaded from file has the email address: " + load_from_file.generateEmail());
        }



        Student[] my_class = Student.fromCourseRosterFile("roster.rst");

        System.out.println("The loaded roster has: " + my_class.length + " students. Their emails are:");
        for ( Student next : my_class ) {
            System.out.println("  - " + next.generateEmail());
        }
        
        
        /*
        Factories are a great way to design flexible object creation that ensures your class attributes still have valid values!
        */

    }

}