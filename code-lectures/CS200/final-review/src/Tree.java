public class Tree extends Plant {

    /* Our inherited class automatically has age, co2, and that filtering method from Plant */

    private String species;

    /* However, I have to do now one special thing when I construct a tree: */

    public Tree(String species_in, double amount_of_co2) {
        /* With inheritance, I still need to construct the base type that I'm inheriting from: */
        super(amount_of_co2);
        this.species = species_in;

    }


    public String getSpecies() {
        return species;
    }
    
    
}
