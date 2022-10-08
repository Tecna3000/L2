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
        if ((this.color == side.color())){
            return true;
        }
        return false;
    }

    @Override
    public List<Side> compatibleSides(List<Side> sides) {
        for(Side side : sides){
            if( accept(side)== false){
                sides.remove(side);
            }
        }
        return sides;
    }


}
