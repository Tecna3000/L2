package model;

import javafx.scene.paint.Color;

import java.util.List;

public class EmptySide implements Side {
  public static final Side EMPTY_SIDE = new EmptySide();

  private EmptySide(){}

  @Override
  public Color color() {
    return Color.WHITE;
  }

  @Override
  public List<Side> compatibleSides(List<Side> sides) {
    return sides;
  }

  @Override
  public boolean accept(Side side) {
    return true;
  }
}
