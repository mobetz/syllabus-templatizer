import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grocery {

    private int barcode;
    private double price;
    private String name;

    private SimpleIntegerProperty simple_barcode;
    private SimpleDoubleProperty simple_price;
    private SimpleStringProperty simple_name;

    public Grocery(String given_name, double given_price, int given_barcode) {
        this.barcode = given_barcode;
        this.name = given_name;
        this.price = given_price;

        this.simple_barcode = new SimpleIntegerProperty(given_barcode);
        this.simple_name = new SimpleStringProperty(given_name);
        this.simple_price = new SimpleDoubleProperty(given_price);
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getBarcode() {
        return barcode;
    }


    public SimpleDoubleProperty getPriceProperty() {
        return this.simple_price;
    }
    public SimpleStringProperty getNameProperty() {
        return this.simple_name;
    }
    public SimpleIntegerProperty getBarcodeProperty() {
        return this.simple_barcode;
    }
}
