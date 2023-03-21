
/*

Our model had the description of:
		  Term
              - LocalDate: a congressperson was elected on a particular date  
              - LocalDate: a congressperson has a term end on a particular date
              - String: the name of the state they ran for
              - String: the name of the party that a candidate ran under
              - String: the position that a rep was elected to
*/


public class Term {

    //ATTRIBUTES

    /* This model directly maps to our attributes:*/
	private LocalDate start_date;
	private LocalDate end_date;
	private String state;
	private String party; 
	private String position;


	//METHODS
	//These come from those 'specialized behaviors' or attributes we realized we didn't need to store and could recalculate on the fly:
	public int getLengthOfTermInDays() {

	}

	public boolean isCurrentlyActive() {
		return start_date.isBefore(LocalDate.now()) && end_date.isAfter(LocalDate.now());

	}
	

}