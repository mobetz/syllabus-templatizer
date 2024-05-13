public class Plant {
    

    private double age_years;
    private double amount_of_co2_filtered;


    public Plant(double amount_of_co2) {
        this.age_years = 0;
        this.amount_of_co2_filtered = amount_of_co2;
    }

    public double reduce_co2_from(double initial_amount) {
        return initial_amount - amount_of_co2_filtered;
    }
}
