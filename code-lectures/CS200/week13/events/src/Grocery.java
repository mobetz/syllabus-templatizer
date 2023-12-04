import java.beans.PropertyChangeSupport;

public class Grocery {

        private String name;
        private double price;

        private PropertyChangeSupport listening_carts;

        public Grocery(String name, double price) {
            this.name = name;
            this.price = price;

            this.listening_carts = new PropertyChangeSupport(this);
        }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double new_value) {
            double old_value = this.price;
            this.price = new_value;

            this.listening_carts.firePropertyChange("price", old_value, new_value);
    }


    public void addListeningCart(ShoppingCart cart) {
            this.listening_carts.addPropertyChangeListener("price", cart);
    }
}
