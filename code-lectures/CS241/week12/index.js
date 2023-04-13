
import Express from 'express'
import bodyParser from 'body-parser'

/*
Objectives for Today

By the end of today, you will:
  * Understand how ORM software allows us to interact with databases in an Object-Oriented way.
  * Use an ORM library to query some of our sample tables.
  * Define 'HTML Templating'.
  * Use EJS HTML templates to return a webpage pre-loaded with dynamic content.
 */



/*
So far, when we've wanted to interact with our database, we've done so using SQL. However,
this is not the only way people interface between databases and server code.

Instead of writing SQL, some developers choose to directly link their database tables to classes.

This allows them to interact with rows of the table as if they were objects, and make requests of
the table using methods, as if they were any other code class.

In order to do this, we must use special libraries, called "Object-Relational Modelling (ORM) frameworks".
Today, we'll learn about one NodeJS ORM framework called sequelize:
 */


/*
To use sequelize, we must import it, like any other framework we've used this semester:
 */
import sequelize from 'sequelize';


/*
Sequelize is instantiated very similarly to 'pg', providing it our database credentials:
 */

let database_connection = new sequelize.Sequelize({
    host: 'localhost',
    port: 5432,
    username: "mobetz",
    password: "mobetz",
    database: "mobetz",
    dialect: "postgres",  //<-- one extra thing: sequelize supports multiple types of DBs, so we need to specify which we're using
    logging: false
});




/*
Once we've connected to the database, we also want to create one or more classes to represent each table we want
to interact with. These classes must 'inherit' from sequelize's "Model"  class, which implements all of the query
methods and behaviors required to sync to the database:
 */



class Student extends sequelize.Model {

    //With our Sequelize models, we're only going to focus on special methods/functions related to the class
    // We will handle attributes separately with a special "constructor"

    //METHODS
    generate_email() {
        let name_parts = this.name.split(" ")
        let first = name_parts[0];
        let last = name_parts[1];
        return first.substring(0, 1).toLowerCase() + last.toLowerCase() + "@massbay.edu";
    }

}


/*
Instead of declaring our attributes directly in the class like we would for a normal class, instead we use our Model's
"init" method and give it a list of property definitions:
 */
Student.init({
  name: sequelize.DataTypes.TEXT,
  major: sequelize.DataTypes.TEXT,
  id: {
        type: sequelize.DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    }
}, { sequelize: database_connection, modelName: 'students', timestamps: false });



/*
To actually load our objects for the first time, we need to .sync() our orm object to the database. This will make sure
our tables have all the appropriate columns.
 */

database_connection.sync().then((data) => {

    /*
    Once we've sync()ed the ORM, we can use our Model classes to read from the table by calling the findAll() method:
     */


    Student.findAll().then((all_students) => {
        for ( let student of all_students ) {
            console.log("#" + student.id + " - " + student.name + ", " + student.major + " major (" + student.generate_email() + ")" );
        }
    });

    /*

    Our findAll() method is even more powerful. We can pass it an 'options' object that lets us filter using where, or
    project only the few attributes we care about from the table:

     */

    Student.findAll({
        where: {
            name: {[sequelize.Op.like]: '%Robert%'}
        } // equivalent to: SELECT * FROM Students WHERE name LIKE '%Robert%';
    }).then((results) => {

        console.log("\n\nMatches for Robert:");
        console.log(results[0].name + " (" + results[0].generate_email() + ")");
    });


    /*
    We can also create new rows of the table, using the factory method .create() to produce an object here in our code:
     */

    Student.create({
        name: "Jacob Cortez",
        major: "Computer Science"
    }).then((new_student) => {

        console.log("The new student we created has id: " + new_student.id);


        //We  can even edit and save our objects with .save():
        //For instance, if our student changes majors:
        new_student.major = "English";
        new_student.save(); //<-- this will write all of our edits to the database.


    });


    /*
        So far when we've loaded endpoints in our browser, the response has been a JSON object with the data we returned
        from the endpoint method.

        However, we can do better. One common approach to serving responses to browsers is returning a version of a webpage
        that has been customized for that request. Templated HTML is a tool that allows us to do this.
     */



    const app = Express();

    app.use(bodyParser.json());


    /*
    To be able to use templated HTML, we have to tell Express what type of HTML templates we're going to use, and
    what folder they live in. The templating language we'll use today is EJS, and we'll put our EJS templates in a
    /templates folder:
     */
    app.set('view engine', 'ejs');
    app.set('views', 'templates');

    app.get("/Students", (request, response) => {

        let name = request.query.name || "";

        Student.findAll({
            where: {
                name: {[sequelize.Op.like]: "%" + name + "%"}
            }
        }).then((students) => {

            if ( request.headers.accept.includes("text/html") ) {
                response.render("student_page", { students: students });
            }
            else {
                /*
                    If the user doesn't accept HTML (i.e if it's a fetch() request or another direct API request, we'll
                    just give them the data they asked for:
                */
                response.json(students);
            }
        })
    })//end of /Students


    app.get("/Students/:id", (request, response) => {

        Student.findOne({
            where: {
                id: {
                    [sequelize.Op.eq]: request.params.id
                }
            }
        }).then((student) => {
            if ( student === null )  {
                response.status(404);
                response.send();
            } else if ( request.headers.accept.includes("text/html")) {
                response.render("single_student", {student: student});
            } else {
                response.json(student);
            }
        })

    });


    app.put("/Students/:id", (request, response) => {

        let modified_student = request.body;
        Student.findOne({
            where: {
                id: {
                    [sequelize.Op.eq]: modified_student.id
                }
            }
        }).then((student) => {
            if (student === null) {
                response.status(404);
                response.send();
            } else {
                student.major = modified_student.major;
                student.name = modified_student.name;
                student.save().then(() => {
                    response.status(200);
                    response.json("Success!");
                    response.send();
                }).catch((error) => {
                    response.status(500);
                    response.json({"Error": error});
                    response.send();
                });
            }
        })

    });

    app.use('/', Express.static('./static'))
    app.listen(9999, () => {
        console.log("Server started on port 9999");
    });

})