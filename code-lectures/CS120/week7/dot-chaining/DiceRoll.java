import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class DiceRoll {

    //ATTRIBUTES
    private int number_of_sides;
    private int number_of_dice;
    private int num_kept;



    //METHODS
    public DiceRoll(int given_sides, int given_dice, int num_kept) {
    	/*
    	The way we've always written our constructors is that you provide ALL the details, in order, and we save them onto the attributes:
    	*/
    	this.number_of_sides = given_sides;
    	this.number_of_dice = given_dice;
    	this.num_kept = num_kept;

    	/*
    	There's something mildly inconvenient about this: my user has to set ALL of these attributes every time, and all of them are
    	ints (so it's easy to get confused which one comes first, what each number is representing...)
    	*/
    }

    /*
    I might realize, 99% of the time, people are just going to want one single six sided die. I can choose to instead make a 
    constructor that has some assumptions about how my class will be used:
    */

    public DiceRoll() {
    	//By default, we get one six-sided die
    	this.number_of_sides = 6;
    	this.number_of_dice = 1;
    	this.num_kept = -1; //<- I'm using a 'nonsense' value here so I can check for it later
    }

    /*
    With my default constructor, I might also choose to write a handful of special setters that each let me modify one single value.
    I'm going to call these setters "with" methods: the difference with a "with" method is that I want the "with" to be easy to chain
    multiple set values one after another.

    */

    public  DiceRoll  withDice(int count) {
    	this.number_of_dice = count;
    	return this;
    }

    public  DiceRoll  withSides(int sides) {
        this.number_of_sides = sides;
    	return this;
    }    


    public DiceRoll keep(int best_count) {
        this.num_kept = best_count;
        return this;
    }

    /*
    When we're using the "with"-method pattern, typically all of the "with" methods are just doing SETUP. There's usually
    a separate method that says 'I'm done setting up and I now want to execute the task'. For us, that's going to be a 
    separate method that actually finishes and ROLLS the dice:
    */



    public Integer[] roll() {


    	//Create an array that can hold all our individual rolls
    	Integer[] rolls = new Integer[this.number_of_dice];


    	//Roll each die individually
    	for ( int die_num=0; die_num < this.number_of_dice; die_num++) {
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

        Integer[] best_rolls = Arrays.copyOf(rolls, this.num_kept); //<- only keep the first "num_kept" number of rolls

       
    	return best_rolls;

    }


}