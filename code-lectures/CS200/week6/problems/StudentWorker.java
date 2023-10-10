public class StudentWorker extends Student,Worker { //<- we would have to extend BOTH Student and Worker to get the behavior we want
    /*
    We can already see the IDE complaining, but let us keep implementing the class to see what happens
     */

    public StudentWorker(String name) {
        /*
        Immediately there's a problem: I need to call super() on my base types...
         */
        super(name); //<- both Student and Worker have constructors... which one am I calling here?
    }

    public String toString() {
        /*
        However, there are more subtle problems as well... What if I refer to an attribute or method with 'this':
         */
        int id = this.getIdNum(); //<- both student and worker have IDs and a getIdNum(), which function will be called?
        return "Student is called " + this.name + " and has ID " + this.id_num; //<- same here... which name and id_num will I get?
    }
}
