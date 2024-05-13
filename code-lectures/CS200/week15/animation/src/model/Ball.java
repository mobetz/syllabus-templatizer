package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

/*
 * One of the nice things about MVC is that it allows me to think about the problem of representing
 * a model (like a Ball) without worrying at all about how that thing is actually rendered to the screen.
 * 
 * 
 */

public class Ball {
    
    private double x;
    private double y;

    private double speed_x;
    private double speed_y;

    public static final int SIZE = 3;
    public static final Paint COLOR = Paint.valueOf("white");


    public Ball(double init_x, double init_y) {
        this.x = init_x;
        this.y = init_y;

        this.speed_x = 100;
        this.speed_y = 100;
    }

    /*
     * In this class, all I need to do is implement the functions that describe the behaviors my
     * Ball will have. For an animation, one of the main things we expect the ball to do is move:
     * 
     * If I want this to work the same no matter how often move is called, I need to normalize speed
     * based on the amount of time since the last time it was fired off.
     */

     public void move(long nanoseconds_passed_since_last_move) {

        double seconds_from_nanoseconds = nanoseconds_passed_since_last_move / 1e9;

        double delta_x = this.speed_x * seconds_from_nanoseconds;
        double delta_y = this.speed_y * seconds_from_nanoseconds;

        this.x = this.x + delta_x;
        this.y = this.y + delta_y;
     }

     public void checkWallCollision(double width, double height) {

        /*
         * 
         * (0,0)
         *      +----------------------------------------+
         *      |                                        |
         *      |                                        |
         *      |                                        |
         *      |                                        |
         *      |     o                                  |
         *      |    (50,200)                            |
         *      |                                        |
         *      |                                        |
         *      |                                        |
         *      |                                        |
         *      |                                        |
         *      +----------------------------------------+
         *                                                (width, height)
         */
        if ( this.x > width ) {
            this.speed_x = this.speed_x * -1; //<- I need to flip the speed (negative speeds are the oppsoite direction)
            this.x = width - 1; //<- if you're "past" the border of the wall, I need to bring you back in bounds
        }  else if ( this.x < 0 ) {
            this.speed_x = this.speed_x * -1;
            this.x = 1;
        }
        
        if ( this.y > height ) {
            this.speed_y = this.speed_y * -1;
            this.y = height - 1;
        } else if ( this.y < 0 ) {
            this.speed_y = this.speed_y * -1;
            this.y = 1;
        }
     }


     public  Point2D getCoordinates() {
        return new Point2D(this.x, this.y);
     }


}
