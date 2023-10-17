
/*
Objectives for Today

By the end of today, you will:

   * Identify the concepts of abstract classes and interfaces in Java.
   * Describe how interfaces avoid the diamond problem.
   * Practice writing an abstract class.
   * Compare the benefits of abstract classes and methods.


Vocabulary of the Day

Abstract Class - An abstract class is a class where some methods are declared,
but left unimplemented. Abstract classes cannot be instantiated directly, but
must be created through a subtype.

Interface - An interface is a description of a group of methods that a class
should implement. Instead of "extending" an interface, we "implement" an 
interface.


*/


import java.util.*;


public class Main {
	
	public static void main(String[] args) {
		/*
		So far, our examples of polymorphic behavior have all been implemented
		using inheritance. As we saw last week, this can cause some problems,
		especially if we want to express multiple identities our objects should
		have.

		However, it turns out there are tools that allow us to create this
		type of relationship: interfaces!

		Let's say, for example, that we want to make an interface representing
		a behavior that multiple unrelated classes might want. For example, let's 
		say we're making a game, and many different types of things are worth points.

		I can make an interface to represent that something is Scoreable.


		Both NameField and QuizQuestion implement Scoreable. Much like a base class,
		one of the benefits of an interface is that we can use it as the type for variables
		and collections:
		*/

		List<Quizzable> quiz_elements = new ArrayList<Quizzable>();


		quiz_elements.add(new NameField());
		quiz_elements.add(new QuizQuestion("What's the current version of Java?", "21", 5));
		quiz_elements.add(new QuizQuestion("What type of programming construct is java.util.List?", "Interface", 5));


		for ( Quizzable q : quiz_elements ) {
			/*
			We can even loop through and assign each element to an interface variable.

			However, much like a base type, we only have access to things defined in the interface:
			*/

			//q.ask(); //<- this isn't in IScoreable, so we can't use it if we only have IScoreables

			q.ask();

		}

		int total_score = 0;

		for ( IScoreable q : quiz_elements ) {
			System.out.println(q);
			total_score = total_score + q.getPoints();
		}

		System.out.println("Final score: " + total_score);

		/*
		Both interfaces and abstract classes are powerful tools for specifying common behaviors.



		         Interfaces                   |           Abstract Classes 
		-------------------------------------------------------------------------------
		                                      |
		You need your objects to implement    |   There is some opportunity for sharing code
        multiple supertypes                   |   between types that implement this supertype.
		                                      |
		Entirely unrelated objects may        |   You need the ability to implement some code
		implement this behavior without       |   right in the common type definition. 
		any shared details in how they        |
		execute their shared methods          |   You need the ability to specify private or
		                                      |   protected fields in addition to public fields!
		You need a purely declarative         |
		approach to listing methods.          |   The normal constraints of inheritance are not
		                                      |   problematic (i.e no multiple inheritance.)
		                                      |
		                                      |
		                                      |
		*/
	}


}