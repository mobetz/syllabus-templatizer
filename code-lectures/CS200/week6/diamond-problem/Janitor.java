import java.util.ArrayList;
import java.util.List;

public class Janitor extends Worker {
    
    public List<String> rooms_assigned;


    public Janitor(String name) {
        super(name);
        this.rooms_assigned = new ArrayList<>();
    }




    public boolean addRoom(String name) {
        this.rooms_assigned.add(name);
        return true;
    }

    @Override
    public double getSalary() {
        return this.rooms_assigned.size() * 5000;
    }

}
