

/*
Objectives for Today

By the end of today, you will:
  * Review the public and private keywords in Java.
  * Analyze when components of a class should be declared as public or private.
  * Identify the meaning of the terms "getter/accessor" and "setter/mutator" when we are creating methods on a class.
  * Describe how private components of a class can still be safely exposed to programmers who use your class.
 */


/*
Vocabulary for the Day:

Accessor Method - An accessor method (or 'getter') is a method whose primary use is exposing private attributes outside
    a class. Most accessors will accept no input parameters, and the return type will match the type of the attribute
    they are returning.

Mutator Method - A mutator method (or 'setter') is a method whose primary use is changing the value of a private attribute
   from outside a class. Most mutators will accept one input parameter, of the same type as the attribute they are
   modifying, and they will not return anything.
 */



public class Interfaces {

	public static void main(String[] args) {


        /* Let's take a look again at our Course class from last lecture:
         */
		Course first_course = new Course("Programming I", "MWF", "F", "10:00 AM", 1);

		/*

           We had moved all of our attributes into a constructor because we had wanted a course object to
           represent a particular course from the moment it was created.


           We set all of the attributes to private because we wanted to prevent others from changing the values in
           our object without our permission. For example, we might assume "previous_meeting_count" can go up,
           but not down, and it should always be at least 0. If it were public, we can't stop anyone from changing that!

        */

        //first_course.meeting_days = "SaturdayTuesdayWednesday";  //<- this is not how we were storing days of the week!
                                                                 //<- we could even put a day that doesn't exist here!


        /*

        This is bad because we've broken a big assumption about why our object exists. Each object is supposed
        to represent a real course, and now it's storing information that doesn't fit that metaphor, or information
        that isn't in the format we assumed we could use.

        We want a way to stop people from using our class incorrectly. Fortunately, because these attributes are
        private if someone actually tries to run this code...

        Our program crashes! The main method cannot see the meeting_days attribute at all. This applies not just to
        setting values, but also to getting values:
        */

        // String meeting_days = first_course.meeting_days;

        /*
        However... this means we can't actually use this data for things like printing, that we probably still want to do!

        This means we need another way to get this information.


        Fortunately, this is exactly what a method can help us do!
        */

        String meeting_days = first_course.getMeetingDays();
        System.out.println("This course meets on: " + meeting_days);




        /*
        This getter allows us to *read* meeting days, without letting someone incorrectly change it!

        This is necessary if there's logic somewhere else in the program that needs to know about days to do something that's
        NOT directly related to a Course.
        */



        System.out.println("Before setter: " + first_course);
        first_course.setLabDay("Saturday"); //<- not a valid choice!!!
        System.out.println("After unreasonable setter: " + first_course);

        //If we try to use a setter but use it incorrectly, the program stops the change from taking place. 
        // This prevents our class from ever violating its assumptions!


        first_course.setLabDay("M"); //<- this follows the 'rules' of our class, so we can allow it.
        System.out.println("After good setter: " + first_course);



        /*
        By combining the use of private attributes with getters and setters, we can create a 'safe' way to
        only let users see and change the parts of an object they have a reason to use.

        Another way to think about this is that we're making the user's perspective of how the class works smaller.


              What We Know                                |              What other programmers know
                                                          | 
           +///////////////////////////+                  |          +//////////////////////////////////+   
           |   Course                  |                  |          |    Course                        | 
           +---------------------------+                  |          +----------------------------------+         
           |   String name             |                  |          |                                  |         
           |   String lab_days         |                  |          |                                  |         
           |   String meeting_days     |                  |          |  made from constructor()         |         
           |   int number_of_meetings  |                  |          |                                  |         
           |                           |                  |          |  stops me from breaking anything |         
           |  getFirstMeetingOfWeek()  |                  |          |                                  |         
           |  - reads this.meeting_days|                  |          |  can give me first day of week   |         
           |  - uses string functions  |                  |          |   with getFirstMeetingOfWeek()   |         
           |  setLabDay()              |                  |          |                                  |         
           |  - modifies this.lab_day  |                  |          |                                  |         
           |  - verifies real day with |                  |          | lets me change the lab day with  |         
           |      'if' statement       |                  |          |       setLabDay()                |         
           |  getMeetingDays()         |                  |          |                                  |         
           |  - reads this.meeting_day |                  |          |  lets me see all meeting days    |         
           |  - makes sure capital     |                  |          |       with getMeetingDays()      |         
           |  toString()               |                  |          |                                  |         
           +---------------------------+                  |          |                                  |         
                                                          |          +----------------------------------+
           "here's how my class works"                    |             "here's what the class lets me do"
                                                          | 

        While we, the author of the class, need to understand why things work, the people using our code
        should only need to understand what they can do with them.

        This is sometimes called the "public interface" of a class.



		A well designed class has a minimal interface that hides unnecessary details about how the class works,
		while still exposing useful functions that give people a reason to use the class.

        */


	} 



}