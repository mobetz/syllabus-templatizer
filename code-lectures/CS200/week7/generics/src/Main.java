
/*
Objectives for Today

By the end of today, you will:

  * Understand how generics allow us to create another layer of type abstraction.
  * Identify how generic type parameters are applied to classes, methods, and attributes.
  * Understand how to apply type constraints to a generic type.
  * Describe situations when generic types are appropriate.
  * Practice writing generic classes.


Vocabulary of the Day

Generic Types - In programming, a generic type is a type left unspecified when declaring a class or method. This type
serves as a "placeholder" that must be given when an object is created. This type can then be used anywhere a normal
type would be used in methods and attributes. Conventionally, we use the letter "T" for the first generic type, then
"U", "V", etc. if a class has more than one generic type.

Type Constraints - When declaring a generic type, a "type constraint" can be provided that limits what types are permitted
to be slotted in when an object is created. Type constraints in Java are specified by using the keyword "extends" on a
generic type.

 */



import java.time.Duration;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        Previously, we've seen how inheritance and interfaces allow us to specify groups of types that we can use
        interchangeably in our code. However, both of these tools have a significant limitation -- both only work on
        types that we've prepared in advance.

        Say, for instance, we want to create a new class that can give us every other item from a list. We might want
        something like this to "shed load" if our arrays get too big, or to easily split a list into halves. We can
        create a class for this using the tools we've used already -- HalfList.

         */

        List<Integer> full_list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        HalfList half_list = new HalfList(full_list);


        System.out.println("Printing out half the list:");

        while (half_list.hasNext()) {
            int next_num = half_list.next();
            System.out.print(next_num + " ");
        }
        System.out.println("\n");

        /*
        However... our design has a big limitation -- it only works with Integers!

        We could try making an interface for our inner_list, but this would still only let us store types that implement
        that interface. If we used Object, then we'd only get back Objects from next() that give us no clues how to use them.
        Even worse, it's impossible for default Java classes like int to ever implement one of our interfaces!

        This is where generics can help: If we look at HalfList, nothing about the class actually *cares* what type
        of thing is in the list. We're using list operations to get() and check the size(), but the actual type inside
        is never used directly, just returned.

        Generically typed class express exactly this. Let's make a GenericHalfList.

         */

        GenericHalfList<Integer> half_of_ints = new GenericHalfList<Integer>(full_list);
        /*
                       ^ What these angle brackets here are saying is "go into the generic class, and replace every
                       instance of "T" with this type "Integer".
         */

        GenericHalfList<String> half_of_strings = new GenericHalfList<>(List.of("a", "b", "c", "d", "e"));
        /*
        Now my GenericHalfList type can work with any type of object, decided right when I make the object!
         */

        /*
        Now methods will be modified to:
         */
        System.out.println("\nHalf of my strings from my generic list:");
        while (half_of_strings.hasNext()) {
            String next_string = half_of_strings.next(); //<- even though GenericHalfList.next() returns a T,
                                                        // we now know that T = String for half_of_strings
            System.out.print(next_string + " ");
        }
        System.out.println("\n");


        /*
        This HalfList is one of the most common uses of generic types -- forwarding them to another generic type
        that already exists. (For example, I'm creating an attribute that is a list of <T>s inside my GenericHalfList.)


        However, there is another way we can use generic classes. We can also create a class that is meant to work
        with multiple disparate types of things. For example, let's make a TimeComparer class that could work with
        any type of time unit.
         */


        TimeComparer<Year> year_comparer = new TimeComparer<>(Year.of(2023));

        year_comparer.until(Year.of(2025), ChronoUnit.YEARS);


        List<Period> time_units = List.of(
                Period.ofYears(1),
                Period.ofYears(10),
                Period.ofYears(100)
        );

        List<Year> added_times = year_comparer.add_self_to_each(time_units.iterator());

        System.out.println("My list of times added to my starting time:");
        for ( Year result_year : added_times) {
            System.out.print(result_year + " ");
        }

        /*
        The main time generics are actually useful is when we need to pull in different types of things that do not
        share an interface, but that all can satisfy some constraints we have. (For example, if we're working with
        standard Java classes, or classes from another library.)

        If you do have control of all the types, you should strongly consider whether generic types are the proper
        tool for this job.
         */
    }
}
