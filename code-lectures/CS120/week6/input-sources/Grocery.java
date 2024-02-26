
import java.io.Scanner;

public class Grocery {


    //ATTRIBUTES
    private double price;
    private double weight_kg;
    private String name;
    private Local expiration_date;


    //METHODS




    /*
     When I'm designing a grocery, I have to think about what information *needs to be assigned*
     right as the Grocery is created. I might decide both the name and the price are part of the
     Grocery's identity, so I want to assign them both.

     Let's look at two different ways I could do this:
     */


        /* ---------------- BAD COUPLED VERSION --------------- */

    // I might think "oh, when I create a Grocery, I'll ask the user to enter its name and price, so let me
    // use a Scanner to collect that info here!"
    /*
    public Grocery() {
      Scanner in = new Scanner(System.in); //<-- I'M ASSUMING THERE'S ONLY EVER ONE WAY TO MAKE A GROCERY
    	                                    // in the future if I decide to use groceries.csv, I have to rewrite this

    	System.out.print("Enter the grocery's name:");
      this.name = input.nextLine();    	 // <- THE PROGRAM STOPS HERE AND WAITS FOR SOMEONE TO TYPE
      System.out.print("Enter the grocery's price:");
    	this.price = Double.parseDouble(input.nextLine());
    }
    */


    /*
     As soon as we write the constructor above, we've done something bad. We've "coupled" the way we make
     a grocery to the way we read input. Getting information from a user is not part of being a grocery.

     Similarly, if I had an idea that groceries could change price, I might end up making a setter that
     looks like this:
     */

    /*
    public void setDiscountPrice() {
      Scanner input = new Scanner(System.in);  //<-- I'M ASSUMING THERE'S ONLY EVER ONE WAY TO CHANGE PRICE
      System.out.print("Enter the percentage discount as a decimal:");
      this.price = this.input * Double.parseDouble(input.nextLine());
    }
    */

    /*  If in the future I decide I want to read from a file, I have to rewrite this entire class from scratch.

	This also applies to PRINTING in classes -- generally, we don't always know how our programmer is going to use
	the data that we can calculations in our methods. We should return the results of calcuations, not print them out.
     */


        /* -------------------- GOOD "DECOUPLED" VERSION ------------------ */
        public Grocery( String name, double default_price ) {
          this.name = name;
          this.price = default_price;
        }


        /*
        In this version of the constructor, I'm now asserting "I don't care how you get the information.
        You will give me a name and a price that are already 'cleaned up' from whatever source they
        came from, and I will store them here."

        NOTE: I am NOT passing in a String for price, even though it is likely to come as a string from
        the terminal or from a File. Grocery only cares about 'being a Grocery', and for a Grocery, price
        makes the most sense as a number. It is the job of whoever is *using* Grocery to make sure they
        have that number to give us.
        */

        public void setDiscountPrice( double percentage_discount ) {
    	     this.price = this.price * (1-percentage_discount);
        }



    /*
    When my Grocery only has to solve just the problem of being a Grocery the code becomes much more readable.

    * I can very clearly see what piece of information is required to set a discount price, because that
    percentage_discount is being passed in as a function parameter.
    * I can more easily understand what changes in my class when the discount is applied, because I'm not
    cluttering the function with Scanner functions or type conversions.
    * MY function doesn't need to change even if the way we create Groceries changes in the future.

    */



    public double getPrice() {
        return this.price;
    }


    public String getName() {
        return this.name;
    }

    /*
    Similarly with returning information (such as in a "getter"), we don't want to do PRINTING here, because
    we don't care what our programmers are DOING with that information, we just want to give it to them
    in the "purest form" we have available. (If the result of a calculation is a number, we return that number,
    not a string containing that number, not something printed out immediately.)
    */

    /*
    Because we have low coupling, I can reuse this class in almost any program I care about that deals with
    Groceries!!!
    */

}
