public class Janitor extends Person {
    
    public Janitor(String given_first, String given_last, int given_age) {
        super(given_first, given_last, given_age);
    }


    public String toString() {
        return String.format("Janitor %s", this.first_name);
    }
}
