


import javafx.scene.paint.Color;
import model.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraySquareTest {

    @Test
    public void getTileTest() {
        Square square = new ArraySquare();
        Tile tile = EmptyTile.EMPTY_TILE;
        square.put(tile);
        assertThat(square.getTile()).isEqualTo(tile);
    }
}