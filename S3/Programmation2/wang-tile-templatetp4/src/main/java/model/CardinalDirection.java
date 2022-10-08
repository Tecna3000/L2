package model;

public enum CardinalDirection {
  NORTH(-1, 0),
  EAST(0, 1),
  SOUTH(1, 0),
  WEST(0, -1);

  public static final int NUMBER_OF_DIRECTIONS = values().length;

  public final int deltaRow;
  public final int deltaColumn;

  CardinalDirection(int deltaRow, int deltaColumn) {
    this.deltaRow = deltaRow;
    this.deltaColumn = deltaColumn;
  }

  public CardinalDirection oppositeDirection(){
    return switch (this){
      case EAST -> WEST;
      case WEST -> EAST;
      case NORTH -> SOUTH;
      case SOUTH -> NORTH;
    };
  }
}
