public class RankingNode {

    //Each "node" of our list will store its one member:
    private String member;

    // Then point to the "next" person.
    // That next person is going to be represented as another RankingNode!
    private RankingNode next;




    /*
    Let's make two versions of the constructor -- one for making something at the end of the list
    (when the 'next' person has no one saved in it) and then another for making someone at a position
    in the list where we already know the next person
     */

    public RankingNode(String member) {
        this.member = member;
        this.next = null;
    }

    public RankingNode(String member, RankingNode successor) {
        this.member = member;
        this.next = successor;
    }

    public String getMember() {
        return member;
    }

    //Now we can implement some behaviors that will be useful for us when dealing with our list:


    //If we want to add someone to the end of the list, there are two possibilities:
    //   - Either this node is already the end, and the person given to us becomes our new next
    //   - Or the node is not the end, in which case we pass the person along to the next node!
    public void push_to_end(RankingNode new_last_person) {
        if ( this.next == null ) {
            this.next = new_last_person;
        } else {
            this.next.push_to_end(new_last_person);
        }
    }
    /*
    If we were to depict this visually:

   +------+  +-------+  +-------+
   | Juan |  | Serge |  | Donna |
   | next-----> next-----> next----->  null
   +------+  +-------+  +-------+
      V
    Juan.push_to_end(NewGuy):
                 V
                Serge.push_to_end(NewGuy):
                            V
                            Donna.push_to_end(NewGuy):

                               +-------+    +--------+
                               | Donna |    | NewGuy |
                               |  next ----->  next ----> null
                               +-------+    +--------+

     */



    //If we want to insert something into the list after this slot, we:
    //  - make that thing the new next
    //  - make that thing's next the original next we stored here
    public void insert_single_node_here(RankingNode new_next) {
        RankingNode old_next = this.next;
        this.next = new_next;
        new_next.setNext(old_next); //<- this makes sure the rest of our list is preserved!
    }
    /*
    If we were to depict this visually:

   +------+  +-------+  +-------+
   | Juan |  | Serge |  | Donna |
   | next-----> next-----> next----->  ...
   +------+  +-------+  +-------+
                ^ calling insert_after on this node:
                NEW_NODE:                      OLD_NEXT:
               +---------+                +---------+
               | Newguy  ---> setNextTo:  | Donna  --->
               +---------+                +---------+
       +---------+               +--------+  +---------+
   ...--> Serge  ---> setNextTo: | NewGuy ---> Donna  --->
       +----------               +--------+  +---------+

       AFTERWARD:
   +------+  +-------+  +-------+  +-------+
   | Juan |  | Serge |  | Newguy | | Donna |
   | next-----> next-----> next-----> next----->  ...
   +------+  +-------+  +-------+  +-------+


     */

    public RankingNode getNext() {
        return next;
    }

    public void setNext(RankingNode next) {
        this.next = next;
    }

    /*
    Now that we've got this single member, let's see what a LinkedRankings object will look like
     */
}
