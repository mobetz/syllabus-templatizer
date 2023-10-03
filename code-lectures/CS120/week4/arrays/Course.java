
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
			//Default constructor -- DO NOT USE!
		}


    public Course(String given_name, String given_meet_days, String given_lab_days, String start_time_in, int meeting_hours) {

		this.course_name = given_name;
		this.meeting_days = given_meet_days;
		this.lab_days = given_lab_days;
		this.start_time = start_time_in;
		this.number_of_hours = meeting_hours;

        this.previous_meeting_count = 0;

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


	public void holdAMeeting() {
		this.previous_meeting_count = this.previous_meeting_count + 1;
	}



	/*
	We've talked about a lot of methods with custom logic, like our "getFirstMeetingOfTheWeek()". However,
	there are also some common patterns of types of methods we write over and over again. One such pattern
	is the use of 'getters' and 'setters'. They do exactly what their names imply: get or set the values
	of our attributes!
	*/


   //GETTERS

     /*
     Sometimes we want to still let programmers read the value of one of our private attributes.
     When this happens, we can create an accessor method (sometimes called a "getter".)

     A getter usually returns our attribute immediately.
      */

	public String getMeetingDays() {

		            /*
            Our getters let us make data available, without necessarily making it mutable/changeable.
               * Since we're just producing data the class already has, getters typically do not have parameters.
               * In the simplest example, getters just immediately return the attribute they reference:

						return this.meeting_days;


            However, because getters are entire functions, getters also give us an opportunity to do any data cleaning
            we might want to do here, like get rid of any spaces or convert text to capital case.
               */
		String pretty_formatted_days = this.meeting_days.toUpperCase();
		return pretty_formatted_days;

	}


	public String getCourseName() {
		return this.course_name;
	}



	//SETTERS

    /*
    Similarly, we can let people change one of our attributes by writing a mutator method, (sometimes called a "setter".)
     */


	public void setLabDay( String new_day ) {

		/*
		Like getters, our setters follow a typical pattern:
		    * They accept a single parameter of the same type as the attribute they set.
		    * They usually return nothing (or possibly a boolean stating whether the change was successful.)

		 In the simplest example, they just directly set an attribute from a parameter:

		   this.lab_days = new_day;


		 However, like getters, setters being functions give us an extra opportunity.
		 We can do data cleaning and validation:
		*/

		if ( new_day.length() == 1 && this.meeting_days.contains(new_day)  ) {//<- this ensures the lab meets only on a single day
			                                                                   //   that class normally meets.
			//This if statement lets me enforce my assumptions about how labs work so that people can only set the "right" values!
			this.lab_days = new_day;
		} 

	}





     @Override
      public String toString() {
         String representation;
         representation = this.course_name + " (" + this.meeting_days + " " + this.start_time + ", lab on " + this.lab_days + ")";
         return representation;
      }


}