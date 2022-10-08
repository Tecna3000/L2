public class DivideEuclide implements DivideAlgorithm{
    private int operationCount =0;
    @Override
    public int run(int numerator, int denominator) {
        operationCount = 0;
        int result = 0;
        int m = 1;
        while (2 * m * denominator <= numerator) {
            m = m * 2;
            operationCount++;

        }
        while (m >= 1) {
            if (numerator >= m * denominator) {
                result = result + m;
                numerator = numerator - m * denominator;
            }
            m = m/2;
            operationCount++;
        }

        return result;
    }

    @Override
    public int getOperationCount() {
        return operationCount;
    }
}
