import java.util.List;

public class Main {


    public static void main(String[] args) {
        /*
         *  INHERITANCE
         *
         * At the beginning of this semester, we had introduced a new term that allowed us to
         * reuse class definitions as a "base" for a new class.
         * 
         * When we wanted to create an inherited type, we used the 'extends' keyword.
         * 
         * 
         */

         Tree example_tree = new Tree("Oak", 20);

         /*(
            When I construct a inherited type, I automatically gain use of all the methods on the base type:
         ) */

         double co2_left = example_tree.reduce_co2_from(100);

         /*
          * I can even save the variable into its base type:
          */

          Plant some_kind_of_plant = example_tree; //<- there is no type error here.... BUT we've lost information

          // ERROR> some_kind_of_plant.getSpecies(); //<- even though the actual object in memory is a Tree, we can't safely assume that here

          /*
           * This is still useful because we can make mixed type collections:
           */

           List<Plant> mixed_plants = List.of(
               example_tree,
               new Tree("Maple", 16),
               new Flower("red"),
               new Plant(1)
           );


           double initial_co2 = 100;
           for ( Plant next_plant : mixed_plants ) {
            initial_co2 = next_plant.reduce_co2_from(initial_co2);
           }

           System.out.println("After using every kind of plant, the co2 left is: " + initial_co2);


           /*
            * LINKED LISTS

               When we have a collection that we're only ever going to iterate successively, we can
               store that collection by only letting each item know about the "next" item in the list
               using pointers.

               [ item: 1        [ item: 2         [ item: 3
                  next: o---->     next: o----->      next o---> null
            */

            Order a = new Order("hamburger");
            Order b = new Order("fries", a);
            Order c = new Order("soda", b);


            /*
             * With a basic linked list, we only ever remove things from the edges of the list.
             * 
             * We had discussed two different linked list-related data structures that pull from
             * opposite ends of the list:
             * 
             * STACK - The first thing pushed in will be the last thing taken out
             *       - This means things are both added and removed at the head
             * 
             * 
             * QUEUE - The first thing pushed in is the first thing taken out
             *       - This means things are added at the tail, and "bubble up" to the head, where they are removed
             */


             OrderStack stack = new OrderStack();

             stack.push(new Order("hamburger"));
             stack.push(new Order("fries"));
             stack.push(new Order("soda"));


             System.out.println("\n\nORDER WHEN USING STACK:");
             Order next = null;
             while ( false == stack.isEmpty()) {
                next = stack.pop();
                System.out.println(next);
             }
             //With a stack, things often pop out in the opposite of the order they were put in



             OrderQueue queue = new OrderQueue();

             queue.add(new Order("hamburger"));
             queue.add(new Order("fries"));
             queue.add(new Order("soda"));

             System.out.println("\n\nORDER WHEN USING QUEUE:");
             while ( false == queue.isEmpty()) {
                next = queue.remove();
                System.out.println(next);
             }
             //In a queue, things are handled in the order they are received


             PlayerRing turn_taker = new PlayerRing();

             turn_taker.add(new Player());
             turn_taker.add(new Player());
             turn_taker.add(new Player());


             System.out.println("\nIterating with a ring: ");
             for ( int i=0; i<30; i++) {
                System.out.println(turn_taker.advance());
             } //<- with a ring, we advance infinitely in the same repeating order 



             /*
              * We had identified two major areas of memory when our programs execute:


              STACK - The stack stores variables local to a function in a "stack" of frames,
                       where each frame holds all the local variables for that function
                    - When a function returns, the entire stack pops off and all those variables
                       are deallocated automatically 
                    - When the program executes, you can only "see" stack variables in the current
                    stack frame
                    - if we're sharing something from one stack frame to another, typically a whole
                       copy is made of that variable   



              HEAP - The heap stores larger regions of memory that would be painful to copy. (Whole objects,
                      arrays...)
                   - When the "new" keyword is used, the programming language reserves sufficient memory 
                   from the heap in the next available space.
                   - When a variable holds a reference to a heap-allocated object, you actually only hold
                    just the memory address where that object exists.
                   - This means when you pass a heap object from one function to another and then modify that
                      object inside the function, it gets modified EVERYWHERE at once.
                   - Because heap objects are designed to outlive functions, cleaning them up requires careful
                   tracking or garbage collection.



              GARBAGE COLLECTION


            Because objects on the heap are designed to live past the end of functions, we need some way
            to know when those objects will no longer be used in the program.

            We had learned that most garbage collection algorithms operate on a simple principle:
            objects are ready to be collected when there is no longer any live reference to that object.
            If nothing points to the variable, nothing can ever get a new pointer to that variable.

            We had discussed some simple algorithms like Reference Counting algorithm (where every time
            we copy a reference we increment a counter by 1.) This works and was efficienct but it 
            required extra memory to store the count and we could end up with "circular references".

            Java does something more sophisticated: it uses the Mark-and-Sweep algorithm.

            MARK AND SWEEP  
                  - Periodically, Java will "freeze" the entire program ("Stopping the world")

                  - Once it does that, it will start from all the stack allocated variables, and follow
                  every reference (and every reference of a reference), and MARK each of those variables
                  as still alive.

                  - Afterward, we "sweep up" any variables that weren't marked when we've exhausted the list
                  of available references, then "compact" the remaining variables.

            Because "stopping the world" often/for a long time is not desirable, Java tries to minimize this
            time spent marking and sweeping by estimating the expected lifetime of objects. Java spends most
            of its time checking the most recently created objects. "Survivors" eventually get moved into a 
            tenured space where they'll only be checked during "major collection events".




             EXCEPTIONS


             In Java certain operations can fail in expected ways, especially activities that require user
             input (formatting text, reading files, working with networks...) 

             Java forces programmers to recognize these situations and handle them. If the programmer doesn't,
             then the entire program crashes.
              */

              try { 

              int the_number_represented_by_this_text = Integer.parseInt("apple"); //<- java will crash with a NumberFormatException

              int attempted_addition = the_number_represented_by_this_text + 1; //<- Java wouldn't know what we even intended here
              }
              catch ( NumberFormatException e ) {
               //Handle the error


              } catch ( Exception e ) {
               // Errors have types

               // all types off of exception, must come last

               // runtime exception in method signature
              }

              /*
               * EVENTS
               * 
               * We also discussed how events help us track updates from one object to another. 
               */


               User one = new User("first_user");
               User two = new User("second_user");

               one.add_follower(two);



               one.create_post("Hello, can anyone hear this?");


               System.out.println("user_two's timeline: ");
               System.out.println(two.getTimeline());



    }

}