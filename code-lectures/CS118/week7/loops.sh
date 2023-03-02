
# In Bash, we have for loops and while loops, just like in Javascript.
# Our Bash while loops are going to look similar to Bash if statements

# We put our boolean expression for the loop inside [square brackets].
# after the condition, we put the word 'do', write the body of our loop, then the word 'done'.


counter=1 
while [ $counter -lt 5 ]
do
	echo "Counter is now: $counter";
	sleep 1;
	counter=$(( $counter + 1 ))
done  # <-- at the end of our loop, we put the word 'done' to end the block of instructions.




# Our for loops use the same double parentheses that we saw for math:


for (( i=1; i<5; i++ ))
do
	echo "I is now: $i";
done



# In Bash, there is one other common type of loop: the "for ... in ..." loop, also called a "for-each" loop.
# This type of loop lets us repeat for each thing in a group:

total=0

for number in 4 8 7 5 3 1
do
	echo "Adding $number to the total. (currently: $total)";
	total=$((  $total+$number ));
done

echo "The total sum of 4 + 8 + 7 + 5 + 3 + 1 is: $total."


# This type of loop is very useful for doing things like showing us every file in a folder:

for next_file in $(ls)  # <- get me each file in this folder as my "group"
do
	 echo "There is a file called '$next_file' in this folder."
done