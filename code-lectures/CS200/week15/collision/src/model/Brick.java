package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

public class Brick implements ICollidable {

    private double x;
    private double y;
    private boolean isAlive;


    public static final double WIDTH = 100;
    public static final double HEIGHT = 20;
    public static final double SPACE_BETWEEN = 2;
    public static final Paint COLOR = Paint.valueOf("white");


    /**
     * Create a brick at a position based on number of bricks across and number of bricks down (not coordinate location.)
     * 
     */
    public Brick(int brick_x_pos, int brick_y_pos) {
        this.x = brick_x_pos * (Brick.WIDTH + SPACE_BETWEEN);
        this.y = brick_y_pos * (Brick.HEIGHT + SPACE_BETWEEN);
        this.isAlive = true;
    }
    

    @Override
    public void handleCollision(ICollidable other) {
        
        //flip the ball
        Point2D other_direction = other.getMovementDirection();
        other.updateMovementVector(
            new Point2D(other_direction.getX() * -1, other_direction.getY() * -1),
         new Point2D(0, 0)
         );


        //destroy this brick
        this.isAlive = false;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public boolean isAlive() {
        return this.isAlive;
    }


    @Override
    public Point2D getCenterpoint() {
        return new Point2D(x + Brick.WIDTH / 2, y + Brick.HEIGHT / 2);
    }

    @Override
    public Point2D getMovementDirection() {
        return new Point2D(0, 0);
    }

    @Override
    public void updateMovementVector(Point2D new_direction, Point2D displacement) {
        // Bricks don't move
    }
    
}
