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

        Once we've created a Role attribute on our Student, we've changed the relationship between these two ideas:
         */

        Student some_student = new Student("John Doe"); //<- this is one kind of person, who *has* a job
        some_student.register_for_work_study("Janitor");


        //Janitor full_time_employee = new Janitor("Bill Cassim"); //<-this is a different representation of a person, who *is* a job


        //List<Role> everyone_scheduled_for_Friday = List.of(some_student, full_time_employee); //<- these don't share a type

        /*
        However, we've hit a small snag -- now, we still can't create a mixed grouping of staff members and work study
        students, because a separately created Janitor role and a Student don't share a supertype (besides Object).

        This is because we're treating our Janitor inconsistently right now -- sometimes it's a whole Person, and
        other times it's a narrow function *of* a Person. Our solution should be separating the concerns of holding
        biographic information about a person and holding employment information about a person into two types that
        both have a single responsibility!

        Now that we've done this, we can mix Students and full time employees together:
         */

            Person a_staff_member = new Person("Bill Cassim");
            a_staff_member.setjob(new Janitor());

            List<Person> everyone_scheduled_for_Friday = List.of(some_student, a_staff_member);

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