/*

Much like the package-java that we looked at previously, module-info isn't "real"
java code. We will not be working with variables or functions here. Instead,
a module-info 'declares' details about all the files it sees:


   * the name of this module
   * what other modules this module will use (what it "requires")
   * what code from this module others are allowed to use (what it "exports")
   * what code from this module others are allowed to *read* (what it "opens")


*/

open module Payroll {
/*	
		requires javafx.controls;
		requires javafx.fxml;

		These lines communicate that our module requires the FXML and Controls modules of JavaFX
*/


	exports data.model;
	exports util;  //<- OUTSIDE THIS MODULE, 
	               //    you're allowed to use classes from data.model and util, but NOT data.loader

/*
	opens data.model;
 
 	This line would say anyone can read the source code inside Java using special java classes.
	If we want to do that for the whole module all at once, we can put the word "open" before the word module 
*/

}


/*

This says:
   - the entire module is named "Payroll"
   - any classes in our "data.model", and "util" packages can be used by other modules.
   - The entire module is 'opened' to other modules reading the source code
        (This is important for some libraries to auto-write code for you.)


  When we want to compile Java code that uses modules, we have to tell Java
  that we want the entire module compiled at once. We do this with two more
  flags to javac:



   --module-source-path tells javac what folder contains the modules
   --module says the name of the module we want to build
   -d says what folder we want to put all the compiled code into


   For example, we could compile this project by running:

     javac --module-source-path ./src  --module Payroll  -d build


   This will take all the Java files in the Payroll module, and compile them
   all into the build/ folder.

   Once we've done that, we have to tell Java we want to run the module with
   similar options:

   java --module-path build --module Payroll/main.Modules

   This says "all my modules built to the build/ folder, and I want to go into
   Payroll, and run the main.Modules class"

   
   As you can see, this is somewhat verbose, but fortunately our build.gradle
   can help by storing all these details so we can still run our code by simply
   typing 'gradle run' -- we just need to add the module details to build.gradle!


     */