
/*
A stack is a linked list where items enter and exit from the "front" of the list.

Much like a stack of papers, things added to the stack first get "pushed to the bottom", and have to
wait for everything on top of them to be finished before they are able to exit the stack (this property
is sometimes called LAST-IN-FIRST-OUT, or LIFO order.)



To implement any of these linked list data structures, we need to ask ourselves:
    - Where do we need to keep "pointers" to our list?
    - Which directions do we need to be able to move through the list?

 For a Stack, insertions happen at the beginning, and removals happen at the beginning. That means
 the only thing we need to keep track of.... is the beginning!

 When things are removed, we advance forward through the list one element. Since this is the only
 way we progress to later items, our items only need to keep track of themselves in one direction!


 */


public class Stack<T> {
    

    private SinglyLinkedNode<T> head;


    public Stack() {
        this.head = null;
    }


    /*
    Programmers like to pretend stacks are spring-loaded. We've named the three things a Stack can do accordingly:
        - "push" a new thing down onto the stack
        - "pop" the thing off the front of the stack
        - "peek" at the thing on the front without consuming it

    For all three of these methods, I'm going to hide how they're implemented. Rather than receiving or returning
    nodes, I'm going to work with contents directly (remember, we want to "encapsulate" how this class works!)
     */

     public void push(T new_item) {
        /*
        For push, new things are always added to the front. With a singly linked node, that means
        creating a node for the head (that points to the old head that now comes after it),
        and then updating the stack's head!
         */
        this.head = new SinglyLinkedNode<>(new_item, this.head);
     }

     public T peek() {

        /*
        Peek is equally easy: check what's going on at the head, then return that:
         */
        T seen_item = null;
        if ( this.head != null ) {
            seen_item = this.head.getContents();
        }
        return seen_item;
     }

     public T pop() {

        T removed_item = null;
        if ( this.head != null ) {
            removed_item = this.head.getContents();
            this.head = this.head.removeSelf();
        }
        return removed_item;
     }

}
