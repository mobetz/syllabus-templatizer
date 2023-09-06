/*
Objectives for Today

By the end of today, you will:
    * Understand the evolution of modern OOP design.
    * Define encapsulation as it relates to class design.
    * Review the mechanics of object oriented programming (OOP) in Java.
    * Identify the concept of a class invariant.
 */

/*
Vocabulary of the Day

Encapsulation -- Encapsulation refers to the problem solving strategy of grouping related
data and logic in a single place in order to promote "cohesiveness" (readability)
of a program.

Invariant -- An invariant is a statement that holds true at all points in the
execution of a program. Invariants can be applied to individual blocks of code (e.g before/
after each iteration of a loop) or to entire classes (before/after any public method.)

Data Hiding -- Data Hiding is the technique of restricting access to data and logic
(often by making fields and methods private,) in order to promote better encapsulation
and protect invariants.

 */


/*

Today our goal is to review Object Oriented Programming.

Before we discuss the syntax of objects in Java, we should take a moment to discuss
the motivations for using objects at all.

Before the advent of Object-Oriented Programming, the dominant paradigm for writing
programs was a strategy called Procedural Programming. In procedural programming,
the goal is to write every step of a task as its own function that describes its
inputs and outputs. Larger workflows are functions that call each individual
step in order, and the main function lets you select the appropriate workflow.

To account for the need to sometimes return multiple variables from a single step,
languages like C introduced the concept of a "struct": a bundle of fields that could
be given a name and passed around as a single variable.

However... there was still a problem. Because each function was its own free entity
and data was visible throughout the entire program, it wasn't always clear where
a function should go -- a function that calculates an employee's weekly pay could
go with other payroll related functions, or with weekly task functions, or with
other employee related functions.

(What makes this a problem?)

This means that to fully understand everything that could interact with the employee
struct, you need to read every single file! This made it common for two people working
in different parts of the program to make changes that conflicted with each other.

To solve this problem, computer scientists introduced the concept of objects (first
in research languages like Simula, but later popularized in C++.) C++ took all
the instructions available in C, and added two small changes:
   
   - First, your data "struct" could now hold not just variables, but also whole
   functions.
   
   - Second, you could put a restriction on some variables in a struct, so that
   they could only be accessed by functions that also belonged to that same struct.
   
   (What is this describing?)
   
This was the advent of objects and classes!
   
   (Why wouldn't it be enough to just bundle data+methods together?)
   
The combination of private fields with bundled methods was powerful -- by ensuring
that ONLY code in a single file could interact with a variable, we greatly limit
the number of things a programmer needs to keep in mind while modifying that code.

This advancement created a programming philosophy of encapsulation, the idea
that each slice of behavior (sometimes called a "concern") should be
grouped and separated from other behaviors.

This worked well, but there was still one issue: classes in C++ were entirely optional.
Codebases ended up being a mash of free-floating functions, global variables, and
class definitions used to create encapsulated objects.

Some computer scientists wanted to take this idea of encapsulation one step
further -- rather than needing to "opt in" to objects, they wanted all code to
be objects by default.

(What are the benefits of this? Drawbacks?)

This is the paradigm used in Java: in order to enforce this, every file must be
named after a single public class it exposes.
 */

public class Encapsulation {
    
    /*
    This guarantees all code will be housed inside a class!
    
    ...But there's a small problem: not all code *is* related to persistent. Some
    tasks are 'pure' functions that take inputs and produce outputs, and then are
    forever done.
    
    (Can you think of any examples?)
    
    Examples of this are things like basic math, printing text, or converting data.
    
    (So how does Java solve this problem?)
    
    To account for this, Java added 
     */
    
    public static void main(String[] args) {
        
        
        
    }
}