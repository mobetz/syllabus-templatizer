
/*
What if we want to join multiple interfaces together to create an idea of something
that will have many behaviors, like IScoreable and IPromptable, or we want to make
a base class where we don't implement all of the methods on the base type right away. 

We can do this with an abstract class!

*/

public abstract class Quizzable implements IScoreable, IPromptable {
	


	/*
	Much like interfaces, abstract classes are allowed to declare methods without
	implementing them. However, they can also have implementations if they want.
	*/

	public abstract String displayText(); 
	/* When we create a method stub in an abstract class, we need to signify it's
	unimplemented by using the "abstract" keyword, so Java knows we didn't just forget
	to implement it.*/



	public String toString() {
		return this.displayText();
	}
	/*
	Much like normal base classes we can write normal methods. However, this means
    abstract classes are susceptible to the diamond problem, so we cannot multiply
    inherit them.
    */

}
