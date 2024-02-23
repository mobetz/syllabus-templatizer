import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private List<String> courses;



    public Student(String given_first, String given_last, int given_age) {

        super(given_first, given_last, given_age);
        this.courses = new ArrayList<>();
    }


    @Override
    public boolean register_class(String new_course) {
        boolean class_added = false;

        if ( this.courses.size() < 5 ) {
            this.courses.add(new_course);
            class_added = true;
        }

        return class_added;
    }
    
    public String toString() {
        return this.getFullName() + " (Student). Registered classes: " + this.courses;
    }

}
