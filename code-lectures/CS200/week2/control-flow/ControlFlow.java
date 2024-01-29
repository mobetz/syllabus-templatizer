/*
Objectives for Today

By the end of today, you will:

   * Review common control flow instructions in Java.
   * Understand how source code is converted to machine instructions.
   * Analyze commonalities between the use of functions, loops, and if statements.
   

Vocabulary for the Day

Abstraction -- An "abstraction" refers to a model that is a step removed from
the way things really work. Abstractions are used to simplify our thinking when
dealing with a complex problem.

*/





public class ControlFlow {
    
    public static void main(String[] args){

        /*
        Today, we will be continuing our review of the Java you learned in Programming I.
        Specifically, we are going to focus on instructions that change the flow
        of a program, like ifs, loops, and function calls.

        As with last week, I want to begin today by providing you a bit of wider context,
        and explore how the instructions you write turn into electricity on metal.
        
        When you write code, you are typically giving instructions to the Central
        Processing Unit (CPU) of your computer.  The CPU is a small chip that has
        intricate patterns of metal called "logic gates"  -- each of these gates has
        many 'slots' for electricity representing inputs to flow in, then mixes those
        wires in clever ways, producing a result flowing out from a few wires.

        Logic Gates can be created for many simple instructions: For instance, you might
        have a logic gate that can add two binary numbers together:
        have a logic gate that can add two binary numbers together:


        0  1  0  1        0  1  1  0
        |  |  |  |        |  |  |  |  
        \  \  \  \      /  /  /  /
        +--+--+--+--+--+--+--+
                |  |  |  |
                1  0  1  1

        These inputs to a logic gate come from "registers", a small group of numbered
        variables that can only hold binary data. Originally, programming involved
        directly telling the computer which logic gate should run on which registers.


       With a relatively simple program, it's easy to see how these gates could be used:
        */

        int x = 5;   //put 5 in a register
        int y = 6;   //put 6 in a register
        int res = x + y; //take those two registers, add them together



        /*
        "Assembly" coding is a low-level form of programming where we more directly
        interact with these circuits, and our program in assembly would look almost
        identical:

        mov eax 5  (save 5 in the "a" register)
        mov ebx 6  (save 6 in the "b" register)
        add eax,ebx (add those two registers together, which saves the result in "a")


        However, this gets much more complicated once we start adding in instructions
        that cause the program to "jump around":
        */

        
        boolean XisMoreThanZero = x > 0;
        
        if ( XisMoreThanZero ) {
            System.out.println("X was positive!");
        } else {
            System.out.println("X was negative!");
        }
        
        /*
        We would never see *both* the "positive" and "negative" messages here.
        So how could something like this exist in assembly...?
        

        The answer is by giving each instruction a number, and then creating
        one more variable that holds what instruction 'number' we're currently on.
        Then, whenever we are supposed to 'skip' to another instruction, we just
        change the number:

                                                    Order instructions will actually happen in:
1   boolean t = x > 0;                      When true:       When false:
2   when t is not true: go to line 5          #1                #1 (jump to 5)
3   print "positive"                          #2                -skip- 
4   go to line 6                              #3 (jump to 6)    -skip-                 
5   print "negative"                          -skip-            #2                 
6   //rest of program                         #4                #3


This is all an if statement is -- much like a "Choose Your Own Adventure Book",
an if statement is telling a program to check whether something is true, and then
skip ahead based on the result.


In fact, we're going to see in a few minutes that this same exact trick works
for loops and functions as well! So why do you think it is we don't use "go to"
statements directly in Java?


It would be very difficult to keep track of the line numbers of every
instruction in Java, and easy to make a mistake with the much more complicated
pattern of jumping around, so we create an "abstraction":  a logical shortcut
that says 'when I put this simple thing, I'm actually talking about this
specific version of this more complicated idea'.



However, remembering what your simple model is representing can be very
useful when debugging what goes wrong. For instance, consider the following
program:
        */


        boolean yellowIsPresent = true;
        boolean blueIsPresent = true;
        
        if ( yellowIsPresent ) {
            System.out.println("yellow!");
        }
        else if ( blueIsPresent ) {
            System.out.println("blue!");
        }
        else if ( yellowIsPresent && blueIsPresent ) {
            System.out.println("green!");
        }
        


        /*
        In our "abstraction", we're simply mentioning three different paths.
        In your head, that model might look something like this:

                +=================+
                |  What color?    |
                +=================+ ---------------- 
                /         |        \                 \
                V         V          V                \
        ___________   ___________   _________________  __________
        | yellow? |   |  blue?  |   | yellow+blue?  | | when none|
        +---------+   +---------+   +---------------+ +----------+
            V               V              V                V
        print yellow     print blue      print green    skip to end


        However... when we run this program, this isn't the behavior that we actually
        get! Even though green *should* show up since yellow and blue are both true,
        what we actually get is yellow.

       Let's convert our if statement back into go tos and try to figure out why:
        
        
1   boolean yellowIsPresent = true;                |  1  boolean yellow = true;
2   boolean blueIsPresent = true;                  |  2  boolean blue = true;
3   if ( yellowIsPresent ) {                       |  3  when false == yellow, go to 6 
4      System.out.println("yellow!");              |  4  print yellow
5   }                                              |  5  go to 11
6   else if ( blueIsPresent ) {                    |  6  when false == blue, go to 9
7      System.out.println("blue!");                |  7  print blue
8   }                                              |  8  go to 11
9   else if ( yellowIsPresent && blueIsPresent ) { |  9  when false == yellow&&blue, go to 11
10     System.out.println("green!");               | 10 print green
11  }                                              | 11 //rest of program



If we look here, we can see that conditions are not actually checked at the
same time. Instead, they happen sequentially -- we check the first one, and
if that fails, we 'skip ahead' to the second one. After a successful branch,
we immediately 'skip ahead' to the end of the conditions.

That means our actual flowchart for an if statement will look something more like this:


______________
| is yellow? |
+------------+
| Yes        \ No
V             V
_______________    _______________  
|print yellow |    |  is blue?   | 
+-------------+    +-------------+
         |                | Yes        \ No
         |                V             V
         |         _______________    _____________________  
         |         | print blue  |    |  is yellow&blue?  | 
           \       +-------------+    +-------------------+
             \       |                         | Yes        \ No
               \     |                         V             |
                 +---+                    _______________    |
                       \                  | print green |    |
                         \                +-------------+    |
                           +---------------------+---------+ |
                                        |
                                        V
                                        rest of program


90% of the time this level of detail is greater than we need, but for that last
10%, understanding the abstraction helps us make more informed decisions about
our code.


        

Now let's extend our example to loops:
*/

x = 10;


if ( x > 0 ) {
    x = x - 1;
    System.out.println(x);
}


while ( x > 0 ) {
    x = x - 1;
    System.out.println(x);
}


        /*
        We already know how the if version of this statement would look with jumping:

        1  boolean xGreaterThanZero = x > 0;
        2  when xGreaterThanZero is false, skip to 5
        3  x = x - 1;
        4  print x;
        5 //rest of program
        
        
        The while version is very similar. In fact, the comparison is identical
        It just adds one more "jump":


        1  boolean xGreaterThanZero = x > 0;
        2  when xGreaterThanZero is false, skip to 6
        3  x = x - 1;
        4  print x;
        5  go to line 2
        6 //rest of program
        


        To the computer, a loop is just an if statement that goes back to the top.

        But this is only a while loop.... will this also work for for loops and
        foreach loops? It turns out for loops and foreach loops can be rewritten
        as while loops!


        This code here:
        */



        String[] names = { "Joe", "Jill", "Jaya", "Jamal"};
        for ( String next_name : names ) {
            System.out.println(next_name);
        }
        
        
        /*
is identical to:
*/


        names = new String[] { "Joe", "Jill", "Jaya", "Jamal"};
        for ( int i = 0; i < names.length; i++ ) {
            String next_name = names[i];
            System.out.println(next_name);
        }
        

        /*
is identical to:
*/

        
         int i = 0;
         while ( i  <  names.length ) {
             String next_name = names[i];
             System.out.println(next_name);
             i = i + 1;
         }

        
        /*
        *All three* of these blocks of code would be converted into the same "go to"
        instructions when the computer was compiling our code:

        1  int i = 0;
        2  int number = names.length;
        3  boolean finished = i >= number;
        4  when finished == true, go to line 9
        5  next_name = names[i];
        6  print next_name;
        7  i = i + 1;
        8  go to line 3
        

Or as a flowchart:

__________________________
|  is i less than names? | <--------+
+------------------------+            \
     | No         | Yes                 \
     |           +------------------+     |
     |           |  get+print name   |    |
     |           +------------------+     |
     |              |                    /
     |           +-------------+        /
     |           | add 1 to i  |  -----+
     |           +-------------+
     v
        


This is another important feature of abstractions: they can be 'layered'!
A foreach loop is an abstraction of a common type of for loop, and a for loop
is an abstraction of a common type of while loop. Each layer saves us some
time and makes our loop easier to think about, but also gives us less control.
You can always use a while loop, but for loops and for each loops only work
for the type of pattern they're made to handle.


        */



    }
}