import java.util.Scanner;

public class QuizQuestion extends Quizzable implements IScoreable, IPromptable{
	/*					               	        ^
							Here we are saying that QuizQuestion objects
							should support everything a Scoreable item
							can do.

    One interesting note about interfaces is that we can implement multiple interfaces.
    Remember: the problem with the diamond problem was that we might not know which
    implementation was the "correct" one. With interfaces, THERE IS NO IMPLEMENTATION,
    so there can't be a conflict.

    You can even have overlapping interfaces (because there's no implementation, it's
    fine if the one method your object declares can satisfy both.)

	Once we've declared this, we can write our class as normal:
	*/

    //ATTRIBUTES

    private String question_text;
    private String correct_answer;
    private String provided_answer;
    private int point_value;
    private boolean correct;


    //METHODS

    public QuizQuestion(String question, String answer, int points) {
        this.question_text = question;
        this.correct_answer = answer;
        this.point_value = points;
        this.correct = false;
    }

    public String displayText() {
        return "Answered with: " + this.provided_answer;
    }


    public int getPoints() {
        if ( this.correct ) {
            return this.point_value;
        } else {
            return 0;
        }
    }

    public boolean activate() {
        this.correct = true;
        return true;
    }


    @Override
    public void ask() {
        Scanner in = new Scanner(System.in);
        System.out.print(this.question_text + " " );
        this.provided_answer = in.nextLine();
        this.correct = this.provided_answer.equals(this.correct_answer);

    }
}
