public class PlayerRing {
    
    private Player current_player;


    public PlayerRing() {
        this.current_player = null;
    }


    public void add(Player new_player) {
        if ( current_player == null ) {
            this.current_player = new_player;
            this.current_player.setNext(new_player); //<- if there's only one player, he takes every turn so he's his own next turn
        }
        else if ( current_player.getNext() == current_player ) {
            this.current_player.setNext(new_player);
            new_player.setNext(this.current_player); //<- if two players, each points to the other
        }
        else  { 
            Player previous_next = this.current_player.getNext();
            this.current_player.setNext(new_player);
            new_player.setNext(previous_next); //<- if more than two players, insert the new one after the current, put the old "next" as the next after that
        }
    }

    public Player advance() {
        
        Player this_turn = this.current_player;
        this.current_player = this.current_player.getNext(); //<- move one "step" around the ring
        return this_turn;

    }


}
