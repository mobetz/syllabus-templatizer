

/*
Objectives for Today

By the end of today, you will be able to:
  * Define "computational complexity" as it relates to computer programs.
  * Understand how complexity affects the running time and the amount of space it takes to run a program.
  * Evaluate how different correct solutions to the same problem might have different complexities.
 */


/* Vocabulary of the Day

Computational Complexity - Computational complexity is a rough estimate of how many steps are required for a program
   to complete. Complexity is normally measured using a mathematical expression for some imaginary input with a size N.
   (For example, a program that takes N^2 steps when given a list of N things has "quadratic complexity".)

Searching - Searching is the act of finding an item with target qualities in a larger list of items.
 */



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;




public class Main {

  public static void main(String[] args) throws IOException {

        /*
        Sometimes, we don't just care about whether a program gets us to the right answer. We also care about how
        long a program takes to run.


        It turns out, not all problems are equally "hard" for a computer to solve. When we want to talk about how
        hard a problem is in scientific terms, we use a measure called "computational complexity".

        We can think of computational complexity as the "number of steps" it takes for a computer to solve a problem.

        For example, let's try downloading words.txt from https://raw.githubusercontent.com/dwyl/english-words/master/words.txt .
        This file has a bunch of english words, one on each line. Let's try writing a simple program to check whether or not a
        word is in that list of words.

        */

        DictionaryLoader loader = new DictionaryLoader("words.txt");
        List<String> all_words = loader.load_dictionary();



        Scanner user_input = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String looking_for_word = user_input.nextLine();


        boolean word_found = false;


        for ( String word : all_words ) {
          boolean it_is_this_word = word.equals(looking_for_word);
          word_found = word_found || it_is_this_word;
        }



        System.out.println("Was " + looking_for_word + " found? " + word_found);



        /*

         To run this program, we had to read each of the 466,000 words in our dictionary, check if the word matched
         what our user input, then finally print out an answer.


        What if our list of words was bigger or smaller?:

        If we treated each iteration of the loop like 1 "step", how many steps do you think it would take if our list had:

                #Things in List   |      #Steps (Worst Case)
              --------------------+--------------------
                        5         |           5 
                       10         |          10 
                      100         |         100 
                        N         |           N



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

         You can usually identify a linear program as one that has a single loop that checks every single thing
         inside a list or range of numbers. (but be careful, some methods might be hiding loops in their implementation too!)

        When you're looking for a word in the dictionary, you don't read every single word from front to back.


        Instead, you usually start somewhere in the middle.


         Next, you check whether the word is 'before' or 'after' your current location, then discard the other 'half' of the list.
         Then you repeat with your smaller list.


        We can do this because we know that dictionaries are usually sorted in alphabetical order. What if our dictionary
        was also sorted in alphabetical order... maybe we could do better!
      */


        // Let's sort all our words in alphabetical order:
        Collections.sort(all_words);

        /*
         The description of how we skim a sorted dictionary sounds very similar to a technique we learned recently...

         In order to find the word in the dictionary, I check the middle word, decide on which half has the answer, 
         then do the same steps again on the half that remains...


         This can be expressed recursively!
         */


  }



  public static boolean binarySearch(  List<String> words_to_search,   String looking_for   ) {

        /*
        Remember: recursive functions always need a 'base case'

        In this case, the easiest example is if there are no words left:
        */

       if ( words_to_search.length == 0 ) {
        return false; //<- if there are no words left to search, the search has failed
       }



      int earliest_possible_word = 0;
      int latest_possible_word = words_to_search.size() - 1;
      int middle_index = (int) Math.floor((earliest_possible_word + latest_possible_word) / 2);


      String middle_word = words_to_search.get(middle_index)



      /*
        Then, we have three possibilities:
           - The words match, and we're done.
           - The word we find is alphabetically after the word we're looking for, and the word might be in the first half
           - The word we find is alphabetically before the word we're looking for, and the word might be in the second half

        One note: Strings have a compareTo() method to test for alphabetical order:
            "a".compareTo("b") will be a negative number, because "a" is alphabetically before "b".
            "a".compareTo("a") will be 0, because they're the same.
            "b".compareTo("a") will be a positive number, because "b" is alphabetically after "a".
         */

        int comparison_result = middle_word.compareTo(looking_for);

      if ( comparison_result == 0 ) {
        return true; //<- where we are now is where the answer is!
      } else if ( comparison_result < 0 ) {  //<- the word we're at is still too early
            List<String> second_half = words_to_search.subList(middle_index+1,  words_to_search.size());
            return binarySearch( second_half, looking_for );
      } else if (comparison_result > 0) { //<- we've already gone past the word
            List<String> first_half = words_to_search.subList(0, middle_index);
            return binarySearch(first_half, looking_for);
      }

        return false;

  }




        /*

          This new way of searching for something is incredibly efficient. Every time we check a word, we cut out HALF
          the words left in the dictionary.

          This means our first comparison brings us from 466,000 possible words to 233,000 possible words.
          The next comparison brings us from 233,000 possible words to 116,500 possible words.
          Next we go to 58,250 words, to 29,125 words.
          Then from 29,125 words to 14,563 words....



          It turns out, we can find our word in at most 19 steps every time!


          With different sizes of lists, we would end up with:


                #Things in List   |      #Steps
              --------------------+--------------------
                        1         |       1  
                        3         |       2   
                        7         |       3  
                       15         |       4  
                       31         |       5  
                    65535         |      16 
                        N         |  log2(N) 


          One way to think about this is that each additional step of searching doubles the number of words we're
          able to sort through. This means if we want to figure out how many steps it will take us to sort through words:



           2^(steps) = (size_of_input)

           To solve for the number of steps, we have to take the binary log of each side:

           log2(2^(steps)) = log2( size_of_input )
           steps = log2( size_of_input )




        #steps
          |
          |         -----------
          |     ---
          |   /
          | /
          +------------------ #things in list


           Logarithmic searchers are incredibly fast. We only have to go through our loop 19 times instead of over 450,000.


            Does this mean we should *always* use binary search?


            No! While binary search can be faster for really large lists, it's also a much more complicated solution. It is
            much more efficient, but at the cost of being less readable. This means it will be far easier for us to make a 
            mistake when coding it, leading to a very hard to find bug. Before you immediately jump to the most "efficient"
            solution, always consider:

                - How long do I have to let my program run?
                - How long will the most readable strategy take?
                - How many times is the program going to be required to use this strategy?

            When all these questions agree that you need to go faster, *then* you should reach for the more complex strategy.
            However, always remember the "hidden" costs of complex solutions -- next class, we'll see how the "sort" 
            at the beginning of our binary search is one such cost! 
           */

}