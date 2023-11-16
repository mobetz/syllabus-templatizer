/*
A queue works slightly differently from a Stack:

Much like the use of the word in real life, things enter in the "front" of a queue, wait their turn,
then leave out the "back" of the queue after everything that arrived before them is processed.
This property is sometimes called FIRST-IN-FIRST-OUT (FIFO order).

Let's ask ourselves our two questions again:
    - Which parts of the list do I need to keep track of?
    - Which directions do I need to traverse the list?


 Since things enter the front but leave the back, I need to keep track of both the front
 and the back!

Things move from front to back in one single direction. This means with a bit of clever thinking,
I should be able to implement this with a singly linked node!
 */

public class Queue<T> {

    private SinglyLinkedNode<T> head; //<- where things enter
    private SinglyLinkedNode<T> tail; //<- where things leave


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
        /*
        Peek will look almost identical to its implementation in our Stack, but we
        peek from the tail rather than the head:
         */
        T observed_item = null;
        if ( this.tail != null ) {
            observed_item = this.tail.getContents();
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

        if ( this.head == null ) {//<- when the list is empty, both head and tail are null
            this.head = new_item;
            this.tail = new_item; //<- this means the new thing is both the beginning and end of the list!
        } else {
            /*
            Otherwise, this new item is pointed to as the next thing BY the old head, and it
            becomes the new head. This may be easier to mentally parse if you replace the word "head"
            with the phrase "last thing in line".
             */
            this.head.setNext(new_item);
            this.head = new_item;
        }
    }


    public T remove() {
        T removed_item = null;
        if ( this.tail != null ) {
            removed_item = this.tail.getContents();
            this.tail = this.tail.removeSelf(); //<- the tail gets removed, and its next is the new tail!

            if ( this.tail == null ) {
                this.head = null; //<- If the removed item was the last thing in the list, the head is also gone
            }
        }

        return removed_item;
    }


}
