import java.util.ArrayList;
import java.util.List;

public class Transcript {
    

    private String course_name;
    private String student;

    private List<Assignment> grades;

    

    public Transcript(String course_name, String student) {
        this.course_name = course_name;
        this.student = student;

        this.grades = new ArrayList<Assignment>();
    }


    public void record_lab(double points_earned) {
        this.grades.add(new Lab(points_earned));
    }


    public void record_exam(double points_earned) {
        this.grades.add(new Exam(points_earned));
    }

    public void record_quiz(double points_earned) {
        this.grades.add(new Quiz(points_earned));
    }


    /**
     * Order a Transcript to calculate a final grade.
     * @param weight_labs a value between zero and 1 representing the percentage of final grade coming from labs 
     * @return a value between zero and 100 representing a student's final grade
     *  */ 
    public double calculate_final_grade(double weight_labs) {

        GradeCalculatorStrategy strategy = new GradeCalculatorStrategy(0.6, 0.2, 0.2);

        for ( Assignment next_assignment : this.grades ) {
            next_assignment.compute_grade(strategy);
        }

        double final_grade =  strategy.return_final_grade();


        return final_grade;

    }




}
