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



    public Grocery ( String given_name, double given_price, int given_barcode) {
        /*
        this.barcode = given_barcode;
        this.name = given_name;
        this.price = given_price;
         */


        this.barcode = new SimpleIntegerProperty(given_barcode);
        this.name = new SimpleStringProperty(given_name);
        this.price = new SimpleDoubleProperty(given_price);
            
    }


    public int getBarcode() {
        return barcode.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
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

}
