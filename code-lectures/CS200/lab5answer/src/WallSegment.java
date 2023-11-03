import java.util.List;

public class WallSegment implements ICollidable, IDisplayable{

    private int x;
    private int y;

    public WallSegment(int x, int y) {
        this.x = x;
        this.y = y;

        ObjectRegistry.getInstance().register((ICollidable) this);
        ObjectRegistry.getInstance().register((IDisplayable) this);
    }

    @Override
    public boolean checkCollision(List<ICollidable> others) {
        return false; //Nothing happens to walls when they collide!
    }

    @Override
    public boolean isDeadly() {
        return true;
    }

    @Override
    public int getGrowthCaused() {
        return 0;
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
        return "X";
    }
}
