package model;

public class ArraySquare implements Square {
    private Tile tile;

    public ArraySquare(){
        tile = EmptyTile.EMPTY_TILE;
    }

    public void put(Tile tile){
        this.tile = tile;
    }

    @Override
    public Tile getTile() {
        return tile;
    }


}
