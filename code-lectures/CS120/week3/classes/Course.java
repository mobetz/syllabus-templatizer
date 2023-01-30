


/*
   Our Course class is going to work like a 'blueprint' for describing a new course.

   Classes have two major parts:

   First, there are "attributes", which list all the data the class *has*.
   Afterward, there are "methods" which list all the things a class *can do*.


   We start the class the same way we've started every other .java file, by defining the class with the
   'class' keyword, and then naming it the same as the file:
 */

public class Course  {

   /*
   Everything related to our 'blueprint' is going to go inside our class' curly braces. Let's start by creating 
   sections for 'attributes' and 'methods':
   */


   //ATTRIBUTES

/*

   Each attribute in this class will list one piece of information that every course needs to have.

   We can do this by creating a variable right inside the class description!

   Note: each thing declared *on* a class needs to start with a scope/visibility keyword. Just like our functions,
   this tells us where this attribute can be seen:
       - If we set it to "private", only this class can see this variable.
       - If we set it to "public", any file that creates a Course can read that course's values.


   Otherwise, our attributes work just like declaring any other variable!

   */



		private String course_name;
		private String meeting_days;
		private String lab_days;
		private String start_time;
		private int number_of_hours;


   /*
      Classes besides the 'main' class work a little bit differently from main. Much like how functions don't do 
      anything until we use them, the variables we create in the class are more like a description of what data 
      an object *will have*. Every time we use this class, the object we make will get its own copies of each
      of these variables. However, we don't want to give them values right away --   Remember, we said a class
      is a blueprint. Unless every single object has the same value, it wouldn't make sense for us to assign 
      a value now.
    */


	//METHODS

   /*
   In addition to attributes, we can also choose to define functions on a class. This allows us to organize
   our code so that all the behaviors related to a course can be right near the variables that impact them.


   This matters most if we set other class elements to private! Remember, if a class feature is private,
   we can only interact with it in this file! 


   The one other difference between methods and functions are that methods are attached to a single, specific
   copy of attributes for one object. The function gets access to all those attributes for free, without
   passing them in as parameters!


   Let's make ourselves a function to set up a course, and another that can help generate the
    helpful details text from earlier:
   */


	public void changeClassDetails(String given_name, String given_meet_days, String given_lab_days, 
		                               String start_time_in, int meeting_hours) {
      /* Our method declaration here works just like every other function we've made this semester. However, 
      since we're now making a function that is supposed to work with objects, we no longer put the 'static'
      keyword!
      */


      /*
      When we're in this method, we might want to use the attribute variables that we created before. To
      do so, we need to use the word "this" with a dot before our attribute name, so that the code knows
      we're talking about a specific course's copies of the variables: 
      */

		this.course_name = given_name;
		this.meeting_days = given_meet_days;
		this.lab_days = given_lab_days;
		this.start_time = start_time_in;
		this.number_of_hours = meeting_hours;


	}


	public String getDetailsText() {

		/*
		Note: We didn't need to include function parameters here! We can use the class attributes we saved
		*/

		String details;

		details = "Showing details for " + this.course_name + " : Meets on " + this.meeting_days + "  at " + 
		    this.start_time + " for " + this.number_of_hours + " hours."; 


		return details;
	}

}