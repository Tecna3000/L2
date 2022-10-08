package view;

import javafx.beans.NamedArg;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

import java.util.List;

public class GridCanvas extends Canvas {

  public final double tileWidth;
  private final double tileHeight;
  private final Integer numberOfColumns;
  private final Integer numberOfRows;
  private final Grid grid;
  private final GraphicsContext graphicsContext;

  public GridCanvas(@NamedArg("tileWidth") Double tileWidth,
                    @NamedArg("tileHeight") Double tileHeight,
                    @NamedArg("numberOfColumns") Integer numberOfColumns,
                    @NamedArg("numberOfRows") Integer numberOfRows) {
    this.tileWidth = tileWidth;
    this.tileHeight = tileHeight;
    this.numberOfColumns = numberOfColumns;
    this.numberOfRows = numberOfRows;
    this.setWidth(tileWidth * numberOfColumns);
    this.setHeight(tileHeight * numberOfRows);
    grid = new ArrayGrid(numberOfRows, numberOfColumns);
    graphicsContext = getGraphicsContext2D();
  }

  public void update(){
    clear();
    drawGrid();
  }

  private void drawGrid(){
    draw(grid);
  }

  private void clear(){
    graphicsContext.clearRect(0, 0, getWidth(), getHeight());
  }

  public void fillGrid(TileGenerator tileGenerator){
    grid.fill(tileGenerator);
  }

  public void draw(Grid tileGrid){
    drawSquares(tileGrid);
    strokeSquares(tileGrid);
  }

  private void drawSquares(Grid tileGrid) {
    for(int row = 0; row < numberOfRows; row++)
      for(int column = 0; column < numberOfColumns; column++){
        Square square = tileGrid.getSquare(row, column);
        drawSquare(square, column * tileWidth, row * tileHeight);
      }
  }

  private void strokeSquares(Grid tileGrid) {
    for(int row = 0; row < numberOfRows; row++)
      for(int column = 0; column < numberOfColumns; column++){
        Square square = tileGrid.getSquare(row, column);
        strokeSquare(square, column * tileWidth, row * tileHeight);
      }
  }


  private void strokeSquare(Square square, double x, double y) {
    PointType[] cornerTypes = PointType.values();
    double[] xPoints = new double[cornerTypes.length];
    double[] yPoints = new double[cornerTypes.length];
    for(int index = 0; index < cornerTypes.length; index++){
      xPoints[index] =  x + (cornerTypes[index].getXPosition() * tileWidth);
      yPoints[index] =  y + (cornerTypes[index].getYPosition() * tileHeight);
    }
    final int indexCenter = 4;
    for(CardinalDirection side : CardinalDirection.values()) {
      int indexCorner0 = side.ordinal();
      int indexCorner1 = (indexCorner0+1)%4;
      strokeSquareSide(square, xPoints[indexCorner0], yPoints[indexCorner0], xPoints[indexCorner1], yPoints[indexCorner1], side);
      strokeInternalTriangleSide(square, xPoints[indexCorner0],yPoints[indexCorner0],xPoints[indexCenter],yPoints[indexCenter], side);
    }
  }

  private void strokeSquareSide(Square square, double x0, double y0, double x1, double y1, CardinalDirection side) {
    graphicsContext.strokeLine(x0, y0, x1, y1);
  }

  private void strokeInternalTriangleSide(Square square, double x0, double y0, double x1, double y1, CardinalDirection side) {
    graphicsContext.strokeLine(x0, y0, x1, y1);
  }

  private void drawSquare(Square square, double x, double y) {
    for(CardinalDirection side: CardinalDirection.values()){
      drawSideTriangle(square, x, y, side);
    }
  }

  private void drawSideTriangle(Square square, double x, double y, CardinalDirection side){
    Color color = square.getTile().side(side).color();
    List<PointType> cornerTypes = PointType.trianglePointTypes(side);
    int size = PointType.trianglePointTypes(side).size();
    double[] xPoints = new double[size];
    double[] yPoints = new double[size];
    for(int index = 0; index < size; index++){
      xPoints[index] =  x + (cornerTypes.get(index).getXPosition() * tileWidth);
      yPoints[index] =  y + (cornerTypes.get(index).getYPosition() * tileHeight);
    }
        graphicsContext.setFill(color);
        graphicsContext.fillPolygon(xPoints, yPoints, size);
        
  }
}
