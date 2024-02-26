public class Lab extends Assignment {


    public Lab(double points_earned) {
        super(points_earned, 50, "Lab");
    }

    @Override
    public void compute_grade(GradeCalculatorStrategy strategy) {
        strategy.add_grade(this);
    }
    
}
