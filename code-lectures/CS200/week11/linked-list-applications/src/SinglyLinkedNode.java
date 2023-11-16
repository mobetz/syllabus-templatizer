

/*
No matter what type of linked list I'm working with, nodes work generally the same way:
    * They have some payload of actual data.
    * They have a reference to the next thing in the list.


 If I want this node to work for any possible type of object in the "contents", we've
 learned a tool that will allow us to do that...


 I can make SinglyLinkedNode generic!

 */
public class SinglyLinkedNode<T> {

    private T contents; //<- this represents the actual item in the list
    private SinglyLinkedNode<T> next; //<- this points to the next item, which holds the same type as this item


    public SinglyLinkedNode( T contents ) {
        this.contents = contents;
        this.next = null;
    }

    public SinglyLinkedNode(T contents, SinglyLinkedNode<T> successor) {
        this.contents = contents;
        this.next = successor;
    }


    public T getContents() {
        return contents;
    }


    public void updateContents(T new_contents) {
        this.contents = new_contents;
    }

    public void setNext(SinglyLinkedNode<T> new_next) {
        this.next = new_next;
    }

    public SinglyLinkedNode<T> getNext() {
        return this.next;
    }


    /*
    There are four things we do with items in a list:
        - look at them
        - modify them
        - remove them
        - insert them

        We've already handled reading and modifying with our contents getter and setter above.
        For a singly linked list node, inserting yourself  just means changing your "next" pointer,
        which we have also handled with our constructor and a setter.

        Removing yourself is equally easy:
     */

    public SinglyLinkedNode<T> removeSelf() {
        SinglyLinkedNode<T> remaining_list = this.next;

        this.next = null; //<- we don't want to "leak" the rest of the list when we remove ourselves, so we null out
        // our reference to our next thing. Once we're gone from the list, there no longer is a next!

        //...but everything else about updating the pointer of the old predecessor is the job of whatever is managing
        // the whole list!


        return remaining_list; //<- normally when removing self, I want to do *something* to the rest of the list
        // so I'm going to return it here for convenience
    }
}
