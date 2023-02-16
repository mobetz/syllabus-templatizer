public class Lecture {

    private String date;
    private String title;
    private double score;
    private String comments;


    public Lecture(String date, String title, double score_out_of_ten) {
        this.date = date;
        this.title = title;
        this.score = score_out_of_ten;
        this.comments = "";
    }


    public void setComments(String new_comments) {
        this.comments = new_comments;
    }

    public String getPromptName() {
        return this.title + " (" + this.date + ")";
    }

    public String toCSV() {
        return this.date + "," + this.title + "," + this.comments;
    }


    public static Lecture fromFile(String line_of_text) {
        String[] parts = line_of_text.split(",");
        String date = parts[0];
        String title = parts[1];
        String score_as_text = parts[2];
        int score = Integer.parseInt(score_as_text);

        Lecture created_lecture = new Lecture(date, title, score);
        return created_lecture;
    }
}
