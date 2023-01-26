
# Powershell scripting is very similar to bash scripting. Just like Javascript and Bash, we have
# comments, variables, and commands we can use.

<#
  Powershell will also use # for single line comments, or <# for multiline comments.
#>

<# In Powershell, dollar signs are used when both assigning and accessing variables: #>


$first_name="Matthew";

<# 

 Yet again, although it looks slightly different, all we're doing is putting data into a 'box' for later:

   +-----+
   |     |
   |    <--- "Matthew"
   +-----+
  first_name

#>


$welcome_message="Hello, $first_name";

# We still have a print, but this time, it's name will be the Write-Output command:

Write-Output $welcome_message;


# Powershell is a bit better about understanding numbers than Bash, so $(()) is not necessary:

$math_result=2+4+6+8+10;

Write-Output "The result of the math is: $math_result"; 



# However, when we do want to differentiate from text, we can use parentheses after our command:

Write-Output  2+4+6+8+10;
Write-Output  (2+4+6+8+10);


# In powershell, arguments are in the $args[#] variable:


Write-Output("The first argument in PowerShell is: " +$args[0]);  # In powershell, arguments start from 0!

#Just like in bash, we can run node with the 'node' command
node variables.js "first argument" "another argument";