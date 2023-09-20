


public class Course {


   //ATTRIBUTES

		private String course_name;
		private String meeting_days;
		private String lab_days;
		private String start_time;
		private int number_of_hours;
		private int previous_meeting_count;


	//METHODS

		/*
		The default constructor that we get automatically by Java looks something like this:
		*/
		public Course() {
			/*
			Java's default constructor doesn't do anything or set our attributes, so it's not very useful....
			Java ONLY creates a method like this if we don't create a constructor of our own, which we
			do below.

			NORMALLY we would only put this second version of the constructor. (It won't crash java to
			have both, because they use different arguments so Java can tell which you mean to use.)
			*/

			/*
			If you do still want to have a default constructor, it's common to put in placeholder values:
			*/
			this.course_name = "DOESNT EXIST";
			this.meeting_days = "NEVER";
		}



    /*
        This week, we're also going to write one 'special' method for our class: the constructor.
        The constructor is a method that describes all the "setup" that needs to occur when an object is created.
        It is written similarly to any other method, but it has a few important differences:
     */

		public                  Course(String given_name, String given_meeting_days, String given_lab_days,
	                                         String start_time_in, int meeting_hours) {
	/*  ^            ^              ^              ^
     public  NO RETURN TYPE     CLASS NAME        arguments

   To define a constructor in Java, we use a method signature with *no return type*, where the method name matches the
   name of our class.

   A lot of the time, a constructor does assignments to attributes straight from the parameters that were passed in:
   */

			this.course_name = given_name;
			this.meeting_days = given_meeting_days;
			this.lab_days = given_lab_days;
			this.start_time = start_time_in;
			this.number_of_hours = meeting_hours;


        // But sometimes, we need to manipulate those variables in some way, or we already know a default value:
        	this.previous_meeting_count = 0;


        //The constructor never returns anything, even though we assign the objects we create with them 
        //(the return is implied --  it would always be 'return this'.)

		}


// NOTE: Normally, this type of "change everything at once" function SHOULDN'T exist, it should be a constructor
// for a new object instead.

		/*
	public void changeClassDetails(String given_name, String given_meet_days, String given_lab_days, 
		                               String start_time_in, int meeting_hours) {
		this.course_name = given_name;
		this.meeting_days = given_meet_days;
		this.lab_days = given_lab_days;
		this.start_time = start_time_in;
		this.number_of_hours = meeting_hours;
	}
	*/


	public String getDetailsText() {

		String details;

		details = "Showing details for " + this.course_name + " : Meets on " + this.meeting_days + "  at " + 
		    this.start_time + " for " + this.number_of_hours + " hours."; 


		return details;
	}

	public String getFirstMeetingOfTheWeek() {
		String first_day = this.meeting_days.substring(0, 1); //<- this will save the "first letter" of the string
		return first_day;
	}



    /* The toString method automatically gets called when an object is concatenated where a String belongs. Let's make
     * our representation more useful: */
    @Override
	public String toString()  {
        /* The signature of toString must exactly match "public String toString()". @Override is an extra keyword that
         * can verify you typed the signature correctly (and are actually changing this default behavior.) It also makes
         * it clear to readers what you intend to do.*/


		String representation;

		representation = this.course_name + "(" + this.meeting_days + " @" + this.start_time + ")";

		return representation;
	}



	/*
	Every object also needs to be able to compare itself to other objects. It does this with the equals() method.
	*/
	@Override
	public boolean equals(Object other) {

		Course other_course = (Course) other; //<- this will turn our other course into its object with all its methods. It's
												// called a "hard cast" or just a cast.

		boolean are_the_same = other_course.course_name.equals(this.course_name)
		                        && other_course.meeting_days.equals(this.meeting_days)
		                        && other_course.start_time.equals(this.start_time); //<- two courses must be the same
		                                                   //If they're both Course objects, and meet at the same time with
		                                                   //with the same name.

		 return are_the_same;


	}



}