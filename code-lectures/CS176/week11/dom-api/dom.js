
/*
Last week, we learned how we can use Javascript to give four simple types of instructions:
     - saving information in a variable (assignment)
         e.g   let first_name = "Matt";
     - making a branch that only happens 'if' a condition is met (if-statements)
               if ( it_is_sunny ) { //do stuff  }
     - repeating something 'while' a condition is met (while-statements)
               while ( its_still_sunny ) { //this will repeat until its no longer sunny }
     - creating and using a whole group of instructions by name (functions)
               function add_two_numbers(x,y) { return x+y; }
               add_two_numbers(4,5);


However, all of the JavaScript we've written so far has been fairly self-contained. Today, we'll be learning about a
few functions that are included with javascript that let us interact with our HTML and CSS more directly.

Before we get to that, we need to cover two more features of JavaScript that are commonly used in these functions.



First is the idea that JavaScript functions are also 'variables'.

When we say that a JavaScript function is a variable, what we mean is that we're allowed to move it around our program.
Most importantly, we're able to give it to another function:
 */

function greet(name) {
    return "Hello " + name;
}

function depart(name) {
    return "Goodbye " + name;
}

console.log(greet("Jim"));


function perform_action(action, person) {
    //^ the "action" parameter of this function is going to be another function!!
    // This means I can call it just like I would call any other function:
    let response = action(person);
    return response;
}


let salutation = perform_action(greet, "Tommy"); //<- here we're sending the function "greet", not calling it -- there are no parentheses.


/*
     Our 'action' in this example is what's called a "callback function".

     Callback functions are great because they let us specify fragments of behavior we want to happen as part of a bigger
     process. One of the most common uses is that they let us describe something we want to happen after some other work
     is done.

     The other thing we need to discuss is the idea of an 'object'. In JavaScript, an object is just a bundle of related
     functions and data that we give a name. The most common way we create an object is with {curly braces} (though
     for those of you coming from Programming I, we could also use the 'class' keyword):
 */

let a_person = {
    first_name: "Matt",
    last_name: "Obetz",
    role: "Teacher",

    say_hello: function () {
        return "Hello everyone!";
    }
}

/*
Once we have an object, we can access a part of that object by putting a dot after the object's name. Watch what happens
when we put a dot after our person:
 */

console.log("A_person's first name is: " + a_person.first_name )

/*
   Our IDE gave us autocomplete suggestions for all the things our object knows about. We can even call one of the functions
   attached to the object:
 */

let persons_greeting = a_person.say_hello();
console.log("A person's greeting is: " + persons_greeting);



/*
    For today, we're going to work with objects that were created for us more often than ones we've made ourselves, but
    it's useful to understand how objects work.

    The first object we'll learn about today is the 'document' object. The document object is a special object that
    represents our entire HTML document. This object has many useful functions that let us interact with our HTML.


    For instance, if we look at document.body, we can see the body tag and all the content rendered to our webpage!
    We can also use document.styleSheets to see our list of CSS style sheets!

    However, the first thing we want to look at is a special function on our document: addEventListener. Our document,
    (and every other HTML element's object we're about to see) can use this function to make a function run when an event
    occurs on the page.


 */
document.addEventListener("DOMContentLoaded", afterLoaded);

function afterLoaded() {
/*
Usually we will put most of the code that affects our HTML page here in DOMContentLoaded, because we want to make
sure the elements all exist before we start trying to modify them.

    The next function we'll look at on the document object is getElementById()
    getElementById() lets us find an element on our webpage by ID:
     */

    let content_section = document.getElementById( "content" );

    /*
    There's also a more powerful version of this function called querySelector(), that lets us use any CSS selector!
     */
     let objectives_header = document.querySelector(" #objectives > h3 ");


    /*
    Once we have an element from our querySelector, we can do many things to it, like change its style object or inner
    text:
     */
    content_section.innerText = "This text and color was added with JavaScript!";
    content_section.style.backgroundColor = "purple";


    /* This is useful because we can set the value not just to some known color, but even to a color the user picks! */

        let color_picker = document.getElementById("pick_content_color");
        color_picker.addEventListener("input", function (newColor) {
            content_section.style.backgroundColor = color_picker.value;
        });


    /*
    But perhaps the most powerful thing we can do is append more child elements. To do that, we first have to create
    those elements with the document.createElement() function:
     */
    let new_div = document.createElement("div");

    new_div.id = "created-div";
    new_div.innerText = "This div was created from nothing!";

    /*
    Once we've created our element, we still have to add it to the document with appendChild() on an existing element:
     */

    content_section.appendChild(new_div);

    document.styleSheets[0].addRule("#created-div", `
       width: 400px;
       height: 400px;
       background-color: white;
       margin: auto;
       padding: 20px;
       color: black;
    `)


    /*
    One of the neat things about accessing elements in JavaScript is that we can also addEventListeners to all of them:
     */

    new_div.addEventListener("click", whenBoxClicked);

    function whenBoxClicked() {
        let r = Math.floor(Math.random() * 255);
        let g = Math.floor(Math.random() * 255);
        let b = Math.floor(Math.random() * 255);

        new_div.style.backgroundColor = "rgb(" + r + ", " + g + " , " + b + ")";
    }



    /*
    One other really useful application of JavaScript on HTML controls is that it lets us read and adjust form controls:
     */


    let input_box = document.createElement("input");
    input_box.type = "text";
    input_box.style.margin = "30px";
    content_section.appendChild(input_box);

    input_box.placeholder = "Enter some text here...";

    input_box.addEventListener("keypress", function whenInputTyped() {
        new_div.innerText = "The textbox now reads: " + input_box.value;
    })







    let current_image = 0;

    let next_button = document.querySelector("#next");
    let back_button = document.querySelector("#back");
    let image_carousel = document.querySelector("#carousel");

    next_button.addEventListener("click", function goNextSlideshow() {
        current_image = current_image + 1;

        if ( current_image > image_carousel.children.length - 1 ) {
            current_image = 0;
        }

        for ( let image of image_carousel.children ) {
            image.style.display = "none";
        }

        image_carousel.children[current_image].style.display = "block";

    });


    back_button.addEventListener("click", function goBackSlideshow() {
        current_image = current_image - 1;

        if ( current_image < 0 ) {
            current_image = image_carousel.children.length - 1;
        }

        for ( let image of image_carousel.children ) {
            image.style.display = "none";
        }

        image_carousel.children[current_image].style.display = "block";

    })


}
