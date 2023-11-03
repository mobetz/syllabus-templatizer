import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectRegistry {


    private List<IMoveable> moveables;
    private List<IActable> actables;
    private List<IDisplayable> displayables;
    private List<ICollidable> collidables;

    private static ObjectRegistry self;
    private ObjectRegistry() {
        this.displayables = new ArrayList<>();
        this.collidables = new ArrayList<>();
        this.actables = new ArrayList<>();
        this.moveables = new ArrayList<>();
    }

    public static ObjectRegistry getInstance() {
        if ( self == null ) {
            return self = new ObjectRegistry();
        } else {
            return self;
        }
    }

    public void register(ICollidable c) {
        this.collidables.add(c);
    }
    public void register(IActable a) {
        this.actables.add(a);
    }
    public void register(IMoveable m) {
        this.moveables.add(m);
    }
    public void register(IDisplayable d) {
        this.displayables.add(d);
    }



    public void destroy(ICollidable c) {
        this.collidables.remove(c);
    }
    public void destroy(IActable a) {
        this.actables.remove(a);
    }
    public void destroy(IMoveable m) {
        this.moveables.remove(m);
    }
    public void destroy(IDisplayable d) {
        this.displayables.remove(d);
    }


    public void process() {
        actables.forEach(IActable::act);
        moveables.forEach(IMoveable::move);
        collidables.forEach((c) -> c.checkCollision(collidables));
    }

    public IDisplayable[][] render(int height, int width){

        IDisplayable[][] grid = new IDisplayable[height][width];

        for ( int i=this.displayables.size()-1; i>=0; i-- ) {
            IDisplayable thing = this.displayables.get(i); //<- grabbing these in reverse order so snake is always "on top"
            grid[thing.getY()][thing.getX()] = thing;
        }

        return grid;
    }

    public boolean isOccupied(int x, int y) {
        return this.collidables.stream().anyMatch((thing) -> thing.getX()==x && thing.getY()==y);
    }
}
