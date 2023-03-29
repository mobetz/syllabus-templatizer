public class Grade {

    private int score;
    private String type;

    public Grade(int given_score, String given_type) {
        this.score = given_score;
        this.type = given_type;
    }

    public int getScore() {
        return score;
    }

    public String getType() {
        return type;
    }
}