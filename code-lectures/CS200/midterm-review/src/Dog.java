/*
I can create a Dog class that is a special kind of animal. I must do a few things to make
this relationship clear:

   - I need to add "extends <BaseClassName>" to the end of my class declaration
   - I need to add "super()" to the constructor with the original parameters:
 */

public class Dog extends Animal {

    //The attributes from Animal are implied, but I'm allowed to add more if I want
    private String breed;

    public Dog(String given_name, String given_breed) {
        super(); //<- I need to build the inner Animal, before I make this a specialized Dog
        this.num_legs = 4; //<- once I've called super(), I have access to all the attributes of Animal
                          // even though I haven't declared them in Dog explicitly.
        this.name = given_name;
        this.breed = given_breed;

    }


    @Override
    public String makeSound() {
        return "bark bark bark";
    }
}
