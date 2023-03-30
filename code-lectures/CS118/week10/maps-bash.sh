

# In Bash, to declare a dictionary, we need to use a special new keyword: declare.

# When using this keyword, we put the word 'declare', followed by a -A, then the name of the variable:


declare -A dinner_votes;



# Once we've done this, we have a new, empty dictionary.

# To add something to the dictionary, we use the same exact syntax we did in Javascript: square brackets, and the name of a key.

dinner_votes[pizza]=0;
dinner_votes[sushi]=0;
dinner_votes[curry]=0;



# To access something from the array we will use the same notation inside the same ${} expander that we used for arrays:

echo "There are currently ${dinner_votes[pizza]} votes for pizza."



# To extend our example a bit further, dictionaries are very useful when we want to do things like tally votes:

votes_from_classroom=("pizza" "sushi" "curry" "pizza" "pizza" "sushi" "sushi" "pizza");

for vote in ${votes_from_classroom[@]} 
do  
	previous_votes=${dinner_votes[$vote]};
	dinner_votes[$vote]=$((  $previous_votes + 1 ))
done


echo "Final Tally: "
for meal in ${!dinner_votes[@]}
do
    echo "  $meal:  ${dinner_votes[$meal]}"
done