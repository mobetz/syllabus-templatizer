import java.util.ArrayList;
import java.util.List;

public class Student extends Person {


    protected int id_num;
    protected List<String> courses_registered;
    
    private static int student_id_generator = 8000;


    public Student(String name) {
        super(name);
        this.courses_registered = new ArrayList<>();
        this.id_num = student_id_generator++;
    }


    public boolean register_for_class(String course_id) {
        this.courses_registered.add(course_id);
        return true;
    }

    public int getIdNum() {
        return id_num;
    }
}
