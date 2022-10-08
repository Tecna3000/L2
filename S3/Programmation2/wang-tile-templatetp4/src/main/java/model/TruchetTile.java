package model;

public class TruchetTile implements Tile{
    private Side northWestColor;
    private Side southEastColor;


    public TruchetTile(Side northWestColor, Side southEastColor) {

        this.northWestColor = northWestColor;
        this.southEastColor = southEastColor;

    }

    @Override
    public Side side(CardinalDirection direction) {
        if(direction == CardinalDirection.NORTH || direction ==  CardinalDirection.WEST) {
            return northWestColor;

        }
        return  southEastColor;

        }
    }

