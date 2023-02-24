/*
Objectives for Today

By the end of today, you will be able to:
    * Describe when objects benefit from being broken down into smaller components.
    * Identify the "single responsibility" principle.
    * Understand how objects can pass through methods to their composed parts.

 */


/*
Vocabulary of the Day

Composition - Composition is the concept of creating classes responsible for specific features
of a larger class. Composed objects can either be managed entirely by the object that holds
them, or they can be created externally and passed in to the object (also called "aggregation".)

 */


import java.util.Scanner;

public class Composition {
    public static void main(String[] args) {

        /*
        We learned very early this semester that each object we create has two major parts:
           - Attributes, which are class-level variables an object holds
           - Methods, which are functions attached to the object that can access those attributes.


        Recently, we've even seen several classes where one of the attributes was itself another object type,
        such as storing a collection of Groceries in a ShoppingList.

        Today, we are going to talk a little bit more about reasons we might choose to split one object into
        multiple parts.



        To start us off, imagine we were writing a program that can print out the position of a person on a grid,
        and simulate them walking around to different places.


        We might want to store a few details about that person, such as:
           - name   (string)
           - current X coordinate (int)
           - current Y coorindate (int)
           - grid width (int)
           - grid height (int)
          - current facing (string from "north" "west" "south" "east")
          - current speed  (int)
        
         This is a lot of details, so we should probably decide to make a Person class:
           */

        Scanner waiter = new Scanner(System.in);

        Person a_guy = new Person("Joe");
        System.out.println(a_guy);

        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        a_guy.speedUp();
        a_guy.speedUp();
        a_guy.step();
        System.out.println(a_guy);

        System.out.println("Press Enter to continue.");
        waiter.nextLine();


        a_guy.step();
        System.out.println(a_guy);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        a_guy.turnRight();
        a_guy.step();
        System.out.println(a_guy);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();


        /*
        Composition is entirely a strategy for organizing our code. To the perspective of someone *using* the class,
        everything continues to work exactly the same way:
         */

        ComposedPerson second_guy = new ComposedPerson("Joe");

        System.out.println(second_guy);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        second_guy.speedUp();
        second_guy.speedUp();

        second_guy.step();
        System.out.println(second_guy);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        second_guy.step();
        System.out.println(second_guy);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        second_guy.turnRight();
        second_guy.step();
        System.out.println(second_guy);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();


        /*
        But now, we can reuse that same behavior without having to create it again a second time:
         */

        System.out.println("Using that same object with a turtle!");
        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        Turtle turtle = new Turtle("Turt");
        turtle.speedUp();
        turtle.speedUp();
        turtle.speedUp();

        System.out.println(turtle);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        turtle.step();
        System.out.println(turtle);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        turtle.step();
        System.out.println(turtle);
        System.out.println("Press Enter to continue.");
        waiter.nextLine();

        turtle.step();
        System.out.println(turtle);
        System.out.println("Press any key to exit.");
        waiter.nextLine();


        /*
        Composition allows us to narrowly focus our objects so that they each only have a "single responsibility".
        This makes them easier to read, and also makes it easier to reuse similar behaviors in different objects!
         */

    }
}