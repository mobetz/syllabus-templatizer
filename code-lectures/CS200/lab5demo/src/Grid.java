import java.util.List;

public class Grid {
    
    private SnakeHead snake;

    private ObjectRegistry important_things;

    
    private int height;
    private int width;
    
    public Grid(int x, int y) {
        this.width = x;
        this.height = y;
        this.important_things = ObjectRegistry.getInstance();

        create_perimeter();

        this.snake = new SnakeHead(x/2, y/2);
        new AppleSpawner(this.width, this.height);
    }

    private void create_perimeter() {
        for ( int i=0; i<this.width; i++) {
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
            System.out.print(important_things.display(this.height, this.width));
        }

        return this.snake.getLength();
    }
    
    
}
