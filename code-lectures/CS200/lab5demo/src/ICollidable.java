import java.util.List;

public interface ICollidable {

    boolean checkCollision(List<ICollidable> c);

    int getX();
    int getY();

    boolean isDeadly();
    int getGrowthCaused();
}
