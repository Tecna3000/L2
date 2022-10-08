package Util;

import javafx.scene.paint.Color;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomConstrainedWangTileGenerator implements TileGenerator {
    private final List<Side> availableSides;
    private final Random randomGenerator;

    public RandomConstrainedWangTileGenerator(List<Color> colors, Random randomGenerator) {
        this.randomGenerator = randomGenerator;
        this.availableSides = new ArrayList<>();
        for (Color color : colors) {
           availableSides.add(new ColoredSide(color));
        }
    }

    @Override
    public Tile nextTile(Square square) {
        Side[] sides = new Side[CardinalDirection.NUMBER_OF_DIRECTIONS];
        for (CardinalDirection direction : CardinalDirection.values()) {
            List<Side> sideList= square.compatibleSides(availableSides,direction);
            sides[direction.ordinal()] = RandomUtil.randomElement(sideList,randomGenerator);

        }
        return new WangTile(sides);

    }
}
