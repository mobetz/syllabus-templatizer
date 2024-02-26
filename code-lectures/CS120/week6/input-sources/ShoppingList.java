
public class ShoppingList {


     private Grocery[] groceries;
     private int num_items;

        public ShoppingList() {
          this.groceries = new Grocery[30];
          this.num_items = 0;
        }


public ShoppingList(int maximum_size) {
this.groceries = new Grocery[maximum_size];
this.num_items = 0;
}


    public ShoppingList(int maximum_size) {
      this.groceries = new Grocery[maximum_size];
      this.num_items = 0;
    }


    public void addItem(Grocery grocery_to_add)  {
      this.groceries[num_items] = grocery_to_add;
      this.num_items = this.num_items + 1;
    }


    public Grocery[] getShoppingList() {
      return this.groceries;
    }


    public double getTotalCost() {
      double total_cost = 0;
      for ( Grocery next_grocery : this.groceries ) {
        total_cost = total_cost + next_grocery.getPrice();
      }

      return total_cost;
    }

}
