
# Unfortunately, Bash does not have a native boolean type. Instead, we usually use numbers or words
# to represent true and false values (the text "true" and the number 0 are both common substitutes for true,
# and the text "false" or the number 1 stand in for false values.)


today_is_sunny="true"
today_is_rainy="false"


# If statements exist in bash as well!

# In bash, instead of using parentheses and curly braces to mark where an if statement starts and ends,
# we use square braces and the word 'then':

# The square brackets after an if statement create a special 'zone' in your bash program, and our
# comparison operators will only work inside this zone.

# Note: you *need* a space between your double equals signs and your two comparisons in Bash

if [ $today_is_sunny == "true" ]
then   # After our conditional, we put the word 'then' on its own line to serve as our 'opening brace'
    echo "Don't forget your sunscreen!"
fi  # When we're done writing all our conditional statements, we put the word 'fi' to end the conditional block


# When we have more than one condition, we can use && and ||, just like in Javascript.
#  Each comparison in our boolean expression should get its own [square brackets]:
if [ $today_is_sunny == "true" ] && [ $today_is_rainy == "true" ]
then
  echo "Check for a rainbow"
fi



# One more detail: the == sign only works for comparing STRINGS. If we want to compare numbers,
# We actually need to use word based comparators:

#   4 -eq 5     -- tests whether 4 matches 5
#   4 -gt 5     -- tests whether 4 is greater than 5
#   4 -lt 5     -- tests whether 4 is less than 5


year_of_majority=2005
birth_year=1991

if [ $birth_year -lt $year_of_majority ]
then
     echo "Congratulations you're an adult!"
elif [ $birth_year -eq $year_of_majority ]
then
      echo "I bet you're excited for your 18th birthday!"
else
      echo "You're still a minor!"
fi

