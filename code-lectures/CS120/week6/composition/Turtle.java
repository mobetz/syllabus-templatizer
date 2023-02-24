public class Turtle {

    private String name;
    private MovementFacing position;
    private int max_speed;
    private String symbol;


    public Turtle(String given_name) {
        this.name = given_name;
        this.position = new MovementFacing(6, 3, 0, "WEST");
        this.max_speed = 1; //<- turtles are slower than people
        this.symbol = "*"; //<- turtles will be a *
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public void step() {
        this.position.step();
    }

    public void turnLeft() {
        this.position.turnLeft();
    }

    public void turnRight() {
        this.position.turnRight();
    }


    public void speedUp() {
        int current_speed = this.position.getSpeed();
        int new_speed = Math.min(current_speed+1, this.max_speed);
        this.position.setSpeed(new_speed);
    }


    public void slowDown() {
        int current_speed = this.position.getSpeed();
        int new_speed = Math.max(current_speed-1, 0);
        this.position.setSpeed(new_speed);
    }


    @Override
    public String toString() {
        String grid = "------------------------------\n";
        int grid_height = 10;
        int grid_width = 10;
        for ( int y=0; y<grid_height; y++) {
            for (int x=0; x<grid_width; x++) {
                if ( x==this.position.getX() && y == this.position.getY() ) {
                    grid = grid + " " + this.symbol + " ";
                } else {
                    grid = grid + " . ";
                }
            }
            grid = grid + "\n";
        }
        grid = grid + "------------------------------\n";
        return grid;
    }
    // We're using the same logic here again for making the grid... this might be a clue that the display grid should
    // also be its own object!



}
