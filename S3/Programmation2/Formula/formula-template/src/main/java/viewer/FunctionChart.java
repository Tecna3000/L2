package viewer;


import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

class FunctionChart extends LineChart<Number, Number> {

  private static final int TICK_UNIT = 1;
  private static final int LOWER_BOUND = -10;
  private static final int UPPER_BOUND = 10;

  int getLowerBound() {
    return LOWER_BOUND;
  }

  int getUpperBound() {
    return UPPER_BOUND;
  }

  FunctionChart(){
    super(new NumberAxis(LOWER_BOUND, UPPER_BOUND, TICK_UNIT), new NumberAxis(LOWER_BOUND, UPPER_BOUND, TICK_UNIT));
    getXAxis().setSide(Side.BOTTOM);
    getYAxis().setSide(Side.LEFT);
    setPrefWidth(900);
    setPrefHeight(900);
    setCreateSymbols(false);
  }

  private Series<Number, Number> getSeries(String name){
    for (Series<Number, Number> series : getData())
      if(series.getName().equals(name)){
        return series;
      }
    return null;
  }

  void removeSeries(String name){
    Series<Number, Number> series = getSeries(name);
    if(series != null){
      getData().remove(series);
    }
  }
}
