package model;

import java.util.Set;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

public class Paddle implements IMoveable, ICollidable {
    

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


    @Override
    public void handleCollision(ICollidable other) {
        
        Point2D centerpoint_other = other.getCenterpoint();
        Point2D self_centerpoint = this.getCenterpoint();

        Point2D other_vector = other.getMovementDirection();

        double angle = self_centerpoint.angle(self_centerpoint.add(Paddle.SIZE, 0), centerpoint_other);
        double magnitude = other_vector.distance(new Point2D(0, 0));

        double new_x_speed = Math.cos(angle) * magnitude;
        double new_y_speed = Math.sin(angle) * magnitude;

        Point2D new_speed = new Point2D(new_x_speed, new_y_speed);
        other.updateMovementVector(new_speed, new Point2D(0, -Ball.SIZE));

    }


    @Override
    public Point2D getCenterpoint() {
        return new Point2D(this.x.get() + (Paddle.SIZE / 2), this.y.get() + Paddle.WIDTH);
    }


    @Override
    public Point2D getMovementDirection() {
       return new Point2D(0, 0);
    }


    @Override
    public void updateMovementVector(Point2D new_direction, Point2D displacement) {
        
    }
    
}
