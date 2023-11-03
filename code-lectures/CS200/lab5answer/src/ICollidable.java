import java.util.List;

public interface ICollidable {

    public boolean checkCollision(List<ICollidable> others);
    public boolean isDeadly();
    public int getGrowthCaused();
    public int getX();
    public int getY();
}
