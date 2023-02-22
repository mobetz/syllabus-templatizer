

public class Group {


	//ATTRIBUTES

    /*
    When I am creating the class, I might decide that each member of the group should be its own attribute:
     */

    private String first_member;   // \
    private String second_member;  //  |
    private String third_member;   //   >   THESE ARE WRONG
    private String fourth_member;  //  |
    private String fifth_member;   // /

    /*


    This is wrong, because we're making a few different assumptions here:
        - We will only ever care about storing the same details of each group member, but we're creating different variables
        - There won't always be exactly five group members
        - In this version, each group member has a specific identity: the first group member and third group member are being
             treated like different "roles", when the names are really in a random order.
        - We have no way to refer to this thing as a whole group.


     When we have multiple *interchangeable* pieces of information that we want to refer to as a group, the correct
     solution is an array:
     */
    private String[] member_names;



	//METHODS

    public GroupMembers (String first, String second, String third, String fourth, String fifth) {
        this.member_names = new String[] {first, second, third, fourth, fifth };
    }


    /*
    The benefit of treating the names as one single group is that now I can use loops with the entire group
    to solve tasks like searching, filtering, and sequential processing:
     */
    public boolean isStudentInThisGroup(String search_target) {
    	boolean student_found = false;


    	for ( int i=0; i<this.member_names.length; i++ ) {
    		if ( search_target.equals(this.member_names[i]) ) {
    			student_found = true;
    		}
    	}

    	return student_found;


    	/*

    	As a quick aside, when we have an if statement that does nothing but set a branch to true, we can
    	represent this with OR:

        boolean student_found = false;
        for ( String next_member : this.member_names ) {
        	student_found = student_found || next_member.equals(search_target);
        }
		

    	*/
    	
        /*
        This type of function would be impossible (or at least really cumbersome) if we had five different student
        attributes!
         */
    }

}