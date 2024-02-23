

/*
Objectives for Today

By the end of today, you will:
    * Identify the concept of polymorphism as it relates to inheritance.
    * Describe benefits of polymorphic objects when creating collections and passing parameters.
    * Understand how polymorphism can be used to create methods that implement common interfaces.

Vocabulary of the Day

Polymorphism - Polymorphism refers to the practice of using methods of an object in multiple different forms.
Typically this is done while extending a parent class with multiple diverging child classes.

 */




import java.util.ArrayList;
import java.util.List;

public class Polymorphism {

    public static void main(String[] args) {

        /*
        So far, our exploration of inheritance has emphasized a vertical relationship between object types:

                  +-----------+
                  | Person    |
                  +-----------+
                  | name      |
                  | age       |
                  +-----------+
                     ^
                     |
                  +-----------+
                  | Student   |
                  +-----------+  A Student is a Person, so it also has a name and age.
                  | classes   |
                  +-----------+
                     ^
                     |
                  +-----------+
                  | Freshman  | A Freshman is a Student (and a Person), so it also has classes, name, and an age.
                  +-----------+
                  | advisor   |
                  +-----------+



            However, sometimes the relationship between different types of things isn't *just* vertical:

                  +-----------+           +----------------+
                  | Student   |           | Teacher        |
                  +-----------+           +----------------+
                  | name      |           | name           |
                  | age       |           | age            |
                  | classes   |           | department     |
                  +-----------+           +----------------+


                  Student and Teacher share *some* of their properties here, but not all of them.  This means
                  we can't inherit one directly from the other.  However, we might still want to extract the pieces they
                  have in common.


                  This would give us a class hierarchy that looks something like this:

                                 +-----------+
                                 | Person    |
                                 +-----------+
                                 | name      |
                                 | age       |
                                 +-----------+
                          +-------------------------+
                          v                         V
                       +-----------+           +--------------+
                       | Student   |           | Teacher      |   Both Student and Teacher are people with a
                       +-----------+           +--------------+    name and age, but they can each have their own
                       | classes   |           | department   |     unique data and behaviors.
                       +-----------+           +--------------+


                Both Teacher and Student are able to take advantage of the shared behavior of Person, even though
                they also have their own divergent behavior.

        */

        Teacher some_teacher = new Teacher("Matthew", "Obetz", 99, "Computer Science");
        some_teacher.haveBirthday();

        /*
            Let's say elsewhere in our program, we end up with a situation where Students and Teachers might mingle
            together. For example, let's say a guest speaker is arriving and both groups have wanted to register.
         */
        List<Student> student_attendees = List.of(
            new Student("John", "Doe", 20),
            new Student("Jane", "Smith", 20)
        );


        List<Teacher> teacher_attendees = List.of(
                new Teacher("Mark", "Professorson", 40, "CS"),
                new Teacher("Kaylee", "Williams", 38, "English")
        );


        /*
        This is not ideal. Every time we want to interact with all these objects, we will need to comb through *both*
        lists:
         */

         System.out.println("Printing attendees:");

        for ( Student student : student_attendees ) {
            System.out.println(student);
        }

        for ( Teacher teacher : teacher_attendees ) {
            System.out.println(teacher);
        }

        /*
        However.... remember: both Student and Teacher are Persons.
         */

        Person teacher_in_general = some_teacher;

        /*
        What if our "same-type" constraint for our list was that we just have a list of Persons:
         */

         List<Person> all_attendees = List.of(
            new Student("John", "Doe", 20),
            new Teacher("Mark", "Professorson", 40, "CS"),
            new Student("Jane", "Smith", 20),
            new Teacher("Kaylee", "Williams", 38, "English"),
            new Janitor("Someguy", "McClean", 40)
         );


        /*
        When you look at the constructors I'm calling, this looks like a list of two different kinds of objects.
        Students and teachers are mixed together. However... Java isn't crashing! This is because we have morphed
        all of our objects into their supertype.

        NOTE: when we do this, it's unlikely we will be instantiating any Persons directly. Normally, we will
        create the specific subtype of Person we want. (In fact, the Person constructor might even be 'protected' to hide it.)

        This has some consequences. Notably, when we loop over or access the collection:
         */


        System.out.println("\nPrinting the combined list:");

        for ( Person attendee : all_attendees ) {
            // ^ the only safe thing we can assume here is that each item in our list is a Person


            attendee.register_class("Guest Speaking 101");//<- both Student and Teacher know how to do this,but we must
                                                         // make sure a Person does too


            System.out.println(attendee);
        }


        /*
        This is the power of polymorphism! We wrote a single collection, that collection held multiple types of objects,
        and each object knew how to implement its own version of a common shared behavior.

        There is a second common application of polymorphism: polymorphism in method calls. This type of polymorphism
        is sometimes called "coercion", and you likely have been using it since Programming I without even realizing it!

        Say, for example, I wanted a function that could check whether someone is of retirement age, but I want it to
        work for both Students and Teachers. I might decide to make a retirement_check() function that accepts a Person.

        Since age is a common shared data attribute of all Persons, this works to solve my problem. However, when I call
        that function, I don't want to be bothered with manual casts:
         */
        Student some_student = new Student("Jayden", "Cooper", 20);
        Person personified_student = (Person) some_student; //<- needing to do this is clunky
        boolean is_retired = retirement_check(personified_student);
        System.out.println("Is " + some_student.getFullName() + " ready for retirement: " + is_retired);


        /*
        Luckily, Java lets us 'coerce' both function parameters and return values. When dynamic dispatch is selecting
        a method, it will look for the best matching function where each parameter is AT LEAST as specific as the types
        passed into in the arguments, and the assigned return is AT MOST as specific as the declared variable:
         */
         System.out.println("Is " + some_teacher.getFullName() + " ready for retirement: " + retirement_check(some_teacher));
         //^ We sent a Teacher directly into a method that asked for a Person, and Java did the conversion for us!
 

        /*
        However, Java will not downcast an object ( we couldn't send a Person into a method that requires a Teacher.)

        Watch what happens when we declare a second retirement_check() specifically for a Student.
         */

        Student old_student = new Student("Annabelle", "Edinborough", 97);
        System.out.println("As a Student, Annabelle should retire: " + retirement_check(old_student));
        
        Person personified_elderly = (Person) old_student;
        System.out.println("As a Person, should Annabelle retire: " + retirement_check(personified_elderly));

        //^ Based on currently cast type of our student object, it will go into a different version of our function!

        /*
        NOTE: This behavior is DIFFERENT from what happens when we override a method inside our class.
        Inside a class, Java 'remembers' the original type an object was created as, but when we're just calling an
        external method, we can only safely assume that we've got the type of object we're currently holding.

        This type of function overloading is sometimes called "ad hoc" polymorphism, because our objects are viewed
        as different types as the situation requires.

         */
    }


    public static boolean retirement_check(Person p) {
        return p.getAge() > 68;
    }

    public static boolean retirement_check(Student s) {
        return false; //<- you're never too old to be a student!
    }


}