
/*
This class describes the things that ALL Animals have in common:
 */

public class Animal {


    /*
    The "protected" keyword here is a scope keyword like public and private.
    Where public says "anyone with an object can modify this", and private says "ONLY THIS EXACT FILE
    can modify this", protected attributes can be modified in this class, and in any inheriting class/subclass
    (in Java 17+, anything in the same "package".)
     */
    protected int num_legs;
    protected int years_old;
    protected String name;


    public Animal() {
        this.years_old = 0;
        this.name = "Unnamed";
    }


    public String makeSound() {
        return "    ";
    }

    public int getNumLegs() {
        return this.num_legs;
    }
}
