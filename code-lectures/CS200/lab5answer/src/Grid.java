import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    private SnakeHead snake;
    private AppleSpawner spawner;

    private ObjectRegistry important_things;

    private int height;
    private int width;

    public Grid(int x, int y) {
        this.width = x;
        this.height = y;
        this.important_things = ObjectRegistry.getInstance();


        create_perimeter();
        this.snake = new SnakeHead(x/2, y/2);
        this.spawner = new AppleSpawner(this.width, this.height);
    }

    public Grid() {
        this.width = this.height = 16;
        this.important_things = ObjectRegistry.getInstance();

        create_perimeter();
        this.snake = new SnakeHead(6, 4);
        this.spawner = new AppleSpawner(this.width, this.height);
    }

    private void create_perimeter() {
        for ( int i=0;i<this.width; i++) {
            new WallSegment(0, i);
            new WallSegment(this.height-1, i);
        }

        for ( int i=0; i<this.height; i++) {
            new WallSegment(i, 0);
            new WallSegment(i, this.width-1);
        }
    }


    public int play() {
        while ( snake.isAlive() ) {
            important_things.process();
            System.out.println(display());
        }

        return snake.getLength();
    }


    public String display() {

        String rep = "";
        IDisplayable[][] grid = this.important_things.render(this.height, this.width);

        for ( IDisplayable[] row : grid ) {
            for ( IDisplayable cell : row ) {
                if ( cell == null ) {
                    rep += " . ";
                } else {
                    rep += " " + cell + " ";
                }
            }
            rep += "\n";
        }

        return rep;
    }

}
