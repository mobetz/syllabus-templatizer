package types;

public class Person {

    //ATTRIBUTES

    protected String name;
    private int age;





    //METHODS

    public Person(String name_in, int age_in) {
        this.name = name_in;
        this.age = age_in;
    }

    public void have_birthday() {
        this.age = this.age + 1;
    }

    public String generate_weekend_plans() {
        return "Watching my favorite TV show!";
    }


    public String toString() {
        return String.format("%s (%d years old)", this.name, this.age);
    }



}
