
public class HotelSuite extends HotelRoom {

    private String suite_name;

    public HotelSuite(String name) {
        super(4, 400, true); //<- maybe we already know hotel suites cost $400, house 4, and have a kitchen
        this.suite_name = name;
    }



    /*
    When we want to change the behavior of a superclass method, we create a method with the exact same signature
    in our subtype and write a new implementation. We're able to use the original implementation with super.methodName()

    If we want to be extra explicit, we can put @Override above this method to indicate that it should hide an existing
    method.
     */
    @Override
    public double getPrice(int nights) {
        double base_price =  super.getPrice(nights);
        double suite_upcharge = 50.0;
        return base_price + suite_upcharge;
    }


}