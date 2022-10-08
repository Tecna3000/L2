package formula;

import java.util.HashMap;

public class Constant implements Formula {

  private double value;

  public Constant(double value) {
     this.value= value;
  }

  /**
   * Compute the value of the formula
   *
   * @param xValue the value of the variable x
   * @return the value of the function when the variable x has value {@code xValue}
   */
  @Override
  public double eval(double xValue) {

    return this.value;
  }

  /**
   * Compute the derivative of the formula.
   *
   * @return the derivative of the formula
   */
  @Override
  public Formula derivative() {

    return new Constant(0);

  }

  /**
   * Return a {@code String} representation of the formula.
   *
   * @return the formula as a {@code String}
   */
  @Override
  public String toString() {

    return "" + this.value;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param obj the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj
   * argument; {@code false} otherwise.
   * @see #hashCode()
   * @see HashMap
   */
  @Override
  public boolean equals(Object obj) {
    if(obj == null) return false;
    if(!(obj instanceof Constant constant)) return false;
    return this.value == constant.value;
  }
}
