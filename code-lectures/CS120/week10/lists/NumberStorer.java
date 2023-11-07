
/*
We've made several different classes that all have an array of things, and a "counter"
that counts up every time we add a thing, using the current value of the counter to 
decide what slot to push something into:
*/

public class NumberStorer {


	//ATTRIBUTES
	private int[] arr;
	private int count;



	//METHODS

	public NumberStorer() {
		this.arr = new int[25];
		this.count = 0; //<- originally, there's nothing in the list so far
	}


	public void add(int new_num) {
		this.arr[this.count] = new_num; //<-add the thing into the first empty slot
		this.count = this.count + 1; //<- now there is one more thing stored, point to the *new* empty slot

	}





	/*
	If we wanted to, we could extend this even further with methods to remove something
	or find something in a particular position:
	*/

	public void remove(int slot_num) {

		/*
		To "remove" something from an array, we would just need to 
		slide everything after it forward one position in the array:
		*/
		for (int i = slot_num+1; i<this.count; i++) { //<- start at the slot being removed, go to one after the first empty slot
			this.arr[i-1] = this.arr[i];  ; //<- take the next slot, move it back into the current slot 

		}


		this.count = this.count - 1;

	}



	public int get(int slot) {
		return this.arr[slot];
	}


	public int size() {
		return this.count;
	}


}