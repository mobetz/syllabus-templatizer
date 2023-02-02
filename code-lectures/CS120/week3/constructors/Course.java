

public class Course  {


   //ATTRIBUTES
		private String course_name;
		private String meeting_days;
		private String lab_days;
		private String start_time;
		private int number_of_hours;
		private int previous_meeting_count;


	//METHODS

		public Course() {

		}

    /*
        This week, we're also going to write one 'special' method for our class: the constructor.
        The constructor is a method that describes all the "setup" that needs to occur when an object is created.
        It is written similarly to any other method, but it has a few important differences:
     */

    public                    Course(String given_name, String given_meet_days, String given_lab_days, 
		                               String start_time_in, int meeting_hours) {
  /*  ^            ^              ^              ^
   public  NO RETURN TYPE     CLASS NAME        arguments

   To define a constructor in Java, we use a method signature with *no return type*, where the method name matches the
   name of our class.

   A lot of the time, a constructor does assignments to attributes straight from the parameters that were passed in:
   */

		this.course_name = given_name;
		this.meeting_days = given_meet_days;
		this.lab_days = given_lab_days;
		this.start_time = start_time_in;
		this.number_of_hours = meeting_hours;

        // But sometimes, we need to manipulate those variables in some way, or we already know a default value:
        this.previous_meeting_count = 0;

        //The constructor never returns anything, even though we assign the objects we create with them 
        //(the return is implied --  it would always be 'return this'.)

		}



// NOTE: Normally, this type of "change everything at once" function shouldn't exist, it should be a constructor 
// for a new object instead.
	public void changeClassDetails(String given_name, String given_meet_days, String given_lab_days, 
		                               String start_time_in, int meeting_hours) {
		this.course_name = given_name;
		this.meeting_days = given_meet_days;
		this.lab_days = given_lab_days;
		this.start_time = start_time_in;
		this.number_of_hours = meeting_hours;
	}


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
      public String toString() {
        /* The signature of toString must exactly match "public String toString()". @Override is an extra keyword that
         * can verify you typed the signature correctly (and are actually changing this default behavior.) It also makes
         * it clear to readers what you intend to do.*/

         String representation;

         representation = this.course_name + " (" + this.meeting_days + " " + this.start_time + ")";

         return representation;
      }




} 
