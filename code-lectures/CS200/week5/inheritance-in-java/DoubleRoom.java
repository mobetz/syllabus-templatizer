

public class DoubleRoom extends HotelRoom {


    public DoubleRoom() {
        super(2, 250, false);
    }

        /*
        Maybe we decide double bed rooms can only be moved to other double beds (we can't give them a better
        room than they paid for.)

        This is a more specific type than our original parameter, so we can no longer @Override!
         */
    public boolean transfer_guests(DoubleRoom other_room) {
        this.current_occupants.addAll(other_room.current_occupants);
        other_room.current_occupants.clear();
        System.out.println("   - Moving people from one DOUBLE to another!");
        return true;
    }


}