public class RankingsArray {

    private String[] rankings;
    private int member_count;

    private RankingsArray(int capacity) {
        this.rankings = new String[capacity];
        this.member_count = 0;
    }

    /*
    If we just want to add someone to the end of the list, that is easy enough:
     */
    public int push_member_onto_end(String member) {
        int target_position = this.member_count;
        this.rankings[target_position] = member;
        this.member_count++; //<- there's now one more person!

        return target_position;
    }

    /*
    Inserting into an arbitrary position works like the method we wrote before:
     */
    public int insert_member_into_position(int position, String member) {
        //First make space starting from the end:
        for ( int i=this.member_count-1; i>position; i--) {
            this.rankings[i] = this.rankings[i-1];
        }
        //Then add the person:
        this.rankings[position] = member;
        this.member_count++; //<- we still added a person

        return position;
    }

    public String remove_from_position(int position) {
        String removed = this.rankings[position];

        for ( int i=position+1; i<this.member_count; i++) {
            this.rankings[i-1] = this.rankings[i];
        }

        return removed;
    }

    /*
    Doing this encapsulates this behavior, but it doesn't simplify it...

    However, there's another way we could store these objects.

    Instead of making a block of contiguous rankings, what if we made a "wrapper" around
    our members, so that each one just knew about the next position:

   +------+  +-------+  +-------+
   | Juan |  | Serge |  | Donna |
   | next-----> next-----> next----->  ...
   +------+  +-------+  +-------+

   Then adding or removing could be done just by changing what the next person points to!

   Let's try this out by creating a RankingNode object and a RankingLinked class to manage
   the whole list.
     */


}
