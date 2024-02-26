

public class GroceryReader {


  private File grocery_location;


  public GroceryReader(String filepath) {
    this.grocery_location = new File(filepath);
  }


  public ShoppingList load() {

    ShoppingList list_of_items = new ShoppingList();

     Scanner grocery_reader = new Scanner(new File(this.grocery_location));

     while ( grocery_reader.hasNext() ) {
         String next_grocery_data = grocery_reader.nextLine();
         String[] parts = next_grocery_data.split(",");
         String next_name = parts[0];
         double next_price = Double.parseDouble(parts[1]);

         Grocery created_from_file = new Grocery(next_name, next_price);
         list_of_items.addItem(created_from_file);

     }

    return list_of_items;
  }
}
