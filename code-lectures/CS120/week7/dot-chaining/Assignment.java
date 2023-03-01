


public class Assignment {

	//ATTRIBUTES
	private int points_earned;
	private int points_possible;
	private String assignment_category;


	//METHODS
	public Assignment(int earned, int possible, String given_category) {
		this.points_earned = earned;
		this.points_possible = possible;
		this.assignment_category = given_category;
	}

	public int getEarned() {
		return this.points_earned;
	}


	public int getPossible() {
		return this.points_possible;
	}


	public String getCategory() {
		return this.assignment_category;
	}

}