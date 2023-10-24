/*
Objectives for Today

By the end of today, you will:
   * Review all the major topics we've covered this semester, including:
      - basic programming and control flow
      - arrays and memory in Java (and references vs values)
      - UML and good documentation practices
      - Inheritance, including:
           + Base classes and Super Classes
           + Polymorphism
           + Protected keyword
           + Abstract Classes
           + Composition vs. Inheritance
           + Pitfalls of Inheritance
           + Interfaces
           + Generic Classes
 */


import java.util.List;

public class Review {
    public static void main(String[] args) {
        /*
        Critical Thinking About Java Programming

        We discovered that when we are writing a Java program, we can decompose a lot of the different constructs
        like if statements and for loops into very similar looking "intermediate code" that describes our program
        as straight lines of statements and jumps between them.

        In this model, if statements had a single check and jump:


         */
        boolean some_condition = false;

        if (  some_condition ) {
            doStuff();
        }


        /*
        This is equivalent to the intermediate code:


        1 boolean some_condition = false;
        2 when some_condition is not true: go to line 4
        3 doStuff()
        4 //continue the program

        To turn this code into a loop, the only thing intermediate code would add is a "go back to line 2"
        at the end of the body:

        1 boolean some_condition = false;
        2 when some_condition is not true: go to line 5
        3 doStuff()
        4 go to line 2
        5 //continue the program

We had also represented this as a flowchart (that we called a control flow graph):


Or as a flowchart:

__________________________
|  is some_condition true? | <--------+
+------------------------+              \
     | No         | Yes                   \
     |           +------------------+     |
     |           |  doStuff()       |     |
     |           +------------------+     |
     |              |                    /
     |           +----------------+     /
     |           | go back to top |  --+
     |           +----------------+
     v


The next topic we had discussed while thinking critically about Java code was arrays.

We had said an array is a long contiguous block of memory:


+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...


The reason we need to provide a type when we create a collection like a list or array is
so that we can subdivide that block into the space required for each object:



+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...

0x00:
|-------------|-------------|-------------|-------------|-------------| <- int array
      0             1              2             3              4


      Knowing this, we discussed many features about how arrays are implemented, such as
      that when we provide a number to an array accessor (like myArray[5]), what we're actually
      doing is multiplying the size of one slot, adding that to the starting location of the array,
      and looking there for the individual data we want to pull out.



|-------------|-------------|-------------|-------------|-------------| <- int array
0             1              2             3              4

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...
                ^
                |
            myArray[1]  (because we start at 0x00 and add  2 blocks * 1 for that [1]th slot)


We'd also discussed objects which work similarly, but create a "structure" of data that isn't
all equally sized:


0x00
|-------------|----------------------------------------|------|     <- a "Person" object
int age       string name                               boolean alive

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...



         */



        /*
        UML Diagramming

         UML is a standard depiction of a system-wide architecture, most commonly used in object oriented designs.

        We had introduced three kinds of UML diagrams in our class:
            + Class Diagrams, which show how different classes relate to one another

     +---------------------------------+                             +---------------------------------+
     | ClassOne                        |                             | ClassTwo                        |
     |---------------------------------|                             |---------------------------------|
     |   -prop1: ClassTwo              |  1..many              1     |   -prop1 : type                 |
     |                                 |  ------------------------   |   -prop2 : type                 |
     |                                 |                             |   -prop3 : List<ClassOne>       |
     |-------------------------------- |                             |-------------------------------- |
     |   +method() : double            |                             |                                 |
     |   +method2(name: String) : void |                             |                                 |
     +---------------------------------+                             +---------------------------------+

            + Activity Diagrams, which show how the program flows from one workflow to another
            + Sequence Diagrams, which show how information flows between different actors in a program over time



            You can see good examples of different diagrams at:
            https://www.lucidchart.com/blog/types-of-UML-diagrams


         */


        /*

        INHERITANCE

        Inheritance is the act of creating a specialized "subtype" of an object that extends a "base type",
        adding additional attributes and methods, while keeping everything already there.

        Let's say, for example, I create an Animal class, and then a Dog subclass that is a type of animal.


         */

        Dog some_dog = new Dog("Spot", "Dalmation");

        int dogs_legs = some_dog.getNumLegs();
        System.out.println("Even though Dog doesn't have a getNumLegs(), I can still use it to find out: " + dogs_legs);
        /*
        Inheritance let us reuse existing methods from the base class on the subclass.

        We saw one of the big applications of inheritance was that it also allowed us to be a little looser with types:
         */

        Animal some_animal = some_dog; //<- A Dog variable can "fit" in an Animal shaped space.

        /*
        POLYMORPHISM

        Casting a subtype as its base type was a technique called Polymorphism, and one of the big uses of this
         was that we could create a collection of mixed animals:
         */

        List<Animal> combined_animals = List.of(
                new Dog("Fido", "Retriever"),
                new Cat("Shadow", "black"),
                new Dog("Azzie", "Lab"),
                new Cat("Pumpkin", "orange")
        );

        /*
        There was an important limitation of this however: when I want to use this list, I can only reference
        each element as an Animal:
         */

        for ( Animal next_animal : combined_animals ) {
            /*
            This means I can only access methods that they have in common:
             */
            System.out.println("The next animal speaks: " + next_animal.makeSound()); //<- this is ok, because each animal has SOME makeSound()
            //next_animal.getColor(); //<- this is NOT ok, because only Cats have a getColor(), dogs would have nothing to do.
            //We would have to "Downcast" our Animal into a (Cat) to do this, but we CANNOT do this safely (because some are Dogs).
        }

        /*
        There was another important limitation: we can only have UP TO ONE SUPERCLASS. (We can only inherit from
        a single thing.) This was because of the "diamond problem", which is that in general names can conflict
        between our two different subclasses and we can't uniquely resolve which version of overridden methods
        we should use.

        Say for example we had a CatDog class that extended both Cat and Dog. If I tried to do this:

        CatDog my_mythical_creature = new CatDog("Catdog");
        Animal my_animal = my_mythical_creature;

        my_animal.makeSound(); //<- we've got a problem: both Cat and Dog do different things for makeSound()
                              // should it "meow" or should it "bark"? We have two methods both REPLACING makeSound() at the same
                              // time, so it's ambiguous


        COMPOSITION

        One way we said we could solve this was with Composition: composition is just placing one object inside
        another, breaking down "verticals" of behavior into completely separate classes.

        In this case, maybe we could create a "Voicebox" object that controls how sound is produced. Instead
        of overriding makeSound() in every Animal, we could pass in the correct Voicebox and save it on an
        attribute of Animal when we build that specific type of animal, and then makeSound() could just
        call the produce_sound() of Voicebox.

        We had discussed the idea that each class should only be responsible for one narrowly defined set of
        tasks (a "single responsibility",) and that we could create many "Strategy" objects that all implement
        different ways of doing the same behavior.




        ABSTRACT CLASSES AND INTERFACES

        We observed that sometimes we don't actually want to create the base type of a class hierarchy:
            - methods may not make sense/be unfinished/return placeholders(, but we still have *some* behavior)
            - the class might not represent a "real example", but instead just a category

        When we have a situation like this, we create an abstract class.

        An abstract class is created by adding the keyword "abstract" before the word "class" in the declaration.
        Whenever we would have an unfinished method, we could put the word "abstract" right before that method
        and then skip writing any method body at all.

        When extending an abstract class, we have to remember to implement all of the previously abstract methods
        (so that there IS actually a runnable version of everything.)

        Interfaces were similar, but with an interface NO METHODS have bodies. This was just a description of
        a list of method signatures that an object must implement. When we create an interface, its declaration
        is "public interface <Name>" instead of public class, and when we add one to a type we use the "implements"
        keyword instead of the "extends" keyword.

        Because interfaces have no implementation at all, they avoid the "diamond problem" (the implementation
        is always on the subtype, and that's the version we use.) This means we can implement as many as we want.


        GENERIC CLASSES

        A generic class is a class that leaves one or more types "unknown". These generic types were able to be
        represented with a letter inside <angle brackets> after the class name.

        For example, if I wanted to make a class that let me hold any type of single object, I could create a
        "public class ObjectHolder<T> {"  I could use "T" as a type in attributes, in method parameters and returns,
        and as a type for a local variable in any (non-static) object methods. When I construct an object from a generic
        class, I need to provide the actual type for <T> (similarly to how we need to provide arguments when a function
        has parameters.)

         */
        ObjectHolder<String> x = new ObjectHolder<>("Apple");
        String held_string = x.getHeld(); //<- now, I can get back an object of the specific type that is held, even though it could be anything!

        /*
        It turns out, this is how ArrayList is implemented. ArrayList is generic over a type T, and the array it creates
        is an array of T[]. This works because ArrayList doesn't actually *use* the things in the array for anything,
        it just moves them around using basic array operations.

        We could even provide "type constraints" that limit the valid classes to put into T (using the "extends" keyword).
        We also saw that generic types can be provided on method signatures directly.
         */





    }
}
