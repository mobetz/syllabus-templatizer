public class Worker {

    //ATTRIBUTES
    protected int id_num;
    protected String name;
    private static int employees_id_generator = 1000;


    //METHODS
    public Worker(String name) {
        this.name = name;
        this.id_num = employees_id_generator++;
    }


    public int getIdNum() {
        return id_num;
    }


    public double getSalary() {
        return 0;
    }



}