package model;

public class ArrayGrid implements  Grid {

    private final Square[][] squares;
    int numberOfRows;
    int numberOfColumns;

    public ArrayGrid(int numberOfRows, int numberOfColumns) {
        squares = new Square[numberOfRows][numberOfColumns];
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                squares[i][j] = new ArraySquare();
            }
        }
    }

    @Override
    public Square getSquare(int rowIndex, int columnIndex) {
        return squares[rowIndex][columnIndex];
    }

    @Override
    public int getNumberOfRows() {
        return this.numberOfColumns;
    }

    @Override
    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    @Override
    public void fill(TileGenerator tileGenerator) {
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                squares[i][j].put(tileGenerator.nextTile(squares[i][j]));
            }
        }
    }
}