
/*
Objectives for Today

By the end of today, you will:
   * Define the concept of a linked list.
   * Understand how linked lists can help us represent groups accessed only in sequence.
   * Implement linked lists using Java classes.
 */
public class Main {

    public static void main(String[] args) {
        /*
        When we want to store a group of elements, the main tools we reach
        for are arrays and lists. These structures are efficient at packing many
        elements into a small amount of space:
         */

        String[] player_rankings = new String[10];

        player_rankings[0] = "Juan";
        player_rankings[1] = "Serge";
        player_rankings[2] = "Donna";
        player_rankings[3] = "Yu";
        player_rankings[4] = "Boris";
        player_rankings[5] = "Rin";

        /*
        In memory, these elements of our array are contiguous:
             "serge"    "yu"      "rin"
        "juan"  | "donna" | "boris" |   null
          v     v    v    v   v     v    v
        +----+----+----+----+----+----+----+
        |    |    |    |    |    |    |    | ....
        +----+----+----+----+----+----+----+

         Normally, this is helpful to us! It means the computer can very
         efficiently advance from one element to the next when we loop.

         However, this same contiguity can be a problem if we need to constantly
         add and remove things from the array.

         In the case of our rankings, let's say Serge drops from 2nd place to 5th place.
         I cannot just swap  Serge and Boris, because that would advance Boris several places
         forward and cheat Donna and Yu out of their current positions.

         First, I need to remove Serge from the array, and shift everything after him forward one spot.

         */

        String removed_name = remove_from_rankings(1, player_rankings);

        /*
        Afterward, I need to shift everything from the end of the array back to make an 'empty slot'
        for him in 5th place:
         */
        add_to_rankings( removed_name, 4, player_rankings);


        display_rankings(player_rankings);

        /*
        This solution is correct, but it is not ideal for a few reasons:
            * It's very complex to write the logic for insertions and removals
            * If we want to add someone to the front of the list, we need to move each item in the list backwards one at a time
            * If we want to remove someone from the front of the list, we also need to move each item forward one at a time
               (If we have N things in the list, both of these take N steps)


         We want to do better. One thing we could do is encapsulating this logic in a class.
         */

        LinkedRankings linked = new LinkedRankings();
        linked.push_person_to_end("Juan");
        linked.push_person_to_end("Yu");
        linked.push_person_to_end("Boris");
        linked.push_person_to_end("Serge");
        // To iterate through our list, we start at the head and keep progressing one node at a time:


        linked.insert_into_position(1, "Donna"); //<- this should be in Yu's place, pushing everything after later

        System.out.println("\nCurrent Rankings (with our Linked List)");
        RankingNode next_node = linked.getHead();
        while ( next_node != null ) {
            System.out.println(next_node.getMember());
            next_node = next_node.getNext();
        }

        /*
        Our linked list is really good at looping through the list in sequence. When we want to add something to the beginning
        or end, we can do it in just one step!

        This makes linked lists ideal for when we know we will only be working with ends of the list (such as if we have
        a first-in-first-out "queue" or a first-in-last-out "stack",) but a poor choice if we often need to get something
        randomly from the middle of the list.

         */


    }


    public static String remove_from_rankings(int place, String[] rankings) {
        String removed = rankings[place];

        //After I remove the person, I have to fill in the gap by moving every later item
        for ( int i=place+1; i<rankings.length; i++) {
            rankings[i-1] = rankings[i]; //<- move each item one at a time
        }

        return removed;
    }


    public static void add_to_rankings(String name, int placement, String[] rankings) {

        //Before I can insert my place, I have to create a space at the placement:
        for ( int i= rankings.length-1; i>placement; i--) {
            rankings[i] = rankings[i-1];
        }

        rankings[placement] = name;

    }

    public static void display_rankings(String[] rankings) {
        System.out.println("Current Rankings:");

        for ( int i=0; i<rankings.length;i++) {
            if ( rankings[i] != null ) {
                System.out.println(rankings[i]);
            }
        }
    }



}
