import java.util.ArrayList;
import java.util.List;

public class ObjectRegistry {
    private List<IMovable> movables;
    private List<IActable> actables;
    private List<ICollidable> collidables;
    private List<IDisplayable> displayables;

    private static ObjectRegistry self;

    public static ObjectRegistry getInstance() {
        if (ObjectRegistry.self == null) {
            return ObjectRegistry.self = new ObjectRegistry();
        } else {
            return ObjectRegistry.self;
        }
    }


    private ObjectRegistry() {
        this.displayables = new ArrayList<>();
        this.collidables = new ArrayList<>();
        this.actables = new ArrayList<>();
        this.movables = new ArrayList<>();
    }

    public void register(ICollidable c) {
        this.collidables.add(c);
    }

    public void register(IActable a) {
        this.actables.add(a);
    }

    public void register(IMovable m) {
        this.movables.add(m);
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

    public void destroy(IMovable m) {
        this.movables.remove(m);
    }

    public void destroy(IDisplayable d) {
        this.displayables.remove(d);
    }


    public boolean isOccupied(int x, int y) {
        for ( ICollidable thing : this.collidables ) {
            if ( thing.getX()==x && thing.getY() == y) {
                return true;
            }
        }
        return false;
    }


    public void process() {
        for (IActable a : actables) {
            a.act();
        }

        for (IMovable m : movables) {
            m.move();
        }

        for (ICollidable c : collidables) {
            c.checkCollision(collidables);
        }
    }

    public String display(int height, int width) {
        String rep = "";

        IDisplayable[][] grid = new IDisplayable[height][width];
        for (int i = this.displayables.size() - 1; i >= 0; i--) {
            IDisplayable thing = this.displayables.get(i);
            grid[thing.getY()][thing.getX()] = thing;
        }

        for (IDisplayable[] row : grid) {
            for (IDisplayable cell : row) {
                if (cell == null) {
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

