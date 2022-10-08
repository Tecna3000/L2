package Util;

import javafx.scene.paint.Color;
import model.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomRotatedTruchetTileGenerator implements TileGenerator {
    private final Color color1;
    private final Color color2;
    private final Random randomGenerator;
    private Tile tile;

    public RandomRotatedTruchetTileGenerator(Color color1, Color color2, Random randomGenerator){
        this.color1 = color1;
        this.color2 = color2;
        this.randomGenerator = randomGenerator;
        this.tile = new TruchetTile(new ColoredSide(color1),new ColoredSide(color2) );
    }


    @Override
    public Tile nextTile(Square square) {
        return  new RotatedTile(this.tile,RandomUtil.randomElement(Rotation.values(),randomGenerator));

    }
}
