
public class ReversibleRing<T> {
    
    private DoublyLinkedNode<T> curr;


    public ReversibleRing() {
        this.curr = null;
    }



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


    /**
     * This method will advance our "current" to the next person in the ring, and keep the
     * old person so that they eventually get a turn again.
     * @return The person whose turn is taken
     */
    public T take() {

        T current_item = null;
        if ( this.curr != null ) {

            current_item = this.curr.getContents();
            this.curr = this.curr.getNext();
        }


        return current_item;
    }



    /**
     * This method will advance our "current" to the previous person in the ring, and keep the
     * old person so that they eventually get a turn again.
     * @return The person whose turn is taken
     */
    public T undo() {

        T current_item = null;
        if ( this.curr != null ) {

            current_item = this.curr.getContents();
            this.curr = this.curr.getPrev();
        }


        return current_item;
    }


     public T consume() {

        T current_item = null;
        if ( this.curr != null ) {

            current_item = this.curr.getContents();


            if (  this.curr == this.curr.getPrev() ) {
                this.curr = null;
            } else { 
                DoublyLinkedNode<T> new_next = this.curr.getNext();
                this.curr.removeSelf();
                this.curr = new_next;
            }
            
        }


        return current_item;
     }



    public void add(T new_item) {

        if ( this.curr == null ) {

            DoublyLinkedNode<T> only_node = new DoublyLinkedNode<>(new_item);

            this.curr = only_node;

            //This is where the "magic" of a Ring happens:
            // We set the first thing's successor back to itself!
            this.curr.setNext(this.curr);
            this.curr.setPrev(this.curr);
        } else {

            DoublyLinkedNode<T> new_node = new DoublyLinkedNode<>(new_item, this.curr.getPrev(), this.curr);
            this.curr.setPrev(new_node);
            this.curr.getPrev().setNext(new_node);
        }


            /*

            Visually, we're pointing the new thing at the current, then pointing the previous at the new thing:

              +-> NEW_THIGN --+
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
