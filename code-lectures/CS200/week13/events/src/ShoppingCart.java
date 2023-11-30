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
        this.total_price = this.total_price + price; //<- we may be smart enough to know to add to price when we add a grocery....

        next_grocery.addListeningCart(this);
    }

    public List<Grocery> getGroceries() {
        return this.groceries; //<- ...but what if someone obtains a reference to our grocery list directly....
    }

    public double getTotalPrice() {
        return this.total_price;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ( evt.getPropertyName().equals("price")) {
            double price_change = ((Double) evt.getNewValue()) - ((Double) evt.getOldValue());
            this.total_price = this.total_price + price_change;
        }
    }
}
