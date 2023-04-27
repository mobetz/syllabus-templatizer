
/*
Objectives for Today

By the end of today, you will:
     * Understand how Javascript can be used to add interactive behavior to websites.
     * Identify methods from the document API that allow us to find and change elements of a website.
     * Describe how to attach functions to events which occur on a web page.
 */



/*
Vocabulary for the Day

Event - An event is a function that runs in response to something occurring (like a button click or received data.)

 */




/*
So far this semester, every time we've wanted to run some of our Javascript code, we have used Node to interpret
the instructions and the console to show output and accept input.

However, there are many different ways to run Javascript programs. In fact, NodeJS is a relatively recent addition.


The earliest way we had to run Javascript code was by attaching it to a web page. Today we are going to have a brief
exploration into some of the extra things we can (and can't!) do when we want to run Javascript code on the web.

To get us started, let us take a brief detour to talk about how web pages actually work!
 */



/*
Now that we've created our HTML page, watch what happens if we use console.log():
 */

console.log("I would to print out some text for my users to see!");
console.log("I am even writing several lines!");
console.log("I hope they all show up!");


/*
Now, let's refresh the page.

None of the text we printed showed up on the page.

Just printing text isn't going to make it show up on our page, because things on the page have to be inside our pages
system of tags. However, watch what happens if we open the "web developer console" with CTRL+SHIFT+K on Firefox or
CTRL+SHIFT+J on Chrome.

All of our printing showed up there! On a website, this little window is called our developer console, and we can even
use it to test out lines of Javascript code interactively.

 */


/*
However, what if we do want to be able to change the text on the web page.... To do this, we'll need to create an 'event'.

An event is a special callback function we write that responds to some event that happens on our web page.

To attach our event to the page, we need to use a special object that automatically exists as soon as our script is run:
the document object.

The document object has an .addEventListener() method that we can use to supply our callback function:
 */

document.addEventListener("DOMContentLoaded", function () {

    /* The 'DOMContentLoaded' event happens as soon as our web page finishes loading.

        Inside of this callback, we can do everything we want that changes some of our existing tags.
     */

     let identifiers_section = document.getElementById("identifiers");

    /*
    Getting an element by its identifier returns an object that represents that tag, which has many useful methods and
    properties we can use to change that tag:
     */
     let text_inside_section = identifiers_section.innerText;

      console.log("The identifiers section contains this text when it's loaded: " + text_inside_section);


      identifiers_section.innerText = text_inside_section + "\n We can even add extra text to the end of our section, or "
      + "delete text already there!";

    /*
    We can also re-style the section by using the 'style' property:
     */

      identifiers_section.style.backgroundColor = "black";
      identifiers_section.style.color = "bisque";
      identifiers_section.style.padding = "10px";





    /*
        Re-styling our page is nice, but what if we want to be truly interactive.

        The most complex websites also move content around or add new content in response to events besides the page loading.

        For example, we might want to do something when our user clicks a button on our page. To do this, we'll need to
        add a button to our site!


        There are two ways we could do this:
           - we could add a <button> tag on our page with an identifier
           - we could also create the button right in our Javascript here and load it in to an existing element



        Let's try out this second way, and append our button to a 'sandbox' section at the bottom of our website:
     */

    let sandbox_element = document.getElementById("sandbox");

    /*
    To create a new button in our code, we can use one more function on our document object: .createElement()
     */

     let my_new_button = document.createElement("button");


    my_new_button.innerText = "Click Me!";
    my_new_button.id = "clicker-button";
    my_new_button.style.backgroundColor = "blue";
    my_new_button.style.color = "white";


    /*
    However, creating the button is not enough.


    We can't see this button yet, because even though it exists, it's not yet added inside another tag.
    Each one of our element objects has a list of 'children', which represents all the tags inside this parent tag.
    We can add our button to this list:
     */

    sandbox_element.appendChild(my_new_button);


    /*
    Similarly, there's also a 'removeChild' that will remove an element that is already added:
    sandbox_element.removeChild(my_new_button);
     */


/*
Right now, clicking our button still doesn't do anything.

    Just like our document, our button also needs to respond to an event, specifically the event of someone clicking
    the button! We can even use a method with the exact same name to add an event to our button:
     */

    let some_counter = 0;

    my_new_button.addEventListener("click", function () {
        /*
        Anything we write inside this 'callback function' is going to happen right when the button is clicked.
         */
        some_counter = some_counter + 1;

        identifiers_section.innerText = "The button below has been clicked " + some_counter + " times.";


        /*
        We can even read the values out of textboxes:
        */
    
        let textbox = document.getElementById("some-text-box");
        console.log("Right now the textbox says: " + textbox.value);


    });







});