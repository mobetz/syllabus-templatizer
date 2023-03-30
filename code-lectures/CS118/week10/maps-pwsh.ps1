

# In Powershell, maps (which they call "hash tables") are created with @{}:

$course_enrollments = @{
    CS118 = 20;
    CS120 = 18;
    CS241 = 15
}




Write-Output "At start of semester:"
Write-Output $course_enrollments


# Accessing and setting is still done with square brackets and a key:


Write-Output "There are $($course_enrollments["CS118"]) students enrolled in CS118.";




$dropped_students = @("CS118", "CS120", "CS118", "CS241")


Write-Output "Performing course withdrawals:"
foreach ( $course in $dropped_students ) {
    $current_count = $course_enrollments[$course]
    $course_enrollments[$course] = ( $current_count - 1 )
    Write-Output "    Dropping a student from $course."

}


Write-Output "After drops:"
Write-Output $course_enrollments