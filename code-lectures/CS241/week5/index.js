

/*
Objectives for Today

By the end of today, you will:
  * Define endpoints and explain how endpoints work like functions for servers.
  * Understand the GET and POST HTTP verbs and how they influence request and response data.
  * Use routes that can accept querystring parameters and post body data.
  * Practice creating multi-file projects.
  * Use a REST test client to trigger endpoints you have defined.
 */



/*
Vocabulary for the Day

Endpoint -- An endpoint is a named service that a web server exposes for public consumption. Webservers accept request
objects containing formatted data (usually in JSON), and return back a single response object (also normally
 JSON.) The path to an endpoint is sometimes called its "route".

HTTP Verb -- In addition to a route, every HTTP request also includes a special identifier, called the HTTP verb. This
  verb roughly describes what type of action is being performed in the associated request. The two most common verbs
  are GET, for requesting information from a server, and POST, for sending information to a server.

Application Programming Interface (API) -- An API is a collection of endpoints that serve as an 'interface' for
communication between a server and clients.

 */



/* Last class, we introduced the idea of importing modules in our JavaScript code, which allowed us to access functions
written by other people.

This class, we're going to take a look at using the 'express' module, which will let us create our very own webserver!


Unlike 'fs', the 'express' module isn't included with NodeJS by default. Instead we'll have to take one extra step
before we can use express... we'll need to add it to the dependencies in our package.json manifest.


Once we have added it, we can right click package.json to run "npm install", which will download all of the necessary
files at the version we have indicated. Afterward, we can import express exactly the same way we did fs:
 */

import Express from 'express';


/*
The Express object that we get when we import the 'express' module can be called as a function. The first line of
code in every webserver we create is going to be to create an "app" object by calling Express(). This 'app' will serve
as our window into creating new endpoints and actually starting up our server. In addition, we'll usually want to
define what port our server is going to listen on here.

 */

let app = Express();
let MY_PORT = 9999;



/* In a 'normal' program, we have a main method that describes some work to do. When that work is done, our program
exits. A web server is going to work slightly differently: instead, we're going to define a whole bunch of endpoints
that each handle a different type of request. Afterward, the server will sit and wait for an endpoint to be requested.


You can think of each endpoint sort of like a mini 'main' function that describes what will happen when an endpoint
is called. The parameters the function receives will be the data in the request object, and the return value it sends
back will be the data loaded into the response.



In order to help us organize our code, we're going to experiment with putting all of our related endpoints in a separate
file, using the express 'Router' object. We'll call this first file we're going to create "v1.js", to represent
the endpoints we are making during this first version of our server.
 */

import router from './v1.js';
app.use("/somepath", router);



/* At the end of our code, we have one last function call to start up our app: we need to tell the app to start listening
on a port. We will use the 'listen' method for this:
 */
app.listen(MY_PORT, () => {
    console.log("Server started on port: " + MY_PORT);

});

/*
The program doesn't do anything by itself but say it started. However, let's go see what happens when we visit the
endpoint in our browser by navigating to http://localhost:9999/somepath/welcome

We see the object we sent via the response!

Let's practice writing a few more types of requests.
 */