
/*
Our server_registration router is going to start exactly like our previous router did for checking logins:
 */
import pg from 'pg';
import Express from 'express';

let pool = new pg.Pool({
    host: "localhost",
    port: 5432,
    user: "mobetz",
    password: "mobetz",
    database: "mobetz"
});

let router = Express.Router();



pool.connect().then((client) => {

    router.post("/create-account", (request, response) => {

        let desired_username = request.body.username;
        let desired_password = request.body.password;

        /*
           However, this time we might want to do some checks to make sure our username is valid...
            */
        if (desired_username.match("[\.\"\-\%\#\^\;\\s]") !== null
            || desired_username.length < 4
            || desired_username.length > 16) {
            response.status(406);
            response.json({"Message": "Invalid username. Username must be 4-16 characters with no special characters."});
        } else if (desired_password.length < 4
            || desired_password.length > 20
            || desired_password.match("[\-\_\d]") === null) {
            response.status(406);
            response.json({"Message": "Invalid password. Must include a number or special character (-, _) and be 4-20 characters."});
        } else {


            /*
            If the username is a valid username, we will have to check whether or not it already exists.
             We can do this with a simple SELECT statement:
             */

            client.query(`SELECT *
                          FROM users
                          WHERE name LIKE $1`, [desired_username])
                .then((results) => {

                    /* If our SELECT statement returns any rows, then we know we found a username with that name already, and we
                    * can't create someone new with the same name: */
                    if (results.rowCount > 0) {
                        response.status(400);
                        response.json({"Message": "Invalid username. Already exists!"});

                    } else {

                        //This user doesn't exist yet, we can finally create our new user!!!


                        /*
                        To actually save the user, we need to use one more kind of statement: the INSERT statement.


                        Insert statements take the format:

                        INSERT INTO TableName (col1, col2, col3, col4, ...)  VALUES ( val1, val2, val3, val4...)
                         */

                        client.query(`
                            INSERT INTO users (name, plaintext_password)
                            VALUES ($1, $2);
                        `, [desired_username, desired_password])
                            .then((results) => {

                                /*
                                For an INSERT, the rowCount property of our results says how many rows were added.
                                If it's 1, our new user was added successfully:
                                 */

                                if (results.rowCount === 1) {
                                    response.status(200);
                                    response.json({"Message": "User created!"});
                                } else {
                                    response.status(500);
                                    response.json({"Message": "Unknown error. Failed to create user."});
                                }

                            });
                    }
                });//when we get back the query for existing usernames

        }//otherwise if password was valid
    });//end of create-user



    router.put( "/change-password", (request, response) => {

        let old_password = request.body.old;
        let new_password = request.body.new;

        /*
        This time, instead of accepting a username and password right now, we're going to check whether the user's login
        session that we created with request session is present here:
         */
        if ( request.session === undefined || request.session.username === undefined ) {
            response.status(403);
            response.json({"Message": "Not logged in, cannot change password."});
        } else {

            /*
            If the user was logged in, we can first validate that the 'old password' they typed in matches their actual password:
             */
            let users_session = request.session.username;

            client.query("SELECT * FROM users WHERE name=$1 AND plaintext_password=$2", [request.session.username, old_password])
                .then((result) => {
                    if ( result.rowCount === 0 ) {
                        response.status(403);
                        response.json({"Message": "Old password incorrect."});

                        request.session.username = undefined; //forcibly log this person out, they got their password wrong.
                        request.session = undefined;
                    }
                    else {

                        /*
                        To update our password, we're going to use one last new type of statement, the UPDATE statement.

                        An update statement takes the form:

                         UPDATE Table SET col1 = val1, col2 = val2 ..... WHERE <condition>
                         */


                        client.query(`UPDATE Users SET
                                        plaintext_password = $1
                                        WHERE name = $2`, [new_password, request.session.username])
                        /*One important note about UPDATE: it's going to update EVERY SINGLE ROW that matches. If we have no
                                                 WHERE clause, every single row is going to get the new value. */
                            .then((results) => {
                                if ( results.rowCount === 1 ) {
                                    response.status(200);
                                    response.json({"Message": "Password update successful!"});
                                }
                                else {
                                    response.status(500);
                                    response.json({"Message": "Update failed."});
                                }
                            })


                    }
                })
        }

    });



    });//once the database is connected



export default router;