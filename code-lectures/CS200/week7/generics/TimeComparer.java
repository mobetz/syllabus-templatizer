
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class TimeComparer<T extends Temporal> {

    T held_time;

    public TimeComparer(T starting_time) {
        this.held_time = starting_time;
    }

    public long until(Temporal other_time, ChronoUnit unit_of_measure) {
        /*
        Now, even though we don't know what kind of time we were given, we know
        that the time is a T, which is *some* kind of Temporal, so it must support until()!

        This means we can still call those methods in our comparer, even though T is unspecified:
         */
        return this.held_time.until(other_time, unit_of_measure);
    }

    /*
    There is one last way we can use generics -- we can use them directly in method declarations.
    Say we wanted to write a static method that can compare any two types of times. We could write
    this generically:
     */

    public <U extends Iterator<V>, V extends TemporalAmount> List<T> add_self_to_each(U group_of_times) {
        List<T> ending_times = new ArrayList<>();

        while ( group_of_times.hasNext() ) {
            V next = group_of_times.next();
            ending_times.add((T) this.held_time.plus(next));
        }

        return ending_times;

    }

}
