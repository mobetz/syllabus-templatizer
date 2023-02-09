

# Powershell functions will also use the 'function' keyword:


function Add-Two-Numbers {

    # Conventionally, powershell functions usually use capitalized names with dashes.
    # Unfortunately, adding parameters to this function is a bit more cumbersome than it was in JS or 
    #   Bash.

    # To define parameters, we'll have to use a "commandlet":
    [CmdletBinding()]
    param(
    # Inside this cmdlet, we put details about each parameter to the function:
    [Parameter(Mandatory)]
    [int] $first,

    [Parameter(Mandatory)]
    [int] $second
        )


    $sum=($first + $second);

    # Just like in Bash, we use printing to 'return' from our function:
    Write-Output $sum;

}



# Then to call the function, we provide its name and then the arguments, just like in Bash:


Add-Two-Numbers 4 5



# Just like in Bash, we can save the output of functions into parameters:

$result_of_math=Add-Two-Numbers 12 15


Write-Output "The result of our math is: $result_of_math";


# Pipelining even still exists:
$matching_lines= cat functions-bash.sh | Select-String "that"



Write-Output "Lines where we said 'that':"
Write-Output "$matching_lines"