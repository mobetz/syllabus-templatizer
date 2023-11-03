import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {


        Grid g = new Grid(12, 12);
        int score = g.play();
        System.out.println("Game over! Score: " + score);
    }
}
