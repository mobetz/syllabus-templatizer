
public class GroceryFinder {
	
	
    private Grocery[] available_items;
    private int num_items;




    public GroceryFinder() {
    	this.available_items = new Grocery[30];
    	this.num_items = 0;
    }


    public void addItem(String name, double price) {
    	Grocery item_as_grocery = new Grocery(name, price);
        this.available_items[num_items] = item_as_grocery;
        this.num_items = this.num_items + 1;
    }

    /*
    NOTE: because our grocery finder is a helper we made just for storing and tracking
    the available groceries, we might decide that constructing those groceries *IS*
    part of GroceryFinder's job. In that case, we ask for just the details required to
    construct one, and do build it here.
    Accepting a pre-made Grocery object would also be fine.
     */



}