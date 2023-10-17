
import java.util.*;

public class QuizQuestion extends Quizzable implements IScoreable, IPromptable {
	/*						                   ^
							Here we are saying that QuizQuestion objects
							should support everything a Scoreable item
							can do.

	Once we've declared this, we can write our class as normal:
	*/

	//ATTRIBUTES

	private String question_text;
	private String correct_answer;
	private String provided_answer;
	private int point_value;
	private boolean correct;

	public QuizQuestion(String question, String answer, int points) {
		this.question_text = question;
		this.correct_answer = answer;
		this.point_value = points;
		this.correct = false;
	}


	/*
	Right now this class looks valid, but if we tried to compile it without
	anything else, we'd get an error:

QuizQuestion.java:3: error: QuizQuestion is not abstract and does not override 
abstract method activate() in IScoreable

What this is telling us is that any class we can create objects of that implements 
IScoreable must have an activate() function. We must *implement* every method
in our interface:

	*/


	public boolean activate() {
		this.correct = true;
		return true;
	}

	public void ask() {
		Scanner in = new Scanner(System.in);
		System.out.print(this.question_text + " ");
		this.provided_answer = in.nextLine();
		this.correct = this.provided_answer.equals(this.correct_answer);
	}


	public String displayText() {
		return "Answered with: " + this.provided_answer;
	}

	public int getPoints() {
		return (this.correct) ? this.point_value : 0;
	}


}