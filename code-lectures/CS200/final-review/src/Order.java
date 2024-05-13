public class Order {
    
    private String meal;
    private Order next_ticket;


    public Order(String meal) {
        this.meal = meal;
        this.next_ticket = null;
    }


    public Order(String meal, Order next_ticket) {
        this.meal = meal;
        this.next_ticket = next_ticket;
    }


    public void setNext(Order next) {
        this.next_ticket = next;
    }

    public Order getNext() {
        return next_ticket;
    }

    @Override
    public String toString() {
        return this.meal;
    }
}
