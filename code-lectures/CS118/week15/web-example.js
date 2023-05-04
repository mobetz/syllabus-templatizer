

document.addEventListener("DOMContentLoaded", () => {
    /*
    We also learned about attaching Javascript to a web page.

    We're not able to work with the console or import a lot of our NodeJS libraries in browser JavaScript,
    but instead, we have access to several special Browser APIs that let us modify elements on our web page:

     */

    let tag = document.getElementById("target_id"); // lets us find an element with the id "target_id"

    let newly_created = document.createElement("section"); //creates a brand new <section> tag.

    newly_created.innerText = "This section has some text." //adds text inside the newly created section.

    newly_created.style.backgroundColor = "pink";
    newly_created.style.color = "black";            //set styling information for our tag.
    newly_created.style.padding = "4px";

    tag.appendChild(newly_created) //attaches our section to the existing #target_id tag

    let input = document.querySelector("#userinput");
    let some_button = document.querySelector("#some_existing_button");

    some_button.innerText = "Click Me!";


    some_button.addEventListener("click", () => {
        newly_created.innerText = "When the button was clicked, the text box next to it said: " + input.value;
    })   // make code run when an 'event' like a button click occurs.


});