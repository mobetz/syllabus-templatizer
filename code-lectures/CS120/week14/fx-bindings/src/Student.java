

public class Student {
	
  private String first_name;
  private String last_name;
  private String major;
  private int final_grade;

    public Student(String given_first, String given_last, String given_major) {
      this.first_name = given_first;
      this.last_name = given_last;
      this.major = given_major;
      
      this.final_grade = 100;
  }


  public Student withGrade(int new_grade) {
      //Conventionally, if we want a setter to be able to 'chain' together by
      // having it return the original object, we name the method with<Attribute>
      
      this.final_grade = new_grade;
      return this; 
  }
    

    
  public String getFirstName() {
      return this.first_name;
  }

  public String getLastName() {
        return last_name;
  }


    public String getMajor() {
      return this.major;
  }
    
    public int getGrade() {
      return this.final_grade;
  }
}