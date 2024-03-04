import java.util.ArrayList;
import java.util.List;

public class Instructor extends Worker {


    protected List<String> courses_assigned;

    public Instructor(String name) {
        super(name);

        this.courses_assigned = new ArrayList<>();
    }


    public boolean assign_class(String name) {
        this.courses_assigned.add(name);
        return true;
    }


    public boolean holds_office_hours() {
        return false;
    }



    @Override
    public double getSalary() {
        return this.courses_assigned.size() * 3500;
    }
}
