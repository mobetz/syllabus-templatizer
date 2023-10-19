
/*
THIS WAS OUR FIRST ATTEMPT, WE CAN REPLACE THIS WITH COMPOSEDPERSON ONCE WE MAKE IT
*/

public class Person {


	//ATTRIBUTES
    private String direction;
    private int speed;
    private int x_pos;
    private int y_pos;

    private String name;
    private int grid_width;
    private int grid_height;


    //METHODS  

    // We would have some standard methods like a constructor and getters...
    public Person(String given_name) {

    	this.name = given_name;

    	/* Maybe I decide people always start standing still, facing north at "4,4" */
    	this.x_pos = 4;
    	this.y_pos = 4;
    	this.speed = 0;
    	this.direction = "NORTH";

    	this.grid_width = 10;
    	this.grid_height = 10;
    }



    public String getName() {
        return this.name;
    }

    public int getX() {
    	return this.x_pos;
    }


    public int getY() {
        return this.y_pos;
    }


    /*
    If we wanted to show where our person was on the grid, we might decide to make
    a toString() that can display the grid:
     */

    @Override
    public String toString() {

        String grid = "------------------------------\n";

        for ( int y=0; y<this.grid_height; y++ ) { //<- for each 'row' in my grid
        	for ( int x=0; x<this.grid_width; x++ ) { //<- for each 'column' inside that row (so each individual cell)
        		if ( x == this.x_pos && y == this.y_pos ) {
        			grid = grid + " o ";
        		} else {
        			grid = grid + " . ";
        		}
        	}

        	grid = grid + "\n"; //<- as the last part of making a row, add a new line to go to the next row
        }


        grid = grid + "------------------------------\n";


        return grid;
    }



    //We might even have the code to move the person on the grid here:

    public void speedUp() {
    	this.speed = this.speed + 1;
    }


    public void slowDown() {
    	if ( this.speed >= 1 ) {//<- speed can't go below zero
	    	this.speed = this.speed - 1;
    	}
    }


	
	public void turnRight() {
		String new_direction = "NOWHERE";


        String[] directions = {"NORTH", "EAST", "SOUTH", "WEST"};

        for ( int i=0; i<directions.length; i++ ) {
        	String next_direction = directions[i];
        	if ( next_direction.equals(this.direction)) {
        		int new_direction_index = (i + 1) % 4; //<- go one forward in the array. 
            	                                       //If we hit the end, wrap around to beginning
        		/* NOTE: the modulo/'remainder' operator (%) is very useful any time we need to "wrap around" */
        		new_direction = directions[new_direction_index];
        	}

        }


		this.direction = new_direction;
	}


	
	public void turnLeft() {
		String new_direction = "NOWHERE";

        String[] directions = {"NORTH", "WEST", "SOUTH", "EAST"};

        for ( int i=0; i<directions.length; i++ ) {
        	String next_direction = directions[i];
        	if ( next_direction.equals(this.direction)) {
        		int new_direction_index = (i + 1) % 4; //<- go one forward in the array. 
            	                                       //If we hit the end, wrap around to beginning
        		/* NOTE: the modulo/'remainder' operator (%) is very useful any time we need to "wrap around" */
        		new_direction = directions[new_direction_index];
        	}

        }


		this.direction = new_direction;
	}


	public void step() {
        if (this.direction.equals("NORTH")) {
            this.y_pos = this.y_pos - this.speed;
        } else if ( this.direction.equals("EAST")) {
            this.x_pos = this.x_pos + this.speed;

        }else if ( this.direction.equals("SOUTH")) {
            this.y_pos = this.y_pos + this.speed;
        } else if ( this.direction.equals("WEST")) {
            this.x_pos = this.x_pos - this.speed;
        }
	}



    /*
    This class works, but as we look at it, it's starting to get fairly long and complicated.

    Additionally, a lot of what it's doing really has nothing to do with being a Person specifically.

    All this math related to a position could easily be reused for a Dog or a Turtle, but it's
    trapped here in this class....


    This is where composition can help! By deciding to split off everything related to movement into
    a "MovementFacing" object, we can make that code reusable for anything that needs a facing!

    Even though a MovementFacing doesn't represent anything physically in the real world, it represents
    a single 'responsibility' that this object has: modeling movement. While the simplest objects represent
    real nouns, it is perfectly alright to create an object that describes a set of behaviors.
 */


}