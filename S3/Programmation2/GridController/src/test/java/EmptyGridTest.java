import model.EmptyGrid;
import model.EmptySquare;
import model.Grid;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyGridTest {
  @Test
  void testGetNumberOfRows(){
    assertThat(new EmptyGrid(10,20).getNumberOfRows()).isEqualTo(10);
    assertThat(new EmptyGrid(100,200).getNumberOfRows()).isEqualTo(100);
  }

  @Test
  void testGetNumberOfColumns(){
    assertThat(new EmptyGrid(10,20).getNumberOfColumns()).isEqualTo(20);
    assertThat(new EmptyGrid(100,200).getNumberOfColumns()).isEqualTo(200);
  }

  @Test
  void testGetSquare(){
    Grid emptyGrid = new EmptyGrid(100,20);
    assertThat(emptyGrid.getSquare(0, 0)).isEqualTo(EmptySquare.EMPTY_SQUARE);
    assertThat(emptyGrid.getSquare(10, 9)).isEqualTo(EmptySquare.EMPTY_SQUARE);
    assertThat(emptyGrid.getSquare(99, 19)).isEqualTo(EmptySquare.EMPTY_SQUARE);
  }

}
