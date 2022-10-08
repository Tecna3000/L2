import javafx.scene.paint.Color;
import model.ColoredSide;

import model.EmptySquare;
import model.EmptyTile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ColoredSideTest {
    @Test
    public void testColor() {
        ColoredSide blue = new ColoredSide(Color.BLUE);
        assertThat(blue.color()).isEqualTo(Color.BLUE);
    }
}