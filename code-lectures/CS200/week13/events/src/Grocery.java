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

    public void setPrice(double new_price) {
        double old_price = this.price;
        this.price = new_price;

        this.listening_carts.firePropertyChange("price", old_price, new_price);
    }

    public String getName() {
        return name;
    }


    public void addListeningCart(ShoppingCart cart) {
        this.listening_carts.addPropertyChangeListener("price", cart);

    }
}
