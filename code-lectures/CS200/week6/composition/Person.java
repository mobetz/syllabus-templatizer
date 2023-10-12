

public class Person {

    private String name;

    private Role job;

    public Person(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }


    public boolean set_job(Role new_job) {
        this.job = new_job;
    }

    public double getSalary() {
        return this.job.getSalary();
    }

}