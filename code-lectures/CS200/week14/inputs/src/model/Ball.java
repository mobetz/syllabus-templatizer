package model;

import java.util.Set;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Paint;

public class Ball implements IMoveable {
    
    private SimpleDoubleProperty x;
    private SimpleDoubleProperty y;

    private double speed_x;
    private double speed_y;

    public static final int SIZE = 3;
    public static final Paint COLOR = Paint.valueOf("white");


    public Ball(double init_x, double init_y) {
        this.x = new SimpleDoubleProperty(init_x);
        this.y = new SimpleDoubleProperty(init_y);

        this.speed_x = 100;
        this.speed_y = 100;
    }


     public void move(long nanoseconds_passed_since_last_move, Set<Input> inputs) {

        double seconds_from_nanoseconds = nanoseconds_passed_since_last_move / 1e9;

        double delta_x = this.speed_x * seconds_from_nanoseconds;
        double delta_y = this.speed_y * seconds_from_nanoseconds;

        this.x.set(this.x.get() + delta_x);
        this.y.set(this.y.get() + delta_y);
     }

     public void checkWallCollision(double width, double height) {

        if ( this.x.greaterThan(width).get() ) {
            this.speed_x = this.speed_x * -1; //<- I need to flip the speed (negative speeds are the oppsoite direction)
            this.x.set(width - 1); //<- if you're "past" the border of the wall, I need to bring you back in bounds
        }  else if ( this.x.lessThan(0).get() ) {
            this.speed_x = this.speed_x * -1;
            this.x.set(1);
        }
        
        if ( this.y.greaterThan(height).get() ) {
            this.speed_y = this.speed_y * -1;
            this.y.set(height - 1);
        } else if ( this.y.lessThan(0).get() ) {
            this.speed_y = this.speed_y * -1;
            this.y.set(1);
        }
     }


     public SimpleDoubleProperty getX() {
         return x;
     }

     public SimpleDoubleProperty getY() {
         return y;
     }

}
