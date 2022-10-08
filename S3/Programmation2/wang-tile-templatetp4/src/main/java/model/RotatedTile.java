package model;

public class RotatedTile implements Tile {
    private final Tile tile;
    private final Rotation rotation;

    public RotatedTile(Tile tile, Rotation rotation) {
        this.tile = tile;
        this.rotation = rotation;
    }
@Override
    public Side side(CardinalDirection direction){
        return tile.side(rotation.rotatedDirection(direction));

    }
}
