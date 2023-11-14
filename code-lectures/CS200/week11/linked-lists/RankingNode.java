public class RankingNode {

    //Each "node" of our list will store its one member:
    private String member;



    // Then point to the "next" person.
    // That next person is going to be represented as another RankingNode!
    private RankingNode next;


    //If we want things from the end of our list to also be easy to do, we should include a "previous" node:
    private RankingNode previous;

    public RankingNode(String member) {
        this.member = member;
        this.next = null;
    }

    /*
    Let's make two versions of the constructor -- one for making something at the end of the list
    (when the 'next' person has no one saved in it) and then another for making someone at a position
    in the list where we already know the next person
     */
    public RankingNode(String member, RankingNode successor) {
        this.member = member;
        this.next = successor;
    }

    public RankingNode getNext() {
        return next;
    }

    public void setNext(RankingNode next) {
        this.next = next;
    }

    public String getMember() {
        return member;
    }

    public void setPrevious(RankingNode previous) {
        this.previous = previous;
    }

    public RankingNode getPrevious() {
        return previous;
    }

    /*
    That's as much as we need to support linked lists. However, let's implement some extra behaviors
    that will be useful for us when dealing with our list to practice appending things:
     */

    public void insert_single_node_here(RankingNode new_next) {
        RankingNode old_next = this.next;
        new_next.setNext(old_next);
        this.next = new_next;

        //With a doubly-linked list, each thing also needs to point to its new predecessor:
        new_next.setPrevious(this);
        old_next.setPrevious(new_next);
    }
    /*
    If we were to depict this visually:

   +------+  +-------+  +-------+
   | Juan |  | Serge |  | Donna |
   | next-----> next-----> next----->  ...
   +------+  +-------+  +-------+
                ^ calling insert_after on this node:

               NEW_NODE:                   OLD_NEXT:
              +---------+                +---------+
              | Newguy  ---> setNextTo:  | Donna  --->--->
              +---------+                +---------+


    THEN:
       +---------+               +--------+  +---------+
   ...--> Serge  ---> setNextTo: | NewGuy ---> Donna  --->
       +----------               +--------+  +---------+

       AFTERWARD:
   +------+  +-------+  +-------+  +-------+
   | Juan |  | Serge |  | Newguy | | Donna |
   | next-----> next-----> next-----> next----->  ...
   +------+  +-------+  +-------+  +-------+

     */


    //If we want to add someone to the end of the list, there are two possibilities:
    //   - Either this node is already the end, and the person given to us becomes our new next
    //   - Or the node is not the end, in which case we pass the person along to the next node!
    public void push_to_end(RankingNode new_last_person) {
        if ( this.next == null ) {
            this.next = new_last_person;
            new_last_person.setPrevious(this);
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
                               |  next ----->  next ----> <rest of NewGuy's original list preserved here>
                               +-------+    +--------+
     */

    /*
    When we want to unlink the node after this one, we must:
       - set our next thing to what was previously our next thing's next thing
    */
    public RankingNode unlink_next_node() {
        RankingNode old_next = this.next;
        this.next = this.next.getNext();
        this.next.setPrevious(this);
        return old_next;
    }

    /*
    Or visually:

                unlink_next()
                 V
   +------+  +-------+     +-------+  +-------+
   | Juan |  | Serge |     | Newguy | | Donna |
   | next-----> next-- ////-> next-----> next----->  ...
   +------+  +-------+     +-------+  +-------+
                 \                       ^
                  \_____________________/

     */



    /*

    This object is all we need to represent a linked list. The "list" is just a chain of these nodes one after another.
     However, often linked lists are used as part of another object -- they solve the problem of storage, but we then
     expose storage and retrieval only to specific parts of the list.

    Now that we've written this single member, let's make a wrapper that can use these methods to give us a more list-like interface!
     */



}
