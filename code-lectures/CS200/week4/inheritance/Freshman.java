/*
We can even chain multiple levels of inheritance together:
 */

import types.Student;

public class Freshman extends Student {

    private String freshman_advisor;

    public Freshman(String name, String advisor_name) {
        super(name, 18);

        this.freshman_advisor = advisor_name;
    }


    
}
