/*
Objectives for Today

By the end of the day, you will:

* Understand how to use the INSERT statement to add data to a database, and the UPDATE statement to modify existing data.
* Describe how to use the 'express-session' library to handle user session tokens.
* Practice writing a few INSERT and UPDATE statements on our sample tables.

 */


/*
Vocabulary for the Day:

Token - A "token" is a secret string of characters or secure random number that identifies its bearer.

Session - A "session" is a temporary interaction between two devices. In web programming, a 'session' is created
when a user logs in, and is maintained as long as the user's session token is valid.
 */




/*
Last class, we made a simple app that allowed us to verify a user against a database and showed information
we had retrieved from the database.

This week, we are going to extend that app, and add in the ability to register for accounts, as well as the ability
to visit a "login-only" control panel page.

To start, our app will use all the same code as last time:


 */




import Express from 'express';
import bodyParser from 'body-parser'

let app = Express();
app.use(bodyParser.json());
app.use(Express.static("./static"));



/*
However, we are going to add one more library: express-session is another 'middleware' library, similar to
the body-parser we have been using for several weeks.

express-session will give us an easy way to validate when a user has already logged in:
 */
import session from 'express-session';

let session_details = session({
    secret: "some_unknwon_secret_password", //some secure password used to encrypt our cookies
    cookie: {
        maxAge: 1000 * 60 * 30 //milliseconds to allow a cookie to persist/a user to stay logged in (1000ms * 60s * 30min)
    },
    resave: true,
    rolling: true, //whether logout time should be updated every time the cookie is reused
    unset: 'destroy', //whether deleting a session in the request should also delete the session permanently.
    saveUninitialized: true
});
app.use(session_details);





import auth from './authenticate.js';
app.use("/", auth);


/*

Today, we're going to add another route for our new endpoints: register.
This router will hold endpoints that let us create brand new users in our database:
 */


import register from './server_registration.js';
app.use("/", register);



app.listen(9999, () => {
    console.log("Server started (port 9999)");
})
