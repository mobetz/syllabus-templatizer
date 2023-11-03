import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class SnakeHead extends SnakeSegment implements IActable {

    private boolean alive;

    private int delta_x;
    private int delta_y;

    private int growing;
    private SnakeSegment end_of_tail;
    private InputStreamReader input_collector;

    private boolean slithering_in_progress;

    public SnakeHead(int x, int y) {
        this.x = x;
        this.y = y;

        this.growing = 0;


        generate_initial_body(4);

        this.alive = true;
        this.slithering_in_progress = false;
        this.input_collector = new InputStreamReader(System.in);

        ObjectRegistry.getInstance().register((ICollidable) this);
        ObjectRegistry.getInstance().register((IDisplayable) this);
        ObjectRegistry.getInstance().register((IMoveable) this);
        ObjectRegistry.getInstance().register((IActable) this);
    }

    private void generate_initial_body(int initial_size) {
        this.end_of_tail = this;
        for ( int i=0; i<initial_size; i++) {
            this.end_of_tail = this.end_of_tail.generate_tail_segment();
        }

    }

    @Override
    public void act() {
        if ( this.growing > 0 ) {
            this.growing--;
            this.end_of_tail = this.end_of_tail.generate_tail_segment();
        }

         char next_dir = '-';
        while (!List.of('w', 'a', 's', 'd').contains(next_dir) ) {
            System.out.print("\nEnter a direction: ");
            try {  next_dir = (char) input_collector.read(); } catch(Exception e) {System.err.println(e.getMessage());};
        }
        process_heading(String.valueOf(next_dir));

    }

    private void process_heading(String dir) {
        Map<String, int[]> dirs = Map.of("w", new int[]{0, -1},
                "a", new int[]{-1, 0},
                "s",  new int[]{0, 1},
                "d",  new int[]{1, 0} );

        int[] headings = dirs.get(dir);
        this.delta_x = headings[0];
        this.delta_y = headings[1];
    }

    @Override
    public boolean checkCollision(List<ICollidable> others) {
        for ( ICollidable other : others ) {
            if ( other.getX() == this.x && other.getY() == this.y && this != other ) {
                if ( other.isDeadly() ) {
                    this.alive = false;
                } else {
                    this.growing += other.getGrowthCaused();
                }
                return true;
            }
        }
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
        if ( !this.slithering_in_progress ) {
            this.slithering_in_progress = true;
            this.end_of_tail.move();
            this.x += this.delta_x;
            this.y += this.delta_y;
            this.slithering_in_progress = false;
        }

    }

    public boolean isAlive() {
        return this.alive;
    }

    public int getLength() {
        return this.end_of_tail.getSize();
    }
}
