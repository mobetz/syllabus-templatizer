
/*
Just like how jQuery gave us access to functions, React also gives us several new functions we can use in our code.


However, while jQuery gave us a smattering of random functions that duplicate functionality available already, all
of the React.js functions are organized around a common theme: React is a 'web framework' that lets us create
reusable HTML components that we want to appear on more than one page.

Note: we're going to be focusing on one way to use React (using the "JSX" extensions, with "function" components.)
React is a very large library with a huge number of features, but we'll only be just scratching the surface of it
to understand why it exists.

The "JSX" version of React adds a few extra features to our Javascript language. In order to use these, we set
the type="text/babel" attribute on this file before we attached it to our webpage. This will allow another one
of the libraries we imported (babel.js) to convert the code we write here back into normal Javascript.

 */


/*
The most important thing that React adds is that it allows us to write brand new fake HTML tags as part of our
Javascript code.  Each new 'tag' is going to be described as a function:
 */

function CustomSidebar() {
    /*
    Then, inside the function, we can return a string of text that represents all the 'hidden' code of our
    HTML tag. In JSX, we can even 'elide' the quotes around strings that represent HTML:
     */
    return (
        <nav>
            <link rel="stylesheet" href="customsidebar.css" />
                <ul>
                    <a href="index.html"><li>Frameworks</li></a>
                    <a href="second.html"><li>Second Web Page</li></a>
                    <a href="https://google.com"><li>Google</li></a>
                    <a href="https://developer.mozilla.org/en-US/"><li>MDN</li></a>
                    <a href="https://www.w3schools.com"><li>W3Schools</li></a>
                </ul>
        </nav>
    );

}


/*
Once we've created this fake tag, we can add it to our webpage by attaching it with React's 'render()' function:
 */

let existing_html_parent = document.querySelector("#nav-container");
ReactDOM.render(<CustomSidebar></CustomSidebar>, existing_html_parent);

/*
The benefit of doing this is that now, if we want to change our sidebar, we only have to change it in one place,
even if we have more than one webpage.
 */