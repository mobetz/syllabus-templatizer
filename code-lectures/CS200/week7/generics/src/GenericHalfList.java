import java.util.Iterator;
import java.util.List;

/*
To make a class generic, we have to add one extra thing to our class declaration: a generic type.

Conventionally, we use the letter "T" to represent our generic Type, and enclose it in <brackets> (just like when
we create a List!):
 */
public class GenericHalfList<T> implements Iterator<T> {

    /*
    Once we've declared our generic type, we're allowed to use that type as if it were any other type inside our
    class:
     */


    //ATTRIBUTES


    private List<T> inner_list; //<- my list is a list of "Any T type" objects
    private boolean evens_only;
    private int current_position;


    //METHODS

    public GenericHalfList(List<T> original_elements) { //<- someone gives me a list of any "T" type when they make my halflist
        /*
        Notably, the "T" in this given list of original elements matches the T in my attribute -- Java will enforce that
        you're receiving something the same type as your attribute.
         */
        this.inner_list = original_elements;
        this.evens_only = true;
        this.current_position = 0;
    }
    public GenericHalfList(List<T> original_elements, boolean prioritize_evens) {
        this.inner_list = original_elements;
        this.evens_only = prioritize_evens;
        this.current_position = (prioritize_evens) ? 0 : 1;
    }



    public T next() { //<- when someone calls next, they get back a T
        T returned_item = this.inner_list.get(this.current_position);//<- even in my methods, I can make "T" type variables

        /*
        Note: the only thing java knows about our "returned_item" variable is that it's the same type of thing as our
        list. This means we can't call *any* methods on it:
         */
        //int sum = 5 + returned_item; //<- even if we have a list of ints, Java doesn't know this is valid!!!
        this.current_position += 2;
        return returned_item;
    }


    public boolean hasNext() {
        int index_offset = this.inner_list.size() % 2;
        index_offset = index_offset + (( this.evens_only ) ? 0 : 1);
        int last_index = this.current_position + index_offset;

        return this.inner_list.size() >= last_index;
    }
    /*
    Type parameters can also be applied to interfaces like Iterator. There's an Iterator<T> interface that will
    preserve our type information!
     */
}
