package view;

import model.CardinalDirection;

import java.util.List;

public enum PointType {
  NORTH_WEST_CORNER(0,0),
  NORTH_EAST_CORNER(1,0),
  SOUTH_EAST_CORNER(1,1),
  SOUTH_WEST_CORNER(0,1),
  CENTER(0.5,0.5);
  private final double xPosition;
  private final double yPosition;

  PointType(double xPosition, double yPosition) {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
  }

  static public List<PointType> trianglePointTypes(CardinalDirection side){
    return switch (side){
      case NORTH -> List.of(PointType.NORTH_WEST_CORNER, PointType.NORTH_EAST_CORNER, PointType.CENTER);
      case EAST -> List.of(PointType.NORTH_EAST_CORNER, PointType.SOUTH_EAST_CORNER, PointType.CENTER);
      case SOUTH -> List.of(PointType.SOUTH_EAST_CORNER, PointType.SOUTH_WEST_CORNER, PointType.CENTER);
      case WEST -> List.of(PointType.SOUTH_WEST_CORNER, PointType.NORTH_WEST_CORNER, PointType.CENTER);
    };
  }

  public double getXPosition() {
    return xPosition;
  }

  public double getYPosition() {
    return yPosition;
  }
}
