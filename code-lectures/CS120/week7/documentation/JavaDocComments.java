

/*
Objectives for Today

By the end of today, you will:
  * Understand how JavaDoc comments allow us to easily write documentation for our code
  * Identify the parts of a JavaDoc method comment
  * Practice writing new classes that are annotated by comments
 */



public class JavaDocComments {

    /* This semester, we've already had a great deal of practice writing comments.

    We've written comments:
      - Recall the purpose or effect of a particularly complex block of code
      - before we write code to describe whole algorithms with text
      - To remind us that there is work left to be done (a "TODO" comment)
      - next to method calls to put expected results/behaviors/inputs
      - above attributes/methods/variables to remind ourselves where they're used
      - To leave behind an old 'broken' version of code in case they need to revert to it
      - above individual lines to describe what that line is doing
      - at the end of blocks to identify what block is closing


    All of these comments are notes left in the code themselves. To be read, someone has to open our code file.

    However, sometimes, we want to leave documentation for people who don't want to or can't read our code files directly.

    One of the great benefits of classes is that people can use a class even if they don't understand how that class was
    written. However, they still need a description of what methods are available and what each of those methods does.

    When we want to write these kinds of comments, we can use JavaDoc! JavaDoc comments are a special extension of
    Java comments that Java knows how to automatically compile into tooltips and websites.

    In order to write comments, we first need a program. I want us to start thinking about programs that break
        things down further where we need to decide what classes exist.


        Imagine the following:

        You're writing a program to handle orders to a restaurant website.

        The restaurant you're working for has a menu with several items for sale.

        Each item has a name, description, section, pricing information, and a list of customizations people are allowed to request.
        Each section of the menu (Appetizers, Entrees, House Specials, Desserts...) tracks the items that belong to that
        section, and a discount that can apply to the entire section.

        When a customer wants to make an order, they provide their name, address, credit card information, and a list of
        all of their ordered items. Each ordered item also records whether each customization should be applied or
        not.  The full order can calculate its own price by summing up the price of every item in the ordered list.


        An order goes from being In Progress to Submitted when the customer clicks checkout. Once submitted, the order
        gets assigned to a waiter who fulfills the order. The waiter has a list of all the orders he's currently fulfilling.
        When he fulfills one, the order is marked as completed, and he removes it from his to do list. Each waiter should
        be able to provide a list of all the items they are working on at any time.

        If you heard this description, what classes would you think about creating?

			-- Menu
			 = Attributes
			    + group of MenuSections

			-- MenuSection
			 = Attributes
			    + group of MenuItems
			    + current discount

            -- MenuItem
             = Attributes
                + name  (String)
                + description (String)
                + price (double)
                + group of customizations  (array of String)
                + group of allergen warnings (array of text)

            -- Order
             = Attributes
                + group of items ordered (array of menuItems)
                + customer (Customer)

            -- Customer
              = Attributes
                - Name (String)
                - Credit Card Info (String/Long?)
                - Address (String)
                - Password (String)

            -- Table
              = Attributes
                + number
                + group of Customers
		      = Methods


            If we look at this group of classes we've defined, everything else needs to know about menu items (or know about
            something else that knows about menu items.) As a result, we might discover that we need to code MenuItem first
            so that the rest of our methods can actually do their job.
    */



    public static void main(String[] args) {

    	System.out.println("Welcome to Menu!");

    	MenuItem pizza = new MenuItem(7.99, "Medium Pizza", "Medium cheese pizza topped with your choice of toppings!");
    	System.out.println("Added " + pizza.getName() + " to menu.");

    }//end of main method



    /*

    To actually generate the website version of our documentation, we just have to run one simple comand:

    javadoc -d [OUTPUT_FOLDER] [package_of_code]

	For example: javadoc -d docs MenuItem.java

    Once this runs, we'll have an entire website in the "docs" folder that's formatted just like the 
    JavaDocs we've read on Oracle's website.


    So... what makes a good comment?

    Good comments:
      - Don't just explain what, but *why* and *how*
      - Explain in plain english with minimal jargon
      - Are written for other people (even those you haven't met yet)
      - Are applied consistently to all public classes, properties, and methods.
    */


}//end of class