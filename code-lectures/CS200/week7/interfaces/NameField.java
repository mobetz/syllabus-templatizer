
import java.util.*;


public class NameField extends Quizzable implements IScoreable, IPromptable {
	/*
	Our NameField can also be scoreable, even though it's not otherwise related
	to quiz questions.
	*/

	private String value;

	public NameField() {
		this.value = "LEFT_EMPTY";
	}

	public void ask() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your name: ");
		this.value = in.nextLine();
	}

	public boolean activate() {
		/*
		Maybe name is always active. It's fine to create a 'stub' method
		to satisfy the interface, even if some implementors don't need it.
		*/
		return true;
	}

	public String displayText() {
		return "Entered Name: " + this.value;
	}


	public int getPoints() {
		return 4;
	}



}