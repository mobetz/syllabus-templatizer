public class DummyList {
    
    private int[] slots; //<- an ARRAY list still internally stores an array for the list elements


    /*
    However, we're going to need to keep track of a few other numbers so we can do the 'magic'
    resizing that lists are known for:
     */
    private int capacity;
    private int currently_stored;


    public DummyList() {

        this.currently_stored = 0; //<- lists always start empty, with 0 things inside.
        this.capacity = 5; //<- we will create some sane default size
        this.slots = new int[this.capacity];
    }


    private void doubleSize() {
        /*
         * First, make a new array that's twice as big:
         */

         this.capacity = this.capacity * 2;
         int[] new_slots = new int[this.capacity];

         for ( int i=0; i<this.currently_stored; i++ ) {
            new_slots[i] = this.slots[i];
         }

         this.slots = new_slots;

    }

    public int get(int index) {
        if ( this.currently_stored > index ) {
            return this.slots[index];
        }
        return -1;
    }


    public void add(int next_thing) {


        if ( this.capacity == this.currently_stored ) {
            doubleSize();
        }

        this.slots[this.currently_stored] = next_thing;
        this.currently_stored = this.currently_stored + 1;

    }

    public int size() {
        return this.currently_stored;
    }


}
