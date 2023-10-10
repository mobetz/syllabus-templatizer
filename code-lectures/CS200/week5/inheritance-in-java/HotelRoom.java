

import java.util.*;

public class HotelRoom extends Object {

    protected int capacity;
    protected double price;
    protected boolean hasKitchen;
    protected List<String> current_occupants;



    public HotelRoom(int capacity, double price, boolean hasKitchen) {
        this.capacity = capacity;
        this.price = price;
        this.hasKitchen = hasKitchen;
        this.current_occupants = new ArrayList<>();
    }

    public double getPrice(int nights) {
        return this.price * nights;
    }

    public boolean add_guest(String guest_name) {
        if (this.current_occupants.size() < this.capacity ) {
            this.current_occupants.add(guest_name);
            return true;
        }
        return false;
    }

    public boolean transfer_guests(HotelRoom other_room) {

        System.out.println("    - We're in the BASIC room transfer function");
        if ( other_room.current_occupants.size() <= this.capacity ) {
            this.current_occupants.addAll(other_room.current_occupants);
            other_room.current_occupants.clear();
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", reserved for: " + this.current_occupants;

        /*
        When we override a method like toString(), we are doing the exact same
        thing as any other @Override -- reimplementing a method that already exists.

        These "default methods" exist on a special class called Object (full name
        java.lang.Object). It's almost like every single class in Java has an implicit
        "extends Object" tacked on the end of its class declaration!

        It turns out inheritance is actually used very liberally in the Java standard
        libraries. Types like Scanners, Files, and many of the objects you use from
        Java inherit shared behaviors.
         */
    }



}