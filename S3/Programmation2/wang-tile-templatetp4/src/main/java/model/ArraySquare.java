package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySquare implements Square {
    private Tile tile;
    private final Square[] neighbors;

    public ArraySquare(){
        tile = EmptyTile.EMPTY_TILE;
        this.neighbors = new Square[CardinalDirection.NUMBER_OF_DIRECTIONS];
        Arrays.fill(neighbors, EmptySquare.EMPTY_SQUARE);
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
        this.neighbors[direction.ordinal()]= neighbor;

    }

    @Override
    public Square getNeighbor(CardinalDirection direction) {
        return this.neighbors[direction.ordinal()];
    }

    @Override
    public List<Side> compatibleSides(List<Side> sides, CardinalDirection direction) {
        List<Side> listSide = new ArrayList<>();
        for (Side side : sides){
//           if (!tile.side(direction).accept(getNeighbor(direction).getTile().side(direction.oppositeDirection()))) {
            if (getNeighbor(direction).getTile().side(direction.oppositeDirection()).accept(side)) {
               listSide.add(side)  ;
           }
        }
        return listSide;
    }

    @Override
    public List<Tile> compatibleTiles(List<Tile> tiles) {
        List<Tile> listTile = new ArrayList<>();
        int nbCompatible = 0;
        for(Tile tile : tiles) {
            for (CardinalDirection direction : CardinalDirection.values()) {
                if (neighbors[direction.ordinal()].getTile().side(direction.oppositeDirection()).accept(tile.side(direction))) {
                    nbCompatible++;
                }
            }
                if (nbCompatible == CardinalDirection.values().length) {
                    listTile.add(tile);
                }
                nbCompatible =0;
        }
        return listTile;
    }

    }



