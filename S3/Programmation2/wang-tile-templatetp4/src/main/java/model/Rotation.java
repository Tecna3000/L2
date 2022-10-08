package model;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public enum Rotation {



    NO_TURN(0),
    QUARTER_TURN(1),
     HALF_TURN(2),
   THREE_QUARTER_TURN(3);

    private final int numberOfQuarterTurns;

    Rotation(int numberOfQuarterTurns) {
        this.numberOfQuarterTurns = numberOfQuarterTurns;
    }

    public CardinalDirection rotatedDirection(CardinalDirection direction) {

        List<CardinalDirection> directions = new ArrayList<>();
        directions.add(CardinalDirection.NORTH);
        directions.add(CardinalDirection.EAST);
        directions.add(CardinalDirection.SOUTH);
        directions.add(CardinalDirection.WEST);
        return(directions.get((direction.ordinal()+numberOfQuarterTurns+1)%4));


    }
}
