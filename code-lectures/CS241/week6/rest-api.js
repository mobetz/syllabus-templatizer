
import Express from 'express';
import bodyParser from 'body-parser';

const router = Express.Router();


/*
In our previous example of endpoints, we had arranged many of our endpoints around the idea of actions like
"performing addition". This is perhaps the most intuitive way for us to design endpoints, especially if we think about
endpoints as "remote functions".

However, it is not always the most effective. Action-based requests were popular in the early web, but often ended up
being very hard to use, and hard to maintain on the server side.


In the early 2000s, Roy Fielding designed a series of rules that he believed would help simplify the way people write
server code. He named his set of rules "Representational State Transfer". In the decades since, REST has become one
of the predominant ways we talk about communication between servers and clients on the web.


There is a lot of misinformation out there about REST. However, we can talk about REST broadly as a few specific
principles:


   1) APIs should be organized around CRUD operations on Resources.
       With action-based APIs, it is common for only parts of data objects that are relevant to a particular action to
       be returned to a user when they make a request. For example, a /getPoliticansByParty endpoint might only return
       a list of politician names, rather than full politician objects. We might then have a separate
       /rankPoliticiansByAge endpoint that returns a sorted list of name-age pairs for each politician, and yet another
       /registerNewPolitician endpoint that takes an entirely different object for creating new politicians.

        Each of these endpoints needs custom response objects, and there's no guarantee to the user what fields they get
        back for each.

       In REST, instead of creating endpoints that sound like verbs, we instead focus on endpoints that work like nouns:
       we would have one endpoint /Politician. If we want to read information about polticians, we always GET /Politician,
       and use querystring parameters to sort or filter.

       Crucially, if we wanted to create a new politician, we would POST /Politician with an object that is formatted
       identically to the ones we received with GET (Reads have enough information to teach us how to Create and Update).


   2) APIs should be 'discoverable'.
       With action-based APIs, it was common to write a large document with a description of every action that could be
       performed. Users had no way to know what endpoints are available to them without referencing this giant list.
       What's more, if data returned about one object referenced data from another object, this data would be duplicated
       into the response, but users would still have no idea this other object existed.

       In REST, if you get data about a resource that references another resource, you are also provided a link to that
       other resource. For example, say we had our politicians, and each politician had information about a Congressional
       District. We might see:


       GET /Politician/W000817
       {
          "name": "Elizabeth Warren",
          "chamber": "senate",
          "party": "D",
          "congressionalDistrict": "http://localhost:6000/CongressionalDistrict/MA02"
       }


       This would allow us to "discover" that there was another endpoint called a congressional district,
       and we could then call:

       GET /CongressionalDistrict/MA02
       {
          "state": "MA",
          "type": "senate",
          "senator": "http://localhost:6000/Politician/W000817"
       }

       As long as we know that either one of these two things exist, we automatically know how to use both! This
       principle is sometimes also called "Hypermedia as the engine of application state" (HATEOAS).



    3) Everything needed to handle a request should be included in that request. (Requests are "stateless")
      With old action based APIs, it was common for the first action a client performed to be /authenticate. Once
      a user had authenticated, they received a special password they could use when making every other request.
      Meanwhile, the server had to keep track of every user that had recently logged in and delete those passwords when
      the logins timed out. Likewise, a user might have to make a series of requests to finalize the creation of a
      resource (for instance, call /registerSenatorStep1, then /registerSenatorStep2, then /finishRegistration.), and
      the server would keep track of the intermediate data between each request.

      In REST, each request is "atomic" -- it is its own start and end. If a user needs permission to do something, they
      provide their login credentials as part of the request. If a resource needs to be created, that entire resource is
      sent at once.



    4) Resources should be able to be used more than once (they should be "cacheable".)
      As the internet has grown more complex, it's become common for single computers to not be able to handle all the
      users who want to use a site at once. Given that all the other rules of REST are followed, a request with the same
      parameters will tend to return the same result (at least at a given point in time.) As a consequence, sites are
      able to build "content distribution networks" that all mirror data that was requested once, to "balance the load"
      on the central database.

 */

/* An HTTP request and response is more than just the data we get back. We already briefly looked at headers last week.



In truth, every HTTP request/response is just a large, specially-formatted text file. For example, when we make a request
against a /Politician endpoint, the request the browser actually sends looks something like this:

GET /Politician HTTP/1.1
Host: localhost:6000
User-Agent: Mozilla/5.0 (Linux) Firefox/51.0
Accept:  text/html, application/json, image/png, audio/mpeg
Accept-Language: en-US

In this request, we are saying we want to perform the GET verb against the /Politician endpoint. Each of the "headers"
describes something about our request, such as what browser we're making the request from, what types of responses we're
ready to accept, or what language we want a response in.

When we get a response back from the server, it actually looks something like this:

HTTP/1.1 200 OK.
Content-Type: application/json; charset=utf-8
Date: Tuesday, 23 Feb 2021 10:05:03 GMT-4

{
  "name": "Elizabeth Warren",
  "chamber": "senate"
  ....
}


Let's try this out by making a simple request in telnet, a program that lets us send text directly to servers.
A simple request would be to open a connection via telnet to google.com on port 80, then type in just: GET / HTTP/1.1


Notice that first line in the response: that line contains some very important information that tells us how to
interpret the rest of the response: the "status code".


Each status code is a three digit numeric value. Broadly speaking, the first number tells us what 'category' of response,
and then the next two digits let us surface extra information.


Some common status codes:

    2xx - Successful Responses
    200 - The standard response for a generic successful request.
    201 - a resource was successfully 'created'
    202 - information was accepted, but it has not finished processing
    204 - response was successful, but no response should be expected.

    3xx - "Redirection"
    301 - The resource has moved to a new URL (usually provided in the body)
    307 - Temporary redirection (for instance, because a server is too busy)
    308 - Permanent redirection (for instance, this server is being decommissioned and a new one was purchased.)

    4xx - User Errors
    400 - Bad Request (a request was formatted improperly or didn't include the correct data)
    401 - Unauthorized (a user requires special permissions, but no authentication data was provided at all)
    403 - Forbidden (a user provided authentication data, but doesn't have permission)
    404 - Not Found (the user asked for a resource that doesn't exist.)

    5xx - Server Errors
    500 - Internal Server Error (something unspecified went wrong in your endpoint code.)
    501 - Not Implemented (server is unfinished and this endpoint has not yet been created.)
    502 - Bad Gateway (the HTTP server is incorrectly configured and can't figure out how to route to this endpoint.)
    504 - Gateway Timeout (the server is too busy and can't handle this request.)


In express, we can set our status code by using the .status() method on our response object:
 */

router.get("/", (request, response) => {
    response.status(400);
    response.json({"ErrorMessage": "Please visit an actual Resource like /Politician"});

});


/*
You may have noticed when we were talking about RESTful architecture, we had used endpoints to talk about a type of
resource, and then a slash and an individual resource identifier, for example:

   GET /Politician          -- get information about all politicians (perhaps filtering with querystring parameters)
   GET /Politician/W000817  -- get information about the individual politician identified by W000817.

In order to implement this type of request, we need to use a "wildcard" in our route. In Express, we specify a wildcard
by putting a colon before a variable name, for example:
 */


router.get("/Politician/:bioguide",  (req, res) => {

    /*
    Once we've created a route with a wildcard, we can get that wildcarded parameter out from the request object in
    the 'params' collection:
     */

    let politician_id = req.params.bioguide;

    if ( politician_id == "W000817" ) {
        let found_politician = {
            "name": "Elizabeth Warren",
            "chamber": "senate",
            "state": "MA",
            "party": "D",
            "age": 71
        }

        res.status(200);
        res.json(found_politician);
    } else {
        res.status(404);
        res.json({"ErrorMessage": "Unable to find the politician with ID " + politician_id});
    }
});

/*
Requests that tend to (or possibly could) return one or more answers are usually implemented on the base resource
as querystring "filtering" parameters. For instance, if we wanted to filter and get back a list of all politicians in
a particular party:
 */
router.get("/Politician", (req, res) => {
    let party = req.query.party;
    //Use this party to filter and build an array:
    let results = []
    //...
    res.status(200);
    res.json(results);
})

/* As we start building more sophisticated REST APIs, we might start creating these endpoint handlers as a normal part
of each class that we create to represent a different concept in our application. Each might live in its own file, and
the router file here just loads each one of our classes into its associated endpoint. This lets us easily and flexibly
create servers that can handle many different types of data.
 */


export default  router;