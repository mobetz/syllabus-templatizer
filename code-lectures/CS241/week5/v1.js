

/* Inside this file, we will again need to import our Express object: */
import Express from 'express';

let router = Express.Router();


/* This router object will let us define endpoints, using the .get() and .post() methods. Both of these methods take
* two arguments:
    - First, they take a path to the endpoint on our server.
    - Second, they take a callback that describes the code that will happen when our endpoint is executed. The
       callback function will have two arguments: a request object, and a response object.
*/


/* The simplest endpoints are GET endpoints with a static route. A GET endpoint is an endpoint that a user can make a
request from to read some data. We can think of these sort of like functions that just do some work and return a value:
 */
router.get("/welcome", (request, response) => {

    /* Like any other function, we might decide to start constructing our return value by declaring a variable for it
    at the top of the function:
     */
    let returned_object;

    returned_object = {
        "text": "When we create an endpoint, we can send back any Javascript Object as JSON!",
        "addendum": "This object can have as many properties as we need.",
        "data_types": ["strings", 1234, {"note": "even complex properties like lists and objects of objects!"}]
    };

    /* Once we've done all the work required in our method, we can use the .json() method on the response callback
    parameter to set our return value. Afterward, we can send off that response with response.send().

    NOTE: this is how we 'return' from endpoints. WE WILL NOT USE THE 'return' KEYWORD!
     */

    response.json(returned_object);
    response.send();
});

/* Sometimes, we want to write a 'get' method that can accept some options.

To do this, we can make use of 'querystring' parameters. Querystring parameters are specified in the URL using a
?, then a bunch of key-value pairs separated by = and &.

Our route declaration will look exactly the same as any other GET:
 */
router.get("/addition", (request, response) => {

    /* Then inside the callback method, we can access querystring parameters provided to us with request.query: */

    console.log("User is requesting to add: " + JSON.stringify(request.query));
    //NOTE: console.log happens in your SERVER not the BROWSER

    /* Note: we can't say for sure whether or not our user is going to provide all of the data we expect in
    the querystring. It's good practice to test that the parameters you expect exist before you try to use them.
     */
    if ( isNaN(request.query.first) || isNaN(request.query.second)) {
        response.status(400);
        response.json({"ErrorMessage": "You did not provide two numbers to add with 'first' and 'second'"});
    }
    else {

        let first_num = parseFloat(request.query.first); //NOTE: all querystring parameters are Strings, you need to convert
        let second_num = parseFloat(request.query.second); // where necessary
        let sum = first_num + second_num;

        response.json({"sum": sum});
    }
});



/* Sometimes, we have requests a user wishes to make where they have data that won't fit in the query string.

For instance, they may want to upload a file, or they may have a complex object we don't want to encode/decode as a
string. For these types of requests, we can create a POST endpoint. POST endpoints specialize in receiving data with the
intent of changing the state on a server. In order to read the body from a post, we will need one more import, the
bodyParser plugin of express:
*/


import bodyParser from "body-parser";
router.use(bodyParser.json());

let uploaded_things = [];

router.post("/upload", (request, response) => {

    // Inside the callback, we can access all the uploaded data by looking at the 'body' property on our request object:
    console.log("Received an upload with body: " + JSON.stringify(request.body));

    uploaded_things.push(request.body);

    response.json({"Message": "Upload Successful!"});
});


/*
Actually testing this will be a bit harder than testing our 'get' examples. Until we learn how to write javascript in
the browser to make POST web requests directly, we'll need to use a special piece of software called a REST client
to make these requests for us. There are many browser plugins that give us REST clients, such as:

   RESTer  - https://github.com/frigus02/RESTer
   Postman - https://www.postman.com/downloads/

Once opened, we can set the HTTP verb and endpoint we want to test, as well as provide things such as the included POST
body. Let's try it out!
 */



router.get("/pastUploads", (request, response)=> {
    response.json(uploaded_things);
})


router.get("/checkPrice",  (request, response) => {

    let found_price = -1;
    let item_searching_for = request.query.grocery;


    for ( let next_grocery of uploaded_things ) {
        if ( item_searching_for === next_grocery.name) {
            found_price = next_grocery.price;
        }
    }


    response.json({"price": found_price});

});


/* Unlike Java, JavaScript has no 'public' or 'private' keywords. When we're importing data between files, we use
the 'export' keyword to say we want to be able to import something from our file elsewhere. We're going to export our
'router' object that has all of our handlers as our 'default' export:
 */
export default router;