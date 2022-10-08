package Util;

import javafx.scene.paint.Color;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConstrainedRotatedTruchetTileGenerator implements TileGenerator {
    private final Color color1;
    private final Color color2;
    private final Random randomGenerator;
    public ConstrainedRotatedTruchetTileGenerator(Color color1, Color color2, Random randomGenerator){
        this.color1 = color1;
        this.color2 = color2;
        this.randomGenerator = randomGenerator;

    }


    @Override
    public Tile nextTile(Square square) {
        Tile tile = new TruchetTile(new ColoredSide(color1),new ColoredSide(color2) );
        List<Tile> compatibletiles = new ArrayList<>();
        for(CardinalDirection direction :CardinalDirection.values()){
           compatibletiles.add(new RotatedTile(tile,Rotation.values()[direction.ordinal()]));
        }

        return RandomUtil.randomElement(square.compatibleTiles(compatibletiles),randomGenerator);

    }
}
