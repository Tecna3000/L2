import java.util.List;

/**
 * A grade with a float value comprised between 0 and 20.
 *
 */
public class Grade {
  /**
   * The maximum value of a grade.
   */
  private static final int MAXIMUM_GRADE = 20;
  private final double value;

  /**
   * Constructs a grade with a value equals to the specified {@code value}.
   *
   * @param value the value of the constructed grade
   */

  public Grade(double value) {
    this.value = value;
  }

  /**
   * Returns the value of the grade as a double.
   *
   * @return the value of the grade
   */

  public double getValue() {
    return this.value;

  }

  /**
   * Returns a string representation of the grade in the format X.X/20.
   * @return a string representation of the grade
   */
  @Override
  public String toString() {
    return value + "/20";

  }

  /**
   * Returns a grade with a value equals to the arithmetic mean of the values of the grade in
   * the specified list.
   *
   * @param grades a list of grades
   * @return a grade corresponding to the mean of grade in {@code grades}
   */
  public static Grade averageGrade(List<Grade> grades){
    int gradesum = 0;
    int nbgrades =0 ;
    for (Grade grade: grades){
      nbgrades+= 1;
      gradesum += grade.value;

    }
    double average = gradesum / nbgrades;
    return new Grade(average);


  }

  /**
   * Determines whether or not two grades are equal. Two instances of Grade are equal if the values
   * of their {@code value} member field are the same.
   * @param o  an object to be compared with this Grade
   * @return {@code true} if the object to be compared is an instance of Grade and has the same value; {@code false}
   * otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Grade grade = (Grade) o;

    return Double.compare(grade.value, value) == 0;
  }

  /**
   * Returns a hash code value for the object.
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    long temp = Double.doubleToLongBits(value);
    return (int) (temp ^ (temp >>> 32));
  }
}
