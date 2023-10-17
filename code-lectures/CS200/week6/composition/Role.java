

public class Role {


    //ATTRIBUTES
    protected int id_num;
    private static int employees_id_generator = 1000;

    //METHODS
    public Role() {
        this.id_num = employees_id_generator++;
    }


    public int getIdNum() {
        return id_num;
    }


    public double getSalary() {
        return 0;
    }

    public double getHoursWorked() {
        return 0;
    }



}