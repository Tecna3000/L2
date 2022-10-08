package model;

public class EmptyTile implements Tile{
  public static Tile EMPTY_TILE = new EmptyTile();

  private EmptyTile(){}

  @Override
  public Side side(CardinalDirection direction) {
    return EmptySide.EMPTY_SIDE;
  }
}
