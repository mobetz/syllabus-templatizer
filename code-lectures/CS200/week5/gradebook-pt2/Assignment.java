public class Assignment {
    
    private double grade_earned;
    private double points_possible;
    private String assignment_type; //<- do we really need both this and points_possible?


    public Assignment(double grade_earned, double points_possible, String assignment_type) {
        this.grade_earned = grade_earned;
        this.points_possible = points_possible;
        this.assignment_type = assignment_type;

    }


    public double get_percentage() {
        double percentage_score = 100*(grade_earned / points_possible);
        return percentage_score;
    }


    public String getAssignmentType() {
        return assignment_type;
    }

    public double getPointsPossible() {
        return this.points_possible;
    }
    
    public double getGradeEarned() {
        return grade_earned;
    }


    public void compute_grade(GradeCalculatorStrategy strategy) {
        throw new RuntimeException("I don't know how to grade this type of assignment!");
    }
}
