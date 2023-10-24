public class Cat extends Animal {

    private String color;

    public Cat(String name, String color) {
        super();
        this.name = name;
        this.color = color;
    }


    @Override
    public String makeSound() {
        return "meow meow";
    }

    public String getColor() {
        return color;
    }
}
