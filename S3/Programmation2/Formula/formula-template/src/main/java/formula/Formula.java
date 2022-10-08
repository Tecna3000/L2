package formula;

public interface Formula {

  /**
   * Compute the value of the formula
   *
   * @param xValue the value of the variable x
   * @return the value of the function when the variable x has value {@code xValue}
   */
  double eval(double xValue);

  /**
   * Compute a {@code String} representation of the formula.
   * @return the formula as a {@code String}
   */
  String toString();

  /**
   * Compute the derivative of the formula.
   * @return the derivative of the formula
   */
  Formula derivative();
}
