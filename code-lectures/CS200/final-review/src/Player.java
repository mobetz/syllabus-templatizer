public class Player {
    
    private int player_num;
    private Player next_player;


    private static int player_count = 0;

    public Player() {
        player_count++;
        this.player_num = player_count;
    }


    public void setNext(Player next_turn) {
        this.next_player = next_turn;
    }


    public Player getNext() {
        return this.next_player;
    }


    @Override
    public String toString() {
        return "Player " + this.player_num;
    }


}
