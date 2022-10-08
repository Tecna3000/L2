package model;

import java.util.Iterator;

public class EmptyGrid implements Grid{
  private final int numberOfRows;
  private final int numberOfColumns;

  public EmptyGrid(int numberOfRows, int numberOfColumns) {
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
  }

  @Override
  public Square getSquare(int rowIndex, int columnIndex) {
    return EmptySquare.EMPTY_SQUARE;
  }

  @Override
  public int getNumberOfRows() {
    return numberOfRows;
  }

  @Override
  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  @Override
  public void fill(TileGenerator tileGenerator) {
  }
}
