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


    /**
     * Order a Transcript to calculate a final grade.
     * @param weight_labs a value between zero and 1 representing the percentage of final grade coming from labs 
     * @return a value between zero and 100 representing a student's final grade
     *  */ 
    public double calculate_final_grade(double weight_labs) {

        double weight_exams = 1 - weight_labs;

        double total_lab_points = 0;
        double possible_lab_points = 0;
        double total_exam_points = 0;
        double possible_exam_points = 0;




        for ( Assignment next_assignment : this.grades ) {

            if ( next_assignment instanceof Lab) {
                total_lab_points = total_lab_points + next_assignment.getGradeEarned();
                possible_lab_points = possible_lab_points + next_assignment.getPointsPossible();
            } else {
                total_exam_points = total_exam_points + next_assignment.getGradeEarned();
                possible_exam_points = possible_exam_points + next_assignment.getPointsPossible();
            }
        }


        double lab_score = (total_lab_points/possible_lab_points)*100;
        double exam_score = (total_exam_points/possible_exam_points)*100;
        double final_grade = weight_labs * lab_score + weight_exams * exam_score;


        return final_grade;

    }



}
