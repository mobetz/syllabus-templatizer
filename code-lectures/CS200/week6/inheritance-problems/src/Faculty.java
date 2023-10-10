public class Faculty extends Instructor {

    private double salary;


    public Faculty(String name) {
        super(name);
        this.salary = 58000;
    }



    @Override
    public boolean holds_office_hours() {
        return true;
    }


    @Override
    public double getSalary() {
        return this.salary;
    }
}
