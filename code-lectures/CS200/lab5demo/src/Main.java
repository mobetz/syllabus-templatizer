public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(16, 16);
        int score = grid.play();

        System.out.println("Game Over! Final Score: " + score);
    }
}
