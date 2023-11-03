public abstract class SnakeSegment implements IDisplayable, IMoveable, ICollidable{

    protected int x;
    protected int y;

    protected int prev_x;
    protected int prev_y;

    public int getSize() {
        return 1;
    }

    @Override
    public boolean isDeadly() {
        return true;
    }

    @Override
    public int getGrowthCaused() {
        return 0;
    }

    public SnakeBody generate_tail_segment() {
        SnakeBody new_tail = new SnakeBody(this, this.prev_x, this.prev_y);
        return new_tail;
    }

    @Override
    public String toString() {
        return "#";
    }
}
