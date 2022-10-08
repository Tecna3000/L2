import model.CardinalDirection;
import model.EmptySide;
import model.EmptyTile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyTileTest {

  @Test
  void testSide(){
    assertThat(EmptyTile.EMPTY_TILE.side(CardinalDirection.NORTH)).isEqualTo(EmptySide.EMPTY_SIDE);
    assertThat(EmptyTile.EMPTY_TILE.side(CardinalDirection.SOUTH)).isEqualTo(EmptySide.EMPTY_SIDE);
    assertThat(EmptyTile.EMPTY_TILE.side(CardinalDirection.EAST)).isEqualTo(EmptySide.EMPTY_SIDE);
    assertThat(EmptyTile.EMPTY_TILE.side(CardinalDirection.WEST)).isEqualTo(EmptySide.EMPTY_SIDE);
  }
}
