

/*
Objectives for Today

By the end of today, you will:
    * Identify the dangers of downcasting a supertype into a subtype.
    * Understand why "diamond-shaped" class hierarchies are disallowed in Java.
 */



/*
Vocabulary of the Day

Downcasting - Downcasting refers to the act of turning an object of a supertype into its subtype. (e.g casting an
Object variable into another class.)

 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
        Previously, we've seen that Java lets you store objects of one type in a variable of any of its ancestor types.
        This was particularly useful when we were creating a collection with many subtypes as members, and also when we
        wanted to pass any subtype into a method.

        However, today we're going to explore some of the limitations of playing fast and loose with types.

        First, let us recreate a Worker class with  subtypes to represent different types of Instructor employees.

        We've seen that one of the benefits of this is that all our objects can fit in one collection:
         */

         List<Worker> college_employees = new ArrayList();

         college_employees.add(new Janitor("John Clean"));
         college_employees.add(new Instructor("Michael Adjunctson"));
         college_employees.add(new Administrator("Don Executive"));



        /*
        Let's say we've noticed a similarity, that all our types of objects allow us to add *something* to a collection.
        We might be tempted to write a block of code like this:
         */

         Scanner s = new Scanner(System.in);
         System.out.print("Enter an employee id: ");

         int id = Integer.parseInt(s.nextLine());
         System.out.print("Enter a thing to add: ");
         String entered_addition = s.nextLine();


        for ( Worker w : college_employees ) {

                /*
                In here, add the added thing using the appropriate method

            Here we've got a problem... we need to call addRoom on Janitor, but add_course on Instructor or Faculty.
            We might end up trying to solve this like this:
             */
            if ( w instanceof Janitor) {
                Janitor janitor_worker = (Janitor) w;  //<- THIS IS UNSAFE
                janitor_worker.addRoom(entered_addition);
             }  else if ( w instanceof Instructor ) {
                Instructor i = (Instructor) w;
                i.assign_class(entered_addition);
             }

        }



        System.out.println(college_employees.get(2));

                /*
                We should NEVER* write code like this! This is a technique known as "downcasting", and it's bad for
                several reasons:

                - This code is inflexible to new subtypes -- if someone adds a new class like a Cashier or a Dean,
                we're going to need to go and add a new branch to every if-chain that tries to downcast Worker.

                - This code doesn't actually make sense. On each branch of this loop, we're doing a fundamentally
                different action. It's very possible in the future one of these things gets upgraded to a full
                Course or Room object, and this entire section will need to be rewritten.

                 - OverLOADED methods may not be called on newly created subtypes in the future -- we might get
                 different behavior than we're expecting if someone else extends our objects.

                - This code is very repetitive -- we're doing similar things on each branch. Even if these *were*
                similar operations, there should likely be a way to express this without using a subtype (e.g there
                should be some method common to all Workers if they did all have similar tasks.)

                Instead, we should focus on creating interfaces that describe all the actions our top level types
                should be able to perform, and only call *those* methods when we have a collection of them grouped
                together.
                 */


        /*
        What about if I also wanted my program to model Students who register for courses...

        I might decide I want to support work study students, who have both courses they're registered for, and a
        salary. In fact, I want these two to interact (say I want to ensure that students can't work hours based
        on how many classes they have...)


        The first thing I might try would be to inherit both Student and Worker. Let's see what would happen.


        As we can see, making a StudentWorker that extends two different classes can lead to a lot of problems. If
        we think about our class hierarchy, it looks something like this:

                                                 +-------------------+
                                                 | Object            |
                                                 +-------------------+
                                              /                        \
                               +---------------+                      +------------------+
                               | Student       |                      | Worker           |
                               +---------------+                      +------------------+
                               | name          |                      | name             |
                               | id_num        |                      | id_num           |
                               | courses       |                      | salary           |
                               +---------------+                      +------------------+
                                        \                                  /
                                          \                              /
                                            \                          /
                                              V                       V
                                              +-----------------------+
                                              | StudentWorker         |
                                              +-----------------------+
                                              | toString              |
                                              +-----------------------+

        This "diamond shape" in the class hierarchy is why we have a problem:   Any attributes or methods
        separately implemented or overridden from the base type cannot be uniquely resolved in the
        rejoined descendant class -- since they share a name, it's impossible to tell which "version"
        of the field we want to use.


        Let's look at a slightly more complicated example. Say we had a class hierarchy like this:

                                     +-------------------+
                                     | Fruit             |
                                     +-------------------+
                                     | getFlavor()       |
                                     +-------------------+
                                  /                        \
                   +---------------------------+         +---------------------------+
                   | Apple                     |         | Banana                    |
                   +---------------------------+         +---------------------------+
                   | getFlavor()->"tangy"      |         | getFlavor()->"sweet"      |
                   +---------------------------+         +---------------------------+
                                 \                                  /
                                   \                              /
                                     \                          /
                                       V                       V
                                       +-----------------------+
                                       | Appleana              |
                                       +-----------------------+
                                       | toString              |
                                       +-----------------------+

                    If I wrote a snippet of code like this:

        */


        Appleana myFruit = new Appleana();

        Fruit disguised_fruit = myFruit;

        disguised_fruit.getFlavor(); //<- it is impossible in ANY programming language to tell whether you mean
                                     // Apple.getFlavor(), or Banana.getFlavor()

        /*
         * This would REQUIRE us to do downcasting to differentiate the two methods, and might not even know 
         * whether it is one of these two types of fruit (remember the rules of dynamic dispatch: getFlavor()
         * is supposed to use the subtype implementation, but we have two.)
         * 
         * 
         * That said, we can still represent relations where some subtype "inherits" behavior from multiple different
         * sources. To do this, we'll actually use a strategy called "composition", where we treat these types as
         * attributes instead of as inherited supertypes.
         */




    }
}