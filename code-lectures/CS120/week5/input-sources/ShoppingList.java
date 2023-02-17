

public class ShoppingList {
	
	//ATTRIBUTES
    private Grocery[] shopping_list;
    private int num_items;




	//METHODS
	public ShoppingList() {
        this.shopping_list = new Grocery[30];
        this.num_items = 0;
	}


	public void addItem(Grocery new_item) {
        this.shopping_list[num_items] = new_item;
        this.num_items++;		
	}
    /*
    NOTE: here we are asking someone using our ShoppingList to provide us with a
    Grocery object. This is the most flexible thing we could ask for in this
    case, because knowing how to construct new groceries isn't related to tracking
    existing groceries.
     */


    public double getTotal() {

        double total = 0;


        for ( Grocery next_grocery : this.shopping_list ) {
            if ( next_grocery != null ) {
            	total = total + next_grocery.getPrice();
            }
        }

        return total;
	}


	

    public String toString() {
        String ret = "Shopping List \n-----------------------";

        for ( Grocery next : this.shopping_list ) {
            if ( next != null ) {
                String formatted_price = String.format("$%.2f", next.getPrice());
                int num_dots = 20 - next.getName().length();
                String repeated_dots = ".".repeat(num_dots);
                ret = ret + "\n" + next.getName()  + " " + repeated_dots + " " + formatted_price;
            }
        }

        return ret;

    }




}