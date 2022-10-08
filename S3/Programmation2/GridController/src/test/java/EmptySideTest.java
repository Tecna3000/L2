import javafx.scene.paint.Color;
import model.EmptySide;
import model.Side;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptySideTest {
  @Test
  void testColor(){
    assertThat(EmptySide.EMPTY_SIDE.color()).isEqualTo(Color.WHITE);
  }
}
