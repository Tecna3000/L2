import javafx.scene.paint.Color;
import model.CardinalDirection;
import model.ColoredSide;
import model.Side;
import model.UniformeTile;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UniformeTileTest {

        @Test
        public void sideTest() {

            Side side= new ColoredSide(Color.PINK);
            CardinalDirection direction = CardinalDirection.EAST ;
            assertThat(new UniformeTile(side).side(direction)).isEqualTo(side);

        }
    }


