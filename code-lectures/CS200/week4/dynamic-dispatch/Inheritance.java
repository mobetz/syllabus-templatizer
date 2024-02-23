

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

import types.Freshman;
import types.Person;
import types.Student;

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

         a_student.has_birthday();
         a_student.add_course("CS200");
         System.out.println(a_student + ", courses taken: " + a_student.list_courses());


         /* 
         We can even have a subtype of our subtype:
         */
        Freshman freshman = new Freshman("Billy",  "Prof. Obetz");

        /*
         * Freshman has access to Freshman specific methods:
         */
        freshman.getAdvisor();

        /*
         * As well as Student specific things:
         */
        freshman.add_course("CS110");

        /*
         * ...and even Person things:
         */
         freshman.has_birthday();


        /*
        However... what if we try to use a method that exists on both Student and
        Person, like generate_weekend_plans:
         */
        String whose_plans = freshman.generate_weekend_plans();
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

         Person covert_freshman = freshman; //Student data fits in Person-shaped spaces

         System.out.println("As a Person, the freshman's data is: " + covert_freshman );
         System.out.println("His plans are: " + covert_freshman.generate_weekend_plans());

         /*
          * Even though the Freshman object is saved in a Person variable, Java still "remembers"
          that it was originally created as a Freshman. It will still start looking for dynamic dispatch
          on the originally created type.
          */

          //covert_freshman.add_course("CS120");  //<- why isn't this valid???

         
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
            could_be_student = new Person("Janet", 35);
         } else {
            could_be_student = new Freshman("Rachael", "Prof. Moussavi");
         }

         //could_be_student.add_course("CS241");  //<- this only makes any sense on one branch


        /*
        Calling shared methods is okay because both Student and Person have a
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


        One more controlled way we can use this reflective behavior is with the instanceof
        keyword. Instanceof allows us to check whether an object is of a certain type:
         */

        if ( covert_freshman instanceof Freshman ) {
                System.out.println(covert_freshman + " has been unmasked!");
                Freshman revealed = (Freshman) covert_freshman;
        }

        //We SHOULDN'T do this any time we can avoid it at all (and we almost always can!)
        /*
         * The reason why is because when we "downcast" we're assuming perfect knowledge of all
         * subtypes that exist, and it's not obvious to someone making a new subclass that this if 
         * statement even exists somewhere outside the classes.
         * 
         * INSTEAD, we should be modifying Person's interface to create a method that we can override
         * in Freshman to do whatever specific behaviors we have in mind.
         */




    }
}