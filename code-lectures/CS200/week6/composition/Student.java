import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class Student {

    protected String name;
    protected int id_num;
    protected List<String> courses_registered;

    private static int student_id_generator = 8000;

    private Role job;


    public Student(String name) {
        this.name = name;
        this.courses_registered = new ArrayList<>();
        this.id_num = student_id_generator++;
    }

    public boolean register_for_work_study(Role job) {
        /*
        Now, we can create methods that allow us to interact with the job. Any interactions between a Student's attributes
        and having a job can occur in this class (like maybe checking that hours worked don't exceed some threshold.)
         */
        this.job = job;
    }

    public boolean validate_schedule() {
        return this.job.getHoursWorked() < 15;
    }


    public boolean register_for_class(String course_id) {
        this.courses_registered.add(course_id);
    }

    public int getIdNum() {
        return id_num;
    }

}
