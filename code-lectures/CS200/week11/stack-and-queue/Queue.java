/*
A queue works slightly differently from a Stack:

Much like the use of the word in real life, things enter in the "front" of a queue, wait their turn,
then leave out the "back" of the queue after everything that arrived before them is processed.
This property is sometimes called FIRST-IN-FIRST-OUT (FIFO order).


Let's ask ourselves our two questions again:
    - Which parts of the list do I need to keep track of?
    - Which directions do I need to traverse the list?


*/


public class Queue<T> {
    private SinglyLinkedNode<T> head; //where things leave
    private SinglyLinkedNode<T> tail; //<- where things enter


    public Queue() {
        this.head = null;
        this.tail = null;
    }

    /*
    Queues support three operations:
      - "adding" something to the front of the queue
      - "removing" something from the back of the queue
      - "peeking" at the next thing that will be removed

      Much like the stack, I never want to give my users a node directly!
     */

     public T peek() {
        T observed_item = null;
        if ( this.head != null ) {
            observed_item = this.head.getContents();
        }
        return observed_item;
     }


    public void add(T item) {

        /*
        Adding is just slightly more involved on a queue. Rather than each item
        added pointing to the old next item, each added item is going to be pointed
        to *BY* the previous item:
         */

         SinglyLinkedNode<T> new_item = new SinglyLinkedNode<>(item);

         if (this.tail == null ) {//<- when the list is empty, both head and tail are null
            this.head = new_item;
            this.tail = new_item; //<- this means the new thing is both the beginning and end of the list!
         } else { 
            this.tail.setNext(new_item);
            this.tail = new_item;
         }
    }


    public T remove() {

        T removed_item = null;
        if ( this.head != null ) {
            removed_item = this.head.getContents();
            this.head = this.head.removeSelf();
            //Removing from our Queue works just like removing from our Stack, BUT.... we're keeping track of two 
            // locations in our list. We have to make sure the other location didn't also change:

            if ( this.head == null ) { //<- this means we just removed the LAST person from the list
                this.tail = null; //<- there's also now no one at the END of the list.
            }
        }

        return removed_item;
    }
}
