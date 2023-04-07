

public class Student {
	

    //ATTRIBUTES
    private String first_name;
    private String last_name;
    private int age;
    private int year_in_program;

    //Normal attributes are specific to each student that is created. Every new student has a different name, a different age...


    //However, some attributes don't make sense as belonging to one particular student:
    //private int total_enrolled_students; //<- this would be stuck on one student -- it can't see other students, and other students can't see it.


    //When we have a "shared" attribute like this, we can represent this with a 'static' attribute:

    //STATIC ATTRIBUTES
    /* "Static" is a special keyword we can put right after "public" / "private" on a attribute or method to say that
    there is only one copy of that variable that is *shared* by all objects of that class.
     */

    private static int total_enrolled_students = 0;  //<- no one student owns this enrollment count, there is one copy shared by everybody.
    /*
    Unlike normal attributes, static attributes do often get a value right when they are created -- we might use them in the methods, but
    they're created right when the program starts, and they need to be initialized before they can be used. 
    */


    //Static attributes are also very useful for storing class-related constants:
    private static final String email_suffix = "@massbay.edu";   //<- every student will have the same email suffix, we don't need multiple copies
    	//The two keywords here express different parts of this value:
         //   - final says "this doesn't change at any point"
         //   - static says "we only need one copy of this thing" -- we can do this *because* it doesn't change
    

    //METHODS
    public Student(String given_first, String given_last, int given_age, int given_seniority) {
    	this.first_name = given_first;
    	this.last_name = given_last;
    	this.age = given_age;
    	this.year_in_program = given_seniority;


        /* When we create a new student, we might increment that "total_enrolled" counter that is shared by
        all Student objects. Since this data is *shared* by all Students everywhere, we can't use the "this" keyword.
        Instead, we use the name of the class (because static properties are shared by the whole class!):
         */
    	Student.total_enrolled_students = Student.total_enrolled_students + 1;

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




    /*
    If I wanted to actually read out the total enrolled students, I could make a normal method on my student object to read the count...
    */

    public int getEnrolledCount() {
    	return Student.total_enrolled_students;
    }


    /*

    ...but this means I always need an object in order to get the count. However, we said the count doesn't really belong to any particular student.
    In our programming model, this is sort of like needing to ask a MassBay student to tell us how many students there are, instead of just visiting
    the website.

    When we want to interact with static attributes, more commonly we'll use static methods:
    */


/* STATIC METHODS */
    /* Just like our static attributes, static methods are shared by all objects of a class:  */
    public static int getEnrollmentCount() {
    	//String someones_first_name = this.first_name; //<- We cannot do this. Which student would we be talking about?!
    	return Student.total_enrolled_students;
    }
    /*
    Just like before, I can fetch out that one shared variable, and return it to anyone who can call this function. 

     NOTE: Static functions have one very important limitation: We cannot access any normal attributes or methods 
     of our class from our static methods -- we wouldn't know which object we were talking about, because this one 
     copy of the method is *shared* by the entire class! 

    */


    public static void addFakeStudent() {
    	Student.total_enrolled_students = Student.total_enrolled_students + 1;
    }


}