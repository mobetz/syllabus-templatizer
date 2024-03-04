

/*
An interface is not a class. Therefore, the first line
of our java file is going to be different:

*/


public interface IScoreable {/*
    Interfaces use the interface keyword, and often have names
    representing a possible action, like "Scoreable" or "Serializable"
    or "Moveable". Some programmers also like starting their name with
    "I" to indicate that they are an interface.


    Remember: an interface represents *behaviors* that any scoreable
	scoreable object *should* implement (not implementing them here.)
	Maybe we want any scoreable object to be able to give us the
	number of points it's worth and "activate" so that its points are enabled:

	*/


    public int getPoints();

    public boolean setCorrect();
    

	/*
	Notice, these methods DO NOT have curly braces. We're declaring methods,
	but we're not giving any indication of *how* these methods should work, just
	that they need to exist.

	Now we can make different types of items that are worth points!
	*/


    
}
