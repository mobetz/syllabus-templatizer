import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppleSpawner implements IMovable, IActable {

    private int x;
    private int y;

    private int bound_x;
    private int bound_y;


    private List<Apple> apples_made;

    public AppleSpawner(int bound_x, int bound_y) {
        this.x = 2;
        this.y = 6;
        this.bound_x = bound_x;
        this.bound_y = bound_y;

        ObjectRegistry.getInstance().register((IMovable) this);
        ObjectRegistry.getInstance().register((IActable) this);

        this.apples_made = new ArrayList<>();

    }

    @Override
    public void move() {

        boolean moved = false;
        while ( !moved ) {
            int delta_x = new Random().nextInt(-1, 2);
            int delta_y = new Random().nextInt(-1, 2);

            int new_x = Math.min(this.bound_x-1, Math.max(0, this.x + delta_x));
            int new_y = Math.min(this.bound_y-1, Math.max(0, this.y + delta_y));

            this.x = new_x;
            this.y = new_y;

            /*
            Only count a spawner move as complete if it's an unused space
             */
            moved = ObjectRegistry.getInstance().isOccupied(this.x, this.y);
        }
    }


    @Override
    public void act() {
        List<Apple> apples_to_remove = new ArrayList<>();

        for ( Apple apple : apples_made ) {
            if ( apple.isEaten() ) {
                apples_to_remove.add(apple);
                apple.destroy();
            }
        }

        //We can't modify a list we're in the middle of looping through, so we have to destroy apples later!
        this.apples_made.removeAll(apples_to_remove);


        if ( apples_made.isEmpty() ) {
            apples_made.add(new Apple(this.x, this.y));
        }

    }
}
