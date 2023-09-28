package types;/*
We can even chain multiple levels of inheritance together:
 */

public class Freshman extends Student {

    private String freshman_advisor;

    public Freshman(String name, String advisor_name) {
        super(name, 18);

        this.freshman_advisor = advisor_name;

    }

    public String generate_weekend_plans() {
        return "Go out and make friends!";
    }


    public String generate_student_id() {
        // But if Person.name is private, this fails:
        // .\Student.java:61: error: name has private access in Person

        /*
        Remember the definition of PRIVATE: a private field can only be accessed
        from the same file. This file is not Person.java, so the field is not
        visible here.


        If we want a field to be hidden from someone holding an object from outside
        this folder, but visible to any subclass or other files in the same folder,
        then instead of private we use 'protected'.
         */

        return "---------\n| "
                + this.name + " ".repeat(7 - this.name.length())
                + "|\n---------";
    }

}
