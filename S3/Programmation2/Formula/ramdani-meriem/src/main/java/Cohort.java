import java.util.ArrayList;
import java.util.List;

/**
 * A group of students.
 */

public class Cohort {
  private final String name;
  private final List<Student> students;

  /**
   * Constructs a cohort with a name equals to the specified {@code name} and no students.
   * @param name the name of the constructed Cohort
   */

  public Cohort(String name) {
    this.name = name;
    this.students = new ArrayList<>();
  }

  /**
   * Add the specified {@code student} to the students of the cohort.
   * @param student the student to be added to the cohort
   */
  public void addStudent(Student student){
    // TODO : add code
  }

  /**
   * Returns the list of students of the cohort.
   * @return the list of students of the cohort.
   */
  public List<Student> getStudents(){
    // TODO : change code
    return null;
  }

  /**
   * Print via the standard output the name of the cohort and all results associated to the students with their average
   * grade.
   */
  public void printStudentsResults(){
    // TODO : add code
  }

  /**
   * Returns the name of the cohort.
   * @return the name of the cohort
   */
  @Override
  public String toString() {
    // TODO : change code
    return null;
  }
}
