

import pg from 'pg';



import Express from 'express';
import bodyParser from 'body-parser'


let pool = new pg.Pool({
    host: "localhost",
    port: 5432,
    user: "mobetz",
    password: "mobetz",
    database: "mobetz"
});

let router = Express.Router();



pool.connect().then((client) => {
    router.post("/authenticate_v1", (request, response) => {
        let attempted_user = request.body.username;
        let attempted_password = request.body.password;


        if (attempted_user === undefined || attempted_password === undefined) {
            response.status(400);
            response.json({"message": "Invalid request, please supply both a username and password."})
        } else {

            client.query("SELECT id, name, plaintext_password FROM Users;")
                .then((results) => {

                    let authenticated = false;
                    for ( let next_user of results.rows ) {
                        if ( next_user.name === attempted_user && next_user.plaintext_password === attempted_password ) {
                            authenticated = true;
                        }
                    }

                    if ( authenticated ) {
                        // in addition to everything we did before, let's also save that the user authenticated with express-session:
                        request.session.username = attempted_user;
                        //===========================================================


                        response.status(202);
                        response.json({"logged_in_user": attempted_user});
                    } else {
                        response.status(403);
                        let ret = {"message": "Username or password incorrect!"};
                        response.json(ret);
                    }

                    response.send();
                });

        }

    });


    router.post("/authenticate_v2", (request, response) => {

        let attempted_user = request.body.username;
        let attempted_password = request.body.password;

        let username_query =  `SELECT id, name, plaintext_password 
                           FROM Users
                            WHERE name LIKE '` + attempted_user + `' 
                            AND plaintext_password LIKE '` + attempted_password + "'";


        client.query(username_query).then((results) => {

            let authenticated = results.rowCount > 0;

            if (authenticated) {
                response.status(202);

                // in addition to everything we did before, let's also save that the user authenticated with express-session:
                request.session.username = attempted_user;
                //===========================================================

                response.status(202);
                response.json({"logged_in_user": attempted_user});
                response.send();
            } else {
                response.status(403);
                let ret = {"message": "Username or password incorrect!"};
                response.json(ret);
                response.send();
            }
        });

    });


});



export default router;
