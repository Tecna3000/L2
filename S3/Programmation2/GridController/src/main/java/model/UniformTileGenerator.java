package model;
import javafx.scene.paint.Color;

public class UniformTileGenerator implements TileGenerator {
    private final Tile tile;

    public UniformTileGenerator(Color color) {
        this.tile = new UniformeTile(new ColoredSide(color));
    }

    @Override
    public Tile nextTile(Square square) {
        return tile;
    }
}
