public class Person {

    protected String first_name;
    protected String last_name;
    protected int age;

    public Person(String given_first, String given_last, int given_age) {
        this.first_name = given_first;
        this.last_name = given_last;
        this.age = given_age;
    }


    public String getFullName() {
        return this.first_name + " " + this.last_name;
    }


    public int getAge() {
        return this.age;
    }

    public void haveBirthday() {
        this.age++;
    }


    public boolean register_class(String new_class) {
        /*
        It's not uncommon for a "base class" implementation of a method to always fail if its behavior doesn't make
        sense for the base class. but we need it to be implemented for every child class. Both Teacher and Student
        do different things when they register a class, but Person needs to be able to call either of them.

        We will learn a few better strategies for this later this semester, but this is one of the "pain points" of
        inheritance-based designs.
         */
        return false;
    }

}
