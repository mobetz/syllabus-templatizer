public abstract  class SnakeSegment   implements IDisplayable, IMovable, ICollidable {

    protected int x;
    protected int y;

    protected int prev_x;
    protected int prev_y;

    public abstract int getSize();

    @Override
    public boolean isDeadly() {
        return true;
    }

    @Override
    public int getGrowthCaused() {
        return 0;
    }

    @Override
    public String toString() {
        return "#";
    }

    public SnakeBody generate_tail_segment() {
        SnakeBody new_tail = new SnakeBody(this, this.prev_x, this.prev_y);
        return new_tail;
    }
}
