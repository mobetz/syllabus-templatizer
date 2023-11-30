
/*
So far, all of the Javascript code we've worked with has either been code we've written ourselves, or functions
that are included by JavaScript by default.

However, sometimes we want to use functions written by someone else. When this is the case, we can import 'libraries'.

When we want to import a library, we just add a script tag to our HTML file. For example, if we want to use JQuery,
we just add:

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            crossorigin="anonymous"></script>


Once we've done that, our code now has access to all the functions provided by jQuery.


So just what *is* jQuery? It's just a set of convenient 'shortcut' functions for doing things that are already part
of Javascript, HTML, or CSS. These shortcuts are all organized around searching for HTML elements using CSS selectors.


The most basic function jQuery gives us is the $() function:

 */


document.addEventListener('DOMContentLoaded', function () {
    let slide_panel = $('#slide-panel');  // this $() function works *almost* like document.querySelector('#slide-panel').
    let html_slide_panel = document.querySelector('#slide-panel');

    /*
    However, there is one difference between an element fetched with jQuery, and an element fetched with querySelector().
    When we $() select an element from our page, we get access to all the other jQuery functions. These functions
    largely map to functions we've learned already:
     */

    html_slide_panel.addEventListener("click", function () {
        //do stuff when the panel is clicked
    });


    let slide_content = $('#slide-content');

    slide_panel.click(function () {
        /* When our slidepanel is clicked, it will fire click()'s callback the same way that an addEventListener callback
        is called.  We also have access to a bunch of animation-related functions, such as slideToggle */

        slide_content.slideToggle("slow");
    })

/* In addition to animations, jQuery does also give us the ability to style our elements similarly to using the .style
object on our normal HTML-fetched elements.*/

    //html_slide_panel.style.border = "1px solid black";
    slide_content.css("border", "1px solid black");


    /* There are many other animation functions, for other common effects like fading in, resizing, or moving...*/
    $('#fade-panel').click(function() {
        $('#fade-content').fadeToggle("slow");
    });


    /* In general, jQuery doesn't let us do anything we couldn't already do with HTML/CSS/Javascript, it just
    gives us a slightly shorter way to do it.

    We can see more jQuery functions at: https://api.jquery.com/

    There are also 'jQuery plugins' that extend jQuery even further, such as the slick carousel:
    https://kenwheeler.github.io/slick/

    */

});