
/*
JavaScript in the browser will work exactly the same as JavaScript did on the server. Functions, loops, branches,
arrays and callbacks are all still available....


However, some things will behave slightly differently: for instance, we can no longer use the fs module to read from
files (in fact, the browser isn't allowed to read files at all without explicit user permission.)

Fortunately, though some things are removed, others are added.  Most importantly, browser JavaScript includes a whole
system of objects that allow us to represent each HTML tag as a node in our DOM. The 'root' of our HTML document
even lives in a variable given to us by default, called "document".

We can use this document object to do many things, from searching for elements to creating brand new ones.


The first thing we are going to try using our document variable for is adding a "listener" to the page, so that the rest
of our code can run after all the other nodes are loaded. To do so, we call the "addEventListener" method on our
document, give it the name of the event we are listening for, then provide a callback function to run when that event
completes:

 */


document.addEventListener("DOMContentLoaded", function (event) {

/*
We need to use the DOMContentLoaded event before running the rest of our code because the browser will load our
entire script as soon as it encounters the <script> tag. However, we want to be able to see and interact with all
the elements on our page!

    Once we're inside this callback function, we can access these other elements using other functions available on
    document.

    The simplest way to access elements is fetching them directly by their ID, using the "getElementById" method:
 */
    let dynamic_paragraph_tag = document.getElementById("dynamic");

    /*
    Once we've got a handle on an element, we can add, remove, and modify attributes, as well as change the inner text:
    */

    dynamic_paragraph_tag.classList.add("blue-paragraph");
    dynamic_paragraph_tag.innerHTML = "This whole paragraph was loaded <strong>dynamically</strong>, <em> even these" +
        "HTML tags!</em>";


    /* In addition to the getElementById() function, we can also use document.querySelector() and
    document.querySelectorAll() to use CSS selectors to target elements of our page:
     */

    let list_of_all_the_second_bullet_points = document.querySelectorAll("ul > li:nth-child(2)");

    for ( let element of list_of_all_the_second_bullet_points ) {
        element.style.fontSize = "12px";
    }


    let search_button = document.querySelector("#search-button");
    search_button.addEventListener("click", function (event) {

        /* For this lecture, I have created an endpoint that lets us get data about available jobs. This endpoint can be accessed
        at http://localhost:8080/api/Jobs, and accepts a querystring parameter of location. To build the URL we want
        to get data from, we could either concatenate a bunch of strings with +, or we could use the convenience class URL:
         */

        let url = new URL("http://localhost:8080/api/Jobs");

        /* We can get the value the user typed into the text box by selecting the "city_field" input we created, then checking
        * the .value property. If we wanted to, we could even change the value typed in here by assigning a new string to
        * .value! */

        let location_input =  document.querySelector("#city_field");
        let typed_location = location_input.value;


        if ( salary_spinner.value != undefined ) {
            let salary_spinner = document.querySelector("#salary_min");
            let entered_salary = salary_spinner.value;
        }

        //url = new URL("http://localhost:8080/api/Jobs?location=" + typed_location + "&min_salary=" + entered_salary);
        url.searchParams.append("location", typed_location);
        url.searchParams.append("min_salary", entered_salary);

        let promise_of_matching_jobs = fetch(url.toString(), {
            method: "GET",
            // headers: {
            //     "Content-Type": "application/json"
            // },
            // body: {
            //    "someBodyProperty": "someBodyPropertyValue"
            // }
        })

        /* Fetch doesn't give you its result right away.

          This is because we don't know how long it will take for the result to come back from the webserver (if it ever comes
          back at all.) However, instead of having you immediately provide a callback, fetch returns to you a special kind of
          object, called a "promise".

          A promise object has a method .then(), which accepts the callback you want to run when the promised operation finally
          completes. For our fetched HTTP request, the parameter we will be given is a response object, similar to the one we
          saw in our express code:
     */
        promise_of_matching_jobs.then((response) => {
            /*
            We can check the status code of the response here in this callback, and retrieve the json body of the response
            with response.json().
             */
            if ( response.status === 200 ) {
                let promised_jobs = response.json();
                /*
                However... just like with fetch() itself, response.json() isn't going to complete right away, and will
                instead give us back a promise.
                 */

                /* Fortunately, if we 'return' a promise inside a promise, this promise will also be returned by the .then()
                function, and we can continue to chain more then() calls onto the end to read from that promise when it
                is complete: */
                return promised_jobs;
            }
        }).then((job_list) => {
            /*
            Now we can actually work with the JSON data we got back from the API. Let's start by looping over the jobs we
            were returned.
             */

            let table_of_results = document.getElementById("search-results");
            table_of_results.innerHTML = "";

            for ( let job of job_list ) {
                /*
                For each job we found, we want to add a brand new row to our table. We could do this with innerHTML, but
                instead, let's get some practice creating HTML elements from scratch with code, by calling
                document.createElement(). This function takes one parameter: a string name of the tag we want to create:  */
                let table_row = document.createElement("tr");


                for ( let field of ["company", "name", "city", "state", "income"]) {
                    let text_for_this_cell = job[field];
                    let this_cell = document.createElement("td");
                    this_cell.innerText = text_for_this_cell;
                    /*
                After we've created this new element, we are not done yet...

                The tag still doesn't know where it belongs on the page. In this case, we have to obtain its 'parent'
                tag, and attach this tag with the .appendChild() method:
                     */
                    table_row.appendChild(this_cell);

                }
                table_of_results.appendChild(table_row);
            }



        })


});
});