public class LinkedRankings {

    //Here on our list, we need to store one person -- just the "head" of our list.
    //To get to later people in the list, we just follow the chain of "nexts":

    private RankingNode head; //<- the "first" thing in our list
    private int member_count;


    private RankingNode tail; // <- the "last" thing in our list


    public LinkedRankings() {
        //Unlike an array, we can hold any number of people in our LinkedRankings.
        // Each new person is just an extra 'next' pointer

        this.member_count = 0;
    }


    public void add_to_beginning(String member) {
        //Adding to the beginning is very easy -- when we change the beginning of
        //the list, we just change our head pointer, and make the new item point to the next thing!
        RankingNode new_member = new RankingNode(member, this.head);
        this.head = new_member;
        this.member_count++;
    }

    public void push_person_to_end(String member) {
        RankingNode node = new RankingNode(member); //<- this will have an empty 'next' pointer, because there's no one after this new person

        if ( this.head == null ) {
            this.add_to_beginning(member);
            this.tail = this.head;
        } else {
            this.head.push_to_end(node);
            this.member_count++;
            this.tail = node;
        }
    }


    /*
    We might notice that pushing to the end takes N steps, which is no better than our array was.

    Taking inspiration from add_to_beginning, we could also improve our push_to_end by keeping track of what's
    at the end of our linked list:
     */
    public void add_to_end(String member) {
        RankingNode new_node = new RankingNode(member);
        RankingNode previous_tail = this.tail;
        this.tail.setNext(new_node);
        this.tail = new_node;
        this.tail.setPrevious(previous_tail);
    }


    /*
    It turns out, removing from the beginning is equally easy:
     */

    public String remove_from_beginning() {
        RankingNode old_beginning = this.head;
        this.head = this.head.getNext(); //<- make the next thing the new head.
        return old_beginning.getMember();
    }

    public String remove_from_end() {
        RankingNode current_node = this.head;
        while ( current_node.getNext() != this.tail ) {
            current_node = current_node.getNext();
        }
        //After our loop, the .next of current node is the last thing. That means current_node is second to last (our new last)
        current_node.unlink_next_node();
        RankingNode previous_last = this.tail;
        this.tail = current_node;
        return previous_last.getMember();
    }
    /*
    We notice that again, the end of the list is requiring about N number of steps for a linked list with N things.

    We could fix this by "doubly-linking" our list -- if we stored both the "next" AND the "previous" entry on each node, then
    removing from the end could be done by going to the tail, and taking a path backwards to the previous node.

    However, this has started to show the weakness of linked lists -- they work really well when you are adding things to
    the ends of the list, but working from the wrong end can quickly become complicated.
     */


    //In fact, inserting into an arbitrary position has become even worse!
    public int insert_into_position(int position, String member) {
        int added_position = -1;
        RankingNode new_person = new RankingNode(member);
        if ( position == 0 ) {
            this.add_to_beginning(member);
            added_position = 0;
        } else if ( position >= member_count ) {
            this.add_to_end(member);
            added_position = this.member_count;
        } else {

            int current_slot = 0;
            RankingNode current_node = this.head;

            //Otherwise, keep going while we're not at the end and we haven't moved far enough
            while ( current_node.getNext() != null && current_slot < position-1) {
                current_node = current_node.getNext(); //<- go to the next node
                current_slot++; //<- count one further slot
            }

            //Once that loop ends, we'll be at the spot where we want to add the person. we can
            // call our insert here!
            current_node.insert_single_node_here(new_person);
            added_position = current_slot;
        }

        this.member_count++;

        return added_position;

    }

    public RankingNode getHead() {
        return head;
    }
}
