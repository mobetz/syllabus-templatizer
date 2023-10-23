

/*
A class is a blueprint or "template" that describes a group of data and actions that belong bundled together.


Classes have two main parts:
        - The "attributes" describe what data a class needs to save.
        - The "methods" describe all the things a class needs to be able to do.

We cannot use the code from a class on its own; we can only use our class by creating an object which gets its own copy
of the attributes and methods.


Classes are defined using the 'class' keyword. All the code related to the class must go inside the curly braces. */


public class Dog {

	//ATTRIBUTES
	/*
    Each attribute is a variable.

    However, attributes have one more keyword: public/private.
       - Private attributes can only be seen inside the methods of the class.
       - Public attributes can be seen outside the class (by using the dot operator on an object.)

    We usually prefer private attributes, because they let us keep full control over our objects and ensure that the
    methods of the class are the only place those attributes can change.

    All of the attributes of our class each represent one "part" of the larger concept we're writing a class for:
    */

    private double age;
    private String name;
    private String breed;


	//METHODS
	/*

    Each method describes a thing our class is able to do, written as a function.


    As we described before, methods need to communicate to Java:
        - the things they need given to them by a programmer in order to run (the inputs/parameters/arguments)
        - the thing they will give back after they are finished (the return value)
        - the name programmers can use to call them


    We put all of this information in a single line at the top of the method, called the "method signature".
    Like attributes, methods can also be public or private:
     */
    public boolean changeName(  String newName  ) {
        /*this method signature says:
           - We need one thing to change a dog's name: the new name of the Dog (as text)
           - We give back one thing when we're done: a true or a false (true if the name change succeeded)


           Each of our input parameters is a declared variable, just like any other declared variable. This means we need
           a type and a name. The value in the parameters can change every time the function is called, and they
           get thrown away at the end of the method.
         */

    	boolean name_change_succeeded = false;

    	String first_letter = newName.substring(0,1);
    	if ( first_letter.equals(first_letter.toUpperCase())) { //<- check that the new name has a capitalized first letter
     		this.name = newName; //<- only dogs with capitalized names will be supported here
     		 // Remember: the "this" keyword refers to the object that we're currently calling this method on.


        	// Using it ensures we're ALWAYS talking about an attribute on the object we're calling this method on.
        	// if we don't put it, Java will first look for a local variable with that name, then it will check for
        	// an attribute afterward.

     		name_change_succeeded = true;
    	}


        //The last statement in a method that gives something back to the programmer who called it is the "return" statement.
        //this statement describes which variable the method is giving back to fulfill its promise.
        return name_change_succeeded;
    }



    /*
     One special method is the constructor. The constructor says everything that has to happen when a programmer wants to
     make a new object that follows the template of this class.

     Unlike every other method, constructors don't have to list their return type (it's assumed they'll give back a new
     object of the class.)

     The name of the constructor method is always exactly the same as the name of the class:
     */

    public Dog(String given_name, String given_breed) {

        //The parameters of the constructor are all the things a programmer must give us when they want to make a new
        // object of this class.

        // When we're inside a class method, we can refer to the object that's being made/used with the special keyword "this".
        // Normally, the first thing we do in the constructor is save the information someone gave us to make this object:
        this.name = given_name;
        this.breed = given_breed;

        // Sometimes, we also have default values that we can calculate in the constructor without input from the programmer.
        this.age = 0;

        //Normally, by the end of a constructor, every single attribute should have a value (nothing should be left as "null" or 
        // as a dummy placeholder).

        //Just as with our method signature, constructors don't need to explicitly reutrn anything.
    }
}