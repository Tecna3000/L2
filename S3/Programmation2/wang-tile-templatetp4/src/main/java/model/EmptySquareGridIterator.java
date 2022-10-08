package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptySquareGridIterator implements Iterator<Square> {



    public  EmptySquareGridIterator(){}



    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Square next() {
         throw new NoSuchElementException( "Error");

    }
}
