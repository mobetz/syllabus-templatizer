
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
        this.job = new Role(); //<- this placeholder role will ensure we have *something*, but it doesn't represent a real job
        /*
        One way that we could implement our composed Role is saying when you create the student, you have to give me
        what job they have right now:

        this.job = given_job; //<- given_job is added as a parameter to the constructor
         */
    }

    public boolean register_for_class(String course_id) {
        this.courses_registered.add(course_id);
    }

    public int getIdNum() {
        return id_num;
    }


    public boolean register_for_work_study(String job_name) {

        /*
        Now, we can create methods that allow us to interact with the job. Any interactions between a Student's attributes
        and having a job can occur in this class (like maybe checking that hours worked don't exceed some threshold.)

        Typically (but not always) we want to avoid directly exposing the Role to our users, because this poorly isolates
        us from any changes that happen in the future (if Role's interface is updated, then we would need to update the code
        every single location the Role is returned, instead of just this Student class.)

        This technique of 'granting access' to an inner object through methods is known as the "Facade Pattern".
         */
        if ( job_name.equals("Janitor")) {
            this.job = new Janitor();
            return true;
        } else {
            System.err.println("invalid job")
        }
        return false;
    }

    public double getSalary() {
        /*
        Sometimes when we are composing objects, it's necessary to make "adapter" methods on the outer object.
        Much like an adapter plug that lets you fit mismatched cables, an adapter method lets someone who holds
        an object find out information from the inner object:
         */
        return this.job.getSalary();//<- in the simplest examples, we just call the inner object method directly
                                   // however, much like a getter/setter, we can also use this opportunity to transform
                                  // or clean the results as we wish.
    }

}