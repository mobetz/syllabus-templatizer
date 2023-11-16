public class Order {

    private String food;

    public Order(String food_in) {
        this.food = food_in;
    }

    @Override
    public String toString() {
        return "Now serving: " + food;
    }
}
