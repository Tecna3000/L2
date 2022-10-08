package model;

import javafx.scene.paint.Color;

import java.util.List;

public class ColoredSide implements Side{
    private final Color color;

    public ColoredSide(Color color) {
        this.color = color;
    }



    @Override
    public Color color() {
        return this.color;
    }

    @Override
    public boolean accept(Side side) {
        return this.color == side.color();
    }
    @Override
    public List<Side> compatibleSides(List<Side> sides) {
        sides.removeIf(side -> !accept(side));
        return sides;
    }


}
