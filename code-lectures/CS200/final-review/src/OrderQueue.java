public class OrderQueue {
    
    private Order head;
    private Order tail;


    public OrderQueue() {
        this.head = null;
        this.tail = null;
    }


    public void add(Order new_order) {
        if ( this.tail != null ) { 
            tail.setNext(new_order);
            this.tail = new_order;
        }

        if ( this.head == null ) {
            this.head = new_order;
            this.tail = new_order;
        }
    }


    public Order remove() {
        Order returned_person = null;

        if ( this.head != null ) {
            returned_person = this.head;
            this.head = returned_person.getNext();
        }

        if ( this.head == null ) {
            this.tail = null;
        }

        return returned_person;
    }


    public boolean isEmpty() {
        return this.head == null;
    }
}
