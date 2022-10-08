package Util;

import model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

public class RandomWangTileGenerator implements TileGenerator {
    private final List<Side> availableSides;
    private final Random randomGenerator;

    public RandomWangTileGenerator(List<Color> colors, Random randomGenerator) {
        this.availableSides = new ArrayList<>(colors.size());
        for(Color color: colors){
            this.availableSides.add(new ColoredSide(color));
        }
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Tile nextTile(Square square) {
        Side []sides = new Side[CardinalDirection.NUMBER_OF_DIRECTIONS];
        for(CardinalDirection direction : CardinalDirection.values()){
            int position = randomGenerator.nextInt(CardinalDirection.NUMBER_OF_DIRECTIONS);

            sides[direction.ordinal()] = availableSides.get(position) ;

        }
        return new WangTile(sides);


    }
}
