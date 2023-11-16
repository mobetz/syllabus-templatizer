
/*
Queues and Stacks are classic data structures that use linked lists.

Now, let's spice things up by creating a custom data structure using these principles!


Our data structure will be called a Ring, and it will use a circular linked list.
 A circular linked list is exactly what it sounds like -- it is
a data structure where there is no "first" or "last" thing in the list!

Instead, our ring has a "current" position. Each time we use the ring,
we advance to the next position, and all adding/removing is done with respect to that
current position.

 */
public class Ring<T> {
    private SinglyLinkedNode<T> curr;
    private SinglyLinkedNode<T> prev;


    public Ring() {
        this.curr = null;
    }

    /*
    With a ring, we're going to have a slightly larger set of operations:
       - "peek" will preview the current item, returning it but not advancing
       - "take" will return the current item, advancing our current pointer
       - "consume" will destroy the current item, returning it.
       - "add" will add something to the "end" of the next loop through the entire ring.
     */

    public T peek() {
        /*
        Peek continues to work exactly the same, just this time with our current position:
         */
        T current_item = null;
        if (this.curr != null) {
            current_item = this.curr.getContents();
        }
        return current_item;
    }


    public T take() {
        /*
        Taking will work just like peeking, but also advance the current position:
         */
        T current_item = null;
        if (this.curr != null) {
            current_item = this.curr.getContents();
            this.prev = this.curr; //<- the new "previous" thing is where we are now...
            this.curr = this.curr.getNext();  //<- ...then we move one slot around the ring
        }
        return current_item;
    }


    public T consume() {
        /*
        Peek continues to work exactly the same, just this time with our current position.


            We have a problem here if we're only tracking the current pointer:
                +  -   -  -  -  -  -  - - +
                         current
                |          V              V
          +--------+    +--------+    +--------+    +--------+    +--------+
          | Obj1   |    | Obj2   |    | Obj3   |    | Obj4   |    | Obj5   |
          |  next ----->|  next ----->|  next ----->|  next ----->|  next ----+
          +--------+    +--------+    +--------+    +--------+    +--------+  |
                ^                                                            /
                 \________________________________________________________ /

                 If we're trying to "consume" the current thing of Obj2, we need to update
                 the pointer of Obj1. We can do this with a "previous" pointer:
         */

        T current_item = null;
        if (this.curr != null) {
            current_item = this.curr.getContents();

            if ( this.curr == this.prev ){
                //If the last thing is this thing, then this is the final thing...
                this.curr = null;
                this.prev = null;
            } else{
                this.prev.setNext(this.curr.removeSelf());
                this.curr = this.curr.getNext();  //<- we move one slot around the ring
            }

        }
        return current_item;
    }

    public void add(T new_item) {

        if ( this.curr == null ) {

            SinglyLinkedNode<T> only_node = new SinglyLinkedNode<>(new_item);

            this.curr = only_node;
            this.prev = only_node;

            //This is where the "magic" of a Ring happens:
            // We set the first thing's successor back to itself!
            this.curr.setNext(this.curr);


        } else {
            SinglyLinkedNode<T> new_node = new SinglyLinkedNode<>(new_item, this.curr);

            this.prev.setNext(new_node);
            if ( this.curr == this.prev ) {
                this.prev = new_node;
            }

            /*

            Visually, we're pointing the new thing at the current, then pointing the previous at the new thing:

              +-> NEW_THING --+
              |               V
              |              current
            prev               V
          +--------+     +--------+    +--------+    +--------+
          | Obj1   |     | Obj2   |    | Obj3   |    | Obj4   |
          |  next --     |  next ----->|  next ----->|  next ----+
          +--------+     +--------+    +--------+    +--------+  |
                ^                                               /
                 \___________________________________________ /
             */

        }
    }




}
