public class NativeDivideAlgorithm implements DivideAlgorithm {
  private int operationCount = 0;

  @Override
  public int run(int numerator, int denominator) {
    operationCount = 0;
    operationCount++;
    return numerator/ denominator;
  }

  @Override
  public int getOperationCount() {
    return operationCount;
  }
}
