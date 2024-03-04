/*
Objectives for Today

By the end of today, you will:
    * Identify the concept of composition.
    * Understand how composition allows us to more expressively model pluggable behaviors for our objects.
    * Practice combining composition and inheritance.


 Vocabulary of the Day

Single Responsibility Principle - The "Single Responsibility" principle is the idea that each class should have a narrowly
defined purpose in our code. Rather than creating large tent objects that capture all the behavior related to a general
concept, we should create classes for each isolated component and then "compose" them together with facade objects
when necessary.
 */

import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
        Last class, we were working on an example that would allow us to give our Students a job. We had seen that
        modeling this just with inheritance is impossible.

        Let's start by recreating some of those objects!

        We had floated the idea of inserting one of these objects into the other rather than inheriting all these
        attributes directly through a supertype. This is a technique called composition -- with composition,
        rather than saying a Student *is* a Worker, we instead say a Student *has* a Job.

        Let's go ahead and see how that would play out in this class.

        Once we've created a Job attribute on our Student, we've changed the relationship between these two ideas:
         */

         Student some_student = new Student("John Doe"); //<- this is one kind of person, who *has* a job
         some_student.set_job(new Janitor());

         /*
          * When we switch to composition, we have the flexibility to change jobs after the object has been
          created. However, note that this means the job object itself is something different: 
          */

          Janitor some_janitor = new Janitor(); //<- This "job" is missing some information that we've moved over to Person
                                                // A janitor created like this would have no name, for instance

          Person some_person = new Person("Joe Clean");
          some_person.set_job(some_janitor); //<- This means any time we want to represent a job in our program,
                                             // we need TWO objects.

           /*
            Our solution was separating the concerns of holding biographic information about aperson and holding 
            employment information. This means all of our workers are now represented as two different objects,
            each holding half the "story" about them.

        Now that we've done this, we can mix Students and full time employees together:
         */


         List<Person> everyone_scheduled_for_friday = List.of(some_person, some_student);
         /*
          * Since every job is still attached to a person, I can still polymorphically represent them in a single
          group. However, when I interact with this collection, I'm not going to have full access to all the 
          information that I did before.
          */

          double total_expenses = 0;
          for ( Person person : everyone_scheduled_for_friday ) {
            //I'm doing payroll, and I need to pay them.
            if ( person.is_employed() ) {
                //person.getSalary(); //<- we can't do this -- we split off salary functions away from Person, so person doesn't automatically know about it
                total_expenses = total_expenses + person.getJobSalary(); //<- by creating a function on our outer interface, we can still access things from the Job inside                
            }
            
          }

          /*

        Composition can be used in many different ways to simplify and reuse behaviors in our classes! When we use
        composition, we:

           - make it easier to create multiple 'strategies' that all execute a behavior, and let the programmer pick
           which one an object should use when they instantiate it. For example, I could make a House class that has a
           HeatingStrategy component that knows how to .heat() the whole house, then let the user decide whether they
           want to use GasHeat, ElectricHeat, or some new heating strategy object they came up with themselves to heat
           the House. House gets the benefit of supporting the behavior, without the burden of needing to know anything
           about how it works!

           - make it possible for more than one unrelated type to "inherit" a well-defined vertical of behavior, such
           as giving multiple different people classes access to job-related functions. In the future, we
           could even create something like a contractor corporation class that can pick up jobs, without needing
           to change Student, Staff, or Role at all!

           - make it easier to build complex interfaces that orchestrate many different subsystems together. For example,
           I can create an AVController class that is composed of a Speakers object, a TV object, a Radio object, and a
           Lights object. A user of my program only needs to interact with one single AVController, and that AVController
           ensures all the other subsystems play nicely together, without needing to implement all the logic in one place.
         */



    }
}