

# In Bash, arrays are created with parentheses, but otherwise work similarly:


some_list=("first" "second" "third" "fourth")
# Note: between the elements of a list in bash, we put spaces (but do not need commas.)


#However, watch what happens when we try to echo our list:
echo "The contents of the list are: $some_list"   # This will only print the FIRST item out of the list



# If we want to see the entire list, we have to access a special index of the list, [@].
# In order to access our list in Bash, we'll use the same square brackets we used in Javascript, and
# wrap the whole expression in ${}:

echo "The full contents of the list are: " "${some_list[@]}"


# Otherwise, we access individual items the same way we did in Javascript:
second_thing=${some_list[1]}
echo "The second thing in the list is: $second_thing"



# If we want to add something to an array in bash the way we did with .push(), we use a += operator on our list:
some_list+=("fifth")
echo "After adding 'fifth', the list contains:" "${some_list[@]}"

# There is no easy way to add things to the beginning, so instead we have to make a new list that has all the existing things at the end:
some_list=('new_beginning' "${some_list[@]}");
echo "After adding 'new_beginning' to the beginning, the list contains:" "${some_list[@]}"


# To remove something from a list in bash the way we did with .splice(), we call the 'unset' command on that index of the list:
unset some_list[2]

echo "After removing the item in index 2, we have:" "${some_list[@]}"



# We even still have access to foreach loops:
for item in  "${some_list[@]}" 
do
	echo "The next item is: $item."
done



# One interesting feature of bash is that some functions that return space-separated output can be
# treated like lists:
for next_file in $(ls .)
do
  echo "Next file: $next_file"
done