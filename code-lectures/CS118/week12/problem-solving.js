
/*
Objectives for Today

By the end of today, you will:
  * Define "recursion"
  * Describe how we can break larger problems down into smaller parts.
  * Identify measures we can use to compare the efficiency of different programs.
  * Learn some simple solutions to the sorting and searching problems.
 */



/* Vocabulary of the Day

Recursion - Recursion is when a function calls itself as one of its steps. A function that uses recursion is called a
      recursive function.


Sorting - Sorting is the act of putting a group of things in order (e.g alphabetical or numeric sorting.)

Searching - Searching is the act of finding an item with target qualities in a larger list of items.


Computational Complexity - Computational complexity is a rough estimate of how many steps are required for a program
   to complete. Complexity is normally measured using a mathematical expression for some imaginary input with a size N.
   (For example, a program that takes N^2 steps when given a list of N things has "quadratic complexity".)

*/




/*

Today, we are going to talk about two of the most famous problems in computer science:
       - How to put a list of things in order ("Sorting")
       - How to find something we're looking for in a list of things ("Searching")


We've seen a way to do this already with functions:

 */



 let list_of_fruits = [ "durian", "apple", "cherry", "banana"];

 list_of_fruits.sort();
console.log(list_of_fruits);

let position_of_cherry = list_of_fruits.indexOf("cherry");
console.log("Cherry is in the " + position_of_cherry + " slot of the list.");


/*
However, sometimes we can't use these existing functions.
  - We might have special sorting rules that are different from the rules that Javascript uses.
  - We might have entire objects and only know some details about them.

For instance, back when we were writing our grocery shopper program, we wanted to find a grocery by name. However,
we didn't yet know what the price was, so we wouldn't be able to call .indexOf().



Instead we had to look at each item in the list, one at a time:
 */


function linear_search( list_of_things, target_item ) {

  for ( let next_thing of list_of_things ) {
    if ( next_thing === target_item ) {
      return true;
    }
  }

  return false;
}


apple_was_found = linear_search(list_of_fruits, "apple");
console.log("Was apple found? " + apple_was_found);


zucchini_was_found = linear_search(list_of_fruits, "zucchini");
console.log("Was zucchini found? " + zucchini_was_found);


/*

It turns out, this is the most basic way to do a search! It's a searching strategy known as "linear search".


If we treated each if statement like 1 "step", how many steps do you think it would take if our list had:

                #Things in List   |      #Steps
              --------------------+--------------------
                        5         |         5
                       10         |        10 
                      100         |       100  
                        N         |         N



We can see that the number of steps increases proportionally to the number of things in our list. If we were plotting
these two numbers on a Cartesian (X-Y) chart, we would end up with a straight line:


  #steps
          |         /
          |       /
          |     /
          |   /
          | /
          +------------------ #things in list


When we have a program that increases in time taken like this, we say it has "linear computational complexity".


This is usually really good, but it can still end up slow... imagine we had a list of 100000000000 things. We might have
to do 100000000000 comparisons!


When you're looking for a word in the dictionary, you don't read every single word from front to back.



Instead, you usually start somewhere in the middle.

Next, you check whether the word is 'before' or 'after' your current location, then discard the other 'half' of the list.
Then you repeat with your smaller list.



It turns out, we can do the same thing on the computer:
*/



function binary_search( list_of_things, target_item ) {

    if ( list_of_things.length <= 0) {
      return false;
    }


    // First, we have to identify what the current thing is in the 'middle' of our list:
    let number_of_things_in_list = list_of_things.length;
    let middle_index = Math.floor(number_of_things_in_list / 2);
    let middle_item = list_of_things[middle_index];


    // Then, there are three possibilities:
       if ( middle_item === target_item ) {// if we just found the thing, we're done!
         return true;
       } else if ( middle_item > target_item ) {// if we're 'after' the target, we only need to check the earlier items.
          let first_half = list_of_things.slice(0, middle_index);
          let found_in_that_half = binary_search(first_half, target_item);
          return found_in_that_half;
       }else if ( middle_item < target_item ) { // if we're 'before' the target, we only need to check the later items.
          let second_half = list_of_things.slice(middle_index+1, number_of_things_in_list);
          return binary_search(second_half, target_item);
       }
}



let apple_position = binary_search(list_of_fruits, "apple");
console.log("Apple was found: " + apple_position);

let durian_position = binary_search(list_of_fruits, "durian");
console.log("Durian was found: " + durian_position);

let yam_position = binary_search(list_of_fruits, "yam");
console.log("Yam was found: " + yam_position);



/*

How many steps do you think this new "binary search" solution to our problem is going to take?



If we again treated each call to our function like 1 "step", then when our list had:

                #Things in List   |      #Steps
              --------------------+--------------------
                        1         |         1
                        3         |         2
                        7         |         3
                       15         |         4
                       31         |         5
                    65535         |        16
                        N         |



Every time we add one more step to our binary search, the number of things we're able to check doubles!


This means the number of steps it's going to take for us to find out whether or not our item is in the list is equal to:

                               num_things  =  2 ^ ( num_steps )



To get the number of steps out of the exponent, we have to take the 'logarithm' of both sides of our equation, and we
end up with:

                               log2( num_things ) =  log2( 2 ^ ( num_steps ) )


                               log2( num_things ) =  num_steps

If we were going to plot the number of things we could measure with each step, we'd end up with a 'logarithmic' curve:


        #steps
          |
          |         -----------
          |     ---
          |   /
          | /
          +------------------ #things in list



We call problems that take this many steps those that have "logarithmic computational complexity". Problems with
logarithmic complexity can be solved very, very fast!



However, this comes at a cost.... Our code for binary search was much more confusing than our code for linear search.
If we're working with a small list, we might still want to use linear search, because we'll be able to write our code
faster, but the program will finish very quickly either way.


 */



function bnry_search( list_of_things, target_item  ) {
    if ( list_of_things.length === 0 ) {
        return -1;
    }

    let number_of_things_in_list = list_of_things.length;
    let middle_index = Math.floor(number_of_things_in_list / 2);
    let middle_item = list_of_things[middle_index];

    if ( middle_item === target_item ) { // if we just found the thing, we're done!
        return middle_index;
    } else if ( middle_item > target_item ) { // if we're 'after' the target, we only need to check the earlier items.
        let first_half = list_of_things.slice(0, middle_index);
        return bnry_search(first_half, target_item);
    } else if ( middle_item < target_item ) {// if we're 'before' the target, we only need to check the later items.
        let second_half = list_of_things.slice(middle_index, number_of_things_in_list);
        return bnry_search(second_half, target_item);
    }
}




/*
We can see that inside our 'binary search' function, we have one step where we tell the program to repeat all of
binary search again.

We did something similar back during our folder searching lab, when we told the program to 'repeat the search inside
the subfolder' of our file. This is a powerful technique in programming, called "recursion".



We can use recursion to keep solving easier and easier versions of our problem. However, in order to use this technique,
we must keep a few things in mind:
    - Each time we look at one of our smaller subproblems, it must actually *be* smaller.
    - When we use a subproblem, whatever the 'return' of that subproblem is becomes the 'return' of our bigger problem
    - Somewhere in the function, our 'simplest' answers should NOT use recursion


For our example here, our simple solutions were either:
     - there's nothing left in the list, and we can return false
     - we already found the thing we're looking for, and we can return true.


Every other branch of our if statements are pushing us closer and closer to having one of those two things be true!

 */





/*
    Sorting

We said binary search is faster, but it does rely on one thing: in order to do binary search, our list must already
be sorted in order.

Since sorting helps us come up with faster solutions to other problems, it's another very important skill for us to have
as programmers. It sounds like a difficult problem, so let's start by thinking about how we would do it in real life.


Lets say we wanted to sort a stack of tests in alphabetical order by name of the person who took the test.


In our description, one important thing we said was that we would need to 'switch' two tests when they were out of order.
Let's start by writing a function to just help us do that simple task:

 */

function swap_places( list_of_things, index1, index2 ) {
    //Switching the place of two things has three steps:
    //    - save one of the two items in a temporary variable
    //    - overwrite the slot we saved with the other item
    //    - overwrite the second with the thing from our temporary variable


    let old_first_thing = list_of_things[index1];
    list_of_things[index1] = list_of_things[index2];
    list_of_things[index2] = old_first_thing;

    return list_of_things;
}



/* Once we've written this little swapping function, our 'sorting' problem just becomes a question of which pairs of
things we should actually swap. The simplest thing we can do is try testing every pair of things:
 */



function select_sort( list_of_things ) {

    let size = list_of_things.length;
    for ( let first=0; first<size-1; first++ ) {
            for ( let second=first+1; second<size; second++) {
               let first_thing = list_of_things[first];
               let second_thing = list_of_things[second];
               console.log("Now comparing: " + first_thing + "(" + first + ") and " + second_thing + "(" + second + ")");
               if ( first_thing > second_thing ) {
                console.log("Doing a swap!");
                swap_places(list_of_things, first, second);
               }
            }
    }
    return list_of_things;
}


let test_list_of_things = [5, 1, 4, 2, 12, 9, 6]
let sorted_list = select_sort(test_list_of_things);
let list_as_text = sorted_list.join(", ");
console.log("Sorted list: " + list_as_text);


/*
This is a strategy called "selection sort", because at each pass through our outer loop, we're 'selecting' the smallest
thing in the list and bringing it to the front (if you've ever taken a hand of playing cards and tried to sort it,
you may have done something similar!)
 */



/*

How many steps do you think this takes?

                #Things in List   |      #Steps
              --------------------+--------------------
                        1         |       1
                        5         |      10
                       10         |      45 
                      100         |    4950  
                        N         |




     0  1  2  3  4


        For our list with 5 things:
          The first pass of the loop, we have to compare 1-2, 1-3, 1-4, and 1-5 (4 checks.)
          The next pass of the loop, we have to compare 2-3, 2-4, and 2-5 (3 checks.)
          The next pass of the loop, we compare 3-4 and 3-5 (2 checks.)
          The last pass of the loop, we just compare 4 and 5 (1 check.)

         4+3+2+1 = 10 checks.


         If we had a list with 6 things, we'd have:
         5+4+3+2+1 = 15 checks.


         At 10 things, we'd have:
         9+8+7+6+5+4+3+2+1 = 45 checks.


         This is getting big even faster than linear growth. In fact, it's getting big much faster.

         If we wanted to write it as a formula, for a list with N things we wanted to sort, we'd have to do:

                      N * ( N-1 )
           steps =   ------------
                          2 


        However, computer scientists are allergic to math. At the end of the day, that "-1" isn't really *that* important.
        If we ignored the -1, we could rewrite the formula as:

                            steps =   1/2 *  N^2


                            
         Similarly, the 1/2 is also much less important than the exponent, so we often ignore this as well. This leaves
         us with "about N^2" as the number of steps we'll need to take. Programs like this are said to have "polynomial
         computational complexity".


         The "polynomial" in this term comes from the fact that we're taking our N number of things raised to some constant x.
         [We could also use the name for specific polynomials, like "quadratic" or "cubic" when they apply.]

         Polynomial complexity is the slowest kind of problem that we can realistically solve on a computer. There are
         actually several different ways to perform sorting in quadratic time. We looked at selection sort, but there's
         also bubble sort, and insertion sort... These all come down to doing those same comparisons in different orders.

