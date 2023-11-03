import java.util.List;

public class Apple implements IDisplayable, ICollidable {

    private int x;
    private int y;
    private boolean eaten;

    public Apple(int x, int y) {
        this.x = x;
        this.y = y;
        this.eaten = false;

        ObjectRegistry.getInstance().register((ICollidable) this);
        ObjectRegistry.getInstance().register((IDisplayable) this);
    }

    @Override
    public boolean checkCollision(List<ICollidable> others) {
        for ( ICollidable other : others ) {
            if (this.x == other.getX() && this.y == other.getY() && this != other) {
                this.eaten = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDeadly() {
        return false;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "O";
    }

    @Override
    public int getGrowthCaused() {
        return 1;
    }

    public boolean isEaten() {
        return eaten;
    }
}
