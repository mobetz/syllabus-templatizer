
/*
Objectives for Today

By the end of today, you will:
    * Review the syntax for interacting with arrays and lists in Java.
    * Understand how array indices offset memory.
    * Identify the concept of a pointer.
    * Understand ramifications of passing reference types like collections between classes.



Vocabulary for the Day

Pointer - A pointer is a numeric address that "points" to a particular position in
a computer's memory. Pointers are typically used to more efficiently store a 'reference'
to some larger structure of data like an object so that it can be quickly passed between
functions.



 */

 import java.util.ArrayList;
 import java.util.List;

public class Collections {
    

    public static void main(String[] args) {


        /*
        Today we will be concluding our review of the fundamental building blocks of
        Java. Specifically, we will be looking at collection types.

        From Programming I, you likely remember that you can declare an array of
        any type by putting square brackets after the type name:
         */

         int[] list_of_numbers;


        /*
        Then, when you want to assign your array, you do so like this:
         */

         list_of_numbers = new int[5];

        /*
        Then accessing individual elements (either storing or retrieving) is done with square brackets:
         */


        list_of_numbers[2] = 47; //<- this saves 47 into the third slot
        System.out.println(list_of_numbers[2]); //<- this retrieves and prints out what's in that third slot (47)



        /*
        Much like an object, an array is created with the 'new' keyword, and
        we need to provide information about the maximum number of things that
        could be stored in the array (in this case, 5.)

        But why is it that arrays use the same word as objects, and why do we
        need to give them a size when lists work just fine without it?

        To understand this, we must understand a bit more about memory on your
        computer. Your computer is equipped with several sticks of "Random Access
        Memory" (RAM). These RAM sticks contain billions of little circuits that can
        each store binary data. We could visualize this data like a very long
        strip of cells:



+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...


Each of these cells can be given an "address", a numbered slot that can hold a set amount
of data. What the 'new' keyword *actually* does is tells Java to calculate how much space
it needs for some data, then reserve that much space from memory. In our case, let's pretend
each int takes up two of these 'boxes'. That means Java will reserve the following space:

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...


0x00:
|-------------|-------------|-------------|-------------|-------------| <- int array
      0             1              2             3              4


We can see that, starting from address 0x00, we've taken up ten boxes total. However,
when numbering our boxes, we've ignored the boxes' original addressing system, and
instead given them the numbers 0 through 4.



We do this because:
    * We want the numbers we use to refer to our array to be consistent no matter what
    addresses the array was reserved at.
    * We don't want to have to worry about how 'big' each box is and skip addresses.


However... this is still a lot of boxes worth of data. It would be slow to pass all
this information around every time we wanted to move the array into or out of a function.


What we do instead is pass *just the location* of the beginning of the array:

   int[] arr = new int[5]; <- what this ACTUALLY stores is just the numeric address 0x00


This 'location' information is called a "pointer" or "reference", and is a very powerful
tool that lets Java be much more efficient when moving large structures of data.


NOTE: some languages like C give you direct access to pointers. Imagine in Java
if we could do something like this:

    int[] arr = new int[5]; //say this gives us 0x15
    int  *location = (int) arr; //<- just the number 15 for address 0x15. NOT POSSIBLE in Java

Once we had this, we would be able to jump to any other location, by just doing a bit of math:

  int next_slot_of_array = location + 2; //(because we decided one int was two "boxes" in our model.)


|-------------|-------------|-------------|-------------|-------------| <- int array
0             1              2             3              4

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...
                ^
                |
            we're now "pointing" here


   At this point, we could 'de-reference' that location in memory and get out the thing in the
   first slot. (In C, there's a special symbol & that lets you do this.)


   Have you ever wondered why arrays in Java start from zero? This is why! Imagine we wanted
   to get to slot number 3. We could express that as:


   int size_of_one_number = 2;
   int location_of_start = 0;
   int location_of_three = location_of_start + size_of_number * 3;

   We use 0 for the first slot because zero anything times * will keep us right at the beginning
   address where the first object is held. When you type arr[3], this is the math
   that Java is doing behind the scenes!

   Objects also work on a similar principle -- Java will reserve 'blocks' for each instance variable
   of an object when you use 'new':

0x00
|-------------|----------------------------------------|------|     <- a "Person" object
int age       string name                               boolean alive

+------+------+------+------+------+------+------+------+------+------+------+------+
|      |      |      |      |      |      |      |      |      |      |      |      |   ...
+------+------+------+------+------+------+------+------+------+------+------+------+
0x00    0x01    0x02   0x03   0x04   0x05   0x06   0x07   0x08   0x09   0x0A   0x0B     ...


This will be relevant later this semester when we learn a bit more about memory management!



   So what about lists? We've seen that lists work very similarly to arrays. If we wanted to create
   one:

         */

         List<String> some_names = new ArrayList<String>(); 

         some_names.add("John");



    }
}
