
# Loops in powershell will also be similar.

# While we're discussing loops, we should talk about a few common 'looping patterns' that you will see
# again and again when solving problems. 


# Though most of our examples today have been simple loops that just count, most loops don't just count.
# Especially for our while loops, we're often waiting for a more complex condition to be met.


# When we want to accept input from a user and then 'test' whether that input is valid, we can use an
# "input validation" loop. In an input validation loop, we define a boolean variable to hold the validity 
# of our input, then update this variable each iteration:

$number=-1
$entered_a_number=$false


while ( -not $entered_a_number ) {

    # Inside the loop, the first thing we do is read an input:
    $number= read-host "Enter a number"
    $number=($number -as [int])

    # Then next, we check to see whether the input satisfies our requirement:
    $entered_a_number= -not ( $number -eq $null )
}


# Written this way, this loop will keep repeating until we get a valid number.

write-output "The entered number is: $number." 
$plusOne=$number+1   # <- I can guarantee this will work, because number has to be valid here!
write-output "One more than that number is: $plusOne." 




# Another useful loop to have in our toolbelt is a "command entry" loop. This variation of the input loop
# is useful when we want to have a user keep entering options until they enter some special value that indicates
# they wish to complete the loop.

# In the command entry loop, we test a single variable that holds our command value:

$entered_command=""

while ( -not ($entered_command -eq "quit")) {
    # Then, we test that our entered command is NOT the special value that exits the loop.

    $entered_command=read-host "Enter a command"

    write-output "You entered: $entered_command"

    # We could add something like an if statement here to select each of our possible commands:

    if ( $entered_command -eq "weather") {
        write-output "We're getting a big snowstorm tonight!"
    } elseif ( $entered_command -eq "time" ) {
        write-output "It's almost time for lab!"
    } elseif ( $entered_command -eq "quit" ) {
        write-output "Goodbye!"
    } else {
        write-output "Invalid command!"
    }


# This loop will keep repeating until the user enters the special value 'quit'

}

