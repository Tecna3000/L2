package Util;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTileSetGenerator implements TileGenerator {
    private final List<Tile> availableTiles;
    private Random randomGenerator;

    public RandomTileSetGenerator(Iterable<Tile> tiles, Random randomGenerator) {
        this.randomGenerator = randomGenerator;
        this.availableTiles = new ArrayList<>();
        for(Tile tile : tiles){
            availableTiles.add(tile);
        }
    }

    @Override
    public Tile nextTile(Square square) {
            List<Tile> compatibleTiles = square.compatibleTiles(availableTiles);
            return RandomUtil.randomElement(compatibleTiles, randomGenerator);
    }
}