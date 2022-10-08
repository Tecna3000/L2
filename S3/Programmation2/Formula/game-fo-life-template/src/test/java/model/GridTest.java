package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {
  private Grid grid;

  @BeforeEach
  public void initializeGrid(){
    grid = new Grid(3,3);
  }

  @Test
  public void testGetNeighbors(){
    assertThat(grid.getNeighbors(1,1)).isNotNull();
    assertThat(grid.getNeighbors(1,1)).hasSize(8);
    assertThat(grid.getNeighbors(1,1))
            .containsExactlyInAnyOrder(grid.getCell(0,0),
                    grid.getCell(0,1),
                    grid.getCell(0,2),
                    grid.getCell(1,0),
                    grid.getCell(1,2),
                    grid.getCell(2,0),
                    grid.getCell(2,1),
                    grid.getCell(2,2));
  }

  @Test
  public void testCountAliveNeighbors(){
    assertThat(grid.countAliveNeighbors(1,1)).isEqualTo(0);
    grid.getCell(2,2).setState(CellState.ALIVE);
    grid.getCell(0,0).setState(CellState.ALIVE);
    assertThat(grid.countAliveNeighbors(1,1)).isEqualTo(2);
  }
}
