import java.util.ArrayList;
import java.util.List;

/**
 * A students with results.
 */

public class Student {
  private final String firstName;
  private final String lastName;
  private final List<TeachingUnitResult> results;

  /**
   * Constructs a student with the specified first name and last name and no associated results.
   *
   * @param firstName the first name of the constructed student
   * @param lastName the last name of the constructed student
   */

  public Student(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.results = new ArrayList<>();
  }

  /**
   * Add a grade associated to a teaching unit to the results of the student.
   *
   * @param teachingUnitName the name of the teaching unit of the added result
   * @param grade the grade of the added result
   */
  public void addResult(String teachingUnitName, Grade grade){
    results.add(new TeachingUnitResult(teachingUnitName, grade));

  }

  /**
   * Returns a string representation of the student in the format first name last name.
   * @return a string representation of the student
   */
  @Override
  public String toString() {
    return firstName + " " + lastName;

  }


  /**
   * Returns the grades of the student.
   *
   * @return the grades of the student
   */
  public List<Grade> getGrades(){
  List<Grade> listgrades =  new ArrayList<>();
  for (TeachingUnitResult teachingUnitResult :  results){
    listgrades.add(teachingUnitResult.getGrade());
  }
  return listgrades;
  }


  /**
   * Returns the average grade of the student.
   *
   * @return the average grade of the student
   */
  public Grade averageGrade() {
    return averageGrade();
  }

  /**
   * Print via the standard output the name of the student, all results associated to the students and
   * the average grade of the student.
   */
  public void printResults(){
   System.out.println(toString()  + "\n" + this.teachingUnitName + " : " + this.grade));
  }


  /**
   * Determines whether or not two students are equal. Two instances of Student are equal if the values
   * of their {@code firtName}  and {@code lastName} member fields are the same.
   * @param o  an object to be compared with this Student
   * @return {@code true} if the object to be compared is an instance of Student and has the same name; {@code false}
   * otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Student student = (Student) o;

    if (!firstName.equals(student.firstName)) return false;
    return lastName.equals(student.lastName);
  }

  /**
   * Returns a hash code value for the object.
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    int result = firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    return result;
  }




}
