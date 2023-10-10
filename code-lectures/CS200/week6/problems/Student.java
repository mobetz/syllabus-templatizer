import java.util.ArrayList;
import java.util.List;

public class Student {

    private static int student_id_generator = 8000;

    protected String name;
    protected int id_num;
    protected List<String> courses_registered;

    public Student(String name) {
        this.name = name;
        this.courses_registered = new ArrayList<>();
        this.id_num = student_id_generator++;
    }

    public boolean register_for_class(String course_id) {
        this.courses_registered.add(course_id);
    }

    public int getIdNum() {
        return id_num;
    }
}
