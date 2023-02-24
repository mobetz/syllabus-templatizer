

    /*
    In our first attempt at making this object, we might decide we want to directly implement
    all of our details as attributes:
     */

public class ComposedPerson {

	//ATTRIBUTES

    private String name;
    private MovementFacing position;
    private int grid_width;
    private int grid_height;

    /*
    This time, all the movement related information gets stored in one place, our 'position' attribute.
     */


    //METHODS    
    
    public ComposedPerson(String given_name) {

    	this.name = given_name;
        this.grid_width = 10;
        this.grid_height = 10;

    	this.position = new MovementFacing(4, 4, 0, "NORTH");
                /*
        ComposedPerson is internally creating a MovementFacing when it is constructed -- from the perspective of
        people using our Person, nothing has changed!
         */

    }

    public String getName() {
        return this.name;
    }


    /*
    If we want to still be able to get the same details we did before, now we have to ask the MovementFacing to
    provide them for us:
     */
    public int getX() {
    	return this.position.getX(); //<- we're still able to get the same data to the user, even though it lives
                                    // in another class
    }

    public int getY() {
        return this.position.getY(); //<- we're still able to get the same data to the user, even though it lives
                                    // in another class
    }


    @Override
    public String toString() {


        String grid = "------------------------------\n";

        for ( int y=0;  y<this.grid_height; y++ ) { ///<- for each 'row' of our grid
        	for ( int x=0; x<this.grid_width; x++  ) { //<- for each 'cell' in that row

        		if ( x == this.position.getX() && y == this.position.getY() ) {
        			grid = grid + " o ";
        		} else { 
        			grid = grid + " . ";
        		}
        	}
            grid = grid + "\n"; //<- at the end of each "row", go to the next row.
        }

        grid = grid + "------------------------------\n";
        return grid;
    }


    /*
    The same holds true when we're trying to perform movement-related behaviors:
     */
	
	public void turnRight() {
        this.position.turnRight();
	}

	public void turnLeft() {
        this.position.turnLeft();
	}

    public void speedUp() {
        //Sometimes our composed objects and our public interfaces don't perfectly match up. That's fine! Our
        // code that changes these values still benefits from the composition:
        int max_speed = 4;
        int current_speed = this.position.getSpeed();
        int new_speed = Math.min(current_speed+1, max_speed); //<- current speed can never get higher than max speed
        this.position.setSpeed(new_speed);
    }

    public void slowDown() {
        int min_speed = 0;
        int current_speed = this.position.getSpeed();
        int new_speed = Math.max(current_speed-1, 0); //<- current speed can never go below 0
        this.position.setSpeed(new_speed);
    }


	public void step() {

        this.position.step();
	}

    /*
    Now, our ComposedPerson class only has to write code that is specific to himself. All of the movement-related
    code that we could want to reuse is outside of this file!
     */



}