package model;

import java.util.Set;

public interface IMoveable {

    public void move(long time_passed, Set<Input> inputs);
    public void checkWallCollision(double width, double height);
    
}
