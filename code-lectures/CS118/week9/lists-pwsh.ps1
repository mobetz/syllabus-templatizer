# In Powershell, arrays are declared simply by putting commas between values:

$some_list=1,2,3,4,5
write-host "The list contains: $some_list"


# Powershell also has a fun abbreviation when we want a list of numbers in a range:

$nums_from_five_to_ten=5..10
write-host "The numbers between five and ten are: $nums_from_five_to_ten"

#Accessing and modifying the elements of an array uses the expected [square bracket] syntax:
write-host "The third number between five and ten is: " $nums_from_five_to_ten[2]


$some_list[2]=9
write-host "Some_list now contains: $some_list"



# Finally, loops through lists still work in Powershell as well, using the keyword 'foreach':
foreach (  $item in $some_list ) {
    write-host "The next item is: $item"
}