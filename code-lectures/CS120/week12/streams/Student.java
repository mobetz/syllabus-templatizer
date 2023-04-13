
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



public class Student {


    private String first_name;
    private String last_name;
    private int final_grade;




    public Student(String given_first, String given_last, int final_grade) {
        this.first_name = given_first;
        this.last_name = given_last;
        this.final_grade = final_grade;

    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public int getGrade() {
        return this.final_grade;
    }



    @Override
    public String toString() {
        return this.last_name + ", " + this.first_name + "(" + this.final_grade + ")";
    }


}
