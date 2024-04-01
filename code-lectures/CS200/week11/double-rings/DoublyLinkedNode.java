
public class DoublyLinkedNode<T> {

    private T contents; //<- this represents the actual item in the list
    private DoublyLinkedNode<T> next; //<- this points to the next item, which holds the same type as this item
    private DoublyLinkedNode<T> prev; //<- this points to the next item, which holds the same type as this item


    public DoublyLinkedNode( T contents ) {
        this.contents = contents;
        this.next = null;
        this.prev = null;
    }

    public DoublyLinkedNode(T contents, DoublyLinkedNode<T> predecessor, DoublyLinkedNode<T> successor) {
        this.contents = contents;
        this.next = successor;
        this.prev = predecessor;
    }


    public T getContents() {
        return contents;
    }


    public void updateContents(T new_contents) {
        this.contents = new_contents;
    }

    public DoublyLinkedNode<T> getNext() {
        return this.next;
    }

    public void setNext(DoublyLinkedNode<T> new_next) {
        this.next = new_next;
    }

    public DoublyLinkedNode<T> getPrev() {
        return this.prev;
    }

    public void setPrev(DoublyLinkedNode<T> new_prev) {
        this.prev = new_prev;
    }


    public T removeSelf() {
        /*
         * Removing onesself is more complicated in a doubly linked list:
         *                               this
         *                                 V
          +--------+    +--------+    +--------+    +--------+    +--------+
          | Obj1   |    | Obj2   |    | Obj3   |    | Obj4   |    | Obj5   |
          |  next ----->|  next ----->|  next ----->|  next ----->|  next -----> ... ---> null
   null <--  prev  |<----  prev  |<----  prev  |<----  prev  |<----  prev  |
          +--------+    +--------+    +--------+    +--------+    +--------+
         */
        DoublyLinkedNode<T> remaining_list = this.next;

        //In a doubly linked list, each of our nodes needs to be forwarded to the next node in that direction:
        this.prev.setNext(this.next);
        this.next.setPrev(this.prev);

        return this.contents; 
    }
}