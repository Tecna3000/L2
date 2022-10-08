public class SquareMatrix { 
 
    private int dimension; 
    int [][] matrix; 
 
    public SquareMatrix(int dimension){ 
        this.dimension= dimension; 
        this.matrix= new int[dimension][dimension]; 
 
    } 
    public int getMatrixDimension(){ 
        return this.dimension; 
    } 
    public int get(int row,int col){ 
        return matrix [row][col]; 
    } 
    public void set(int row, int col, int value){ 
        matrix [row][col] = value; 
 
    } 
    public SquareMatrix getSubMatrix(int firstRow, int firstColumn, int dim){ 
      SquareMatrix subMatrix = new SquareMatrix(dim); 
        for (int i= 0 ;i< dim; i++){ 
            for(int j= 0; j <dim; j++){ 
                subMatrix.set(i,j,this.get(i,j)); 
            } 
        } 
            return subMatrix; 
    } 
 
    public void setSubMatrix(SquareMatrix matrix , int row, int column){ 
        for (int i= row; i< matrix.getMatrixDimension();i++){ 
            for(int j =column; j< matrix.getMatrixDimension();j++){ 
                this.set(i,j,matrix.get(i,j)); 
            } 
        } 
        } 
 
    public String toString(){ 
        String affichage = String.valueOf('['); 
        for(int i=0;i<this.dimension;i++){ 
            for(int j =0; j<this.dimension; j++){ 
                affichage += this.get(i,j) + ","; 
            } 
            affichage += "\n"; 
        } 
        affichage += "]"; 
        return affichage; 
    } 
 
    public SquareMatrix sum(SquareMatrix matrix){ 
        SquareMatrix sumMatrix = new SquareMatrix(dimension); 
        for(int i=0;i<this.dimension;i++) { 
            for (int j = 0; j < this.dimension; j++) { 
                sumMatrix.set(i,j,this.get(i,j)+ matrix.get(i,j)); 
            } 
        } 
        return sumMatrix; 
    } 
 
    public SquareMatrix subtract(SquareMatrix matrix){ 
        SquareMatrix subtractMatrix = new SquareMatrix(dimension); 
        for(int i=0;i<this.dimension;i++) { 
            for (int j = 0; j < this.dimension; j++) { 
                subtractMatrix.set(i,j,this.get(i,j) - matrix.get(i,j)); 
            } 
        } 
        return subtractMatrix; 
    } 
 
    public SquareMatrix product(SquareMatrix matrix){ 
        SquareMatrix productMatrix = new SquareMatrix(dimension); 
        for(int i=0;i<dimension;i++) { 
            for (int j = 0; j < dimension; j++) { 
 
                for (int k = 0; k < dimension; k++) { 
               productMatrix.set(i,j, productMatrix.get(i,j)+ this.get(i, k) * matrix.get(k, j)); 
 
                } 
            } 
        } 
        return productMatrix; 
    } 
    }
