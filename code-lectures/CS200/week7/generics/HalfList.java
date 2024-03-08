import java.util.Iterator;
import java.util.List;

public class HalfList implements Iterator {

    //ATTRIBUTES
    List<Integer> inner_list;
    private boolean evens_only;
    private int current_position;

    

    //METHODS
    public HalfList(List<Integer> original_elements) {
        this.inner_list = original_elements;
        this.evens_only = true;
        this.current_position = 0;
    }

    public HalfList(List<Integer> original_elements, boolean prioritize_evens) {
        this.inner_list = original_elements;
        this.evens_only = prioritize_evens;
        this.current_position = (prioritize_evens) ? 0 : 1;
    }

    /*
    We might decide our HalfList has methods like next(), hasNext(), similarly to a Scanner.

    It turns out, there's an interface for exactly this: Iterator!
     */

     @Override
     public Integer next() {
        int returned_item = this.inner_list.get(this.current_position);
        this.current_position += 2;

        return returned_item;
     }


    @Override
    public boolean hasNext() {

        int is_odd_offset = this.inner_list.size() % 2;
        is_odd_offset = is_odd_offset + (( this.evens_only ) ? 0 : 1);
        int last_index = this.current_position + is_odd_offset;

        boolean past_the_end_of_the_list = this.inner_list.size() >= last_index;
        return past_the_end_of_the_list;
    }
}
