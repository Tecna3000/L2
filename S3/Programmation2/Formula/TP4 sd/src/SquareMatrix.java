import java.util.Random;
public class SquareMatrix {

    private int dimension;
    int[][] matrix;
    public static int countAddition =0;
    public static int countMultiplication = 0;


    public SquareMatrix(int dimension) {
        this.dimension = dimension;
        this.matrix = new int[dimension][dimension];

    }

    public int getMatrixDimension() {
        return this.dimension;
    }

    public int get(int row, int col) {
        return matrix[row][col];
    }

    public void set(int row, int col, int value) {
        matrix[row][col] = value;

    }

    public SquareMatrix getSubMatrix(int firstRow, int firstColumn, int dim) {
        SquareMatrix subMatrix = new SquareMatrix(dim);
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                subMatrix.set(i, j, this.get(i, j));

            }
        }
        return subMatrix;
    }

    public void setSubMatrix(SquareMatrix matrix, int row, int column) {
        for (int i = row; i < matrix.getMatrixDimension(); i++) {
            for (int j = column; j < matrix.getMatrixDimension(); j++) {
                this.set(i, j, matrix.get(i, j));
            }
        }
    }


    public SquareMatrix sum(SquareMatrix matrix) {
        SquareMatrix sumMatrix = new SquareMatrix(dimension);
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                sumMatrix.set(i, j, this.get(i, j) + matrix.get(i, j));
                countAddition ++;
            }
        }
        return sumMatrix;
    }

    public SquareMatrix subtract(SquareMatrix matrix) {
        SquareMatrix subtractMatrix = new SquareMatrix(dimension);
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                subtractMatrix.set(i, j, this.get(i, j) - matrix.get(i, j));
                countAddition ++;
            }
        }
        return subtractMatrix;
    }


    public SquareMatrix product(SquareMatrix matrix) {
        int[][] productMatrix = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                productMatrix[i][j] = 0;
                for (int k = 0; k < dimension; k++) {
                    productMatrix[i][j] += this.get(i, k) * matrix.get(k, j);
                    countAddition++;
                    countMultiplication++;
                }
            }
        }
        this.matrix = productMatrix;
        return this;

    }


    public static SquareMatrix identity(int dimension) {
        return new SquareMatrix(dimension);
    }


    public void power(int n) {
        SquareMatrix powerMatrix = this;
        if (n > 1) {
            this.product(powerMatrix);
            power(n - 1);

        }
    }

    public SquareMatrix quickPower(int n) {
        SquareMatrix quickPowerMatrix = this;
        if (n % 2 == 0) {
            quickPowerMatrix.power(n / 2);
        } else {
            quickPowerMatrix.quickPower((n - 1) / 2);
        }
        quickPowerMatrix.product(this);
        return this;
    }

        public SquareMatrix strassen(SquareMatrix X, SquareMatrix Y) {


        int n = X.getMatrixDimension();

        SquareMatrix product = new SquareMatrix(n);

        if (n == 1) {

            product.set(0, 0, X.get(0, 0) * Y.get(0, 0));
            countMultiplication++;

        }
        if(n%2 == 0 && n!= 0){

            int k = n / 2;
            SquareMatrix A1 = new SquareMatrix(k);
            SquareMatrix B1 = new SquareMatrix(k);
            SquareMatrix C1 = new SquareMatrix(k);
            SquareMatrix D1 = new SquareMatrix(k);
            SquareMatrix A2 = new SquareMatrix(k);
            SquareMatrix B2 = new SquareMatrix(k);
            SquareMatrix C2 = new SquareMatrix(k);
            SquareMatrix D2 = new SquareMatrix(k);


            //Extract four blocs from each matrix
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    A1 = X.getSubMatrix(i, j, k);
                    B1 = X.getSubMatrix(i, k + j, k);
                    C1 = X.getSubMatrix(i + k, j, k);
                    D1 = X.getSubMatrix(i + k, j + k, k);
                    A2 = Y.getSubMatrix(i, j, k);
                    B2 = Y.getSubMatrix(i, k + j, k);
                    C2 = Y.getSubMatrix(i + k, j, k);
                    D2 = Y.getSubMatrix(i + k, k + j, k);

                }
            }

            //creating the seven sums
            SquareMatrix M1 = strassen( A1.sum(D1),A2.sum(D2));
            SquareMatrix M2 = strassen(C1.sum(D1), A2);
            SquareMatrix M3 = strassen(A1, B2.subtract(D2));
            SquareMatrix M4 = strassen(D1, C2.subtract(A2));
            SquareMatrix M5 = strassen(A1.sum(B1), D2);
            SquareMatrix M6 = strassen(C1.subtract(A1), A2.sum(B2));
            SquareMatrix M7 = strassen(B1.subtract(D1), C2.sum(D2));

            // creat the four products
            SquareMatrix CA = ((M1.sum(M4)).subtract(M5)).sum(M7);
            SquareMatrix CB = M3.sum(M5);
            SquareMatrix CC = M2.sum(M4);
            SquareMatrix CD = ((M1.subtract(M2)).sum(M3)).sum(M6);

            //add the four products to the four blocs in matrix product

            product.setSubMatrix(CA, 0, 0);
            product.setSubMatrix(CB, 0, k );
            product.setSubMatrix(CC, k , 0);
            product.setSubMatrix(CD, k , k );

            if (n %2 !=0){
                SquareMatrix A = SquareMatrix.identity(n +1);
                SquareMatrix B = SquareMatrix.identity(n+1);
                A.set(n-1,n,0);
                A.set(n-1,n,0);
                A.set(n,n,1);

                B.set(n-1,n,0);
                B.set(n-1,n,0);
                B.set(n,n,1);

                SquareMatrix result =  A.quickProduct(B);

                result.getSubMatrix(0,n-1,n);
                product  = result;

            }
            }
            return product;

        }

        public SquareMatrix quickProduct (SquareMatrix matrix){
            SquareMatrix product = strassen(this, matrix);
            return product;

        }

        public SquareMatrix veryQuickPower(int n){
                SquareMatrix quickPowerMatrix = this;
                quickPowerMatrix.quickProduct(quickPowerMatrix);
               quickPowerMatrix.quickPower((n-1)/2);

            return this;

        }

        public static SquareMatrix createRandomMatrix(int dimension){
        SquareMatrix randomMatrix = new SquareMatrix(dimension);
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    Random random = new Random();
                   int value =-10+random.nextInt(20);
                    randomMatrix.set(i,j,value);
                }
            }
            return randomMatrix;
    }

    }




