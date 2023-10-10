/*
Objectives for Today

By the end of today, you will:
    * Identify the prototypical base object: java.lang.Object
    * Describe the function of the @Override annotation
    * Examine examples of inheritance that exist in Java standard libraries.
    * Further practice applying inheritance to solve problems.
 */


public class InheritanceInJava {

    public static void main(String[] args) {
        /*
        We have been practicing creating new classes with the 'extends' keyword. When we extend a base type, we
        create a new subtype that has access to all the methods and attributes of its parent type.
        */
        HotelSuite room1 = new HotelSuite("Waterfall Room");

        room1.add_guest("Bob");
        room1.add_guest("Sally");

        /*
        We've also seen that we can implement the same methods on different parts of an inheritance tree, and Java
        will dynamically dispatch to the most specific available method for our objects.
         */

        HotelRoom unknown_room_type = room1;
        System.out.println("The price for two nights: " + unknown_room_type.getPrice(2));

        /*

        This @Override completely replaces the original method on HotelRoom for any double
        rooms we create. Any time the normal method would be executed on this object, this
        method gets executed instead. Overriding a method "hides" the original implementation.

        However... there's somewhere else we've seen @Override before...


        toString(), equals(), and hashCode() can use @Override. So what exactly are they
        hiding?


        Let's go implement one on HotelRoom and use our IDE to find out!

         */



        /*
        However, what happens when we use our various types with our non-overridden method:
         */

        DoubleRoom first = new DoubleRoom();
        DoubleRoom second = new DoubleRoom();
        second.add_guest("Jim");
        second.add_guest("Sally");

        System.out.println("Before the swap: " + first + ", " + second);

        first.transfer_guests(second);  ///<- this uses DoubleRoom's version of transfer_guests()

        System.out.println("After the swap: " + first + ", " + second);


        /* What if we did the same thing, but first turned the room we're transferring INTO into a HotelRoom?

         */

        first = new DoubleRoom();
        second = new DoubleRoom();
        second.add_guest("Jim");
        second.add_guest("Sally");

        HotelRoom second_as_general_room = second;
        System.out.println("Even though second is now a HotelRoom, the price is still: " + second_as_general_room.getPrice(1));
        //^ this still uses DoubleRoom's getPrice, but...

        System.out.println("Before the swap: " + first + ", " + second);

        first.transfer_guests(second_as_general_room);//<- this uses HotelRoom's version of transfer_guests, even though room1 is
                                                      // a DoubleRoom!

        System.out.println("After the swap: " + first + ", " + second);

        /*
        Because transfer_guests() is no longer an @Override, it is going to use whatever type a
         parameter is *currently* cast as, not what type it originally had. This is the opposite of
        what happened with get_price() -- get_price() treated guest_room as a DoubleRoom even
        when we had cast it.

        Because remember -- we can't safely know that HotelRoom is going to be a DoubleRoom here (the object
        is a parameter, so we could be passing anything in.)

            What about the other direction?
         */


        first = new DoubleRoom();
        second = new DoubleRoom();
        second.add_guest("Jim");
        second.add_guest("Sally");

        HotelRoom first_as_general_room = first;

        System.out.println("Before the swap: " + first + ", " + second);

        first_as_general_room.transfer_guests(second); // this is a ((HotelRoom) DoubleRoom).transfer_guests(DoubleRoom)

        System.out.println("After the swap: " + first + ", " + second);


        /*
        We're *still* in the basic room transfer function!

        transfer_guests() does not *hide* the original method. That means we follow the type the object is currently
        cast as and only look for methods there.

        One way to think about this is that Overridden methods completely replace and "delete" the old method on an
        object. When we try to access the method only the new version from our subclass remains, squatting in the old
        space left behind. OverLOADED methods, on the other hand, work just like our function examples from previous
         lectures -- they can only access the methods visible to their current object type, and can't make any assumptions
         about the subtypes of parameters.


         */


    }
}