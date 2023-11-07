
/*
Objectives for Today:

By the end of today, you will be able to:
   * Explain how lists give us an object-oriented interface for interacting with groups.
   * Know how to instantiate, access, and modify lists.
   * Identify commonly used utility functions present on lists.
*/



/*

Vocabulary of the Day

List - A list in Java is an object that supports many common operations related to storing a group
of data. The 'interface' of a list includes operations such as .add() and .remove() items from a position
, as well as the ability to .get() and .set() a particular item. 


ArrayList - The List type in Java only describes what you can do to a list. When we actually create a list,
we must provide a specific "implementation" of List that we want to use. An ArrayList is a list that stores
its data internally in an array.


Parameterized Type - A parameterized type is a type in Java that requires additional information about what type of
   data it will be working with. We provide the "parameter" part of a parameterized type with <angle brackets> after the
   normal type name. Lists are parameterized types.

*/


import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class Lists {


   public static void main(String[] args) {

      /*
      Over the last few weeks, any time we've wanted to talk about a group of data points, we've used
      an array:
      */
      int[] some_numbers;
      some_numbers = new int[5];

      some_numbers[0] = 4;
      some_numbers[1] = 3;
      some_numbers[2] = 7;
      some_numbers[3] = 1;
      some_numbers[4] = 6;



      /*
      However, arrays have had a few pain points:
          - We must know the maximum number of things we will want to store.
             - I can accidentally "overrun" the end of an array, and this will crash (an ArrayIndexOutOfBoundsException)
             - There's no easy way to add things onto the current end of the list.
             - "Empty" slots in the array still have to be null checked.   



      To solve a few of these problems, we've gotten into the habit of making classes that "wrap"
      our arrays with an index:
      */



      NumberStorer storer = new NumberStorer();
      storer.add(4);
      storer.add(3);
      storer.add(7);
      storer.add(1);
      storer.add(6);


      storer.remove(3); //<- empty out the fourth slot, the thing holding 1.



      /*
      It turns out that every time we've done this, what we've actually been doing is making a basic
      form of a List!

      In Java, a List is a description of methods that allow you to store and retrieve items from a group.


      Unlike arrays, lists are objects! However, they work slightly differently from the objects we've used
      the rest of the semester, in two important ways:
          - First, Lists are pure interfaces -- they describe what things can be done, but not how to do them
         - Second, Lists are "generic" -- they describe how these operations could be done for any type of object



       This ends up reflected in how we construct a new list. When I want to make a list, I have to provide one 
       extra detail in the type name: what *type* of List I'm making. I provide this extra detail inside a pair
       of <angle brackets>:
      */

      List<Integer> num_list;


       /*
       Note: I used the capital 'I' Integer class here -- because lists are objects, their contents can only be
       other objects. That means we'll need to use our base type 'wrappers' when we make a list of numbers or bools.
       */




       /*
       Once I've declared my list, I need to construct it. But remember -- when we instantiate an object, Java needs 
       to know all the details about how it works. This means we'll need to pick an actual "flavor" of list. This shouldn't
       change anything about how our code works, but it might make it a bit faster or slower depending on the type of program
       we're writing.

       The most common type of list is an ArrayList, so for now let us make one of those:
       */


      num_list = new ArrayList<Integer>();

       /*
       Just like our variable declaration, ArrayList needs to describe what *kind* of list it is, and this must match
       the type of variable we're storing this list into.

       We've always said that the types on the left and right side of the equals need to match, so it might initially look
       confusing that we're putting "List" on the left, but "ArrayList" on the right -- this is okay, because an ArrayList
       is a specific *kind* of list (what's called a "sub-type"). You'll learn how to write classes like this next semester!

      One of the really nice things about lists is that they have no maximum length. They'll grow automatically to fit their
      contents.
       */


       /*
      Upon building the list, we can now use all of the methods available to us on List's interface:
            https://docs.oracle.com/javase/17/docs/api/java/util/List.html

      Some of them will look pretty familiar:
      */



      num_list.add(4);
      num_list.add(3);
      num_list.add(12);



      /*
      One of the nice things about lists is that we don't have to specify how many 'slots' a list has
      when we create it. It will grow automatically as the inner array runs out of room.
      */

      for ( int i=0; i<100; i++) {
         double random_between_zero_one = Math.random();
         int random_between_one_twenty = (int) (Math.floor(random_between_zero_one * 20)) + 1; 
         num_list.add(random_between_one_twenty);
      }


      System.out.println("\nEven if we add one hundred things, the list doesn't run out of room: " + num_list.toString());

      /*
      Lists also give us helpful functions for modifying and accessing the collection:
      */

      num_list.set(1, 100);                     //<- set slot '1' to have the value 100
      int third_thing = num_list.get(2);        //<- like arrays, lists count from 0, so slot #2 is the third thing
      int count_of_things = num_list.size();    //<- unlike array.length, size() always returns the CURRENT count, not the maximum



      /*
      When we want to remove something from a list, we can use .remove(). One nice feature of remove() is that it moves
      all the later items in the list forward, so there's no 'blank space' left behind:
      */



      for ( int i=num_list.size()-1; i>0; i=i-2 ) { //<- note, if we counted forward here, we'd be messing up where "i" points to
         //remove every even-numbered thing from the list:
         num_list.remove(i);
      }
      System.out.println("\nAfter removing: " + num_list); //<- every blank slot was "slid to the back", so there are no empty null values here!

      /*
      They even work with for-each loops!
      */
      int total = 0;
      for ( int next_num : num_list ) {
         total = total + next_num;
      }

      System.out.println("\nTotal count is: " + total);


      /*
      Lists are a convenient way to work with groups of items when you don't know the exact size or 
      want to be able to easily manipulate items in the middle of the collection. 
      */


      /* However, lists are not the only type of collection Java gives us. Among the other collections
      there is another specialized object for storing a group of *unique* items: the Set.

      A set is another "parameterized type" that works quite similarly to lists:
      */


        Set<String> fruits = new HashSet<String>();
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("orange");

        System.out.println("Our set of fruits: " + fruits);


         /* However, sets have two important differences: */


        /* First, sets cannot have duplicate items: */
        fruits.add("banana");
        System.out.println("Our set of fruits after adding banana again: " + fruits); 

        /* Second, sets don't have an order, so we can't sort, reverse, or fetch things by ID with them: */
        //fruits.get(0);
   }
}