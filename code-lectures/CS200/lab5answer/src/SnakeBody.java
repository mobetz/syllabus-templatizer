import java.util.List;

public class SnakeBody  extends SnakeSegment {

    private SnakeSegment parent;

    public SnakeBody(SnakeSegment parent, int x, int y) {
        this.x = x;
        this.y = y;
        super.prev_x = x+1;
        super.prev_y = y;
        this.parent = parent;

        ObjectRegistry.getInstance().register((ICollidable) this);
        ObjectRegistry.getInstance().register((IDisplayable) this);

        /*
        Detaching snake body parts from the global list to make sure they
        happen from tail to head:
        ObjectRegistry.getInstance().register((IMoveable) this);
        */

    }

    public int getSize() {
        return this.parent.getSize() + 1;
    }



    @Override
    public boolean checkCollision(List<ICollidable> others) {
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
    public void move() {
        /*
        We move the snake body from back to front by moving each segment to its
        parents position, then moving that segment.

        It happens all at once, so collision isn't checked!
         */
        this.prev_x = x;
        this.prev_y = y;
        this.x = this.parent.getX();
        this.y = this.parent.getY();
        this.parent.move();
    }

}
