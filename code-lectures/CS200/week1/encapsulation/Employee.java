
/*
Just like we discussed in main, each public class must exist in its own file
with a name that matches the class name exactly.
*/



public class Employee {

    /*
Our class has two parts: the class attributes/instance variables, and the
associated methods that operate on those variables:
*/

    private String name; //<- instance variables being private lets us hide the
    //implementation of our class (and should be our default assumption!)
    
    private double salary;
    
    private final int id;   /* <- the 'final' keyword seals an attribute once the constructor
has completed. This is most often used when changing that value would mean we're
talking about an entirely different object (like an employee ID number,)
or would violate assumptions about how the world works (like a mathematical constant.)
*/
    
    private static int total_employees = 0; /* <- remember: the 'static' keyword says
the total number of employees is not actually data that belongs to
any individual employee. Instead, the total is something known to this file
and shared by all objects.
*/
    
    

    public Employee(String name, int starting_salary) {

        /*
        Every class in Java needs a constructor. The purpose of the constructor is
        to ensure that as soon as an object is assigned, it already exists in a
        valid state.
        
        Imagine if we had an employee who had NULL for a name... this could cause
        cascading errors through our program if we ever tried to concatenate or
        inspect their name elsewhere.

       Most often, the bulk of a constructor's job is setting the initial attribute
       values:
*/

        this.name = name;
        this.salary = starting_salary;

        /*
The keyword 'this' asserts to Java that you are talking about a class
instance variable. The "this" is a reference to the object we are currently
operating on. In this case, it is an Employee object (the same one that would
be called some_employee or whatever other variable name we give it when
we construct it back in main().) That means instructions like this are
completely valid:
*/
        Employee the_current_employee = this;
        

        /*
        So why is 'this' necessary?

Because without 'this', it would be unclear what we're talking
about. Look at the parameters to the constructor: the token "name" can
refer to the name that someone gave us for this object, or it can refer
to the name attribute that is part of the object.


Java has to perform "name resolution" any time it sees a variable name
to decide what space a name refers to. In general, it will always prefer
the most specific/limited scope, so the function parameter would 'mask'
the class attribute. This is called 'variable shadowing'.

        */

        Employee.total_employees = Employee.total_employees+1;

        //NOTE: because total_employees is static, we conventionally refer to
        //it using the class name, not 'this'
        this.id = 1000 + Employee.total_employees; 
    }
    
    

    /**
     * giveRaise increments the internal salary of an employee.
     * @param percentage_increase the percentage raise being given, as a decimal
     * @return the amount that the salary has increased
     * @apiNote Percentage increase should only ever be positive, and returned
     * value should be greater than zero. 
     */
    public double giveRaise(double percentage_increase) {

        /* Public methods are part of the "interface" of a class: they are the
       tools other programmers will know exist for this object, and what gives
       it value.
*/
        if ( percentage_increase < 0 ) {
            System.err.println("Cannot give a decrease as a raise!!!!");
            return 0;
        }
        
        double raise_dollars = salary * percentage_increase;
        this.salary = salary + raise_dollars;
        return raise_dollars;

        /*
        Sometimes, there are implicit assumptions about how a method should
        work (for instance, that a raise will only ever increase salary.)

        These are what is known as the class' "invariants". Invariants can
        be very useful to help you identify if-checks you should perform or
        errors you should throw, so it's important to take a moment and identify
        them when you write a method.
        */
        
    }
    
    

    public double getSalary() {

        /*
Even though our attributes tend to be private, sometimes it can be
important to expose some information to users of the class to make an object
useful. In this case, we can use a getter or setter to allow users limited
access to a class that protects our expectations.

NOTE: This means you should not universally create a getter and setter
for every single property. Some really might be hidden "implementation
details" that the user shouldn't care about.
*/
        return this.salary;
    }
    
    public void setSalary(double new_salary) {
        if ( new_salary < 0 ) {
            System.err.println("You cannot charge someone to work for you!!!!");
        }
        
        this.salary = new_salary;
    }
    

    /*
We can override default Object methods and have Employee provide its own
more useful version:
*/

    @Override
    public String toString() {
        return String.format("Employee %d: %s", this.id, this.name); 
    }


}