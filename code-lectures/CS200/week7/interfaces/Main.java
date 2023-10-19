

/*
Objectives for Today

By the end of today, you will:

   * Identify the concepts of abstract classes and interfaces in Java.
   * Describe how interfaces avoid the diamond problem.
   * Practice writing interfaces and abstract classes.
   * Compare the benefits of abstract classes and interfaces.


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


		Let's say that we want to make an interface representing
		a behavior that multiple unrelated classes might want. For example, let's
		say we're making a game, and many different types of things are worth points.

		I can make an interface to represent that something is Scoreable.


		Both NameField and QuizQuestion implement Scoreable. Much like a base class,
		one of the benefits of an interface is that we can use it as the type for variables
		and collections:
		*/

        List<IScoreable> quiz_elements = new ArrayList<IScoreable>();

        quiz_elements.add(new NameField());
        quiz_elements.add(new QuizQuestion("What's the current version of Java?", "21", 5));
        quiz_elements.add(new QuizQuestion("What type of programming construct is java.util.List?", "Interface", 10));

        /*
        Even though QuizQuestion and NameField are completely separate types with no shared base class,
        they both "fit" in this list of elements.
         */

        for ( IScoreable element : quiz_elements ) {
            element.activate();
            System.out.println("This element is worth: " + element.getPoints());

            /*
            I can even loop through and assign each element to a variable whose type is an interface.

            When I do this, it's almost like assigning to a base type: we only have access to the things
            defined on the interface.
             */

            //element.displayText(); //<- displayText isn't part of IScoreable so we can't use it here!
        }


        /*
        I might decide that my collection of things needs to be both Promptable and Scoreable
        (each quiz question should be able to ask the user for something, and should also be able
        to carry a certain point value.)

        Unfortunately, in Java there's no way to make a collection with two interfaces of type
        constraint.

        List<IScoreable + IPromptable> my_list_of_questions_worth_points; //Java doesn't support this.... yet

        What I'm going to need to do is create a third type that represents the composite of the other
        two types: some type of thing "Quizzable" that also implements both that can be used by both my QuizQuestions
        and my NameFields.

         */

        NameField n = new NameField();
        System.out.println(n);


        //Quizzable x = new Quizzable(); //<- this doesn't make sense -- Quizzable isn't "finished" yet.


        List<Quizzable> questions = new ArrayList<>();

        questions.add(new NameField());
        questions.add(new QuizQuestion("What's the current version of Java?", "21", 5));
        questions.add(new QuizQuestion("What type of programming construct is java.util.List?", "Interface", 10));

        int total_score = 0;
        for ( Quizzable question : questions ) {
            question.ask();
            total_score = total_score + question.getPoints();
        }

        for ( Quizzable question : questions ) {
            System.out.println(question);
        }
        System.out.println("Your final score was " + total_score);

		/*
		Both interfaces and abstract classes are powerful tools for specifying common behaviors.



		         Interfaces                   |           Abstract Classes
		-------------------------------------------------------------------------------
		                                      |
		  You need your objects to implement  |  The normal constraints of inheritance are not
		    multiple supertypes               |  problematic (i.e no multiple inheritance.)
		                                      |
		   You want a purely declarative      |   You need the ability to implement some code
		   approach to listing methods.       |   right in the common type definition.
		   (you want to describe methods,     |
		   but not implement them right away) |    There is some opportunity for sharing code
		                                      |     between types that implement this supertype.
		   Entirely unrelated objects may     |
		   implement this behavior without    |  You need the ability to specify private or
		   any shared details in how they     |  protected fields in addition to public fields!
		   execute their shared methods       |
		                                      |
		                                      |
		 */

    }
}
