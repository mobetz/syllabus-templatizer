
/*
Objectives for Today

By the end of the day, you will:
   * Motivate the need for thinking about our data at each layer of our application.
   * Define "Unified Modeling Language"
   * Understand how to use UML diagrams to demonstrate the relationship between different types of data in an application
   * Define "relational database"
   * Create tables in a relational database.
 */


/* Vocabulary for the Day

   Unified Modeling Language - UML is a standard for describing data as different classes of entities that each have
     a number of 'attribute' properties.


   Relation - Entities in a UML diagram are 'related' to other entities when they hold references or associations with
   those other entities. Relations between entities can be "One-to-One", "One-to-Many", or "Many-to-Many".


   Relational Database - A relational database is a system for persisting information as a series of tables. Each table
   has one or more typed columns and a number of rows that represent individual data points under that table.


   Structured Query Language - SQL is a programming language we use to make 'queries' of a relational database.

 */





/*
  In the first half of this semester, we built a data-driven web application that could help our users search for a
  Politician by name, party, or chamber.

  To get this application to work, we needed to read data from our data source (a CSV file), convert it into a
  representation that worked for our server, and then convert the results of our computation into a representation we
  could return to our users in the endpoint response.


  Any time we build a web application from the ground up, we need to think about how we want our data represented
  at each 'layer' of the application:
           - The "physical data model" is how we want to persist the data for long term storage outside our application
               + These are things like files, databases, spreadsheets
           - The "logical data model" is how we want to 'load' the data for use in our code
               + These are usually structs or objects with different attached attributes and methods/functions
           - The "transport data model" is how we want to send the data between our server and our client
               + This is often text-based, and may use a "serialized" format like JSON, but only contains data (no methods)


  Each of these layers are not necessarily aligned with one another.

  For instance, we might:
      - combine data from several files to get the representation of a single 'object' in our code.
      - Split up each row of a file into several different objects that each only use certain columns from the file.
      - Add 'reference links' to our objects before transporting them to the client (i.e. to be "discoverable"!)
      - 'stringify' data before we store or transport it.

  However, it is important that we try to align these representations as closely as possible.




  One tool we have to think conceptually about our data is the Unified Markup Language.

  Unified Markup Language is a set of rules that allow us to describe how different 'entities' in our data relate to one
  another.


     - With UML, each 'entity' in our data model becomes a box, with its name written at the top.
     - Below, we write each 'attribute' of that entity.
      _______________
     |__           __|
     |               |
     |               |
     |               |
     |               |
     |_______________|

      _______________
     |__ Politician__|
     | first_name    |
     |  last_name    |
     |   district    |
     |        age    |
     |_______________|

      _______________
     |____Chamber ___|
     |        name   |
     |   num_seats   |
     | term_length   |
     |  ordinal_id   |
     |_______________|

    Where UML becomes valuable is in letting us express how these different concepts relate to one other.


    - We 'relate' two entities together with a line:

       - ------- represents a "one-to-one" relationship: A and B are both exclusive to one another.
                                                              For example: each course has exactly one final exam,
                                                                          and each final exam is written for exactly one course.

                           _________________                                   ________________
                          |__  Course   ____|                                 |____Final_Exam _|
                          |    course_id    |                                 |   num_quest    |
                          |         name    |   ----------------------------  |   start_time   |
                          | meeting_time    |                                 |  mins_allowed  |
                          |          age    |                                 |    due_date    |
                          |_________________|                                 |_______________ |


       - ------< represents a "one-to-many" or "many-to-one" relationship: each A has many Bs, but each B is exclusive to
                                                                            a single A.
                                                              For example: Each course can have many sections, but a course section
                                                                          only belongs to exactly one course.

                           _________________                                   ________________
                          |__ Playing_Card _|                                 |____ Deck  _____|
                          |          suit   |                                 | manufacturer   |
                          |         value   |   >---------------------------  | deck_art_img   |
                          |                 |   stored_in                     | rounded_edge   |
                          |                 |                                 |                |
                          |_________________|                                 |_______________ |


       - >-----< represents a "many-to-many" relationship: each A can hold many Bs, and each B can be held by many As.
                                                              For example: Students are registered for many courses,
                                                                     and each course has many students registered.


                           _________________                                   ________________
                          |__ Course       _|                                 |____Student ____|
                          |  name           |                                 | name           |
                          |  id             |   >---------------------------< | id             |
                          |  meeting_time   |           registrations         | major          |
                          |                 |                                 |                |
                          |_________________|                                 |_______________ |


       - Sometimes, you will see an 'empty circle' at the end of a line to represent the possibility of a "zero" or
        nullable value.

      - Entities can have more than one relationship between each other.

                               _______________                                   _______________
                              |__Politician __|                                 |____Chamber ___|
                              |  first_name   | member of                       |        name   |
                              |   last_name   | > ----------------------------  |   num_seats   |
                              |    district   |                                 | term_length   |
                              |         age   | o ----------------------------  |  ordinal_id   |
                              |_______________|                         leader  |_______________|


   The good thing about our UML diagram is that it is a system and language agnostic way to talk about the relationship
   between different types of data in an application!

    One last note: UML doesn't have any idea of arrays/lists for storing whole groups of things -- every attribute
    is a simple type.

    As a consequence, one-to-many relationships always get stored on the "one" side -- in the example above, member_of would
    live with the Politician, because each politician only needs to store the one chamber they work in, but a Chamber
    can't store all the members without a list.

                               _______________                                   _______________
                              |__Politician __|                                 |____Chamber ___|
                              |  first_name   |                                 |        name   |
                              |   last_name   | > ----------------------------  |   num_seats   |
                              |    district   |                                 | term_length   |
                              |         age   | o ----------------------------  |  ordinal_id   |
                              |   member_of   |                                 |               |
                              |_______________|                         leader  |_______________|

                  member_of in this example will always store either "senate" or "reps", so that we can re-connect
                  the two related pieces of data.

                  if we had tried to put it on Chamber, we would've needed members: [ W00123, S000567, G000891...],
                  which is a list!


    When we have a many-to-many relationship, like the "many students each being registered in multiple courses" above,
    it can be tricky to figure out which side of the relationship "owns" the data. One common approach is to 'decompose'
    many-to-many relationships into their own entity with two one-to-many relationships:

                           _________________                                   ________________
                          |__ Course       _|                                 |____Student ____|
                          |  name           |                                 | name           |
                          |  id             |   >---------------------------< | id             |
                          |  meeting_time   |           registrations         | major          |
                          |                 |                                 |                |
                          |_________________|                                 |_______________ |


                           _________________          ________________         ________________
                          |__ Course  ______|        |__ Registration |       |____Student ____|
                          |  name           |        |   student_id   |       | name           |
                          |  id             |   >----|    course_id   | ----< | id             |
                          |  meeting_time   |        |----------------|       | major          |
                          |                 |                                 |                |
                          |_________________|                                 |________________|

     This gives us a canonical place where each individual registration can be stored, alloweing us to avoid
     having to talk about a whole group for any one of our entities.

 */



/*

  When we made our Politician application, we used JSON data files to represent our physical data. However, this format
  has a few limitations....

  It is very difficult to discuss the relationship between different entities when we have a bunch of data files.


  When we have data with several entities and complex relations between them, rather than tracking them in a file, we
  might instead choose to use a specialized piece of software to record this data, called a "relational database".


  A relational database stores data in a series of 2D tables with columns, very similar to the tabular format of a
  CSV file. However, the database system also performs many additional checks for us, such as validating type constraints
  on our data and keeping track of additional constraints we can define.

  There are entire courses dedicated to studying the theory and development of databases, but for the next few weeks,
  we are going to get a crash course that covers just the bits most relevant to web development.


  There are many different relational database products available on the market, such as MySQL, Oracle, MS SQL Server,
  and PostgreSQL. We will be setting up our own local Postgres database during Thursday's lab period!

  In order to make a connection to this database, we will also begin using a new library in our NodeJS server code:
  pg.
 */


import pg from 'pg';


/*
To form the databse connection, we will create a pg.Pool object:
 */

let connection = new pg.Pool({
    host: '127.0.0.1',    //<- 127.0.0.1 is the "localhost" IP address
    port: 5432,
    user: 'mobetz',       //<- the default user for a postgres database is called "postgres", but you can make more accounts
    password: 'mobetz',
    database: 'mobetz'    //<- each application you build should use its own separate "database" as a space for creating tables
});

/* Once we have our Pool object, we can call .connect() to get ourselves a promise, then use .then() with a callback
function to tell the database what we want to do once the connection has been established:
 */


let promise_to_eventually_be_connected = connection.connect();
promise_to_eventually_be_connected.then((client) => {
    /*
Before our script runs, we're going to delete any tables that were already there with the DROP TABLE
command, to make sure we're always getting a 'fresh' copy of the table (YOU'RE NOT NORMALLY GOING TO
BE DOING THIS AS PART OF YOUR CODE.)

One thing to be careful about: we can't drop a table that is still 'referenced' in another table
 */
    client.query("DROP TABLE IF EXISTS Politician;");
    client.query("DROP TABLE IF EXISTS Chamber;");


    //the "ALTER" statement lets us change an existing table or object. "ALTER USER" will let us change our password.
    client.query("ALTER USER mobetz WITH PASSWORD 'mobetz';");


/*
  In order to facilitate transfer between different DB products, most database software supports a programming
  language called the "Structured Query Language" that describes how to ask questions and make updates to the data.


   This language divides requests into one or more 'queries' that express what data we want to interact with.

   Just like with our HTTP methods for endpoints, we can divide up the commands of SQL into the CRUD categories:

                                 Create       |      Read      |    Update     |    Delete
                            ----------------------------------------------------------------------
             For tables/users:    CREATE      |       \d       |    ALTER      |    DROP
                     For data:    INSERT      |     SELECT     |    UPDATE     |    DELETE
                                              |                |               |

   Typically, most SQL commands follow the pattern of: [ACTION] [TARGET] [DETAILS].

   To issue one of these commands from our NodeJS code, we use the query() function on the client we received earlier.

    Today, the main statement we are going to talk about is the "CREATE TABLE" statement.
    Once we've decided on our data model with a UML diagram (or, the database-specific Entity-Relational Diagram),
    we can create a 'table' in our database to represent each entity. This is quite similar to 'declaring' a
    class:
 */

    let create_politicians_table = `
       CREATE TABLE Politician(
           bioguide TEXT,
           first_name TEXT,
           last_name TEXT,
           state CHAR(2),
           party CHAR(1),
           age NUMERIC
       )
    `;


/*

       Our create table has a few parts:

          - First, we say we are creating a table, and give the table a unique name (just like our 'class' name)

             - Afterward, we list each column of the table and the type of data that column will hold in between a set
                of parentheses. (Unlike declaration in Java, we put the name first, then the type AFTER the name.)
                Common types include:
                       TEXT -- for textual/string information
                    NUMERIC -- for representing numbers
                    CHAR(#) -- text that is exactly some number of characters, e.g CHAR(2) for 2 letters exactly
                       BOOL -- represents true or false

*/




    /*
    Types on create table are great, but they're just the beginning of the constraints we can express in SQL.

      After each column of our create table, we can also optionally put any additional constraint keywords.
             Some useful checks we commonly use are:
                        NOT NULL -- indicates this row *must* have a value
                          UNIQUE -- indicates no two rows in this table can have the same value
              DEFAULT some_value -- indicates this column should get filled in with some_value when not supplied
                     PRIMARY KEY -- indicates this column is the unique "main identifier" for the row
      REFERENCES OtherTable(col) -- indicates this column must refer to the primary key of another table


             - After the list of all columns, we can also list any additional checks on the table. If preferred, primary keys
             and foreign keys can be specified here instead:

                PRIMARY KEY(col1, col2....)  //<- note, you can choose to use a combination of columns as the unique identifier this way
                FOREIGN KEY(col1, col2...) REFERENCES Table(col)


     */

    create_politicians_table = `
       CREATE TABLE Politician(
           bioguide TEXT UNIQUE NOT NULL,
           first_name TEXT,
           last_name TEXT,
           state CHAR(2) NOT NULL,
           party CHAR(1) NOT NULL,
           age NUMERIC,
           PRIMARY KEY(bioguide)
       )
    `

    /*
      Now that we've done this, let's go check the table in our Database browser in IntelliJ.

      We can add a data source on the right-side tab, and enter our database credentials. Once we do this, we can navigate to the
      tables, and find a Politician table that looks like this:

         +----------+------------+-----------+-------+-------+-----+
         | bioguide | first_name | last_name | state | party | age |
         +----------+------------+-----------+-------+-------+-----+
         |          |            |           |       |       |     |
         +----------+------------+-----------+-------+-------+-----+

       We've got space for each of those columns that we defined. We can even double click into the cells of this table and add rows, and
       the database will stop us if we try to violate one of the constraints we defined (like trying to put the wrong type for age!)

       */

    //Remember, this SQL won't do anything until we send it off to the database server with query()!


    client.query(create_politicians_table).then((result) => {
        console.log("Result of creating Politicians table: " + JSON.stringify(result));
    });


    // Now that we've created our Politicians table, I want to try creating a second table and linking them together

    let create_chamber_table = `
    CREATE TABLE Chamber(
        name TEXT UNIQUE NOT NULL,
        num_seats INTEGER,
        term_length INTEGER,
        PRIMARY KEY(name)
    )`;



    client.query(create_chamber_table)
        .then((result) => {
            console.log(result)
        });


    /*
    Our two tables each represent one of our entities, but the power of the UML model
    was that we could link these entities together. We need to add a column to one of two
    tables that can represent this connection.
     */

    /*
      In addition to creating our table, sometimes we also want to be able to change an existing
      table, we can do that with the "ALTER TABLE"  statement:
     */

    let add_chamber_column = `
       ALTER TABLE Politician
          ADD COLUMN member_of TEXT REFERENCES Chamber(name)
    `

    /*
    What we're saying here is that that only valid values for the "member_of" column are values that we draw
    from the "name" column of the rows in Chamber. In other words, if Chamber has a "senate" row and a "house"
    row, then ONLY the words "senate" or "house" can appear in member_of.
     */

    /* We could also use ALTER TABLE ... DROP COLUMN <column_name> to delete a column here.  */

    client.query(add_chamber_column)
        .then((result) => {
            console.log(result);
        });

    /*
    Now that we've done this, the Politician table has another row: member_of.

    Since this member_of references a chamber, we must put a name that matches a name from our Chambers table!

    NOTE: with foreign keys, it's important to think about the order you are creating your tables (you can't reference a
    table that doesn't yet exist.)
    */

    /*
    Relational databases give us a powerful new tool for storing clean and easily accessible data. Over the next few classes,
    we'll learn how we can enter data into and read data from these data sources directly in our code!
    */
});
