

/*

Objectives for Today

By the end of today, you will:

    * Describe how objects allow us to store related information in a single variable.
    * Understand how classes let us describe a new datatype for objects.
    * Identify the two main components of a class: attributes and methods

*/



/* Vocabulary of the Day

Class -- A class is a template that describes a collection of bundled data and functions. When we use a class, we
create an "object" of that class.

Attribute -- An attribute is a special variable that is declared inside of a class. Unlike normal variables, which
            can only be seen in the block of code where they were declared, attributes are shared by all methods of
            a class.


Object-Oriented Programming - Object-Oriented Programming is a problem solving approach where a programmer divides
           program logic up into one or more classes.

*/


public class Classes {

	public static void main(String[] args) {
		/*
		So far, the programs we've written have focused mostly on using base types. This is sufficient for expressing
		complex programs, but at times it can be inconvenient. Say, for example, I wanted to create a program that
		helps track my courses to print myself a calendar.
		*/

		String course_name = "Programming I";
		String meeting_days = "MWF";
		String lab_days = "F";
		int start_hour = 11;
		int number_of_hours = 1;



		/*
		Every time we were talking about the course, we would need to use all these variables in combinations:
		*/


		System.out.println("Showing the details for " + course_name + ": " + "Meets on " + meeting_days + " at " + 
			start_hour + ":00 for " + number_of_hours + " hours.");


		/*
		While we *can* use individual variables, this can quickly become unwieldy.... As an example, I teach more than one course.

 			To do this, I would need to make a 'second copy' of all these variables:
		*/


		String second_course_name = "Programming II";
		String second_meeting_days = "TR";
		String second_lab_days = "T";
		int second_start_hour = 11;
		int second_number_of_hours = 2;


		/*

		Just like when we were learning about functions, this is bad because I've repeated myself -- I have copied my 'template'
		for a course. I can easily introduce an error when copy pasting, and if my idea of what things a course needs to track 
		changes in the future, I need to update it in every single place I copied the code, with no way to find those places.

		*/


		System.out.println("Showing the details for " + second_course_name + ": " + "Meets on " + second_meeting_days + 
			" at " + second_start_hour + ":00 for " + second_number_of_hours + " hours.");


		/*
		Luckily, we can do better. To start, we need to define a template of what one 'course' represents. We're going
		to do that by creating a second file for our program!
		*/

		/* 
		Creating a class has a very important effect: Every time we create a new class, we automatically get a new data type
		we can use for variables! The data type of the variable will match the name of the class we made!

		Once I've created my 'class' description in another file, I get a brand new type I can declare like any other:
		*/

		Course some_course;


		/*
		Just like how a variable needs to be assigned a value before it can be used, our course also needs to be assigned
		a value. To do this, we call a special function whose name matches the name of the data type:
		*/
		some_course = new Course();  //<- this is called the "constructor" function, and we'll learn more about it next class.



		/*

		A way to think of this is that I'm storing one reference to a giant box with many sub-divisions:

                       +//////////////+
		+------+       |  Course      |
		|      |       +--------------+ 
		|     <------- | [ ] name     |
		+------+       | [ ] number   |
	  some_course      | [ ] link     |
		               | [ ] hours    |
		               |              |
		               +--------------+


		Now that I have this new Course-typed variable, it has its own copy of every attribute we expect to belong to a class.

		In addition to the attributes, this first_course can also perform all the actions that we described a Course being
		able to do. It has its own copy of each of the functions that we described for it. However, we have a dilemma...
		How do I tell changeClassDetails() that I want to touch "first_course"'s copy of the class attributes?

		To call an object method, I will put the name of the object I want to use, then a dot, then the name of the method I am 
		going to call:
		*/
		
		some_course.changeClassDetails("CS120", "MWF", "M", 11, 1);
		String some_courses_details = some_course.getDetails();

		System.out.println("Showing somecourses' details: " + some_courses_details);


        /*
		If I make a second object, it's copies of variables are completely separate from the first course:
		*/

		Course second_course = new Course();

		second_course.changeClassDetails("CS200", "TR", "T", 11, 2);


		System.out.println("some_course's details: " + some_course.getDetails());
		System.out.println("Second_course's details: " + second_course.getDetails()); 

		                                                                          //<- even though I'm calling the 'same' function, I 
		                                                                          //  get different results.

		/*
        The big benefit of classes is that they let us separate our thinking into just considering how small bundles of related 
        variables solve small problems, without needing to think about the whole program at once. Much like functions, once they're
        created, we only need to know about what named functions are available for us to call!

        This is a technique called Object Oriented Programming, and is one of the biggest philosophical decisions that shapes the way we
        write programs.
        */



	} 

}


