public class MaybeCoordinate {

    Coordinate contents;

    public MaybeCoordinate(Coordinate contents) {
        this.contents = contents;
    }

    public boolean hasContents() {
        return this.contents != null;
    }
    
    private Coordinate unwrap() {
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
    public Coordinate unwrap_or_else(Coordinate backup) {
        if ( this.contents != null ) {
            return unwrap();
        } else {
            return backup;
        }
    }

    /*
     By the time we get this far in the class, we might realize.... nothing about a "Maybe" coordinate
     actually has anything to do with a Coordinate. In fact, a Maybe answer would be equally useful
     pretty much any time we have a value that *might* be null. 
     
     This is the way to identify a potential generic class: look for behaviors where you care about the
     "meta"-operation of moving data around, not about any features of the data itself.

     We could write this generically as a Maybe<Anything>
     */
     


}
