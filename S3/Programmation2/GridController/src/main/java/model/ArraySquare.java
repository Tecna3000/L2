package model;

import java.util.List;

public class ArraySquare implements Square {
    private Tile tile;
    private final Square[] neighbors;

    public ArraySquare(){
        tile = EmptyTile.EMPTY_TILE;
        for
    }

    public void put(Tile tile){
        this.tile = tile;
    }

    @Override
    public Tile getTile() {
        return tile;
    }

    @Override
    public void setNeighbor(Square neighbor, CardinalDirection direction) {

    }

    @Override
    public Square getNeighbor(CardinalDirection direction) {
        return null;
    }

    @Override
    public List<Side> compatibleSides(List<Side> sides, CardinalDirection direction) {
        return null;
    }

    @Override
    public List<Tile> compatibleTiles(List<Tile> tiles) {
        return null;
    }


}
