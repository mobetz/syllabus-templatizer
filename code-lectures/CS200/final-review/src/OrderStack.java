public class OrderStack {


    public Order head;


    public OrderStack() {
        this.head = null;
    }


    public void push(Order next) {
        //New items are always pushed to the "front" of the list

        if ( head == null ) {

            this.head = next;
        } else {
            next.setNext(this.head);
            this.head = next;
        }
    }

    public Order pop() {

        //Things also get removed from the front

        Order returned_order = null;
        if ( this.head != null ) {

            returned_order = this.head;
            this.head = returned_order.getNext();
        }

        return returned_order;
    }


    public boolean isEmpty() {
        return this.head == null;
    }
    
}
