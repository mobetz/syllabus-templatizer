public class Chef extends Job {

    //ATTRIBUTES
    private String station_worked;


    public Chef(String station) {
        this.station_worked = station;
    }


    @Override
    public double getSalary() {
        if ( this.station_worked.equals("cold prep")) {
            return 30000;
        } else {
            return 35000;
        }
    }
    
}
