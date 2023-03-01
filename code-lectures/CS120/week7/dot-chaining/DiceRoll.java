import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class DiceRoll {


    //ATTRIBUTES
    private int number_of_sides;
    private int number_of_dice;
    private int num_kept;




    //METHODS
    public DiceRoll() {
    	//By default, we get one six-sided dice
    	this.number_of_sides = 6;
    	this.number_of_dice = 1;
    	this.num_kept = -1; //<- I'm using a 'nonsense' value here so I can check for it later
    }


    /* Now when I'm thinking about implementing "withDice()", I need to think about what type of result I actually
    need to return to 'keep my chain going':
     */

    public DiceRoll withDice(int count) {
        this.number_of_dice = count;
        // But what is the DiceRoll object I have that I want to return...?

        return this;     //<- it's "this" die that I just changed! "this" is always an object of the type we're currently in!
    }


    public DiceRoll withSides(int sides) {
        this.number_of_sides = sides;
        return this;
    }


    public DiceRoll keep(int best_count) {
        this.num_kept = best_count;
        return this;
    }



    public Integer[] roll() {

    	//Create an array that can hold all our individual rolls
    	Integer[] rolls = new Integer[this.number_of_dice];


    	//Roll each die individually
    	for ( int die_num=0; die_num<this.number_of_dice; die_num++) {
            Random random_number_generator = new Random();
            //int roll = random_number_generator.nextInt(1, 7); //<- get me a random number starting at 1, less than 7
            int roll = random_number_generator.nextInt(1, this.number_of_sides + 1);
            rolls[die_num] = roll;
    	}


    	//Some work to figure out the best
        //To keep the "best" N rolls, we're going to use a function that lets us sort our array in numeric order:
        Arrays.sort(rolls, Collections.reverseOrder());  //<- put them in order from high to low

        // 6 1 3 5
        // 6 5 3 1
        // [6 5 3]  /1/

        //Then, we just need to keep the first N:
        Integer[] best_rolls = Arrays.copyOf(rolls, this.num_kept); //<- only keep the first "num_kept" number of rolls
        Integer[] worst_rolls = Arrays.copyOfRange(rolls, this.num_kept, rolls.length);


        return best_rolls;

    }






}