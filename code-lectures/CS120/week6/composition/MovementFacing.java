

public class MovementFacing {
	

    /*
    Our MovementFacing object is going to look very similar to the movement related attributes and functions from
    Person:
     */

    //ATTRIBUTES
    private int x_pos;
    private int y_pos;
    private int speed;
    private String direction;

    //METHODS
    public MovementFacing(int given_x, int given_y, int initial_speed, String direction) {
        this.x_pos = given_x;
        this.y_pos = given_y;
        this.speed = initial_speed;
        this.direction = direction;
    }

    public int getX() {
        return this.x_pos;
    }

    public int getY() {
        return this.y_pos;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }


    public void step() {
        if (this.direction.equals("NORTH")) {
            this.y_pos = this.y_pos - this.speed;
        } else if ( this.direction.equals("EAST")) {
            this.x_pos = this.x_pos + this.speed;
        } else if ( this.direction.equals("SOUTH")) {
            this.y_pos = this.y_pos + this.speed;
        } else if ( this.direction.equals("WEST")) {
            this.x_pos = this.x_pos - this.speed;
        }
    }


    public void turnLeft() {
        String new_direction = "NOWHERE";

        String[] directions = {"NORTH", "WEST", "SOUTH", "EAST"};
        for ( int i=0; i<directions.length; i++ ) {
            String test_direction = directions[i];
            if (test_direction.equals(this.direction)) {
                int new_direction_index = (i+1) % 4; //<- go one forward in the array. If we hit the end, wrap around to beginning
                new_direction = directions[new_direction_index];
            }
        }

        this.direction = new_direction;

    }


    public void turnRight() {
        String new_direction = "NOWHERE";

        String[] directions = {"NORTH", "EAST", "SOUTH", "WEST"};
        for ( int i=0; i<directions.length; i++ ) {
            String test_direction = directions[i];
            if (test_direction.equals(this.direction)) {
                int new_direction_index = (i+1) % 4; //<- go one forward in the array. If we hit the end, wrap around to beginning
                new_direction = directions[new_direction_index];
            }
        }

        this.direction = new_direction;

    }



    
    /*
    Once we have created our MovementFacing, let's make a new attempt at a "ComposedPerson" that uses this
    object.
     */
}