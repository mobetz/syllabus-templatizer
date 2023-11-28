

/*

Objectives for Today

By the end of today, you will:

   * Describe the rationale for organizing Java classes into separate folders
   * Identify how to access Java code that is in a different folder
   * Understand dot notation for Java packages
   * Identify how Java packages relate to the 'import' statements that we've been using all semester.
   * Build documentation for our Java packages with JavaDoc.

 */




import java.io.FileNotFoundException;
import java.util.List;

import data.loader.PayrollLoader; //<- treat PayrollLoader like it was in this folder
import data.model.*; //<- the * here says "grab every single file in that package."



public class Packages {
    public static void main(String[] args) throws FileNotFoundException {

        /*
        This semester, every single program that we've written has had all of its code live side by side in a single 'source' directory,
        normally the root folder of our project. This works well enough for small programs that only have one or two classes, but can
        quickly become unwieldly -- imagine trying to find the one .java file you wanted to edit hidden in a folder with 100 different
        classes!

        The "payroll" program we've been working on for the last couple weeks is on the cusp of being big enough to consider splitting
        up the files -- we've got some classes that model data, some classes that load data, and some classes that help us search through
        data.



        Let's try sorting the code files from that project so that our data model classes, data loading classes, and helper classes are
        split up into separate folders.


              │   ├── PayrollLoader.java
              │   ├── Employee.java
              │   ├── Job.java
                  ├── PayrollSearcher.java
                  └── Packages.java

              ├── data
              │ ├── loader
              │ │   └── PayrollLoader.java
              │ └── model
              │      ├── Employee.java
              │      └── Job.java
              ├── util
              │   └── PayrollSearcher.java
              ├── resources
              │   └── 2022-payroll.csv
              └── Packages.java



             Great! Now that we've done this, it's much easier to at a glance find Employee and Job, and when we don't need them collapse
             that folder so we can concentrate on the other code.

             However... watch what happens when we try to go and use one of those classes here in main:

        */

        /*
        PayrollLoader loader = new PayrollLoader("resources/2022-payroll.csv");
        List<Employee> employees = loader.load();
        */



        /*
         Packages.java:56: error: cannot find symbol
         PayrollLoader loader = new PayrollLoader("resources/2022-payroll.csv");
         ^
         symbol:   class PayrollLoader
         location: class Packages

         Our PayrollLoader class is right there, but javac can't see it when we try to compile our code!


         In Java, when we use folders to organize our code, we create what are called "packages"-- a package is just 
         a bundle of related files that exists in its own 'name space'.  You can think of this like giving your class 
         a category name or a tag that prefixes the actual class name.

         Historically, Java liked people to name these packages after the website where people could download the code, 
         with each part of the web address listed in reverse (this is why a lot of packages have names like 
         org.apache.commons, because you can find it at http://commons.apache.org ). Today, most people just use 
         a category or organization name that makes sense.

         To finish adding the packages to these classes, we need to add one more command up at the top of the files:

         package <path.to.file>;


         Let's go to our PayrollLoader and add a package designation!

        One solution to this problem would be to use the fully qualified name of our class whenever we want to load it, 
        by listing out the whole package path before the name of our class:
         */

        data.loader.PayrollLoader loader = new data.loader.PayrollLoader("resources/2022-payroll.csv");
        List<data.model.Employee> employees = loader.load();


        /*
        However, this very quickly becomes quite cumbersome.  It turns out, we've been using another tool all semester 
        that can help us here: the import command.

        What an import statement is *actually* saying is "treat everything in the place I list here as if it were part 
        of our current package."

        If I go to the top of the file and import the data.model and data.loader packages, suddenly my classes will be available.
        By adding imports, I can now use those class names exactly the way I did before I split the files up:
         */

        Employee first_employee = employees.get(0); //<- I didn't have to put data.model.Employee, because Employee is imported!
        System.out.println(first_employee.toString());



        /*
        The imports we've been writing all semester work no differently. When you import java.util.ArrayList, 
        what you're actually telling Java to do is:
                - Go to the 'base directory' where Java is installed
                - navigate to the ./util/ folder inside that base directory
                - Treat the ArrayList.java file there as if it were part of my current package


         In fact, we can even use the 'fully qualified' names of packages we haven't imported, the same way we did 
         when we specified data.model.Employee:
         */

        java.util.ArrayList<java.lang.Integer> list_of_numbers = new java.util.ArrayList<>(); //<- I never imported ArrayList, but I'm still using it!

        list_of_numbers.add(3);
        list_of_numbers.add(4);

        System.out.println(list_of_numbers);


        /*
        You usually don't want to do this, but it can be helpful to know its possible if you end up with two classes 
        from different packages with the same name and you want to disambiguate the two!


           One of the nice things about packages is that they let us use the same name for classes that live in different packages.
           For example, if we wanted to we could have a data.model.Employee, and a http.request.Employee that represented an employee
           in different parts of our program. Both files would be named Employee.java, but Java would be able to tell them apart!

           (NOTE: it can still be inconvenient to do this if you're likely to use both in the same file.)


           This can even help us write more succinct class names -- if I were creating a bunch of classes named things like
           EmployeeModel and JobModel, I could instead stick them in a 'model' package and chop the "Model" off the end of their name.
           Then when I import the packages I don't have the extra visual 'noise' of the word Model over and over again, but if at some
           point it's important for me to designate that I'm talking about a model, I can use the fully qualified name.

        */



        /*
        One last thing we can mention about packages: sometimes it makes sense to have extra documentation around your package that describes what the purpose
        of a folder is for or how to use the classes inside. We looked at a tool called JavaDoc that can help us write descriptions and generate a webpage that
        describes how to use our code. Let's make a package-info for data.model, and see that package-level documentation in action!
         */


        
        /*
        Packages let us provide a bit more organization to our code, and can help us more clearly and succinctly describe the name and role of classes we create.
        Next class we'll see how we can take packages a step further by creating modules that describe "public" and "private" parts of packages!
         */


    }
}