package model;

import java.util.Set;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Paint;

public class Paddle implements IMoveable {
    

    private SimpleDoubleProperty x;
    private SimpleDoubleProperty y;

    public static final int SIZE = 60;
    public static final int MAX_SPEED = 90;
    public static final Paint COLOR = Paint.valueOf("white");
    public static final int WIDTH = 4;
    public static final int GOAL_OFFSET = 30;


    public Paddle(double starting_position_x, double window_height) {
        this.x = new SimpleDoubleProperty(starting_position_x);
        this.y = new SimpleDoubleProperty(window_height - GOAL_OFFSET); //<- paddles only move in the X dimension, they're locked to 30px in Y
    }


    public void move(long nanoseconds_passed_since_last_move, Set<Input> keys_pressed) {

        double seconds_from_nanoseconds = nanoseconds_passed_since_last_move / 1e9;
        double time_normalized_speed = Paddle.MAX_SPEED * seconds_from_nanoseconds;

        double delta_x = 0;

        for ( Input input : keys_pressed) {
            switch (input) {
                case LEFT:
                    delta_x = delta_x + (time_normalized_speed * -1) ;
                    break;
                case RIGHT:
                    delta_x = delta_x + time_normalized_speed;
                    break;
                case UNKNOWN:
                    break;
            }
        }

        this.x.set(this.x.get() + delta_x);
     }


     public void checkWallCollision(double width, double height) {

        if ( this.x.greaterThan(width - Paddle.SIZE).get() ) {
            this.x.set(width - Paddle.SIZE);
        } else if ( this.x.lessThan(0).get()) {
            this.x.set(0);
        }

     }



    public SimpleDoubleProperty getX() {
        return x;
    }

    public SimpleDoubleProperty getY() {
        return y;
    }
    
}
