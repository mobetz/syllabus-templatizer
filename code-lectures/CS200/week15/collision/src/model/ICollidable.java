package model;

import javafx.geometry.Point2D;

public interface ICollidable {
    
    public void handleCollision(ICollidable other);

    public Point2D getCenterpoint();
    public Point2D getMovementDirection();
    public void updateMovementVector(Point2D new_direction, Point2D displacement);
}
