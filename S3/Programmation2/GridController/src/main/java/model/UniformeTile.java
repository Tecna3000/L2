package model;

public class UniformeTile implements Tile {

    private final Side side;

    public UniformeTile(Side side) {
        this.side = side;
    }

    @Override
    public Side side(CardinalDirection direction) {
        return this.side;
    }
}