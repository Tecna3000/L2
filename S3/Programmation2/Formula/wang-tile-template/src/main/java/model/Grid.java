package model;

public interface Grid{
  Square getSquare(int rowIndex, int columnIndex);
  int getNumberOfRows();
  int getNumberOfColumns();
  void fill(TileGenerator tileGenerator);
}
