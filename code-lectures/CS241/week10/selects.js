/*
Objectives for Today

By the end of the day, you will:

* Understand how to use the SELECT statement to retrieve data from a database.
* Describe how to use the WHERE keyword to filter results of a query.
* Explain how the JOIN keyword lets us combine multiple tables together to ask more complex questions.
* Use select statements to implement a password authentication webservice.

 */




/* Last class, we discussed how we can use the 'pg' package to open a connection to a PostgresQL
database.
 */

import pg from 'pg';
import bcrypt from 'bcrypt';

/*
Let's also set up a basic Express app that can serve an HTML file and respond to requests.
 */

import Express from 'express';
import bodyParser from 'body-parser'


let pool = new pg.Pool({
    host: "localhost",
    port: 5432,
    user: "mobetz",
    password: "mobetz",
    database: "mobetz"
});


let app = Express();
app.use(bodyParser.json());

let router = Express.Router();
app.use('/', router);



pool.connect().then((client) => {
    /* Today, we're going to try implementing an endpoint that will allow us to check whether a user and password
    were entered correctly.
     */
    router.post("/authenticate_v1", (request, response) => {
        /*
        Our endpoint is going to get the username and password fields from the request.
         */
        let attempted_user = request.body.username;
        let attempted_password = request.body.password;


        if (attempted_user === undefined || attempted_password === undefined) {
            response.status(400);
            response.json({"message": "Invalid request, please supply both a username and password."})
        } else {

            /*
            Then, once we've done that, we can use our connection to the database to ask about our user.
             */


            /* Last week, we learned about the CREATE statement, which we could use to create brand new tables.
            If we want to ask for information from the database, then the command we will use is SELECT.

             A SELECT statement has the following format:
                      SELECT <some list of columns> FROM <some_table>

             (We can also use * to represent 'all the columns' succinctly!)
             */


                client.query("SELECT id, name, plaintext_password FROM Users;")
                .then((results) => {

                    /* Once we execute the query, we can use .then() to get back the results.
                     Each 'row' of our table becomes another object that is returned back in results.rows
                     */

                    /* For our logging in, let's assume in the beginning that the user has failed. */
                    let authenticated = false;
                    for ( let next_user of results.rows ) {
                        if ( next_user.name === attempted_user && next_user.plaintext_password === attempted_password ) {
                            authenticated = true;
                        }
                    }

                    if ( authenticated ) {
                        response.status(202);
                    } else {
                        response.status(403);
                        let ret = {"message": "Username or password incorrect!"};
                        response.json(ret);
                    }

                    response.send();
                });

        }

    });


    /*
    This first authenticate works, but maybe we don't want to go through the whole process of writing a loop and
    checking the password against each user one at a time.

    Fortunately, we can SELECT just specific data from our server as well. Let's try making a version 2 of our
    authenticate method:
     */
    router.post("/authenticate_v2", (request, response) => {

        /* Our second version of authenticate is going to start exactly the same: */
        let attempted_user = request.body.username;
        let attempted_password = request.body.password;

        /* But now, we can add something more to our SELECT:
        After we put the name of a table we want to access, we can put the word WHERE, then provide a
        list of conditions.
         */
        let username_query =  `SELECT id, name, plaintext_password 
                           FROM Users
                            WHERE name LIKE '` + attempted_user + `' 
                            AND plaintext_password LIKE '` + attempted_password + "'";


        client.query(username_query).then((results) => {

            let authenticated = results.rowCount > 0; //<- if we got results, there's a matching user and password!!!

            if (authenticated) {
                response.status(202);
                response.send();
            } else {
                response.status(403);
                let ret = {"message": "Username or password incorrect!"};
                response.json(ret);
                response.send();
            }
        });

    });


    app.get("/leaders", (request, response) => {

        /*
        We add the JOIN clause right after the table name (but before the WHERE).

        The format of the JOIN clause is:

        JOIN <second_table> ON some_key = that_key_in_the_other_table

        For our politicians search:
         */

        client.query(`
          SELECT chamber.name, chamber.num_seats, politician.first_name, politician.last_name
          FROM chamber
              JOIN politician ON chamber.leader = politician.bioguide`)
            .then((results) => {
                response.json(results.rows);
                response.send();
            });

    });


    app.get("/leaderOf", (request, response) => {

        let chamber = request.query.chamber;
        if ( chamber === undefined ) {
            response.status(400);
            response.send();
        }
        else {
            client.query(`
                SELECT chamber.name, chamber.num_seats, politician.first_name, politician.last_name
                FROM chamber
                         JOIN politician ON chamber.leader = politician.bioguide
                         WHERE chamber.name = '` + chamber + "'")
                .then((results) => {
                    if ( results.rowCount === 0 ) {
                        response.status(400);
                        response.json({"message": "Chamber you specified doesn't exist!"});
                    } else {
                        response.json(results.rows[0]);
                    }
                    response.send();
                });
        }
    });

});




app.listen(9999, () => {
    console.log("Server started (port 9999)");
})

