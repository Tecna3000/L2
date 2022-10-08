import model.ArrayGrid;
import model.EmptyTile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayGridTest {

    @Test
    public void testGetSquare() {
        int numberOfRows = 15;
        int numberOfColumns = 20;
        int i =5;
        int j = 6;
       ArrayGrid grid = new ArrayGrid(numberOfRows, numberOfColumns);
       assertThat(grid.getSquare(i, j).getTile()).isEqualTo(EmptyTile.EMPTY_TILE);
    }

    @Test
    public void testGetNumberOfRows() {
        int numberOfRows = 15;
        int numberOfColumns = 20;
        ArrayGrid grid = new ArrayGrid(numberOfRows, numberOfColumns);
        assertThat(grid.getNumberOfRows()).isEqualTo(15);

    }

    @Test
    public void testGetNumberOfColumns() {
        int numberOfRows = 15;
        int numberOfColumns = 20;
        ArrayGrid grid = new ArrayGrid(numberOfRows, numberOfColumns);
        assertThat(grid.getNumberOfColumns()).isEqualTo(20);

    }

}