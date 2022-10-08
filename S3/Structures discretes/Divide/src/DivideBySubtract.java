public class DivideBySubtract implements DivideAlgorithm{
    private int operationCount = 0;


    @Override
    public int run(int numerator, int denominator) {
        int result = 0;
        while(numerator>=denominator){
            numerator = numerator - denominator;
            result = result +1;
            operationCount++;
        }

        return result;


    }

    @Override
    public int getOperationCount() {
        return operationCount;
    }
}
