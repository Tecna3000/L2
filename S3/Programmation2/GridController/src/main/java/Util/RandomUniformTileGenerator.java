package Util;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

public class RandomUniformTileGenerator implements TileGenerator {
    private final List<Tile> tiles= new ArrayList<>() ;
    private final Random randomGenerator;

    public RandomUniformTileGenerator(List<Color> colors, Random randomGenerator) {
        for (int i=0; i< colors.size();i++){
           tiles.add(i,new UniformeTile(new ColoredSide(colors.get(i))))  ;
        }
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Tile nextTile(Square square) {
        int position = randomGenerator.nextInt(this.tiles.size());
        return tiles.get(position);
    }
}
