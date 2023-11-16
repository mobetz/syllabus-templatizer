import java.util.List;

public class WallSegment implements IDisplayable, ICollidable {

    private int x;
    private int y;

    WallSegment(int x, int y) {
        this.x = x;
        this.y = y;

        ObjectRegistry.getInstance().register((ICollidable) this);
        ObjectRegistry.getInstance().register((IDisplayable) this);
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public boolean checkCollision(List<ICollidable> c) {
        return false;
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
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X";
    }
}
