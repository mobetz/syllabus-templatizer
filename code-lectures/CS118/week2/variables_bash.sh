
<<comment
 In bash, just like in javascript, we have a way to write comments. Instead of using a slash and star, we use
 a # for a single line comment, or <<comment for a multiline comment.
comment



# Variables in bash work slightly differently. We no longer need to use the word 'let' to declare our variable,
# and can just assign it immediately:

first_name="Matthew";


# However, the basic idea is the same. We're still creating a box, and filling it with a value:
#
#           +-----+
#           |     |
#           |    <--- "Matthew"
#           +-----+
#          first_name


# More importantly, if we want to *access* a value from a variable, we need to put a $ in front of the variable:

welcome_message="Hello, $first_name";


# In this line, we are concatenating the text "Hello, " with the value in the variable 'first_name'.


# We also still have access to commands like printing. The word 'echo' works just like console.log() from our
# javascript example. It's a command we can use to print out text that appears when we run our program. Unlike
# in Javascript, we do not need to put parentheses after the name of one of our commands, we can just put each
# input to that command separated by a space:

echo $welcome_message;


# One important note about bash: Bash likes to assume everything is text. If you forget the $ or the "", it will
# assume that the first word after your or command is a string of text.



# As a consequence, this also means we cannot put a space on either side of the equals sign when we assign a value.
# It also means that in order to do math, we need to provide special notation:

result_of_math=2+4+6+8+10
echo "The result of the math is: $result_of_math";


result_of_math=$(( 2+4+6+8+10 ))
echo "The result of the math is: $result_of_math";

# To run a bash script, we open a bash command prompt and type ./ then the name of the script we want to run.
# Remember, if you're using Windows, you'll need to open your bash prompt with "wsl" first.
# If you have a message about "permission denied", you may need to modify permissions by typing "chmod a+x <FILENAME>"


# After the name of the script, we can optionally put 'arguments' we want to send to the script. These arguments
# work just like the arguments to our echo command above -- each one separated by a space gets sent to your
# script as a pre-filled variable. In Bash, those variables are named after numbers. For example, the first
# argument sent will be $1:

echo "The first argument this script was pased in: $1";
echo "The second argument this script was passed in is: $2";


# Each argument is a piece of data that was provided to the script when the script was run.


# We can even run our javascript scripts from bash, by using the 'node' command. The argument to the node
# command is the path to the .js script:

echo "Now running my node program: ";
node variables.js;


# One important note: if we want to save the output of that script in a variable, 
# we need special syntax just like with math:
output_of_script=$(node variables.js);



# Just like with bash, we can even pass arguments into our node program by sending them as additional
# space-separated parameters:

node variables.js "first argument" "another argument";