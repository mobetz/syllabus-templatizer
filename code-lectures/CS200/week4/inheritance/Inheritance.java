
/*
Objectives for Today

By the end of today, you will:
    * Define the concept of inheritance in object oriented programming.
    * Identify the extends, super ,and protected keywords.
    * Define dynamic dispatch.
    * Understand how Java selects the implementation of a method when called.
 */

/*
Vocabulary for the Day

Inheritance - Inheritance refers to the act of creating a "subtype" of an existing
type using the extends keyword. When we create a subtype, we refer to its
origin class as the "supertype".

Dynamic Dispatch - Dynamic Dispatch refers to the rules Java uses to discover
what function to execute when a method is called.
 */

public class Inheritance {
    
    public static void main(String[] args) {
        /*
        One of the earliest things we learned in Java was how to create classes
        that bundle state and behaviors. When we create a class, we are defining
        a new type that we can use in the rest of our program:
         */
        
        Person someone = new Person("John", 20);
        
        /*
        However, what if we wanted a class that works very similarly to this one,
        like a Student that is a special version of a Person.
        
        Right now, we would need to cut-and-paste all of the existing code
        to serve as the "starting point" for our Student.
         */
        
        Student a_student = new Student("John", 20);
        
        /*
        Instead, we can use the "extends" keyword in our Student class to
        indicate that we want Student to be a *kind* of Person.
        

         However... there's a snag. We still need to call Person's constructor
         *inside* Student's constructor in order to tell Java how to set up the Person.
         Otherwise, Java will crash when we try to run our program.
         */
        
        
        /*
        Now that we've inherited the abilities of a Person, our Student
        can still do everything a Person can do:
         */
        
        a_student.have_birthday();//<- even though Student doesn't implement have_birthday(),
                                  // we can use Person's implementation.
        System.out.println(a_student); //<- same for printing a Student
        
        /*
        At the same time, we can do all the student-specific things with a_student:
*/
        a_student.add_class("CS200");
        a_student.add_class("CS213");
        
        System.out.println(a_student.list_classes());
        
        /*
        However... what if we try to use a method that exists on both Student and
        Person, like generate_weekend_plans:
         */
        
        String whose_plans = a_student.generate_weekend_plans();
        
        /*
        When we actually print this out...
         */
        
        System.out.println("Our plans are: " + whose_plans);
        
        /*
        ...we see that Student's plans "win" because they are more specific.
        
        This is a special feature of object oriented languages called Dynamic
        Dispatch. Since Java doesn't know what class holds a method definition,
        it actually waits until runtime to decide which method it wants to call.
        
        It will start from the class of the current object for a method with this
        name that can be called with these arguments, then check the supertype,
        then that type's supertype... until it gets to the prototypical Java
        Object.
        
        (Fun sidenote: this is why "static" methods are called static -- when
        you call a static method, it always refers to the exact same function
        and Java doesn't have to figure anything out dynamically.)
        
        
        Another fun feature of our Student class is that a Student is still
        a valid Person, and we can save a Student into a Person type variable:
         */
        
        Person covert_student = a_student; //<- Student data fits in Person-shaped spaces
        
        /*
        However... when we do this, we lose access to the Student-specific behaviors:
         */
        
        //covert_student.add_class("CS241"); //<- why isn't this valid???
        
        
        /*
        This makes sense -- a Person variable *could* be holding a Student,
        or it *could* be holding a Person. If we're using something like a pair
        of if statements, it could even be holding either by the time we get to
        where a method is called:
         */
        Person could_be_student;
        
        if ( Math.random() > 0.5 ) {
            could_be_student = new Person("Jane", 21);
        } else {
            could_be_student = new Student("Janet", 22);
        }
        
        // covert_student.add_class("CS241"); //<- this only makes any sense on one branch
        
        
        /*
        Since Java can't validate that a method will even exist for your object,
        it disallows the method call completely to avoid crashing.
        
        What about if we generated our weekend plans for our "covert_student" Person
        that's actually a Student:
         */
        
        
        String weekend_plans = covert_student.generate_weekend_plans();
        
        System.out.println("Covert student's weekend plans:" + weekend_plans);
        
        /*
        Calling this method is okay because both Student and Person have a
        weekend plans function, so Java is sure there's always *something* to call.
        
        Even more interesting, even though our covert_student is currently saved
        in a Person, Java still remembers that it was once a Student, so it will
        use Student's version of the method. In fact, we can even have Java tell
        us what kind of object Student is using a technique called "reflection":
         */
        
        System.out.println("Covert_student is a: " + covert_student.getClass().getName());
        
        /*
        ...However, doing this is very inefficient and bad for many reasons,
        and we should avoid it whenever possible. (More on that in our pitfalls
        lecture.)
         */
        
        
        /*
        One last thing I want to discuss is how the "this" and "private" keywords
        interact between Student and Person, so let us return to Student.java.
         */
        System.out.println(a_student.print_id());
        
        
    }
}