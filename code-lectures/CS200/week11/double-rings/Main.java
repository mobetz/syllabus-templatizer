/*
  Objectives for Today
  
  By the end of today, you will:
  
   * Discover how to create a doubly-linked list
   * Identify situations in which a doubly-linked list is beneficial or required
   * Create a Ring from linked lists and understand its applications

 */


 public class Main {
    public static void main(String[] args) {
        /*
         * So far, all the linked lists we've created have been singly linked:
         *  each node only knows about the NEXT node in the list:
         * 
          +--------+    +--------+    +--------+    +--------+    +--------+
          | Obj1   |    | Obj2   |    | Obj3   |    | Obj4   |    | Obj5   |
          |  next ----->|  next ----->|  next ----->|  next ----->|  next -----> ... ---> null
          +--------+    +--------+    +--------+    +--------+    +--------+
         * 
         * Usually this is enough to solve our problems, but sometimes it can 
         * make solutions a little bit tricky to devise.
         * 
         * As an example of this, let's take a look at creating a Ring.
         * 
         * A ring, much like a stack or a queue, will be a linked list based data
         * structure. Unlike the stack and queue, things don't normally "leave"
         * a ring -- we're going to use this as a sort of round-robin turn taking
         * structure. 
         */


         Ring<String> players = new Ring<>();

         players.add("Player 1");
         players.add("Player 2");
         players.add("Player 3");

         for ( int i=0; i<20;i++) {
            String next_player = players.take();
            System.out.println(next_player + ", it's your turn!");
         }
    }


        /*
        Circular linked lists are great for:
            - taking "turns" in order
            - dividing up load
            - round robin scheduling

         Our Ring class shows us how flexible linked lists can be. As long as we have
         a process where insertions and removals happen at well-defined locations,
         linked lists let us create data structures that can do those operations in a
         single step!
         */


         /*
          * There's another way we could have implemented ring... we could instead have
          each node know about its own predecessor. This is what's called a doubly linked
          list ("double" because each node has two pointers.)
          */


 }