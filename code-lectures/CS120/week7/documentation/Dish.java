
/*
When we are writing our class, we should not just be writing the code, but writing comments that describe WHY 
things are written a certain way, and HOW they should be used in the future.

However... this information is only as useful as it is visible. We need some way to display this outside our 
program, and this is where JavaDoc can help.


To make JavaDoc comments, all we need to do is create a normal block comment with an extra star at the beginning: /**

JavaDoc comments can be added to three types of things:
   - Right above the line "public class _______", to describe what one object of the class is supposed to be/do.
   - Right above a method declaration, to describe a methods' purpose, input parameters, return value, and thrown exceptions.
   - Right above a attribute declaration, to describe what that attribute is supposed to hold and what assumptions there are
   about the value.
*/


/**
 * A Dish represents one thing that a Customer may want to purchase. Dishes store all the information about
 * available customizations and price, and are created just once for the Menu that they belong to. When a Bill
 * uses Dishes, it will not change those dishes, just store additional details about customizations separately.
 * @author Prof. Obetz
 * @version 1.0
 * @Date 2023-10-18
 */
public class Dish {


	//ATTRIBUTES

    /**
     * Price represents the amount of money someone needs to pay in order to purchase this item.
     * Price should only ever have two decimal places, and should never be less than zero.
     */
	private double price;


	/**
	 The way the menu item should be referred to on the menu and by servers. */
	private String name;


	/**
	 The longform description of a menu item. This should include adjectives that make it sound appetizing.
	*/
	private String description;

	/**
	 The customizations someone can order. Each customization should be a short phrase, like "Extra Cheese" that
	 will appear in a bulleted list after the description.
	*/
	private String[] customizations;

	/**
	 The allergy warnings. These should be options from the list "Dairy", "Gluten", "Vegan", "Shellfish", "Peanuts"
	*/
	private String[] allergens;

	/*
	NOTE: Our attribute javadoc comments are not just describing what the thing does, but also assumptions, 
	(like that price should only have two decimal places, or that allergy should be from a known list of options.)

	One of the benefits of writing these assumptions down is that sometimes you'll realize you need a different
	data type or group of attributes to actually solve your problem.

	We may also describe how something is used (like that we expect to print the customizations in a bulleted list.)
	*/


	//METHODS

	/**
	  Creates a new menu item. When the menu item is created, it holds just the name, price, and description, but 
      assumes there are no allergens or customizations. If you wish to add these, use the addDairyWarning(), 
      addGlutenWarning(), addVegan() methods described elsewhere in this class.

      @param price             This is the amount of money someone needs to pay to buy this item at full price. Should have at most two decimal places.
	  @param given_name        This is the short name of the item that the customer wants to buy.
	  @param given_description This is the long description of the item.
	 */
	public Dish(double price, String given_name, String given_description) {
		this.price = price;
		this.name = given_name;
		this.description = given_description;

		this.customizations = new String[3];
		this.allergens = new String[5];
	}



    /**
     The getName() function returns a read-only copy of the name of this item.
     @return The name of the item as a string of text. Will always be one line and less than 80 characters.
     */
	public String getName() {
		return this.name;
	}


	public double getPrice() {
		return this.price;
	}


}