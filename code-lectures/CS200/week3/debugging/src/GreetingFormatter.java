

public class GreetingFormatter {

    private String person_greeted;

    public GreetingFormatter(String target) {
        target = target.trim(); //<- let me remove whitespace from the ends of any name that the user input
        this.person_greeted = target;
    }

    /**
     * Takes the person this Greeter was built for, and returns a title cased "Hello <Person>!" style greeting string.
     * @return
     */
    public String prettify_greeting() {
        char first_letter = this.person_greeted.charAt(0);

        if ( first_letter > 'a' && first_letter < 'z' ) {
            //If I've narrowed down the problem is in the if statement, I can try this
            System.out.println("INSIDE IF STATEMENT");
            this.person_greeted = capitalize_first_letter();
        }

        String greeting = "Hello " + this.person_greeted + "!";

        return greeting;
    }


    private String capitalize_first_letter() {
        this.person_greeted = this.person_greeted.substring(0,1).toUpperCase() + this.person_greeted.substring(1).toLowerCase();
        //With print debugging, I can try looking here to see if my function works:

        System.out.println("AFTER I CAPITALIZE, ITS NOW: " + this.person_greeted);

        return this.person_greeted;
    }
}
