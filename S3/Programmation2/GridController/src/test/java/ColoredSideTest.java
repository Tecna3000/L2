import javafx.scene.paint.Color;
import model.ColoredSide;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;


public class ColoredSideTest {
    @Test
    public void testColor()
    {

        ColoredSide blue = new ColoredSide(Color.BLUE);
        assertThat(blue.color()).isEqualTo(Color.BLUE);

    }


}
