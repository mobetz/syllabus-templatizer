
/*
Objectives for Today

By the end of today, you will:
  • Describe Representational State Transfer (REST) and what it means for the design of web APIs.
  • Identify common HTTP status codes and understand how status codes allow us to communicate information to clients.
  • Practice using wildcarding to make resource-based requests.
 */

/*
Vocabulary for the Day

Representational State Transfer (REST) -  REST is the idea that web APIs should be organized as actions performed on
   'resources'. An API that follows the rules of REST is described as "RESTful".

Status Code - A status code is a special header that is transmitted at the beginning of an HTTP response. Status codes
   contain a number and a brief phrase which communicate whether or not a request was successful, as well what type of
   error occurred (if any.)

Create-Read-Update-Delete (CRUD) - The four CRUD operations form the basis of most data-based interactions. In web
   servers, these four actions roughly translate to the HTTP verbs POST, GET, PUT, and DELETE, respectively.
 */



/*
Last class, we learned about using the 'express' library to create endpoints.

Each endpoint was like a function that a server knew how to run, which could be called from a client.

This class, we're going to get more practice working with our endpoints and talk about some strategies for making
endpoints that are a bit easier to discover and use.
 */


import Express from 'express';
import restApi from './rest-api.js';

const app = Express();

app.use("/", restApi);

app.listen(8745, () => {
    console.log("Server started! Listening on port 8745.");
});

