public class Exam extends Assignment {
    

    public Exam(double points_earned) {
        super(points_earned, 100, "Exam");
    }


    @Override
    public void compute_grade(GradeCalculatorStrategy strategy) {
        strategy.add_grade(this);
    }
}
