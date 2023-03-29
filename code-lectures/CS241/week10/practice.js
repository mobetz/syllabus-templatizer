


/* As some practice, let's make a Students, Courses, and Registrations table. They are related like this:


Students                    Courses
----------   many-to-many  ---------
name                       course_id
id        "registers for"       name
major                        credits

We can't actually store 'many-to-many' directly in a database, so instead we create two one-to-manys linked together:

Students                    Registrations                      Courses
----------   many-to-one   -----------------    one-to-many    ---------
name                        student_id                         course_id
id                          course_id                          name
major                       semester                           credits


    Each student has many courses they register for, and each course can have many registered students. When we have
    a many-to-many relationship like this, in the database we usually create a third entity that acts as the connector
    between the two (in this case, our "Registrations".)

    This helps us avoid needing to use an array to express the relationship between Students and Courses anywhere in our
    columns!

    However, this makes some questions more complicated -- if I wanted to know the major of everyone in a particular
    class, I need to do MULTIPLE JOINS:
    */


let cs241_majors = `
   SELECT students.major
       FROM students
          JOIN Registration ON registration.student_id = students.id
          JOIN courses ON registration.course_id = courses.course_id
          WHERE courses.name LIKE 'CS241' AND semester LIKE 'Fall 2022';
`


import pg from 'pg';

let pool = new pg.Pool({
    host: 'localhost',
    port: 5432,
    user: 'mobetz',
    password: 'mobetz',
    database: 'mobetz'
});


pool.connect().then((client) => {
   client.query(cs241_majors).then((results) => {

       console.log("All majors of students in CS241: ");
       for ( let registration of results.rows) {
           console.log("    - " + registration.major);
       }
   });


   let get_all_registrations_for_2022 = `
      SELECT
          Students.name AS student_name,
          Courses.name AS course_name,
          Registration.semester
      FROM students
           JOIN registration ON students.id = registration.student_id
           JOIN courses ON registration.course_id = courses.course_id
      WHERE registration.semester = 'Fall 2022'
   `;


    client.query(get_all_registrations_for_2022).then((res) => {
        console.log("Registrations from Fall 2022: ")
        for ( let registration of res.rows) {
            console.log("   - " + registration.student_name + ": " + registration.course_name);
        }
    });

});


/*
 We can also 'parameterize' queries by putting all the wildcard user-submitted portions into variables we provide
separately from the main SQL statement. This lets us write the query once without needing to do ugly string concatenation!
*/

let target_student = "Joe Smith";
let target_major = "Communications"
pool.query(
    `SELECT * FROM students WHERE name LIKE $1 AND major LIKE $2`,    //<- instead of string concatenating here,
                                                                      // I'm going to put a placeholder
    [target_student, target_major]    //As a second argument to .query(), give values for all the $# "placeholders"
).then((results) => {
    console.log("Matching rows: " + JSON.stringify(results.rows));
})
