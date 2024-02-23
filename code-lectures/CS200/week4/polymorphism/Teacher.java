import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {

    private String department;
    private List<String> teaching_assignments;
    private double salary;

    public Teacher(String given_first, String given_last, int given_age, String given_dept) {
        super(given_first, given_last, given_age);

        this.department = given_dept;
        this.teaching_assignments = new ArrayList<>();
        this.salary = 50000;
    }


    @Override
    public boolean register_class(String new_class) {
        this.teaching_assignments.add(new_class);
        this.salary = this.salary + 2000;

        return true;
    }

    public void have_a_raise(double percentage) {
        this.salary = this.salary * (1+percentage);
    }

    

    public String toString() {
        return String.format("%s (Teacher). Teaching: %s for $%.2f", this.getFullName(), this.teaching_assignments, this.salary);
    }
}