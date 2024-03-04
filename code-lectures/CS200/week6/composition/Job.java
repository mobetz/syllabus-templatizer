public class Job {
    
    //ATTRIBUTES
    protected int id_num;
    private static int employees_id_generator = 1000;

    public static Job UNEMPLOYED = new Job();

    //METHODS
    public Job() {
        this.id_num = employees_id_generator++;
    }



    public double getSalary() {
        return 0;
    }
}
