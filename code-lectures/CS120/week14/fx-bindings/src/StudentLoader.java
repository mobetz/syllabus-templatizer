import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;
import java.io.*;


public class StudentLoader {

  public static List<Student> load() {

      //For our lecture today, we'll just make a loader that gives us some
      // "dummy" data, but this would still work if we loaded it from a file
      // or elsewhere!

      return new ArrayList<Student>(List.of(
              new Student("John", "Doe", "Computer Science").withGrade(96),
              new Student("Jane", "Smith", "English").withGrade(94),
              new Student("Kelly", "Friedman", "Computer Science").withGrade(89),
              new Student("Jacob", "Wheeler", "Computer Science").withGrade(84),
              new Student("Robert", "Moralez", "Engineering").withGrade(92),
              new Student("Beatrice", "Williams", "Nursing").withGrade(97)
      ));
	}


	public static List<Student> load_from_file(File location) throws FileNotFoundException {
		List<Student> students = new ArrayList<>();

		Scanner s = new Scanner(location);
		while ( s.hasNextLine() ) {
			String line = s.nextLine();
			String[] parts = line.split(",");
			students.add(new Student(parts[0], parts[1], parts[2]).withGrade(Integer.parseInt(parts[3])));
		}

		return students;

	}


    private static String getRandomFirstName() {
      List<String> names = List.of("Wade", "Dave", "Seth", "Ivan", "Riley",
              "Gilbert", "Jorge", "Dan", "Brian", "Roberto", "Ramon", "Miles",
              "Liam", "Nathaniel", "Ethan", "Lewis", "Milton", "Claude",
              "Joshua", "Glen", "Harvey", "Blake", "Daisy", "Deborah", "Isabel",
              "Stella", "Debra", "Beverly", "Vera", "Angela", "Lucy", "Lauren",
              "Janet", "Loretta", "Tracey", "Beatrice", "Sabrina", "Melody",
                                   "Chrysta", "Christina", "Vicki", "Molly" );
      
      Random r = new Random();
      int random_selection = r.nextInt(0, names.size());
      return names.get(random_selection);
  }

  
    private static String getRandomLastName() {
      List<String> names = List.of( "Smith", "Johnson", "Williams", "Brown",
              "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
              "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas",
              "Taylor", "Moore", "Jackson", "Martin", "Lee");

      Random r = new Random();
      int random_selection = r.nextInt(0, names.size());
      return names.get(random_selection);
  }
    
        
    private static Integer getRandomGrade() {
      Random r = new Random();
      return (int) r.nextGaussian(80, 10);
  }

   
    public static Student generateRandomStudent() {
      return new Student(
              getRandomFirstName(),
              getRandomLastName(),
              "Computer Science"
          	  ).withGrade(getRandomGrade());
        
        
  }


}