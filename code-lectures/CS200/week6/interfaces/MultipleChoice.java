import java.util.Scanner;

public class MultipleChoice extends Quizzable implements IPromptable, IScoreable {

    //ATTRIBUTES

    private String stem;
    private String[] options;
    private int correct_index;
    private int selected_index;
    private int points;
    private boolean correct;


    public MultipleChoice(String stem, String[] options, int correct_option, int points) {
        this.stem = stem;
        this.options = options;
        this.points = points;
        this.correct_index = correct_option;
        this.correct = false;
    }

    public int getPoints() {
        if ( this.selected_index == this.correct_index ) {
            return this.points;
        } else {
            return 0;
        }
        
    }

    public boolean setCorrect() {
        this.correct = true;
        this.selected_index = this.correct_index;
        return true;
    }

    @Override
    public void ask() {
        
        Scanner in = new Scanner(System.in);
        System.out.println(this.displayText());

        System.out.print("Enter a choice number: ");
        this.selected_index = Integer.parseInt(in.nextLine());
        this.correct = this.selected_index == this.correct_index;
        
    }
    

    public String displayText() {
        String text = this.stem + "\n";
        for ( int i=0;i<this.options.length; i++) {
            text = text + "" + (i+1) + ") " + this.options[i];
        }

        return text;

    }
    
}
