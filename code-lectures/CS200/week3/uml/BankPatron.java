
/*
When we are writing a class, we can choose to document various functions and pieces using the JavaDoc
syntax. To write a javadoc comment, you write a / with TWO stars **. The comment must be directly above
the thing its documenting, and can use @ to provide key-value pairs of information:
 */


import java.util.List;

/**
 * A BankPatron represents one client of our bank. The Patron will hold multiple accounts, and those
 * accounts will each have many transactions. All instance variables related to  money should be in an
 * account or transaction, though this class implements methods to access summaries.
 *
 * @author Matthew Obetz
 * @version 1.0
 */
public class BankPatron {


    private String name;
    private List<BankAccount> accounts;


    /**
     * getTotalAssets gives us the total money across all accounts this user holds.
     * @return the sum of all money
     */
    public double getTotalAssets() {
        double total = 0;
        //TODO: implement this
        return total;

    }


    public void setName(String new_name) {
        this.name = new_name;
    }
}
