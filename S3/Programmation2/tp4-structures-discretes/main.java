public class Main { 
    public static void main (String args[]){ 
        SquareMatrix matrix1 = new SquareMatrix(3); 
        SquareMatrix submatrix = new SquareMatrix(2); 
 
        submatrix.set(0,0,9); 
        submatrix.set(0,1,9); 
        submatrix.set(1,0,9); 
        submatrix.set(1,1,9); 
 
        matrix1.set(0,0,3); 
        matrix1.set(0,1,2); 
        matrix1.set(0,2,5); 
        matrix1.set(1,0,4); 
        matrix1.set(1,1,7); 
        matrix1.set(1,2,3); 
        matrix1.set(2,0,8); 
        matrix1.set(2,1,9); 
        matrix1.set(2,2,0); 
 
        SquareMatrix matrix2 = new SquareMatrix(3); 
 
        matrix2.set(0,0,2); 
        matrix2.set(0,1,2); 
        matrix2.set(0,2,2); 
        matrix2.set(1,0,2); 
        matrix2.set(1,1,2); 
        matrix2.set(1,2,2); 
        matrix2.set(2,0,2); 
        matrix2.set(2,1,2); 
        matrix2.set(2,2,2); 
 
 
 
 
        System.out.println("matrix1:\n" + matrix1); 
        System.out.println("matrix2:\n" + matrix2); 
//        System.out.println("submatrix:\n" +matrix1.getSubMatrix(0,2,2)); 
//// 
//        matrix.setSubMatrix(submatrix, 0,0); 
//        System.out.println("matrix*:" + matrix); 
 
 
//        System.out.println("sum :\n" +matrix1.sum(matrix2)); 
//        System.out.println("subtract :\n" +matrix2.subtract(matrix1)); 
        System.out.println("product :\n" +matrix1.product(matrix2)); 
 
    } 
} 
