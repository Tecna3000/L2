package model;

import javafx.scene.paint.Color;

public class ColoredSide implements Side{
    private final Color color;

    public ColoredSide(Color color) {
        this.color = color;
    }



    @Override
    public Color color() {
        return this.color;
    }
}
