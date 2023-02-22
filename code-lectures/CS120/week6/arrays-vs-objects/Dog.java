
public class Dog {
	
    private String name;
    private String breed;
    private int age;


    public Dog(String given_name, String given_breed, int given_age) {
        this.name = given_name;
        this.age = given_age;
        this.breed = given_breed;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}