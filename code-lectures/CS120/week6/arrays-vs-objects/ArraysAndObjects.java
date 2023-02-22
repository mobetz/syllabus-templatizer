/*
Objectives for Today

By the end of today, you will be able to:
    * Consider appropriate situations to use arrays and classes
    * Demonstrate the comparative advantages of different complex data types
    * Identify signs that code should be creating a new class for objects.
 */


/*
Vocabulary for the Day

   Semantic Data - Semantic data is the concept that data means something beyond just the type we use in a
   program to represent it. For example, the data type of a variable may be "a string of text", but its semantic
   type may be "a first name of a student".
 */


import java.time.LocalDate;



public class ArraysAndObjects {
    public static void main(String[] args) {

        /*
        So far, we've talked about two different types of variables that allow us to store multiple pieces
        of information under a single name:

              - Classes let us store many details (possibly of different data types), all about one 'thing'
              - Arrays let us store many different instances (of exactly one type), each with one piece of information.

        The distinction here can be subtle for some people, so today we are going to investigate when to use arrays,
        when we should be creating a class, and when we should be combining both to create an array of objects.


         Say, for instance, we were writing a program that let us track the weather. Initially, we might decide we're
         going to record a few different measures. Since they're all coming from a text file, they will all initially
         be strings:
        */

        String temperature = "68F";
        String wind_direction = "NE";
        String date = "2023-02-22";


        /*
        I might remember that it is poor form to keep a bunch of distinct variables that are all part of one thing.


        Looking at this collection of information, I might incorrectly conclude that these are all strings, so they
        should all be part of an array:
         */

        String[] day_of_weather = new String[]{temperature, wind_direction, date}; //<- THIS IS WRONG!!!

        /*

        Semantically, temperature, wind_direction, and date don't all mean the same thing.

        In arrays, each item of the array is meant to be interchangeable. Usually when we create an array, the intent
        is to loop over each item in the array and repeat something for it. However in this case, the thing in the first
        slot, second slot, and third slot each have a different meaning, so a loop would be meaningless.

        */


        for (String next_field : day_of_weather ) {
            /*
            What does "next_field" even mean here?!?! Sometimes it's a temperature, sometimes it's a date...
            I could write a ton of if statements to try to parse out the different sections, but if I can't do anything
            meaningful in a loop over an array, it might be an indicator that the array is currently storing more than
            one semantic type of data, even if they are all the same type of data.
             */
        }


       /*
        When you have one piece of information with multiple parts, the correct tool is an object:
         */

        WeatherDate a_day = new WeatherDate(Integer.parseInt(temperature), wind_direction, LocalDate.parse(date));

        /*
        Now, each 'part' of the day's weather is correctly associated with a semantic name that identifies its
        purpose. I can say that the date is available by getting the "Date" property, rather than having to remember
        that it's stored in day_of_weather[2], since 2 has no inherent connection to dates.
         */

        String arrays_date = day_of_weather[2]; //<- What is [2], why is it a date?!?!?!!?
        LocalDate weather_date = a_day.getDate(); //<- getDate() is very clearly related to dates, and I know what to expect


        if ( weather_date.isEqual(LocalDate.now())) {
            System.out.println("This is today's weather: " + a_day.getTemperature());
        }



        /*
        Sometimes this mistake is also made in the other direction. Say, for instance, we were tracking a bunch of
        group members. We might decide to create an object that represents the group, and create attributes to store
        each member of the group:
         */


        Group my_group = new Group("Joe", "Mary", "Sally", "Steven", "Kenisha");
        /*
        Let's see how we would implement these attributes on the class...
        */





        /*
        As one last example, let us pretend we were writing a program to track dogs in a daycare. We might build up
        multiple different arrays, each storing a different detail:
         */



        String[] dog_names = new String[] { "Fido", "Spot", "Skipper", "Allie" };        // \ once again....
            int[] dog_ages =    new int[] {      4,      5,        11,    7    };        //  > THIS IS WRONG
        String[] dog_breeds = new String[] { "Lab", "Beagle", "Retriever", "Samoyed" };  // / (at least if you're doing
                                                                                         // it more than temporarily...)



         /*

        This is wrong because now, we've split up all the data related to one dog.

        In this example, there are multiple pieces of information that we will typically want to access together.
        For example, if we want to find Fido's age, then first we have to find Fido in the "dog_names" group, and
        then hope that index in the "dog_ages" group refers to the same dog.


         One way to think about this, is it's almost like we're dividing up our table of information based on 
         column instead of based on row:


                          Dogs    |   Name    |  Age    |  Breed
                        ---------------------------------------------
                            1     |  Fido     |   4     |  Lab  
                            2     |  Spot     |   5     |  Beagle
                            3     |  Skipper  |   11    |  Retriever
                            4     |  Allie    |   7     |  Samoyed


       */


        for ( int i=0; i<dog_names.length; i++ ) {
            String dog_name = dog_names[i];
            if ( dog_name.equals("Fido")) {
                int fidos_age = dog_ages[i]; //<- we're using "i" on the wrong list here...
               System.out.println("Fido is: " + fidos_age + " years old.");
            }
        }


        /*
        I have to assume that "i" will be the right dog in a different list!!!
        If we are adding or removing dogs, it will be really easy for the lists to become out of sync.

        Any time you are using 'i' from one list to talk about the 'i' in a different list, it's a sign you should
        probably be using objects.
        */

        Dog[] daycare = new Dog[]{
                new Dog("Fido",         "Lab",  4),
                new Dog("Spot",       "Beagle", 7),
                new Dog("Skipper", "Retriever", 11),
                new Dog("Allie",     "Samoyed", 7)
        }


        /*
        Now, all the information that belongs to one dog lives together and when we loop over our group, we have all
        the information we need:
         */


        for ( Dog next_dog  :  daycare ) {
            if ( next_dog.getName().equals("Fido")) {
                System.out.println("Fido is " + next_dog.getAge() + " years old.");
            }
        }


        /*
        Knowing when to use arrays and objects can help us clearly maintain the semantic meaning of our data and
        leave our variables easier to use and maintain in the future!
         */


    }
}

