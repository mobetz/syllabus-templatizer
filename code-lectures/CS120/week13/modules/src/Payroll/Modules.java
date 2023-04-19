/*
Objectives for Today

By the end of today, you will:
     * Understand how Java modules allow us to describe the package-level
        dependencies in our code.
     * Describe how packages can be exposed to each other through modules.
     * Identify how Java modules are built and executed.
     * Analyze how we can use jpackage to convert our java code into a binary
       executable.
 */


package main;

import data.loader.PayrollLoader;
import data.model.Employee;
import java.util.List;
import java.io.FileNotFoundException;


public class Modules {
    public static void main(String[] args) throws FileNotFoundException {
        /*
        Previously, we learned how packages let us break down a large set of
        classes into bundles of related code each sitting in their own folder.

        
        When we did this, we had to specify the 'package' of each .java file at
        the top of the code above imports and the class declaration, and code
        from other packages needed to be imported using the package name.

        However, packages by themselves didn't give us very many controls to
        decide *who* was allowed to import our code.

        Note: Every .java file in a module must belong to a package! You can use
        'package main' for files that don't belong in any other packages.

        Let's go look at the structure of a module-info.java file now.
         */

        PayrollLoader loader = new PayrollLoader("resources/2022-payroll.csv");
                List<Employee> employees = loader.load();
        System.out.println(employees.get(0));
    }
}