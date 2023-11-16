

/*
Objectives for Today

By the end of today, you will be able to:
  * Define "polynomial complexity" and give examples of problems that can be solved in polynomial complexity.
  * Evaluate "best" and "worst" cases for solutions to problems.
  * Identify different strategies for sorting a list of items.
 */

import java.util.Scanner;

public class IntroToSorting {

  public static void main(String[] args) {

        /*
            Last class, we discussed how to find an item in a list of items. We came up with two different strategies
            for solving this problem. Both retrieved the same item, but they took a very different number of steps to do
            so.

            The first of these solutions was to look at each item one at a time. We decided this had "linear complexity"
            -- each new item added adds one more thing we have to check.

            As a list gets longer and longer, it might start to take an undesirable amount of time to find something 
            using this approach.

            Our second solution was a strategy called "binary search". We sorted our items first,
            then kept "splitting our list in half", and picking the side that was closer to the thing we were looking for.
            With this approach, we were able to find a result among half a million choices in just 19 steps!

            When one step doubles the number of things we can check, we called this "logarithmic complexity".

            However, this solution is only better if we also have a way to efficiently sort our list, because we have to
            sort and THEN search.

            It turns out, sorting a list of things is much harder than searching for something.


            This is because when we're searching, we just care about finding the "best" single match. We can do this
            by just reading every item in the list once. However, when we're sorting, we have to compare every thing to
            every other thing in the list; they all need to be reorganized with respect to one another.

            To get us ready to think about sorting, let's get some practice with manipulating the position of 
            items in arrays. Specifically, let's think about how we could reorder a list by just swapping the 
            elements in pairs of positions:
            */


            int[] array = new int[]{1, 2, 3, 4, 5};


            for ( int first_idx = 0; first_idx < array.length; first_idx++  ) {//<- for each thing from the beginning to the end 
              for ( int second_idx = first_idx + 1; second_idx < array.length; second_idx++ ) {//<- for each thing that comes after it

               swapTwoPlaces(array, first_idx,  second_idx);
              }
            }


        System.out.println("If we swap every single thing one time in the list of 1-5, we get:");
        printArray(array);


        System.out.println("However, this is a very inefficient way to reverse a list, when we could just loop backwards:");
        for ( int i = array.length-1; i>=0; i--) {
            System.out.println(array[i]);
        }





        /*
            It turns out, we can do something very, very similar to sort our list. We just need to only do these "swaps"
            when a certain condition is met:
         */

        array = new int[]{5, 4, 1, 3, 2};



          for ( int first_idx = 0; first_idx < array.length -1; first_idx++) {
            for ( int second_idx= first_idx+1;  second_idx<array.length; second_idx++) {
              if ( array[first_idx] > array[second_idx] ) { //<- only do the swap if these things are "out of order", i.e if the thing in front is bigger
                 swapTwoPlaces(array, first_idx, second_idx);
              }
            }
          }

        System.out.println("After adding the if to our for loop, a shuffled array produces:");
        printArray(array);


        /*
        Let's watch what happens to our list as we make these changes.

        After each pass through the outer loop, the next smallest number is pushed to the front of the list.

        */
        Scanner in = new Scanner(System.in);
        array = new int[]{5, 4, 1, 3, 2};



          for ( int first_idx = 0; first_idx < array.length -1; first_idx++) {
            System.out.println("Now comparing " + array[first_idx] + " with the rest of the list.");
            for ( int second_idx= first_idx+1;  second_idx<array.length; second_idx++) { 
               if ( array[first_idx] > array[second_idx] ) {//<- only do the swap if these things are "out of order", i.e if the thing in front is bigger
                  System.out.println("Swapping: " + array[first_idx] + " in slot " + first_idx + " with " + array[second_idx] + " in slot " + second_idx );
                  swapTwoPlaces(array, first_idx, second_idx);
                  System.out.print("Now we have: ");
                  printArray(array);
                  System.out.println("(Press enter to continue...)");
                  in.nextLine();
                }
                else  {
                  System.out.println( "----" + array[first_idx] + " in slot " + first_idx + " and " + array[second_idx] + " in slot " + second_idx + " are already in order." );

                }
            }
          }


          /*

        This is a kind of sorting called "selection sort". It's one of the most intuitive ways to sort a list.
        Let's stop and try to count the number of checks we need to do:


         For our list with 5 things:
            The first pass of the loop, we have to compare 1-2, 1-3, 1-4, and 1-5  (4 checks.)
            The next pass of the loop, we have to compare 2-3, 2-4, and 2-5 (3 checks.)
            The next pass of the loop, we compare 3-4 and 3-5 (2 checks.)
            The last pass of the loop, we just compare 4 and 5 (1 check.)


            4+3+2+1 = 10 checks.

         If we had a list with 6 things, we'd have:
             (All the checks from the previous example, but shifted one place to the right)
             The new first thing in our list compared to each of the five previous things.

             In other words:

                 The zeroth pass of the loop, we have to compare 0-1, 0-2, 0-3, 0-4, 0-5
                 (Plus everything we did before:)
                 The first pass of the loop, we have to compare 1-2, 1-3, 1-4, and 1-5  (4 checks.)
                 The next pass of the loop, we have to compare 2-3, 2-4, and 2-5 (3 checks.)
                 The next pass of the loop, we compare 3-4 and 3-5 (2 checks.)
                 The last pass of the loop, we just compare 4 and 5 (1 check.)
        
             5+4+3+2+1 = 15 checks.

         At 10 things, we'd have:
             9+8+7+6+5+4+3+2+1 = 45 checks.

         This is getting big faster than linear growth. (With linear growth, one new thing added always = 1 more check.)
         In fact, it's getting big much faster. If we have a list with >100,000 things, we might see our computer start taking
         some time to actually finish doing this sort!


           If we wanted to write it as a formula, for a list with N things we wanted to sort, we'd have to do:

                       N  *  (N - 1) 
              steps =  -------------
                            2

        However, computer scientists are allergic to math. At the end of the day, that "-1" isn't really *that* important.
        If we ignored the -1, we could rewrite the formula as:


        steps = 1/2  N * N  = (1/2) N*2


         Similarly, the 1/2 is also much less important than the exponent, so we often ignore this as well. This leaves
         us with "about N^2" as the number of steps we'll need to take. Programs like this are said to have "polynomial
         computational complexity".


         The "polynomial" in this term comes from the fact that we're taking our N number of things raised to some constant x.
         [We could also use the name for specific polynomials, like "quadratic" or "cubic" when they apply.]


         We can normally identify these by the number of nested loops over the same collection that we've got.


         Polynomial complexity is the slowest kind of problem that we can realistically solve on a computer.

         There are actually several different ways to perform sorting in quadratic time. We looked at selection sort,
         but there's also bubble sort, and insertion sort... These all come down to doing those same comparisons in different orders.
        */


        array = new int[]{5, 2, 1, 3, 4};


        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if ( array[j] > array[j+1] ) {
                    swapTwoPlaces(array, j, j+1);
                }

            }
        }

        System.out.print("Bubble sort: ");
        printArray(array);

        /*
        Sorting is a more complicated problem, but it's one that we can come up with an easy solution to by breaking it down into a 
        high level goal (compare each pair of things, and swap those out of place) with a strategy for picking the order we do comparisons
        (each adjacent pair, each thing compared with a slot...).

        We can look at really good visualizations and descriptions of these strategies: https://www.toptal.com/developers/sorting-algorithms
        */


  }



  public static void swapTwoPlaces( int[] array,  int first_index,  int second_index ) {

      // We CAN'T do the expected easy solution:
      //array[first_index] = array[second_index];  //<- once we get here, we've "thrown away" the original first index
      //array[second_index] = array[first_index];  //<- this ends up copying the second slot back to itself



        //Save the item from the "first" slot into a temporary variable
        //move the "second" item into the "first" slot
        //move the first item (from the "temporary" variable) into the "second" slot

          int original_first = array[first_index];
          array[first_index] = array[second_index];
          array[second_index] = original_first;
        
  }


  public static void printArray(int[] array) {
        System.out.print("[");
        for ( int i=0; i< array.length-1; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println(array[array.length-1] + "]");
    }

      
}