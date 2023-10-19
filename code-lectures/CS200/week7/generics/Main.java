
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
type would be used in methods and attributes.

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
        while (half_list.hasNext())  {
            int next_num = half_list.next();
            System.out.print(next_num + " ");
        }
        System.out.println("\n");


        /*
        However... our design has a big limitation -- it only works with Integers!

        We could try making an interface for our inner_list, but this would still only let us store types that implement
        that interface. Even worse, it's impossible for default Java classes like Integer to ever implement one of our
        interfaces!

        This is where generics can help: If we look at HalfList, nothing about the class actually *cares* what type
        of thing is in the list. We're using list operations to get() and check the size(), but the actual type inside
        is never used directly, just returned.

        Generically typed class express exactly this. Let's make a GenericHalfList.

        Now when we make a GenericHalfList, we make it similarly to a normal ArrayList, providing a type:
         */

        GenericHalfList<Integer> half_of_ints = new GenericHalfList<>(full_list);
        /*
        Now methods will be modified to
         */
        while ( half_of_ints.hasNext() ) {
            int next_num = half_of_ints.next(); //<- even though GenericHalfList.next() returns T, we know now T = int
            System.out.print(next_num + " ");


        }

        /*
        One of the neat things about implementing Iterator<T> is that it gives us some magic Java methods like
        forEachRemaining() for free:
         */

        half_of_ints = new GenericHalfList<>(full_list);
        System.out.println("\nForEachRemaining list:");
        half_of_ints.forEachRemaining((Integer item) -> {
            System.out.print(item + " ");
        });


        /*
        This HalfList is one of the most common uses of generic types -- forwarding them to another generic type
        that already exists.

        However, there is another way we can use generic classes. We can also create a class that is meant to work
        with multiple disparate types of things. For example, let's make a TimeComparer class that could work with
        any type of time unit.
         */

        TimeComparer<Year> this_year = new TimeComparer<>(Year.of(2023));

        long years_until_2050 = this_year.until(Year.of(2050), ChronoUnit.YEARS);

        System.out.println("\nYears left until 2050: " + years_until_2050);

        List<Period> time_units = List.of(
                Period.ofYears(1),
                Period.ofYears(10),
                Period.ofYears(100)
        );


        List<Year> added_times = this_year.add_self_to_each(time_units.iterator());

        System.out.println("My list of times added to my starting time:");
        for ( Year result_year : added_times) {
            System.out.print(result_year + " ");
        }



    }
}
