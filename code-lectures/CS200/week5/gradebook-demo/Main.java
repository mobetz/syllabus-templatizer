import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        

        Scanner in = new Scanner(System.in);

        System.out.print("Enter a course:");
        String course_name = in.nextLine();
        System.out.print("Enter a student:");
        String student_name = in.nextLine();


        Transcript course_transcript = new Transcript(course_name, student_name);
        


        String received_lab = "";

        while ( !received_lab.equals("done")) {
            System.out.print("Enter a lab grade (or done to finish labs):");
            received_lab = in.nextLine();
            if ( !received_lab.equals("done")) {
                double lab_score = Double.parseDouble(received_lab);
                course_transcript.record_lab(lab_score);
            }
        }

        System.out.print("Enter the midterm grade:");
        String received_midterm = in.nextLine();
        course_transcript.record_exam(Double.parseDouble(received_midterm));


        System.out.print("Enter the final exam grade:");
        String received_final = in.nextLine();
        course_transcript.record_exam(Double.parseDouble(received_final));


        System.out.println("Your final grade was: " + course_transcript.calculate_final_grade(0.6));
        

    }
}