public class Coordinate {
    
    //ATTRIBUTES
    private final int x;
    private final int y;



    //METHODS
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    /**
     * Give me the distance on each axis from the other point.
     * 
     * For example, if I am holding (1,1) and the other coordinate is (5, 8), I should get back
     * (4, 7), because you have to add 4 to x, and add 7 to y to get to the other coordinate.
     * @param other
     * @return
     */
    public Coordinate offsetFrom(Coordinate other) {

        return new Coordinate(other.x-this.x, other.y-this.y);
    }



    public double distanceFrom(Coordinate other) {

        //The distance formula: 
        /*   ____________________________________
         * \/  ( x_2 - x_1 )^2 + (y_2 - y_1)^2
         */
        
         Coordinate offset = this.offsetFrom(other);
         return Math.sqrt(Math.pow(offset.x, 2) + Math.pow(offset.y, 2));
    }

    /**
     * Give me a Coordinate that only cares about the "direction" the coordinate is headed.
     * 
     * For example, (-4, 10), we would get (-1, 1) because we're "left" of center and "above" center.
     * @return
     */
    public Coordinate normalizedCoordinate() {
        int x_dir = this.x / Math.abs(this.x); //TODO: handle divide by zero
        int y_dir = this.y / Math.abs(this.y);

        return new Coordinate(x_dir, y_dir);
    }


    @Override
    public String toString() {
        return String.format("%d,%d", this.x, this.y);
    }


    public static Coordinate fromText(String text) {
        String[] parts = text.split(",");
        if ( parts.length == 2 ) {
            return new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else {
            return null;
        }
    }


    public static Maybe<Coordinate> betterFromText(String text) {
        String[] parts = text.split(",");
        if ( parts.length == 2 ) {
            Coordinate contents = new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            return new Maybe<Coordinate>(contents);
        } else {
            return new Maybe<Coordinate>(null);
        }
    }



}
