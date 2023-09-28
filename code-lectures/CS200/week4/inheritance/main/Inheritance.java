

/*
Objectives for Today

By the end of today, you will:
    * Define the concept of inheritance in object oriented programming.
    * Identify the extends, super, and protected keywords.
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
package main;


import types.Freshman;
import types.Student;
import types.Person;
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


        Student a_student = new Student("Jane", 22);

        System.out.println(a_student);

        /*
        Instead, we can use the "extends" keyword in our Student class to
        indicate that we want Student to be a *kind* of Person.


         However... there's a snag. We still need to call Person's constructor
         *inside* Student's constructor in order to tell Java how to set up the Person.
         Otherwise, Java will crash when we try to run our program.

         We can even have a subtype of our subtype:
         */
        Freshman freshman = new Freshman("Billy",  "Prof. Obetz");


        /*
        Now that we've inherited the abilities of a Person, our Student
        can still do everything a Person can do:
         */

        freshman.have_birthday();//<- even though Freshman and Student doesn't implement have_birthday(),
                                 // we can use Person's implementation.
        System.out.println(freshman); //<- same for printing a Freshman


        /*
        At the same time, we can do all the student-specific things with a_student:
        */

        freshman.add_class("CS110");
        freshman.add_class("CS118");

        System.out.println(freshman.list_classes());


        /*
        However... what if we try to use a method that exists on both Student and
        Person, like generate_weekend_plans:
         */
        String whose_plans = freshman.generate_weekend_plans();
        /*
        When we actually print this out...
         */

        System.out.println("Our plans are: " + whose_plans);

        /*
        ...we see that Freshman's plans "win" because they are more specific.

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

        Person covert_freshman = freshman; //<- Student data fits in Person-shaped spaces

        System.out.println("Even as a Person, the freshman is still: " + covert_freshman);

        //covert_freshman.add_class("CS200"); //<- why isn't this valid???

        /*
        Dynamic Dispatch has an unfortunate second rule: We can only assume we are a class that is
        'above' our current variable type. Because of this, our Person variable can't see
        any methods that only exist on Student or Freshman.

        This makes sense -- a Person variable *could* be holding a Student,
        or it *could* be holding a Person. If we're using something like a pair
        of if statements, it could even be holding either by the time we get to
        where a method is called:
         */

        Person could_be_student;

        if ( Math.random() > 0.5 ) {
            could_be_student = new Person("Janet", 23);
        } else {
            could_be_student = new Freshman("Rachael", "Prof. Moussavi");
        }


        //could_be_student.add_class("CS241"); //<- this only makes any sense on one branch



        /*
        Since Java can't validate that a method will even exist for your object,
        it disallows the method call completely to avoid crashing.


        What about if we generated our weekend plans for our "covert_student" Person
        that's actually a Student, that's actually a Freshman:
         */
        String weekend_plans = covert_freshman.generate_weekend_plans();
        System.out.println("Covert student's weekend plans:" + weekend_plans);
        /*
        Calling this method is okay because both Student and Person have a
        weekend plans function, so Java is sure there's always *something* to call.

        Remember: Dynamic Dispatch starts with the CONSTRUCTED TYPE (after checking the method
        is valid for all subtypes.) This means that our Person variable that's holding a Freshman
        will still use Freshman's version of generate_weekend_plans() (because that was the constructed
        object.)


        Even more interesting, even though our covert_freshman is currently saved
        in a Person, Java still remembers that it was once a Freshman, so it will
        use Freshman's version of the method. In fact, we can even have Java tell
        us what kind of object Freshman is using a technique called "reflection":
         */

        System.out.println("Covert_freshman is a: " + covert_freshman.getClass().getName());

        /*
        ...However, doing this is very inefficient and bad for many reasons,
        and we should avoid it whenever possible. (More on that in our pitfalls
        lecture.)
         */

        /*
        One last thing I want to discuss is how the "this" and "private" keywords
        interact between Student and Person, so let us return to Student.java.
         */

        System.out.println(freshman.generate_student_id());
        //System.out.println(freshman.name); //<- we can't access freshman.name here, because we're in a different package

    }
}


