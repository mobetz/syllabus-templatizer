/*

          CongressPerson
              - String: a congressperson has a name
              - LocalDate: a congressperson's birthday
              - Term[]: the terms a congressperson was elected for

*/

public class CongressPerson {

	private String name;
	private LocalDate birthday;
	private Term[] terms_elected;


	public Term getCurrentTerm() {
		for ( Term term : terms_elected ) {
			if ( term != null ) {
				if ( term.isCurrentlyActive() ) {
					return term;
				}
			}
		}
		return null;
	}
	
}