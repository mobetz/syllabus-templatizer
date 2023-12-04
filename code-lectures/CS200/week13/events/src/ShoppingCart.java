import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements PropertyChangeListener {
    private double total_price;
    private List<Grocery> groceries;


    public ShoppingCart() {
        this.total_price = 0;
        this.groceries = new ArrayList<>();
    }

    public void addGrocery(String name, double price) {
        Grocery next_grocery = new Grocery(name, price);
        this.groceries.add(next_grocery);

        next_grocery.addListeningCart(this);

        this.total_price = this.total_price  + price; //<- we're smart enough to know to add the price when we add a grocery....
    }

    public List<Grocery> getGroceries() { //<- someone may need to print out all the groceries in their receipt
        return groceries;
    }

    public double getTotalPrice() {
        return total_price;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ( evt.getPropertyName().equals("price")) {
            double price_change = ((Double) evt.getNewValue()) - ((Double) evt.getOldValue());
            this.total_price = this.total_price + price_change;
        }

    }
}


