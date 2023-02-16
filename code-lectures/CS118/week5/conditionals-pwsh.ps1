

# As with our functions last week, PowerShell if statements are somewhere halfway
# between Javascript and Bash.

# Powershell does pre-define two variables that work similarly to the true and false
# keywords from Javascript:

$today_is_rainy=$false  # $false holds the official 'false' value
$today_is_sunny=$true   # $true holds the official 'true' value


# Just like in Javascript, to make a if statement, we'll use the word if, followed by parentheses and curly braces:

if ( $today_is_sunny )  {
    Write-Output "Don't forget sunscreen!"
}


# However, like bash, our comparison operators and logical operators (and, or, and not) will use words:

if ( $today_is_sunny -and $today_is_rainy ) {
    Write-Output "A rainbow is possible!"
}


$user_entered_number=$args[0]

if ( $user_entered_number -gt 9 -and $user_entered_number -lt 100) {
    Write-Output "Wow, that's a two digit number!"
}
elseif ( $user_entered_number -lt 10 ) {
    Write-Output "That number only has one digit..."
}
else {
    Write-Output "This is a huge number!"
}