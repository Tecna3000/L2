package model;

import java.util.List;

public class EmptySquare implements Square{
  public static final Square EMPTY_SQUARE = new EmptySquare();

  private EmptySquare() {}

  @Override
  public void put(Tile tile) {}

  @Override
  public Tile getTile() {
    return EmptyTile.EMPTY_TILE;
  }
}
