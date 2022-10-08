package model;

public class EmptyTileGenerator implements TileGenerator{
  @Override
  public Tile nextTile(Square square) {
    return EmptyTile.EMPTY_TILE;
  }
}
