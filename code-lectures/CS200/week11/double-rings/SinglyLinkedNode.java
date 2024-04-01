
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


    public SinglyLinkedNode<T> removeSelf() {
        SinglyLinkedNode<T> remaining_list = this.next;

        this.next = null; 

        return remaining_list; 
    }
}