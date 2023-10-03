import java.util.ArrayList;
import java.util.List;

public class Janitor extends Person {

    private List<String> room_assignments;


    public Janitor(String given_first, String given_last, int given_age) {
        super(given_first, given_last, given_age);
        this.room_assignments = new ArrayList<>();
    }


    public String toString() {
        return String.format("Janitor %s, Room Assignments: [%s]", this.first_name, this.room_assignments);
    }


}
