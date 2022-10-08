package model;

import java.util.Iterator;

public class ArrayGrid implements  Grid {

    private final Square[][] squares;
    int numberOfRows;
    int numberOfColumns;

    public ArrayGrid(int numberOfRows, int numberOfColumns) {
        this.squares = new Square[numberOfRows][numberOfColumns];
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                squares[i][j] = new ArraySquare();}}

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if(i !=0){
                      squares[i][j].setNeighbor(squares[i-1][j],CardinalDirection.NORTH);
                }

                if(i !=numberOfRows-1){
                    squares[i][j].setNeighbor(squares[i+1][j],CardinalDirection.SOUTH);
                }
                if(j !=0){
                    squares[i][j].setNeighbor(squares[i][j-1],CardinalDirection.WEST);
                }
                if(j <numberOfColumns-1){
                    squares[i][j].setNeighbor(squares[i][j+1],CardinalDirection.EAST);
               }

                }
            }
        }


    @Override
    public Square getSquare(int rowIndex, int columnIndex) {
        return squares[rowIndex][columnIndex];
    }

    @Override
    public int getNumberOfRows() {
            return this.numberOfRows;
    }

    @Override
    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    @Override
    public void fill(TileGenerator tileGenerator) {
//        for (int i = 0; i < getNumberOfRows(); i++) {
//            for (int j = 0; j < getNumberOfColumns(); j++) {
//                squares[i][j].put(tileGenerator.nextTile(squares[i][j]));
//            }
//        }
        //tache 8.3
        for(Square square : this){
            square.put(tileGenerator.nextTile(square));
        }
    }

    @Override
    public Iterator<Square> iterator() {
        return new SquareGridIterator(this);
    }
}