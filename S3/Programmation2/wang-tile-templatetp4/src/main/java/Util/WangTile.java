package Util;
import model.*;

public class WangTile implements Tile {
    private final Side[] sides ;

    public WangTile(Side[] sides) {
        this.sides = sides;

    }
    @Override
    public Side side(CardinalDirection direction) {
        return sides[direction.ordinal()];
        };
    }

