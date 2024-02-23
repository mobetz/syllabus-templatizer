package types;
/*
We can even chain multiple levels of inheritance together:
 */

public class Freshman extends Student {

    private String freshman_advisor;

    public Freshman(String name, String advisor_name) {
        super(name, 18);

        this.freshman_advisor = advisor_name;
    }


    public String getAdvisor() {
        return this.freshman_advisor;
    }

    @Override
    public String generate_weekend_plans() {
        String students_plans = super.generate_weekend_plans();
        return  students_plans + " and then go out and make friends!";
    }




    
}
