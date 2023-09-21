

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GreetingFormatterTest {

    /*
    In my created "test" class, I can write static methods that do my small examples I can think of:
     */

    @Test
    public void testGreetingForJohn() {

        String target_name = "John";
        GreetingFormatter f = new GreetingFormatter(target_name);
        String received_greeting = f.prettify_greeting();

        String expected_output = "Hello John!"; //<- with my small examples, I should already know what I want

        assertEquals(expected_output, received_greeting, "When these don't match, print this line of text to say John worked incorrectly!");
    }


    @Test
    public void testGreetingForNameWithSpaceInFront() {

        String target_name = " John";
        GreetingFormatter f = new GreetingFormatter(target_name);
        String received_greeting = f.prettify_greeting();

        String expected_output = "Hello John!"; //<- with my small examples, I should already know what I want

        assertEquals(expected_output, received_greeting, "Doesn't work with space in front");
    }

    @Test
    public void testGreetingForNameWithLetterA() {

        String target_name = " alex";
        GreetingFormatter f = new GreetingFormatter(target_name);
        String received_greeting = f.prettify_greeting();

        String expected_output = "Hello Alex!"; //<- with my small examples, I should already know what I want

        assertEquals(expected_output, received_greeting, "prettify_greeting() doesn't work with A");
    }

}