public class DivideBySubtractTricky implements DivideAlgorithm {
    private int operationCount =0;
    @Override
    public int run(int numerator, int denominator) {
        int result = 0;
        int k = 1;
        while(numerator>=denominator*k){
            numerator = numerator - k*denominator;
            result = result +k;
            k = k+1;
            if(denominator*k>numerator) {
                k=1;
            }
                operationCount++;
        }
        return result;
    }

    @Override
    public int getOperationCount() {
        return operationCount;
    }
}
