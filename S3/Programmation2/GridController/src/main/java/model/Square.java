package model;

import java.util.List;

public interface Square {
  void put(Tile tile);
  Tile getTile();
  void setNeighbor(Square neighbor, CardinalDirection direction);
  Square getNeighbor(CardinalDirection direction);
  List<Side> compatibleSides(List<Side> sides, CardinalDirection direction);
  List<Tile> compatibleTiles(List<Tile> tiles);
}
