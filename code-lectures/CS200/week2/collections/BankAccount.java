import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String name;
    private double balance; //<- base types, like doubles, store their actual values
    private List<Double> transactions; //<- reference types, like arrays and objects, store an address!


    public BankAccount(String person) {
        this.name = person;
        this.balance = 0;
        this.transactions = new ArrayList<Double>();
    }


    public void deposit(double amount) {
        this.balance = this.balance + amount;
        this.transactions.add(amount);
    }

    public void withdraw(double amount) {
        this.balance = this.balance - amount;
        this.transactions.add(-1 * amount); //<- a withdrawal should *subtract*, so we multiply by -1
    }


    public double getBalance() {
        return this.balance;
    }

    public List<Double> getTransactionHistory() {
        return this.transactions;
    }


    /*
All of our data fields for this class are private! This allows us to assume that we have a
class invariant -- with the way our code works, this.balance should always be equal to the sum
of all the transactions.

Even though we return our private variables, we intentionally don't have direct setters: our users
can only change balance and transactions by depositing or withdrawing, which changes both simultaneously.


     */

    public boolean validateAccount() {
        //Because of our invariant, this should always be true:
        double running_balance = 0;
        for ( double trans : this.transactions ) {
            running_balance = running_balance + trans;
        }

        return this.balance == running_balance;
    }


    /*
    To make our getter of transaction list ACTUALLY SAFE, we would need to return something different
    from the original list... We would need to make a copy:
     */

    public List<Double> getTransactionHistorySAFE() {
        return new ArrayList<>(this.transactions); //<- we don't return the same list, we make a new one from the old one
    }

}
