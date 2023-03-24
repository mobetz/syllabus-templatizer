

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Student {
	

    //ATTRIBUTES
    private String first_name;
    private String last_name;
    private int age;
    private int year_in_program;





    private static int total_enrolled_students = 0; 
    private static final String email_suffix = "@massbay.edu";   

    //METHODS
    public Student(String given_first, String given_last, int given_age, int given_seniority) {
    	this.first_name = given_first;
    	this.last_name = given_last;
    	this.age = given_age;
    	this.year_in_program = given_seniority;

    	Student.total_enrolled_students = Student.total_enrolled_students + 1;
    }



    /* BAD VERSION -- Constructors that can FAIL */

    public Student(String student_as_text) {

        /*
        In this version of the constructor, we're going to parse this string and try to set each value
        from it:

        */
    	String[] parts = student_as_text.split(",");

        if ( parts.length == 4 ) {
    	    this.first_name = parts[0];
    	    this.last_name = parts[1];

            if ( parts[2].chars().allMatch( Character::isDigit ) ) {
    	    	this.age = Integer.parseInt(parts[2]);
    		}
            if ( parts[3].chars().allMatch( Character::isDigit ) ) {
    	    	this.year_in_program = Integer.parseInt(parts[3]);
    		}
        }
        else {
            System.err.println("WARNING: I got bad student data, but I'm still creating something...");
        }
    	Student.total_enrolled_students = Student.total_enrolled_students + 1;


        /*
        However, there's a big, glaring problem with the design of this constructor....

        I have no way to enforce that users will actually use it correctly! They can send me literally any text,
        and Java won't complain. In fact, right now my program will crash if there are the wrong number of
        'parts' to the string, or if the last values aren't numbers. 

        I can try to mitigate this by doing a bunch of if checks, but there's still a problem:
        constructors *always* return a created object at the end of the method. If someone gives me bad data, I'm
        forced to give them back a student with junk values.
        */
    }






    public String getName() {
    	return this.first_name + " " + this.last_name;
    }


    public String generateEmail() {
    	String first_letter_of_first_name = this.first_name.toLowerCase().substring(0,1);
    	String last_name = this.last_name.toLowerCase();
    	String full_email = first_letter_of_first_name + last_name + Student.email_suffix;
    	return full_email;
    }


    public int getEnrolledCount() {
    	return Student.total_enrolled_students;
    }




    /* STATIC METHODS */
    public static int getEnrollmentCount() {
    	return Student.total_enrolled_students;
    }


    public static void addFakeStudent() {
    	Student.total_enrolled_students = Student.total_enrolled_students + 1;
    }



    /* GOOD VERSIONS - Factory Pattern */

    public static Student parse(String student_as_text) {

        /*
        Now that our 'from string' constructor is a static method, we need to supply a return type. 
        The return type is going to be the type of object we're getting back -- a Student! Most
        of the code will be identical, but now we need to actually supply the return:
        */

        Student created_student = null; //<- in the beginning, I have a placeholder


        String[] parts = student_as_text.split(",");

        boolean right_number_of_parts = parts.length == 4;

        //Before I try to create the object, I can do all my validation on the input....
        if ( right_number_of_parts ) {

        	boolean age_is_number =  parts[2].chars().allMatch( Character::isDigit );
        	boolean senority_is_number =  parts[3].chars().allMatch( Character::isDigit );

        	if ( age_is_number && senority_is_number ) {
            	//...then call the 'safe' version of the constructor to build the object:
        		created_student = new Student(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        	}
        } else {
            System.err.println("WARNING: I got bad student data. I'm returning null!");
        }

        return created_student;
    }



    public static Student fromFile(String filepath) throws FileNotFoundException {
        File student_file = new File(filepath);
        if ( student_file.exists() && filepath.endsWith(".sdt")) {
        	Scanner some_scanner = new Scanner(student_file);
        	String student_text = some_scanner.nextLine();

        	return Student.parse(student_text);
        }
        return null;
    }


    public  static Student[]  fromCourseRosterFile(String filepath)  throws FileNotFoundException  {

    	Student[] roster = new Student[100];
    	int student_count = 0;

    	File roster_file = new File(filepath);
    	if ( roster_file.exists() && filepath.endsWith(".rst") ) {
    		Scanner some_scanner = new Scanner(roster_file);

    		while ( some_scanner.hasNextLine() ) { 
    			String student_text = some_scanner.nextLine(); //<- get each next student one at a time
    			Student next_student = Student.parse(student_text); //<- try to convert just that student
    			if ( next_student != null ) { //<- if the conversion was successful, add that student to the roster
    			    roster[student_count] = next_student;
    			    student_count = student_count+1;
    			}  
    		}
    	}


    	Student[] right_sized_roster = new Student[student_count];

    	for ( int i=0; i<student_count; i++) {
    		right_sized_roster[i] = roster[i];
    	}

    	return right_sized_roster;

    }

}
