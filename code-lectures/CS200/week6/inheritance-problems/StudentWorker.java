public class StudentWorker extends Student, Worker {//<- we would have to extend BOTH Student and Worker to get the behavior we want
    /*
    We can already see the IDE complaining, but let us keep implementing the class to see what happens
     */


     //ATTRIBUTES

    /*
    Remember: each attribute from a supertype is inherited into the subtypes. This means we implicitly have
    a Student.id_num, a Worker.id_num, a Student.name, a Worker.name.....

    Which one of these would this.name actually point to?!?!
     */


     //METHODS

    public StudentWorker(String name) {
        /*
        Immediately there's a problem: I need to call super() on my base types...
         */
        super(name);  //<- both Student and Worker have constructors... which one am I calling here?
    }



    public String toString() {
        /*
        However, there are more subtle problems as well... What if I refer to an attribute or method with 'this':
         */
        int id = this.getIdNum(); //<- both student and worker have IDs and a getIdNum(), which function will be called?
        // Will I get a 100x worker id, or a 800x student id?!?!
        return "Student is called " + this.name + " and has ID " + this.id_num; //<- same here... which name and id_num will I get?

    
}



/*
Because of all this ambiguity, Java ENTIRELY DISALLOWS this type of "multiple inheritance" where we are extending more
than one class.
 */