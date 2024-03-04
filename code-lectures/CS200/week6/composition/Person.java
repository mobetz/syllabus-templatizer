
public class Person {
    
    private String name;
    protected Job job;

    public Person(String name) {
        this.name = name;
        this.job = Job.UNEMPLOYED; //<- this placeholder role will ensure we have *something*, but it doesn't represent a real job

        /*
         * It would also be valid to say when you create a Person, you need to tell me what their job is as 
         * an attribute, and assign it:
         *   this.job = given_job;  //<- given_job is added as a parameter to the constructor
         */
    }

    public String getName() {
        return name;
    }

    public boolean set_job(Job new_job) {
        this.job = new_job;
        return true;
    }

    public boolean is_employed() {
        return false == this.job.equals(Job.UNEMPLOYED);
    }


    //Public methods are our "interface" for our object. If some behavior we need isn't on THIS class, but
    // on a composed inner attribute.... I need to add a method that allows me to see that behavior from inside:

    public double getJobSalary() { //<- this function will allow me to have LIMITED access to the relevant parts of the inner class
        return this.job.getSalary(); //<- here we're forwarding the information from the object to people who have a Person

    }

}
