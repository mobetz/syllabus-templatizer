import java.util.function.Function;

public class Maybe<T> {

    T contents;

    public Maybe(T contents) {
        this.contents = contents;
    }

    public boolean hasContents() {
        return this.contents != null;
    }
    
    private T unwrap() {
        return this.contents; //<- this would be unsafe, because we might be "leaking" that null back to the rest of the program!
    }



    /**
     *  SAFELY unwrap a Maybe. When you call this function, you must provide a "backup" value that
     * will be used instead of the contents when the contents are null. This ensures that methods can
     * always be called on the object returned by this function without crashing the program.
     * 
     * @param backup The default value to use when the Maybe has a null
     * @return the value contained in the maybe, or the backup when the maybe was null
     */
    public T unwrap_or_else(T backup) {
        if ( this.contents != null ) {
            return unwrap();
        } else {
            return backup;
        }
    }

    /*
      
     If we decide that unwrap doesn't fully satisfy our desire for a safely, we can go a step further.
     Instead of having people "unwrap" the value, we can have them manipulate the value inside the maybe.

     However, this gets a bit complicated.... If they do this, then we still can't be sure they have a valid
     value -- if it was null, the operation will need to be skipped entirely. Furthermore, it means what was
     a maybe with one type of value can turn into a maybe with another type of value (e.g imagine we wanted
     to call the distance function on our Coordinate. Before we maybe have a coordinate, but now we maybe have
     a Double, or still null if the coordinate was originally null.)

     Fortunately, there's an interface that can help us with this: The Function<InputType, ReturnType> interface lets
     us say that we will accept a function that takes InputType and gives back a ReturnType. Then, we can call
     .apply() on the function to call it!

     However, there's a problem... We know the input type will be the type of our contents, but we don't actually know 
     return type (the user could pass us a function that returns *anything*...)

     This means we need to introduce another type parameter!
     */

     public <U> Maybe<U> ok_then(Function<T, U> func) {
        if ( this.hasContents()) {
            return new Maybe<U>(func.apply(this.contents));
        } else {
            return new Maybe<U>(null);
        }
     }



    /*
      This type of class is called a "monad" -- we've taken a dangerous thing (a possible null value),
      and created a whole separate universe of state that this thing lives in. Forcing people to only
      interact with this dangerous state using our few state functions allows us to guarantee that this
      dangerous thing is only handled safely.
     */

}
