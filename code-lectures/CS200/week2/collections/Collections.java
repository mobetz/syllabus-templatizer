
/*
Objectives for Today

By the end of today, you will:
    * Review the syntax for interacting with arrays and lists in Java.
    * Understand how array indices offset memory.
    * Identify the concept of a pointer.
    * Understand ramifications of passing reference types like collections between classes.


Vocabulary for the Day

Pointer - A pointer is a numeric address that "points" to a particular position in
a computer's memory. Pointers are typically used to more efficiently store a 'reference'
to some larger structure of data like an object so that it can be quickly passed between
functions.



 */

import java.util.ArrayList;
import java.util.List;

public class Collections {


    public static void main(String[] args) {

        /*
        Today we will be concluding our review of the fundamental building blocks of
        Java. Specifically, we will be looking at collection types.

        From Programming I, you likely remember that you can declare an array of
        any type by putting square brackets after the type name:
         */

        int[] list_of_numbers;


        /*
        Then, when you want to assign your array, you do so like this:
         */

        list_of_numbers = new int[5];

        /*
        Then accessing individual elements (either storing or retrieving) is done with square brackets:
         */

        list_of_numbers[2] = 47; //<- this saves 47 into the third slot
        System.out.println(list_of_numbers[2]); //<- this retrieves and prints out what's in that third slot (47)


        /*
        Much like an object, an array is created with the 'new' keyword, and
        we need to provide information about the maximum number of things that
        could be stored in the array (in this case, 5.)

        But why is it that arrays use the same word as objects, and why do we
        need to give them a size when lists work just fine without it?

        To understand this, we must understand a bit more about memory on your
        computer. Your computer is equipped with several sticks of "Random Access
        Memory" (RAM). These RAM sticks contain billions of little circuits that can
        each store binary data. We could visualize this data like a very long
        strip of cells:



+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...



Each of these cells can be given an "address", a numbered slot that can hold a set amount
of data. What the 'new' keyword *actually* does is tells Java to calculate how much space
it needs for some data, then reserve that much space from memory. In our case, let's pretend
each int takes up two of these 'boxes'. That means Java will reserve the following space:


+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...

0x00:
|-------------|-------------|-------------|-------------|-------------| <- int array
      0             1              2             3              4


We can see that, starting from address 0x00, we've taken up ten boxes total. However,
when numbering our boxes, we've ignored the boxes' original addressing system, and
instead given them the numbers 0 through 4.


We do this because:
    * We want the numbers we use to refer to our array to be consistent no matter what
    addresses the array was reserved at.
    * We don't want to have to worry about how 'big' each box is and skip addresses.


However... this is still a lot of boxes worth of data. It would be slow to pass all
this information around every time we wanted to move the array into or out of a function.


What we do instead is pass *just the location* of the beginning of the array:

   int[] arr = new int[5]; <- what this ACTUALLY stores is just the numeric address 0x00

This 'location' information is called a "pointer" or "reference", and is a very powerful
tool that lets Java be much more efficient when moving large structures of data.


NOTE: some languages like C give you direct access to pointers. Imagine in Java
if we could do something like this:

    int[] arr = new int[5]; //say this gives us 0x15
    int  *location = (int) arr; //<- just the number 15 for address 0x15. NOT POSSIBLE in Java

Once we had this, we would be able to jump to any other location, by just doing a bit of math:

  int next_slot_of_array = location + 2; //(because we decided one int was two "boxes" in our model.)


|-------------|-------------|-------------|-------------|-------------| <- int array
0             1              2             3              4

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...
                ^
                |
            we're now "pointing" here


   At this point, we could 'de-reference' that location in memory and get out the thing in the
   first slot. (In C, there's a special symbol & that lets you do this.)


   Have you ever wondered why arrays in Java start from zero? This is why! Imagine we wanted
   to get to slot number 3. We could express that as:


   int size_of_one_number = 2;
   int location_of_start = 0;
   int location_of_three = location_of_start + size_of_number * 3;


   We use 0 for the first slot because zero anything times * will keep us right at the beginning
   address where the first object is held. When you type arr[3], this is the math
   that Java is doing behind the scenes!

   Objects also work on a similar principle -- Java will reserve 'blocks' for each instance variable
   of an object when you use 'new':

0x00
|-------------|----------------------------------------|------|     <- a "Person" object
int age       string name                               boolean alive

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...


This will be relevant later this semester when we learn a bit more about memory management!




   So what about lists? We've seen that lists work very similarly to arrays. If we wanted to create
   one:

         */

        List<String> some_names = new ArrayList<>();


        /*
        The big differences here:
            * The array type is specified inside <angle brackets>.
            * We call a constructor (notice the () after the type name on the right hand side.)
            * We do NOT specify a total size.


        This is because a List is an actual object. Unlike an array, there is a class
        defined that describes all of a list's attributes and methods. The reason the
        type of list is called an "ArrayList" is because the main attribute of that
        class is an array!

        This is why every single operation we perform on lists uses a method -- methods
        are our main way to interact with objects.

        Let's go make a simplified DummyList to see how some of list's methods work.
         */


        DummyList favorite_numbers = new DummyList();

        favorite_numbers.add(4);
        favorite_numbers.add(12);
        favorite_numbers.add(7);
        favorite_numbers.add(26);
        favorite_numbers.add(32);
        favorite_numbers.add(16);

        System.out.println("The third number is " + favorite_numbers.get(2));


        /*
        We can actually go inspect ArrayList.java and see how these methods are implemented
        on the real class.


        So what are those 'angle brackets' that let a List work for any type?
        Those are what is called a generic type -- the generic type allows you
        to treat the type of a class almost like a variable. After we talk about
        inheritance, we will briefly look at generic types closer to midterms!


        So we've now seen how arrays work in memory as well as how lists are built from
        arrays. The last thing I want to do today is talk a bit more about how references
        can trip you up when programming.

        Remember, both arrays and objects (including lists) are stored as an array.
        Imagine I were making a class where I *thought* I had private information
        stored in an list, like transactions in a bank account:
         */

        BankAccount account = new BankAccount("John Hackerman");
        System.out.println("Is account valid: " + account.validateAccount() + ". Current balance: $" + account.getBalance());


        account.deposit(100);
        System.out.println("Is account valid: " + account.validateAccount() + ". Current balance: $" + account.getBalance());


        account.withdraw(50);
        account.withdraw(25);
        System.out.println("Is account valid: " + account.validateAccount() + ". Current balance: $" + account.getBalance());


        //initially, the way our class works how we'd expect

        System.out.println("The current balance is: " + account.getBalance());
        System.out.println("The transactions were: " + account.getTransactionHistory());

        //even though we can *get* our balance, changing the balance doesn't change the class:

        double johns_balance = account.getBalance();
        johns_balance = johns_balance + 99999999;

        System.out.println("Even after changing our local variable, John's balance is: " + account.getBalance());

        /*
        John's balance stays 25 in the account object, because the number returned is completely separate from the number
        stored (the full value was copied.)
         */


        List<Double> local_transactions = account.getTransactionHistory();
        local_transactions.add(9999999.0);

        System.out.println("The current balance is: " + account.getBalance());
        System.out.println("The transactions were: " + account.getTransactionHistory());
        System.out.println("After doing this, does our invariant still hold true?: " + account.validateAccount());

        // ...our fake deposit was added to the transaction list, even though it's private!!!
        // This also means our balance is now off, and John Hackerman can contest his charges!


        /*
        To understand what happened, we need to think again about what actually gets passed around with
        a reference:

       0x01:
       |-------------|-------------|-------------|-------------|-------------| <- transactions
       0             1              2             3              4

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...


        In BankAccount, what we *actually* stored was:
            this.transactions = 0x01;
        In getTransactionHistory(), when we returned this.transaction, what we were actually returning was:
            return 0x01;

       When we called getTransactionHistory() in main, what we saved into local_transactions was:
            local_transactions = 0x01;


        At the end of this, *both* this.transactions and local_transactions are pointing
        at 0x01 in memory. If I change the thing stored *in* 0x01[0], it changes for both!


       0x01:
       |---9999999---|-------------|-------------|-------------|-------------| <- transactions
       0             1              2             3              4

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...
        ^ this.transactions points here
        ^ local_transactions


        When I read transactions[0] for either, it will find 999999.


    Our private attribute has "leaked" out of the class, and allowed us to violate
    the class invariant. So what can we do? When we return reference types like objects
    or arrays, if we don't want changes on them to modify the original class, we should
    make a copy and return that instead.

         */

        List<Double> transactions_that_arent_bugged = account.getTransactionHistorySAFE();
        transactions_that_arent_bugged.add(123456.0);
        System.out.println(account.getTransactionHistorySAFE()); //<- because we have a copy, the 123456.0 did not get
                                                                // mysteriously added outside of deposit()


    }

}
