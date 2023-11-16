import java.util.List;

public class SnakeBody extends SnakeSegment {

    private int x;
    private int y;

    private SnakeSegment parent;

    public SnakeBody(SnakeSegment parent, int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = parent;

        ObjectRegistry.getInstance().register((ICollidable) this);
        ObjectRegistry.getInstance().register((IDisplayable) this);

        /*
        Detaching snake body parts from the global list to make sure they
        happen from tail to head:
        ObjectRegistry.getInstance().register((IMoveable) this);
        */
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean checkCollision(List<ICollidable> c) {
        return false;
    }

    @Override
    public void move() {

        this.prev_x = this.x;
        this.prev_y = this.y;
        this.x = this.parent.getX();
        this.y = this.parent.getY();
        this.parent.move();
    }

    public int getSize() {
        return this.parent.getSize() + 1;
    }
}
