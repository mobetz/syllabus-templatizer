
/*
Generic classes are created with one or more generic types in <angle brackets>, like ObjectHolder<T>:
 */

public class ObjectHolder<T> {
                     /*   ^
                      This "T" here is a generic type. We don't know what it actually represents
                      */

    private T held_object; //<- even though I don't know what a T is, I can say I have one stored on my class.

    public ObjectHolder(T thing_to_hold) { //<- I don't know what kind of thing I'm being given, but I know it's the type of
                                          // thing that I know how to store (because T is the same here and on the attribute.)

        this.held_object = thing_to_hold; //<- even though I don't know the type yet, this MUST be valid.

    }


    public T getHeld() {
        T returned_object = this.held_object; //<- I can even assign to local T variables....
                                              //...but since I don't know what kind of thing T is, I can't call methods on it
        return returned_object; //<- similarly here, I'm returning a thing of type T, whatever that is.
    }
}
