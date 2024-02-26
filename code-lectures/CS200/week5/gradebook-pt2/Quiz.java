public class Quiz extends Assignment {

    public Quiz(double points_earned) {
        super(points_earned, 10, "Quiz");
    }

    @Override
    public void compute_grade(GradeCalculatorStrategy strategy) {
        strategy.add_grade(this);
    };

    
}
