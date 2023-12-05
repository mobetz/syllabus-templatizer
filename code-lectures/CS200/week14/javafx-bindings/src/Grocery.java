import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grocery {

    /*
    private int barcode;
    private double price;
    private String name;

    In order for Groceries to be able to be used in something like a Table, its attributes
    need to be "observable". In JavaFX, the SimpleXXXXXProperty classes are all classes that
    implement a listener architecture (like the ones from Observable List and the classes we
    talked about last Thursday), so we can replace our properties with these "wrappers"
     that will make them listenable:
     */

    private SimpleIntegerProperty barcode;
    private SimpleDoubleProperty price;
    private SimpleStringProperty name;

    /*
    These versions of our attributes will still hold the same internal data, but now they can register
    event listeners, which will be important for our Table. We will need to update our class to use them:
     */

    public Grocery(String given_name, double given_price, int given_barcode) {
        /*
        this.barcode = given_barcode;
        this.name = given_name;
        this.price = given_price;
         */


        this.barcode = new SimpleIntegerProperty(given_barcode);
        this.name = new SimpleStringProperty(given_name);
        this.price = new SimpleDoubleProperty(given_price);

    }

    public double getPrice() {
        return price.get();
    }

    public String getName() {
        return name.get();
    }

    public int getBarcode() {
        return barcode.get();
    }

    /*
    If we want things to be able to listen to these attributes, then we also need to be able to expose those
    attributes directly:
     */


    public SimpleDoubleProperty getPriceProperty() {
        return this.price;
    }
    public SimpleStringProperty getNameProperty() {
        return this.name;
    }

    public SimpleIntegerProperty getBarcodeProperty() {
        return this.barcode;
    }


    public void increment_cost(int num_dollars) {
        this.price.set(this.getPrice() + num_dollars);
    }


}