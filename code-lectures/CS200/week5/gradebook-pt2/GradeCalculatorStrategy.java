
/*
 * Objectives for Today:
 *  Identify the Visitor pattern, and understand how this pattern allows us to implement different
       behavior for each type of a subtype
 * Practice using this and see how we can implement additional types once the visitor is created
 * Understand how Visitors simplify algorithms that require dynamic dispatch 
 */



public class GradeCalculatorStrategy {
    
  

        double total_lab_points;
        double possible_lab_points;

        double total_exam_points;
        double possible_exam_points;

        double total_quiz_points;
        double possible_quiz_points;

        double lab_weight;
        double exam_weight;
        double quiz_weight;


        public GradeCalculatorStrategy(double given_lab_weight, double given_exam_weight, double given_quiz_weight) {

            this.total_lab_points = 0;
            this.possible_lab_points = 0;

            this.total_exam_points = 0;
            this.possible_exam_points = 0;
            
            this.lab_weight = given_lab_weight;
            this.exam_weight = given_exam_weight;
            this.quiz_weight = given_quiz_weight;

        }


        public void add_grade(Exam next_exam) {
            total_exam_points = total_exam_points + next_exam.getGradeEarned();
            possible_exam_points = possible_exam_points + next_exam.getPointsPossible();
        }


        public void add_grade(Lab next_lab) {
            total_lab_points = total_lab_points + next_lab.getGradeEarned();
            possible_lab_points = possible_lab_points + next_lab.getPointsPossible();
        }


        public void add_grade(Quiz next_quiz) {
            total_quiz_points = total_quiz_points + next_quiz.getGradeEarned();
            possible_quiz_points = possible_quiz_points + next_quiz.getPointsPossible();
        }


        public double return_final_grade() {
            double lab_score = (total_lab_points / possible_lab_points) * this.lab_weight ; 
            double exam_score = ( total_exam_points / possible_exam_points) * this.exam_weight;
            double quiz_score = ( total_quiz_points / possible_quiz_points ) * this.quiz_weight;

            return 100*(lab_score + exam_score + quiz_score);
        }
  
}
