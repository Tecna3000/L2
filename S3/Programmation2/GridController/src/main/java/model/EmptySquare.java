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

  @Override
  public void setNeighbor(Square neighbor, CardinalDirection direction) {}

  @Override
  public Square getNeighbor(CardinalDirection direction) {
    return new EmptySquare();
  }

  @Override
  public List<Side> compatibleSides(List<Side> sides, CardinalDirection direction) {
    return sides;
  }

  @Override
  public List<Tile> compatibleTiles(List<Tile> tiles) {
    return tiles;
  }
}
