
/*
Objectives for Today

By the end of today, you will:

  • Understand the difference between synchronous and asynchronous functions in JavaScript.
  • Understand how to call asynchronous functions and provide them with code that should happen after they complete.
  • Identify the fs module and understand how the fs module allows us to work with our file system.
  • Practice code to read, write, and modify files with JavaScript.

 */


/*
Vocabulary for the Day

Asynchrony - A function is "asynchronous" if it happens outside the normal order of instructions in a computer program.
   Many slow or risky operations in JavaScript happen 'asynchronously' -- we give the name of a "callback function"
   to asynchronous code if we want more steps to happen after the out-of-step operation is complete.
   The opposite of asynchronous code is "synchronous" code.

Encoding - Every text file has an "encoding", which is a set of rules about how the characters of text are stored
    in the file. There are many popular encodings, but most files we will be working with this semester will be encoded
    with a set of rules called "Unicode" (specifically, UTF-8).

*/



/*
Previously, we learned about how to create a Prompt in our program.
 */


import Prompt from 'prompt-sync';
let prompt = Prompt({});


/* When we wanted to use the prompt, we called the prompt function, and supplied a question for the user of our program. */

//let user_answer = prompt("What is your favorite color?: ");


/* When this function is called, the value that was returned to us represents the 'answer' to the user's question. */


//console.log("Wow, I love " + user_answer + " too!");

/*

However.... how long does the "prompt" function take to finish?

The prompt() function is going to pause on line 42 and wait... and wait.... and wait.... until we eventually get
an answer from the user.


Sometimes, we don't want to wait -- we may have other work that we can do to keep ourselves busy while we are waiting
for a response that could take a long time, or we might not know if they are ever going to respond at all!

It turns out, there is another version of asking for user input that works this way (in fact, it's the version of
prompting that comes with NodeJS by default!). This other flavor of input is called the "readline" module:
 */




import Readline from 'readline';

/*
The 'readline' module is a special module that comes packaged with NodeJS by default. In fact, we don't even need to
 add it to our list of dependencies!

When we create one of these 'readline' prompts, we need to tell it to read from the "standard input", and write its
questions out to the "standard output" (where our standard default is the little text box we get in our terminal),
 so we have one extra set up function, just like we did when we had to call Prompt({}):
 */

let impatientPrompt = Readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

/*  Our impatientPrompt gives us access to one  function that's attached to the prompt, called "question".
The question() function works very similarly to prompt(), but with one important difference:
  */


// let answer = impatientPrompt.question("Who is your best friend?");
// console.log("Wow, " + answer + " sounds like a very good friend!");



/*

The question() function didn't wait for our user to type in an answer. In fact, our code immediately continued on, and
pretended "undefined" is what the user typed back as their answer.

This is because question() is what is known as an "asynchronous function" -- an asynchronous function is a function that
doesn't wait until it is finished. Instead, everything happens out-of-step with the normal rest of the program.

If we want something to happen after our question is answered, we will need to create a brand new function that describes
all the things we want to do with the information we learned:
 */

function complimentFriend(friends_name) {
    console.log("Wow, " + friends_name + " sounds like a very good friend!");
}



/*
Then, when we ask the question() with impatientPrompt, we give it all of the instructions that we want to happen after
the question is answered:
 */

/*
 impatientPrompt.question("Who is your best friend?", complimentFriend);
 console.log("\nIt's so cloudy out today!");
*/

/*
Notice: when we put the function to do after the question is answered, we are NOT calling complimentFriend().

There are no parentheses after the function name, and we haven't yet provided the answer the user gave.
Instead, we are just putting the *name* of the function that should happen later. We're actually passing our function
in like a variable! When we use a function this way, we are using what is called a "callback function".

You can think of this like leaving a callback number when you are waiting on hold with a phone service -- when question()
is done putting us on hold, it knows to "call us back" at the function we provided!
 */


/* If we want, we can even create our callback function right inside the question() function call, just like how we can
provide a literal number or line of text instead of providing a variable where that number or text lives:
 */

impatientPrompt.question("What day of the week is it? ",    function (day_of_week) {
  if ( day_of_week !== "Friday") {
        console.log("If only " + day_of_week + " could be Friday.")
    } else {
        console.log("Woo! It's almost time for the weekend!")
    }
});


console.log("\n(Pssst, it's Thursday)");



/* There's even an abbreviation we can use when we write a function name this way -- instead of the word "function",
we can put our parameter list in parentheses then an => arrow to our function body:

(first, second, third) => {
   return first + second + third;
}


 */



/*
The reason we spent time learning about asynchronous methods was so that we could use them to do one more very important
task on our computer: working with files!


In order to work with files, we will need to use another 'built-in' module that comes with NodeJS, the 'fs' module:
 */


import fs from 'fs';


/*
 The 'fs' module gives us many useful functions for working with files and folders. For instance, we can use the
 readdir() function to find out about all the files that live in a particular directory/folder.

 readdir() takes two arguments:
     - a "file path" -- the place where we want to look (like "C:\Users\USERNAME\Desktop")
     - a callback function that should run when all the files files in that folder are indexed

 */


fs.readdir("/home/mwobee/Teaching/2023_S/CS118/", function (errors, files) {

     /* Our callback for readdir() has two different input parameters:
         - First, it has an error that might have happened while we were trying to read from the folder
         - Second, it gives us back a list of all the files/folders that live in the file where we are looking.
      */

    if ( errors != null ){
           console.log("Uh oh, we weren't able to read from the folder!: " + errors);
    }
    else {

          console.log("Here are all the files I can see right now: ");

          for ( let file of files ) {
            console.log(" - " + file);
          }
    }

});




/*
 There are two special values we can put in our file path:
       - a single dot "." means "start by looking in the current folder where we are right now"
       - a double dot ".." means "go up one folder from where we are"
 */

 fs.readdir("./../../CS120/", function(errors, files) {
    if ( errors != null ){
        console.log("Uh oh, we weren't able to read from the folder!");
    }
    else {
        console.log("Here are all the files I can see right now in CS120: ");
        for ( let file of files ) {
            console.log(" - " + file);
        }
    }
});

/*
If we know the location, we also have functions to let us create, rename, or delete a file:
 */



fs.open("./a_brand_new_file.txt", "w", function ( errors, fd) {


  fs.rename("./a_brand_new_file.txt", "./renamed_file.txt", function (error) {

        if ( error != null ) {
            console.log(error);
        } else {
            console.log("Renamed our file to 'renamed_file.txt'!");
        }
        

        fs.rm('./renamed_file.txt', function (error) {
            if ( error != null ) {
                console.log(error);
            } else {
                console.log("Removed the file 'renamed_file.txt'!");
            }
        }); //this will remove a file with the name we provide.

  });
});


/*
If the asynchronous versions of these functions give you a headache, there are actually synchronous versions of most
(but not all) functions in the 'fs' module. They have names that are just like the names of the functions we've looked
at, but they add the word 'Sync' to the end:
 */



let search_folder = "../week6"
let list_of_files_in_folder = fs.readdirSync(search_folder)

console.log("My week6 folder contains: ")
for ( let file of list_of_files_in_folder ) {


    /*
    In addition, we have a very useful function that we can use once we know about a path: fs.lstatSync() on a path
    gives us back an object that we can use .isDirectory() on to receive true when a file is a folder, and false when a
    file is not:
     */

    let full_path = search_folder + "/" + file;
    let is_file_a_folder = fs.lstatSync(full_path).isDirectory();
    if (is_file_a_folder) {
        console.log(" > " + file + "/");
    } else {
        console.log(" - " + file);
    }
}

fs.openSync("./created_file.txt", "w");
fs.renameSync("./created_file.txt", "./other_rename.txt");
fs.rmSync("./other_rename.txt");

/* Note: if something goes wrong with these versions of the file functions, your program won't just give you an error
message, it will crash entirely!
 */

/*
Finding files in a folder is great, but the most important thing we can do with the 'fs' module is read and write to
files themselves. To do this, we will be using two functions: readFile, and writeFile.
 */


/*
When we read from a file, we need to provide a few things:
   - the path to the file
   - the "encoding" of the file (how its data is stored)
   - if we're using the async version, a callback function to run once the file is read

We get back all of the data in the file in one variable, either as a callback parameter or as the returned value: */


let file_contents = fs.readFileSync("./package.json", "utf-8");


console.log("Our package.json contains: ");
console.log(file_contents);


/*
If we forget to supply the encoding, watch what NodeJS is going to give us back:
 */
let byte_contents = fs.readFileSync("./package.json");
console.log("Without an encoding, our contents are: ");
console.log(byte_contents);

/*
We saw:
   <Buffer 7b 0a 20 20 22 6e 61 6d 65 22 3a 20 22 77 65 65 6b 31 31 22 2c ....

It turns out, on a computer, every single letter is stored as a number. What we see if we forget the encoding is all
of the raw numbers, in something known as a "byte array". If you ever see an error like this, hopefully you'll remember
what you did wrong!

 */



/*
Once we have file contents, it's common to want to split it up by line. Something that you'll write very often is
a bit of code that looks like this:
 */
let file_by_lines = file_contents.split("\n");
for ( let next_line of file_by_lines ) {
    console.log("The next line is: " + next_line);
    if ( next_line.includes("prompt-sync")) {
      console.log("Aha! This project requires prompt-sync!!!!!");
    }

}


/*
In addition to reading files, sometimes we also want to save some information in a file, so that it lasts for more than
just one execution of a program.

When we want to do this, we can use the writeFile function:
 */


let new_contents =  "Writing to files is so much fun!\nWe can save any information we want!";
fs.writeFileSync("./output.txt", new_contents);


/*
However, there is one important thing we need to keep in mind: every time we call writeFile, we delete the entire file
that lived there before and 'overwrite' it with the new information we add:
 */


let another_message = "Did you know that NodeJS invented the 'fs' module all by itself? It's not a part of regular " +
    "JavaScript!";
fs.writeFileSync('./output.txt', another_message);


/*
If we want to 'append' our message to the end of the file, then instead of 'writeFile' we instead call the 'appendFile'
function:
 */

let extra_details = "\nIn fact, a lot of the modules that we've used this semester wouldn't exist without NodeJS!";
fs.appendFileSync('./output.txt', extra_details);


/*
Using the 'fs' module, you have more power than ever before to write programs that have a real effect on your computer
and let your users do useful things!
 */