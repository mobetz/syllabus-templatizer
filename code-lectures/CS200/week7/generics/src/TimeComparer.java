import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TimeComparer<T extends Temporal> {
    /*
    At the top here, "extends Temporal" is placing a constraint on T. The only types that will be valid for T are those
    that implement the Temporal interface.
     */
    private T held_time;


    public TimeComparer(T starting_time) {
        this.held_time = starting_time;
    }


    public long until(Temporal other_time, ChronoUnit unit_of_measure) {
        /*
        Remember, we said we can't assume ANYTHING about the type T... However, here we need to call methods related
        to time on T. This means we need to constrain "T" -- T can't be any old type, it specifically needs to be a type
        that supports the Temporal interface. The way I do this is by providing a type constraint on the class declaration.

        Now, even though we don't know what kind of time we were given, we know
        that the time is a T, which is *some* kind of Temporal, so it must support until()!

        This means we can still call those methods in our comparer, even though T is unspecified:
         */


        return this.held_time.until(other_time, unit_of_measure);
    }

    /*
    There is one last way we can use generics -- we can use them directly in method declarations.

    Say we wanted to write a static method that can add this amount of time to any other group of time amounts,
    then return the type that I am.

     */

    public <U extends Iterator<V>, V extends TemporalAmount> List<T> add_self_to_each(U group_of_times) {

        List<T> ending_times = new ArrayList<>();

        while (group_of_times.hasNext()) {
            V next = group_of_times.next();
            ending_times.add((T) this.held_time.plus(next));
        }

        return ending_times;

    }

    /*
    One fun fact: you can actually write an "extends" type constraint that intersects two or more constraints.
    For example, if I want something for type T that is both IAddable and ISubtractable, I could say <T extends IAddable & ISubtractable>.

     */
}
