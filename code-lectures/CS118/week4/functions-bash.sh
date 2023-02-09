

# Functions in Bash work mostly the same -- we write a function that acts like a "recipe"
#  with all our instructions. Just like in Javascript, this function has a few inputs and
# can return a 'final answer'.


# Declaring functions in Bash is done using the same 'function' keyword:


function add_two_numbers() {

	# However, we don't put parameters in the parentheses with bash!

	# Bash 'functions' act like mini-script files. Instead of using normal function parameters,
	# in bash we will use the same syntax we did for command line arguments: $1 for the first, etc...



	# One other important note -- in Bash, all variables are "global" -- they exist everywhere in the
	# script once they're assigned. If we only want our variables to be visible in this function, we
	# must assign them with the 'local' keyword:

	local first=$1;
	local second=$2;


	local sum=$(( $first + $second ));



	# We said that bash functions work like mini-scripts....
	# Since bash doesn't have a 'return' keyword, if we want to give output data at the end of a function,
	# we'll use echo to "print" our result to the function caller:
	echo $sum;

}



# To call our function after we've created it, we use the name of the function like any other bash command --
# each of the arguments is separated by a space (note that unlike Javascript, there are no parentheses!)
echo "Doing the add_two_numbers command:"

add_two_numbers  4  5



# If we want to save that return value for later in our script, we'll have to wrap the function with $():
result=$(  add_two_numbers 12 15  )


echo "On this later line, we can see that the result stored $result.";


# One other neat feature of functions: In Javascript, each function returned an 'expression' we could use
# anywhere we put another value, such as inside the parameters of another function.



# In Bash, the metaphor we use instead is the idea of a 'pipeline' -- we use the | character to 'pipe' output
# from one command as the input to another command. To illustrate this, I'm going to show you two more commands
# given to us by bash:


# the "cat" command is a command we can use to print out the contents of a file in bash.
# the "grep" command shows us lines that match a search string.




# While we could store the result of cat in a variable and then send that to grep, instead, we can "pipeline"
# these two functions together:


lines_where_the_word_that_appeared=$( cat "functions.js"  |  grep "that" );

echo "That appeared in:";
echo "$lines_where_the_word_that_appeared";