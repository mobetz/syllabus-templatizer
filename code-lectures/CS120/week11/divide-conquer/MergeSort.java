
/*
Objectives for Today:

By the end of today, you will be able to:
   * Define the "divide and conquer" approach to solving problems.
   * Apply divide-and-conquer to discover the steps of "merge sort" in Java.
   * Identify the time complexity of merge sort.
 */


import java.util.ArrayList;
import java.util.List;



public class MergeSort {
    public static void main(String[] args) {

        /* So far, we've learned a very fast way to search for things (binary search), and a moderately slow way to 
        sort things (selection sort.)

        The reason binary search was so fast is that repeatedly splitting something in half is a very, VERY fast way
        to go from a large number down to 1. We were removing half of the possibilities at each step without even looking
        at them.

        In fact, when we did the math, we discovered that it would take only log2(N) steps to find something in a list
        that started with N things. We described problems that take this number of steps with the term
        "logarithmic complexity".

        It turns out, dividing something in half over and over is a very flexible approach to solving all sorts of
        problems. This technique is so useful, we've even given it a name: "Divide and Conquer". Just like with our
        searching example, we'll only ever have to do log2(N) number of divisions, no matter what type of problem we're
        solving.


        NOTE: It is a point of debate whether binary search itself counts as divide-and-conquering (because there isn't really
        a 'conquer' step).  However, the sort we are about to learn today is a classic example of this type of problem.

        */



        /*

        We also learned about recursion.

        Recursion was a new way for us to write repeating instructions: We described one step of a problem with a
        function, and then some branch of that function called itself again for some smaller 'sub-problem'. Let's practice
        writing a recursive function again today. Specifically, let's write a recursive function that can calculate the
        factorial of a number.

         */

        int result = factorial(6);
        System.out.println(result);


        /* We can combine recursion with our "divide and conquer" strategy to get a really efficient means of sorting.
        We call this form of sorting "merge sort".

         In merge sort, we have two parts:
            - A 'splitting' process, where we split our list in half and perform mergeSort on each half
            - A final 'merging' process, where we take two lists that are already sorted, and merge them together.

         */

        List<Integer> unsorted_list = List.of(2, 4, 12, 5, 1, 7, 6, 3, 9);
          List<Integer> sorted_list = mergeSort(unsorted_list);
        System.out.println("our sorted list is: " + sorted_list);


      /*



        So... how many times does each item actually get compared in merge sort?

        Our mergeSort function itself is very similar to our binary search algorithm was: if we keep cutting the list in
        half, we're going to run mergeSort log2(N) times.



        However, every one of those log(N) times, we're going to call merge. And merge is going to linearly go through
        each element of one of the two sub-lists and copy it into a new "merged" list.

        So this means we have to touch each of the N things in our list log2(N) times.

        That means the total complexity of mergeSort is N * log2(N). (Sadly, we don't have a fancy name for this complexity,
        we just call it "n log n".)


        So is n log(N) complexity going to be better or worse than our polynomial sorts from last week?

        Well... the lowest polynomial is 2, and N^2 is N*N. log(N) is going to be smaller (maybe even much smaller)
        than N, so a merge sort that finishes in N*log(N) steps is going to end up being much faster than a selection
        sort that takes N^2 steps.
        */

    }



    private static List<Integer> mergeSort( List<Integer> unsorted_list ) {

        //If we have a list that only has 1 or 0 things, the list is already sorted, and just return it.
        if ( unsorted_list.size() <= 1 ) {
            return unsorted_list;
        }


        //If we have real lists, we'll do a splitting process:
            //find the halfway point in the list.
            //Split the list in half
            //mergeSort the first half, then mergeSort the second half

              int halfway_point = unsorted_list.size() / 2;
       List<Integer> first_half = unsorted_list.subList(0, halfway_point);
      List<Integer> second_half = unsorted_list.subList(halfway_point, unsorted_list.size());

         first_half = mergeSort(first_half);
        second_half = mergeSort(second_half);

        //finally, merge those two sorted halves together and return that as our answer
        List<Integer> full_sorted_list = merge(first_half, second_half);
        return full_sorted_list;

    }


    /*
    Let's assume for our merge(a,b) function that both of the lists we accept are already sorted.

    All we have to do to keep the lists in order is to repeatedly take the smaller thing off the front of the two lists,
    and put that into our "merged" list.
     */
    private static   List<Integer>  merge(List<Integer> a, List<Integer> b) {

        List<Integer> merged_list = new ArrayList<>();

        int a_index = 0;
        int b_index = 0;             
  
        // While there's still an index that's not at the end of one of the two input lists:
        while ( a_index < a.size() || b_index < b.size()  ) {

            // if one of the lists is finished, add the next element from the other list
            if (  a_index == a.size() ) {
                int next_item = b.get(b_index);
                merged_list.add( next_item );
                b_index = b_index + 1;
             }
             else if ( b_index == b.size() ) {
                merged_list.add( a.get(a_index));
                a_index = a_index + 1;
            }/* otherwise, if both lists have things left and A's thing comes first: */
            else if ( a.get(a_index) < b.get(b_index)) {
                merged_list.add( a.get(a_index));
                a_index = a_index + 1;

            } else {
                merged_list.add( b.get(b_index));
                b_index = b_index + 1;
            }
         }


         return merged_list;





    }






    /* Our factorial function is going to represent "one step" of performing a factorial
    Each single step is going to be multiplying just the next number.

        5! = 5 * 4 * 3 * 2 * 1
           = 5 * (4*3*2*1)
           = 5 * 4!


    We had also said that recursive functions need a "base case".
    The base case is some branch of the function that returns a result immediately without doing more recursion.
    Without a base case, our function would recurse infinitely.
    */

    public static int factorial(int n) {

         if ( n == 1 ) {
            return 1;  // <-- this n=1 is our "base case" -- we don't want to keep multiplying smaller factorials to it!
                       // we return one because we already know for sure that 1! = 1
         }

         return  n * factorial(n-1);
    }



 }