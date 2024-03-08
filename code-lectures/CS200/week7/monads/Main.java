
/*
 Objectives for Today

 By the end of today, you will:
     * Get more practice with generic classes
     * Identify situations in which generic classes provide benefit
     * Define "monads" 
     * Understand how monads can help us encapsulate dangerous or uncertain behavior


Vocabulary of the Day

Monad -- A monad is a programming construct that "wraps" some data in an object, and exposes functions
that allow users to inspect or interact with that state in a safe capacity. Monads are typically used
to hide dangerous or uncertain operations and protect the rest of the program from undesirable side effects. 


*/



public class Main {

    public static void main(String[] args)  {
        
        /*
         We've seen how generic classes help us create objects that work with any arbitrary data type. To help us
         see another way we can use them, we're going to re-derive a classic programming construct: the Optional monad.

         To start us off, We're going to create a basic object with the ability to parse itself from text: a Coordinate class.

         Coordinates can be built from text in the format "x,y":

         */
        Coordinate point_a = Coordinate.fromText("4,4");
        Coordinate point_b = Coordinate.fromText("0,8");


        /*
           They've got basic functions for operations like calculating offset or distance: 
         */
        double distance = point_a.distanceFrom(point_b);
        System.out.println(point_a + " is " + distance + " from " + point_b); // 4^2 + 4^2 = c^2.  c = sqrt(32) = 4 * sqrt(2) = 5.6...


        /*
           However... parsing from text is always dangerous! If the operation fails, our typical way to 
           handle this is by returning null. But there's a problem -- doing operations on a value that *might*
           be null is unsafe:
         */
        Coordinate invalid_point = Coordinate.fromText("five;5");
        //Coordinate offsets = invalid_point.offsetFrom(point_b); //<- this will crash anytime we fail to parse!


        /*
           The solution we've always used is to put a null checking if statement before the future operation
           we want to do:
         */
        Coordinate offsets = null;
        if ( invalid_point != null ) {
            offsets = invalid_point.offsetFrom(point_b);
        }
        System.out.println("The offset from " + invalid_point + " to " + point_b + " is " + offsets );

        /*
          However.... this can get messy. Especially if we're doing a pipeline of operations where each
          depends on the last in different parts of the program, we're going to pollute the entire codebase
          with null checks!
         */
        if ( offsets != null ) {
            Coordinate normalized_offset = offsets.normalizedCoordinate();
            System.out.println("Normalized to lengths of 1, the directions are " + normalized_offset);
        }

        /*
        If we ever forget one, we go right back to crashing! 
        What if we made a class that encapsulates "maybe" getting an answer!
        */

        Maybe<Coordinate> shroedingers_point = Coordinate.betterFromText("5,5");

        //To actually use the coordinate inside, we would need to 'unwrap' the point...
        Coordinate point = shroedingers_point.unwrap_or_else(new Coordinate(0, 0));
        //...but then we have the same null-checking problem again if any operation might fail.


        //Instead, we can keep it in its wrapper and keep translating it!
        Maybe<Coordinate> maybe_offset = shroedingers_point.ok_then((when_point) -> { return point.offsetFrom(point_a)});

        //This allows me to keep this point in its "maybe" wrapper, and only unwrap it when I need to use the value directly.
        //This even works when the function changes the type:

        Maybe<Double> maybe_distance = shroedingers_point.ok_then((when_point) -> { return point.distanceFrom(point_b)});

        /*
          When the computer actually executes this program, it will "skip" all the code for null values, because of the way ok_then() was written.

          This allows us to create conditional operations on a point, and if any single step of that pipeline of operations fails,
          everything else gets skipped without the need for if statements.
         */



        /*
          It turns out, there's a class that handles this in Java already! The Optional<> class:
         */

         Optional<Coordinate> c = Optional.of(new Coordinate(5, 5)); //<- of() creates the Optional
         Optional<Coordinate> d = c.map((inner_pt) -> { return inner_pt.offsetFrom(point_b);}); //<- we can manipulate it with map
         Optional<Double> e = d.map((inner_pt) -> { return inner_pt.distanceFrom(point_b);}); 
 
         Double answer = e.orElse(0.0); //<- and unwrap it with orElse()!
 
         






    }
}