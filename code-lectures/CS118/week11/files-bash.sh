

#  one of the most powerful features of bash is the idea of "input/output redirection"


# Redirection is the process of taking the normal 'input' or 'output' of a program, and sending it somewhere other than
# where we'd normally expect it.


# the returned 'output' of everything in bash is printed text.
# The 'input' to a bash script is the same as the input in our console javascript programs: text fed into the program.
# So far, to read from stdin, we've used the 'read' command:


function ask_color() {
  echo -n  "What's your favorite color?: ";
  read favorite_color;
  echo "Wow, I love $favorite_color too!";
}


# ask_color;


# However, users typing on the command line isn't the only way that can fill stdin with text.

# If we want to read text from a file into the stdin buffer, we can use the 'input redirection' symbol: < .
# When we put a < after a command, we're telling bash we want the file to do all our input for us:


ask_color < ./my-favorite-color.txt;



# When we ran this, it went right to the response, without us typing anything at all!

# The input redirection symbol works with any command that can read from stdin:
sort < colors.txt;



# Stdout is the buffer that holds all the text every time we print text with echo (or even console.log() in Javascript.)
# Just like we can use < to redirect input, we can use an output redirection symbol to overwrite stdout.

echo "I have some message I want to save into a file." > output.txt;


# When we put >, our output is redirected; instead of printing out on the command line, it is sent directly to a file,
# and we see nothing at all.

# However, watch what happens if we try writing more than one line to the same file:

echo "My message might have many different lines" > output.txt;
echo "I hope you enjoy reading it all." > output.txt;

# When we redirect to a file, we erase everything that was previously in that file! Fortunately, we have a second
# version of the output redirection symbol, >>, which 'appends' to a file instead of rewriting it from scratch:

echo "I have some message I want to save into a file." > output.txt;
echo "My message might have many different lines" >> output.txt;
echo "I hope you enjoy reading it all." >> output.txt;


# Output redirection also works with any command that prints output:
ls . > files_in_this_folder.txt;



# In fact, we can even combine input redirection and output redirection:
sort < colors.txt > colors_sorted.txt


# tee is the 'multiplexing' command -- it takes its standard input, and creates copies to send to one or more files:
tee colors1.txt colors2.txt  < colors.txt > /dev/null;

ls . > current_files.txt;
grep "color" < current_files.txt;




# We can even chain many pipes together and write the output to file!
cat colors.txt | sort | tr '\n' ' ' | cut -d ' ' -f 3  > filtered_colors.txt

# The 'tr' command works like .replace() from Javascript; it takes in a first argument to find, and replaces it with the second.
# This command takes the colors.txt file and prints it to stdout,
#  ...which is piped as the stdin of sort and prints out the sorted list of colors to stdout,
#  ......which is piped as the stdin of tr to replace the newlines with spaces, and prints all the colors on one line,
#  .........which is piped as the stdin of cut to get only the third color,
#  ............which is redirected to the file 'filtered_colors.txt'
