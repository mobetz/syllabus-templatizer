import java.util.List;
import java.util.Scanner;

public class SnakeHead extends SnakeSegment implements IActable{


    private int x;
    private int y;

    private int delta_x;
    private int delta_y;

    private boolean isAlive;

    private Scanner input_reader;

    private SnakeSegment end_of_tail;
    private int amount_to_grow;

    private boolean slithering_in_progress;

    SnakeHead(int x, int y) {
        this.x = x;
        this.y = y;
        this.delta_y = 0;
        this.delta_x = 0;

        this.isAlive = true;
        this.end_of_tail = this;
        this.slithering_in_progress = false;
        this.amount_to_grow = 0;

        this.input_reader = new Scanner(System.in);

        ObjectRegistry registry = ObjectRegistry.getInstance();
        registry.register((ICollidable) this);
        registry.register((IMovable) this);
        registry.register((IActable) this);
        registry.register((IDisplayable) this);
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void process_direction(String dir) {
        if (dir.equals("w")) {
            this.delta_x = 0;
            this.delta_y = -1;
        }  else if (dir.equals("a")) {
            this.delta_x = -1;
            this.delta_y = 0;
        }  else if (dir.equals("s")) {
            this.delta_x = 0;
            this.delta_y = 1;
        }  else if (dir.equals("d")) {
            this.delta_x = 1;
            this.delta_y = 0;
        }
    }

    @Override
    public void act() {
        if ( this.amount_to_grow > 0 ) {
            this.amount_to_grow--;
            this.end_of_tail = this.end_of_tail.generate_tail_segment();
        }


        char next_dir = '-';
        while ( !List.of('w', 'a', 's', 'd').contains(next_dir)) {
            System.out.print("\nEnter a direction:");
            next_dir = this.input_reader.next().charAt(0);
        }

        process_direction(String.valueOf(next_dir));
    }


    @Override
    public void move() {
        if ( !this.slithering_in_progress) {
            this.slithering_in_progress = true;
            this.end_of_tail.move();
            this.prev_x = this.x;
            this.prev_y = this.y;
            this.x = this.x + this.delta_x;
            this.y = this.y + this.delta_y;
            this.slithering_in_progress = false;
        }
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
    public boolean checkCollision(List<ICollidable> others) {
        for ( ICollidable other : others ) {

            if (other.getX() == this.x && other.getY() == this.y && this != other) {
                if (other.isDeadly()) {
                    this.isAlive = false;
                } else {
                    this.amount_to_grow = other.getGrowthCaused();
                }
            }
        }



        return false;
    }


    @Override
    public int getSize() {
        return 1;
    }

    public int getLength() {
        return this.end_of_tail.getSize();
    }
}
