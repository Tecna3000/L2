import Util.WangTile;
import javafx.scene.paint.Color;
import model.CardinalDirection;
import model.ColoredSide;
import model.Side;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class WangTileTest {

    @Test
    public void sideTest() {
        Side side1 = new ColoredSide(Color.PINK);
        Side side2 = new ColoredSide(Color.PURPLE);
        Side side3 = new ColoredSide(Color.BLACK);
        Side side4 = new ColoredSide(Color.GRAY);

         Side[] sides = new Side[4];
         sides[0] = side1;
         sides[1] =side2;
         sides[2] = side3;
         sides[3] = side4;


        WangTile wangTile = new WangTile(sides);
        assertThat(wangTile.side(CardinalDirection.NORTH)).isEqualTo(side1);
        assertThat(wangTile.side(CardinalDirection.EAST)).isEqualTo(side2);
        assertThat(wangTile.side(CardinalDirection.SOUTH)).isEqualTo(side3);
        assertThat(wangTile.side(CardinalDirection.WEST)).isEqualTo(side4);

    }


}