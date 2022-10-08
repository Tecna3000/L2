package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SquareGridIterator implements Iterator<Square> {

    private final ArrayGrid grid;
    int row = 0;
    int col = -1;

    SquareGridIterator(ArrayGrid grid) {
        this.grid = grid;

    }

    @Override
    public boolean hasNext() {

        return row!= grid.getNumberOfRows()-1 || col!= grid.getNumberOfColumns()-1;
    }

    @Override
    public Square next() {
        if(!hasNext()){
            throw new NoSuchElementException("no square left");
        }
        else {
            if (col == grid.getNumberOfColumns() - 1) {
                row++;
                col = 0;
            } else {
                col++;
            }
            return grid.getSquare(row, col);
        }

    }
}