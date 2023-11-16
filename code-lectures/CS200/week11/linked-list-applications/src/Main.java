/*
Objectives for Today

By the end of today, you will:
     * Define several common data structures built from linked lists.
     * Practice insertion and removal operations on linked lists.
     * Understand the strengths and weaknesses of various linked list-based data structures.
 */


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        /*
        Last class, we learned about the linked list: this data structure presented to us
        a way to store a group of things as a daisy-chain of pointers, where each object knew
        about the "next" object in the list:

          +--------+    +--------+    +--------+    +--------+    +--------+
          | Obj1   |    | Obj2   |    | Obj3   |    | Obj4   |    | Obj5   |
          |  next ----->|  next ----->|  next ----->|  next ----->|  next -----> ... ---> null
          +--------+    +--------+    +--------+    +--------+    +--------+

        we saw that this data structure was incredibly flexible when adding and removing
        single items from a known point (but that it struggled with handling many arbitrary
        insertions and deletions.)

        Near the end of the lecture, we suggested that most linked lists only offer a subset
        of the operations we implemented. Today, we are going to name a few of the most common
        linked list patterns that people use in real applications!

        All of our linked lists will need to use nodes to represent their items, so let's make ourselves
        a class: a SinglyLinkedNode.


        The first linked list application we are going to look at is a Stack:
         */

        Stack<String> list_of_letters = new Stack<String>();

        list_of_letters.push("a");
        list_of_letters.push("p");
        list_of_letters.push("p");
        list_of_letters.push("l");
        list_of_letters.push("e");

        System.out.println("Added the letters 'apple' to the stack!");

        /*
        A stack lets us push things onto the front, then pop things off of the front in reverse order.
         */
        System.out.println("Things get removed from the stack in this order:");
        String next_letter = list_of_letters.pop();
        while ( next_letter != null ) {
            System.out.print(next_letter);
            next_letter = list_of_letters.pop();
        }

        /*
        This makes stacks good for implementing:
            - easy reversal
            - "breadcrumb trails" (undo, browser history, function calls!)

          If you've ever wondered why the debug messages in a programming language are called a "stack trace",
          it's because we use a Stack to store them -- we have to exit each function in the opposite order we
          entered it!
         */



        Queue<Order> order_queue = new Queue<>();


        order_queue.add(new Order("Breadsticks"));
        order_queue.add(new Order("Salad"));
        order_queue.add(new Order("Pasta"));
        order_queue.add(new Order("Dessert"));


        System.out.println("\n\nAdded Breadsticks -> Salad -> Pasta -> Dessert to the queue!");

        Order next_order = order_queue.remove();
        while ( next_order != null ) {
            System.out.println("  " + next_order);
            next_order = order_queue.remove();
        }

        System.out.println("All meals arrived!");

        /*
        With a queue, the items are removed in the same order they arrived!


        This makes queues ideal for implementing:
            - scheduled tasks/to-dos
            - wait lists
            - pipelines/streams

         Queues have the nice property that as long as the queue continues moving,
         nothing can ever be "starved" -- with a Stack, it's possible the
         stack never "catches up" and the first thing added sits unemptied forever,
         even if things are continuously being pushed and popped.


         */

        Queue<String> task_list = new Queue<>();
        task_list.add("clean the bathroom");
        task_list.add("cook dinner");
        task_list.add("shop for groceries");
        task_list.add("repair the stove");
        task_list.add("get gas for the car");
        task_list.add("drive grandma to the doctor");
        task_list.add("replace the batteries");
        task_list.add("reset the clocks");
        task_list.add("write new chores");

        Ring<HouseholdMember> people = new Ring<>();
        people.add(new HouseholdMember("Bob"));
        people.add(new HouseholdMember("Sally"));
        people.add(new HouseholdMember("Shane"));

        System.out.println("\n\nNow rotating through chores!");
        String current_task = task_list.remove();
        while ( current_task != null ) {
            HouseholdMember person = people.take();
            System.out.println("  " + person + "'s turn -- " + current_task);
            current_task = task_list.remove();

        }

        /*
        Circular linked lists are great for:
            - taking "turns" in order
            - dividing up load
            - round robin scheduling

         Our Ring class shows us how flexible linked lists can be. As long as we have
         a process where insertions and removals happen at well-defined locations,
         linked lists let us create data structures that can do those operations in a
         single step!
         */


    }
}
