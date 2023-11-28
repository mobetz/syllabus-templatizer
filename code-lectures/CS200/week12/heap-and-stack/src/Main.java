
/*

Objectives for Today

By the end of today, you will:
    * Understand how programs are laid out in running memory during execution.
    * Identify the difference between "heap" memory and "stack" memory.
    * Describe how local variables are allocated and deallocated on the stack.


Vocabulary for the Day

Stack -- When discussing program memory management, the "stack" refers to the region in memory
where function-local information is stored. The stack is divided into "frames", with each
frame representing a function that has been called.

Heap -- When discussing memory management, the "heap" refers to the region of memory where
dynamically allocated objects that exist independent of functions are stored.



Before we start today, a note: several times today I am going to talk about the memory layout of your
application like this is synonymous with RAM. In reality, Operating Systems have "virtual memory" and
complex rules for when that memory gets loaded into *actual* RAM, but we will simplify and pretend
everything in the memory layout is also in RAM.

 */

import java.io.File;
import java.util.Scanner;

public class Main {


/*

Today, we will be learning more about what is happening inside your RAM when you run a Java program.

However... before we can talk about Java, we have to talk about what came before. C programs have
a model for managing memory that has become almost universal among programming languages, and
will prove useful to understanding what goes on in Java.


When you run an .exe file (or .bin on Mac!), your computer immediately reserves a bunch of RAM
for that running program:


| ------------------------------------------------------------------------------------------- |
                ??? What's going on in here, why does it need so much space ???



It turns out, this memory can be broken into a few different regions. All the regions near the
front of the memory space are reserved for things that don't change. The first region at the
very beginning of the program is actually just for all the compiled instructions in your code:



| ------------------------------------------------------------------------------------------- |

| "code" region |        ??????????????????????????????????????????????????????????


Inside the code region, each function's description has its own address, and the steps the computer
must actually take live here. However, there is no space for variables (we'll discuss them a bit later.)

When your program is running on the CPU, the CPU has its own special "instruction pointer" register that tells
it what part of the code region its currently working in!


After this code region, the computer reserves space for all literal values and constants defined in the program.
When you write the word "apple" somewhere in the code, that has to be stored *somewhere*, and so the binary
creates a big old list of all of them one right after the next, and then uses the address of that literal any
time it is used:


| ------------------------------------------------------------------------------------------- |

| "code" region | "text" region  |      ??????????????????????????????????
 */


    public static void main(String[] args) {
        String str1 = "apple";
        String str2 = "apple";

        System.out.println(str1 == str2); //<- weird... these strings are equal. Normally for strings, we use .equal()
        //Remember: any time the word "apple" appears in our code as a literal, it gets replaced with an address in the text region
        // == compares two different objects addresses, and these are the same address


        Scanner s = new Scanner(System.in);
        System.out.print("Enter a str to compare to apple: "); //<- the prompt text here is also in the text region!
        String usr_str = s.nextLine();
        //Even if I type "apple" here, it won't be saved in the text region (dynamically allocated objects get memory when they're made):
        System.out.println(usr_str == str1); //<- these are definitely two different objects with different addresses

        str2 = str2 + "s"; //<- this actually ALSO creates new dynamically allocated text (strings are immutable)
        // when we concatenate, we don't reuse anything from the old string. We copy the entire thing into a new string.
        String str3 = "apples";

        System.out.println(str3 == str2); //<- even though these are the same value, still false (comparing addresses)
    }

    /*



Next, the program creates room for all your static variables. Remember: static variables have unambiguous names
(we always know exactly how many there will be and how to call them), and they exist independent of any function
or object. This means they get created as soon as the program starts, and they exist until the program ends.
Because these variables are globally available, the amount of memory we need for them will never change,
so the program puts them right up front:


| ------------------------------------------------------------------------------------------- |

| "code" region | "text" region |  static vars |    ??????????????????????????????????



     */

    public static String some_var = "My Value Here"; //<- this is created when program first starts, and exists until it ends


    /*

These first three regions were easy, and in fact they're so ubiquitous that we rarely ever even talk about them in
programming (unless you are interested in reverse engineering, compiler theory, or cybersecurity research!) The
remaining memory of a program is much more interesting: all the remaining space is reserved for your variables that
change over the lifetime of the program.


We divide this space up into two regions: one for variables local to a function (the 'function stack'), and one for
"big" variables we don't want to copy every time we call a new function (the 'heap'). These two regions share the
same space but start at opposite ends





| ------------------------------------------------------------------------------------------- |

| "code" region | "text" region |  static vars |  heap -->                       <-- stack


First, let's talk about the stack. The stack, as its name suggests is a stack, almost like the ones
we learned about in our discussion of linked lists! The stack's job is to store local function variables
that get created somewhere inside a function, and who disappear at the end of that function. Each called
function becomes a new "frame" on the stack. So for instance, if we have a function:

*/

    public int someFunction(int x, int y) {
        int starting_num = 0;
        for (int i = 0; i < x; i++) {
            starting_num = starting_num + y;
        }

        starting_num = doubleValue(starting_num);

        return starting_num;
    }


    public int doubleValue(int value_to_double) {
        return value_to_double * 2;
    }



/*
Then when someFunction() is called by the code, an instance of someFunction is "pushed" onto the stack.
This instance holds all the sapce that the local executing function needs:


0xFF ------------------- end of memory
0XFE --------------- main stack frame
      .....
0xF0 --------------- end of main stack frame

0xEF --------------- someFunction stack frame
     location of previous func (0xFE)
     space for x
     space for y
     space for starting_num
     space for i
0xE0 --------------- end of someFunction stack frame

When someFunction calls doubleValue, it pushes yet another stack frame into the memory:


0xFF ------------------- end of memory

0XFE --------------- main stack frame
      .....
0xF0 --------------- end of main stack frame

0xEF --------------- someFunction stack frame
     location of previous func (0xFE) (this is called the "frame pointer")
     space for x
     space for y
     space for starting_num
     space for i
0xE0 --------------- end of someFunction stack frame

0xDF --------------- doubleValue stack frame
     location of previous func (0xEF)
     space for value_to_double
0xD4 --------------- end of someFunction stack frame




when doubleValue() finishes and we reach the return statement, that function is "popped" off the
stack, removing it:

0xFF ------------------- end of memory

0XFE --------------- main stack frame
      .....
0xF0 --------------- end of main stack frame

0xEF --------------- someFunction stack frame
     location of previous func (0xFE) (this is called the "frame pointer")
     space for x
     space for y
     space for starting_num
     space for i
0xE0 --------------- end of someFunction stack frame

     (doubleValue is GONE, along with all its variables)   (our stack pointer gets updated to where the frame pointer of doublevalue was)
                                                            (now we can only use things from someFunction, the new thing on "top" of the stack)

Every time we "pop" from the stack, we update a special CPU "stack pointer" to the beginning of the previous function.
We can only read local variables from in that current stack frame, and then we delete all the things in that previous frame.


Note: this is once again a small simplification -- in real stack frames, we push parameters
onto the stack from the previous frame and then play with that "location of previous function" using another
special CPU register called the "stack pointer" to make passing arguments into functions slightly more efficient,
but this is a general idea of stack-allocated memory.



So if the stack is growing from the end of our memory with information about function variables, and every
variable is ultimately part of a function, then we might wonder what the heap is even for.

Remember how we've about variables 'passed by reference'?
What do you think would happen if we stored a reference to a variable on the stack, and then that stack frame was popped?


 */

    public void heapExample() {

        File someObject = createFile("path_to_file");
    }

    public File createFile(String filepath) {

        File f = new File(filepath);
        return f;
    }

/*
Entering the function would work fine....


0xEF --------------- heapExample stack frame
     location of previous func (...)
     space for someObject
0xE0 --------------- end of heapExample stack frame

0xDF --------------- createFile stack frame

     location of previous func (0xEF)
     space for filepath <- saved address of literal from "text" region
0xD9 space for f        <- save some new object here

0xD4 --------------- end of someFunction stack frame



But we'd have a problem once we returned to heapExample():



0xEF --------------- heapExample stack frame

     location of previous func (...)
     space for someObject  <- would point to 0xD9, but that no longer exists!!!!!!!

0xE0 --------------- end of heapExample stack frame


This is what the heap is for -- saving large allocations of memory that we don't want
to completely copy every time we call a new function. The heap starts growing from the
"front" of the memory region, and objects and arrays get randomly stuffed wherever they fit.

This means our example of creating someObject would *actually* look more like this:



0xEF --------------- heapExample stack frame
     location of previous func (...)
     space for someObject
0xE0 --------------- end of heapExample stack frame

0xDF --------------- createFile stack frame

     location of previous func (0xEF)
     space for filepath <- saved address of literal from "text" region
     space for f        <- calling "new File" saves a reference to 0x11

0xD4 --------------- end of createFile stack frame
        |
        v
  ................
        ^
        |

0x16  --------
        space for file object attributes
0x11  -------- File Object
0x10  ------------------- start of heap
 (...Static, Text, and Code regions here...)


This now solves our problem! When we return from createFile, we can still be pointing at our
File object with all its attributes in the same space they were before.


Note: In languages like C, we very explicitly use the heap with the malloc() function with a number
representing the exact amount of heap bytes we wanted. In C++, this was simplified to calling
the "new" keyword, and new has remained the indicator of reserving heap space in languages
made since.



So how does data *leave* the heap? With our stack, this was easy -- stack variables get deleted
as soon as we leave the function they're declared in. But this doesn't work for our heap -- the whole
point of the heap was that it keeps existing past the end of a function!


In C and C++, we have to explicitly tell the program when a heap object should be destroyed (either
with the dealloc() function, or with a "destructor" function that does the opposite of a constructor.)
Failure to do either of those meant a program would NEVER get rid of that object, and a bit of memory
would "leak" -- we'd need to reserve even more space for an ever growing number of things that were never
destroyed. (You might notice Java has neither of these -- that's the topic of our next lecture!)



Now that we've learned about the stack and the heap in C, we're finally ready to talk about Java.

So how does Java fit into all of this? Remember: Java.exe is a C program, so when you run Java.exe the
binary creates all these regions like any other program. However, what the Java program does is then
creates an entire second layout of memory inside its own heap:



                                               | The "Java Virtual Machine" | ->
| -----------------------------------   Java.exe    ------------------------------------- |

| "code" region | "text" region |  static vars |  heap -->                       <-- stack

If you've ever heard parts of Java referred to as the JVM, that is because Java recreates an entire
second abstraction on top of the basic stack and heap as one giant heap object.

Inside this JVM, the layout is very similar to the layout we saw in C:




|  ------------------------------- Java Virtual Machine -------------------------------- |
| "Method area" |              Heap      +     Stack              | Registers | (Native  |
     Stores                                                                       Code)
   .class code
   and statics


Just like C, Java has a place where it stores compiled class code, a stack, and a heap. The
stack and the heap work much the same as they did in our previous example -- the Stack stores
all the local variables created with each function, and the Heap stores everything else.

One note about the JVM stack -- unlike our C example, the JVM stack isn't actually contiguous.
Each stack frame holds a reference to the next stack frame, just like our linked list stacks!
This means the heap and stack can share even more than they did in C.

So what about that "Registers" memory area -- in C, certain pieces of information (like where
"stack pointer" pointing to the end of the stack region and the "instruction counter" saying
what we're currently doing in the code region) live directly on the CPU.  Since Java is creating
an entire "virtual machine", it needs to create its own virtual version of these special CPU variables.


 */
}