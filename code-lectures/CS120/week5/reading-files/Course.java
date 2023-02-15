

public class Course  {


   //ATTRIBUTES
		private String course_name;
		private String meeting_days;
		private String lab_days;
		private String start_time;
		private int number_of_hours;
		private int previous_meeting_count;


	//METHODS


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


	public String getMeetingDays() {
		String pretty_value = this.meeting_days.toUpperCase();
		return pretty_value;
	}




	public void setLabDay(String new_day) {
		if ( new_day.length() == 1 && this.meeting_days.contains(new_day)) { 
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
