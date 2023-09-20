

/*
Objectives for Today

By the end of today, you will:
      * Review syntax for writing Javadoc comments in code
      * Understand the motivations behind the Unified Modeling Language (UML)
      * Identify UML Class Diagrams, Activity Diagrams, and Sequence Diagrams
      * Contrast the strengths and weaknesses of "Forward Design" and "Backward Design"


Vocabulary for the Day

Unified Modeling Language - UML is a standard depiction of a system-wide architecture,
most commonly used in object oriented designs.

Forward Design - Forward design is the practice of writing system-wide documentation *before*
you code.

Backward Design - Backward design is the practice of documenting already written code after
it is implemented.

 */

public class UML {


    /*
    UML diagrams are a system for documenting our code. They are one of many, but we've actually
    seen some other forms of documentation all semester:
        - comments can be written inline next to hard-to-read passages of code
        - class documentation can be written describing whole methods or whole objects and their purpose
        - we can even use JavaDoc to "compile" these comments into an external document.

    However... if your program contains dozens or hundreds of different files, it can be hard to recognize
    where we should even look for comments. A "system wide" view of a program can be helpful in "finding your
    footing" in an unfamiliar part of larger programs.

    This was the motivation for the creation of UML. IN the late 90s, a famous "system architect" (senior programmer)
    named James Rumbaugh was dissastified with the state of tooling for describing whole programs. He, along with
    designers from multiple large companies (General Electric, designers of the Ada language4, researchers at Bell
    Labs) came together and decided to create one common language for designing programs.

    Each of these corporations had different mechanisms for documenting systems that emphasized different features
    of a program (which physical users interacted with which components, how classes related, or how ideas mapped
    to programs, etc...) These "Three Amigos" created the UML working group, which was supposed to create one
    single design tool that could do all of these things.

    The underlying goal was to make programmers more "interchangeable"/"replaceable". If everyone "speaks" the same
    language in system design, then any programmer can implement any design and quickly become informed when they
    are hired or move to a new project.


     Though UML was supposed to be one "unified" model, in truth there are dozens of different models that represent
     different aspects of a program all under the "UML" banner. We can broadly divide UML diagrams into two types
     of documents:

          - "Structural" Diagrams focus on displaying the relationship between pieces of data, classes, or other
           units of code in a system design. Boxes usually represent units of code (e.g classes), and lines in a
           diagram are how one unit interacts with another (e.g a class holding a reference to another class.)

           - "Behavioral" Diagrams focus on displaying the way components interact and how information flows across
           the lifetime of a program. Boxes usually represent actions (e.g calling a function, making a decision...)
           and lines are which actions enable which other actions (e.g after an if statement, we move to one of two
           other functions.)



     Our first type of diagram that we're going to look at will be a Class Diagram. As the name suggests, this diagram
     is meant to display classes and how they relate. Each class is represented by a Box:


     +-----------------------+
     | BankPatron            |
     |-----------------------|
     |                       |
     |                       |
     |                       |
     |                       |
     |                       |
     |                       |
     |                       |
     +-----------------------+


     Inside the box, we'll put each method and each attribute that is part of the class:

     +---------------------------------+
     | BankPatron                      |              Important Features of our UML Class:
     |---------------------------------|
     |   -name: String                 |                  - Type names come along with each attribute
     |   -accounts : List              |                 (properly after with a colon, but this varies)
     |                                 |
     |                                 |                 - Each field's visibility/scope is specified with
     |-------------------------------- |                     a symbol (- for private, + for public)
     |   +getTotalAssets() : double    |
     |   +setName(name: String) : void |                  - Return types come after each method
     +---------------------------------+


    However... "list" isn't a complete detail of what's going on with BankPatron's accounts.

     +---------------------------------+
     | BankPatron                      |
     |---------------------------------|
     |   -name: String                 |
     |   -accounts : List              |   <---- this is related to a BankAccount class, that needs its own box
     |                                 |
     |                                 |
     |-------------------------------- |
     |   +getTotalAssets() : double    |
     |   +setName(name: String) : void |
     +---------------------------------+


    The other key feature of a UML Class diagram, is that we use lines to represent when one attribute of one class
    is a reference to an object of another class.


     +---------------------------------+                             +---------------------------------+
     | BankPatron                      |                             | BankAccount                     |
     |---------------------------------|                             |---------------------------------|
     |   -name: String                 |                             |   -type : String                |
     |   -accounts : List<BankAccount> |  ---------------------      |   -owner : BankPatron           |
     |                                 |                             |   -transactions : List<Transaction> |
     |                                 |                             |   -balance : double             |
     |-------------------------------- |             ^               |-------------------------------- |
     |   +getTotalAssets() : double    |  This line says that the    |                                 |
     |   +setName(name: String) : void |  'accounts' in BankPatron   |                                 |
     +---------------------------------+  relates to the             +---------------------------------+
                                             'owner' of BankAccount




     +---------------------------------+                             +---------------------------------+
     | BankPatron                      |                             | BankAccount                     |
     |---------------------------------|                             |---------------------------------|
     |   -name: String                 |  1..many              1     |   -type : String                |
     |   -accounts : List<BankAccount> |  ------------------------   |   -owner : BankPatron           |
     |                                 |                             |   -transactions : List<Transaction> |
     |                                 |                             |   -balance : double             |
     |-------------------------------- |             ^               |-------------------------------- |
     |   +getTotalAssets() : double    |  It's common to put a num   |                                 |
     |   +setName(name: String) : void |  that indicates 'how many'  |                                 |
     +---------------------------------+  of a relationship exist    +---------------------------------+
                                      between two classes (e.g that Accounts is a list, but there's only
                                         one owner per account).

     Some people even omit properties that are represented by a line:


     +---------------------------------+                             +---------------------------------+
     | BankPatron                      |                             | BankAccount                     |
     |---------------------------------|                             |---------------------------------|
     |   -name: String                 |  1..many              1     |   -type : String                |
     |                                 |  ------------------------   |   -transactions : List<Transaction> |
     |                                 |                             |   -balance : double             |
     |-------------------------------- |                             |-------------------------------- |
     |   +getTotalAssets() : double    |                             |                                 |
     |   +setName(name: String) : void |                             |                                 |
     +---------------------------------+                             +---------------------------------+


             If the relationship only works one way, we can represent with an arrow instead of a line.




        Activity diagrams describe how we move between different workflows in a system. This is often
        represented as a simple flowchart:


                                    O                 <- a Circle represents the 'start' of the program
                                    |
                                    v
                             +----------------+        <- each box in the activity diagram represents
                        ---->|   collectInput |          a method or action that occurs
              [input    |    +----------------+
            was invalid]|           |          \
                        +=======   /\            \        <- a choice or diverging path gets represented with
                                   \/              \         a diamond
                                   |                |
                                   | [valid deposit]|
                                   v                |
                             +----------------+     |
                             | depositMoney   |     |  [yes, more transactions] <--- descriptions of paths are
                             +----------------+      \                                 labeled with square brackets
                                  |                    \
                                  |                     |
                                  v                     |
                             +----------------+         |
                             |askMoreTransacts|  -----> /\
                             +----------------+         \/   more transactions?  <- we can label diamonds with what
                                                         | [no]                       they're testing
                                                         V
                                                         O  <- the end of an activity is a filled in circle


            The final type of diagram to discuss are Sequence Diagrams. In a sequence diagram,
            we do not use boxes and arrows in quite the same way as other "Behavioral" UML diagrams.

            Instead, we represent "actors" (different people or parts of a system) and show how those actors
            communicate over time:


            +------+                             +---------------+
            | ATM  |                             | Banking Cloud |           <- major components/actors are boxes
            +------+                             +---------------+
               || sends request to check user balance   ||
               || ----------------------------------->  ||                     <- two big arrows represent the
               ||   return how much money user has      ||                                 flow of time
               || <----------------------------------   ||
               ||     user inputs some transaction      ||                      <- messages sent between actors
               ||  -------------------------------->    ||                           are lines between the big lines
              \  /                                     \  / validate how much
               \/      allow/disallow the transaction   \/   money is available
                   <---------------------------------


           This type of model is specifically useful when we are dealing with networks, the internet, or designs
           where messages are the primary means of running different parts of the program (this includes GUIs, which
           often handle things like "clicking a button" and "updating the UI" as message!)


            There are many tools that help us produce UML diagrams (they're not usually drawn with ASCII art!)

            Some even use "markup" languages or pseudo-code to describe the boxes, and autogenerate images from that.
            (For example, https://www.planttext.com/)

            By entering text like


title Classes - Class Diagram


class BankAccount {
   -owner: BankPatron
   -balance: double
   -transactions: List


   +deposit(amount: double): boolean
}

class BankPatron {
  -name: String
  -accounts: List

}

BankAccount "1" -- "1..many" BankPatron



We can produce the exact same class diagram that we wrote with text above.






*/






