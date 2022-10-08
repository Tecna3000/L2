package viewer;

import formula.Formula;
import javafx.scene.chart.XYChart;

public class PlottableFunction {
  private final Formula formula;
  private final String name;
  private static final double PRECISION = 0.01;
  private boolean isPlotted = false;
  
  PlottableFunction(Formula formula, String name) {
    this.formula = formula;
    this.name = name;
  }

  @Override
  public String toString() {
    return name + "(x) = " + formula;
  }

  public PlottableFunction derivative(){
    return new PlottableFunction(formula.derivative(), name + "'");
  }

  XYChart.Series<Number, Number> getData(double lowerBound, double upperBound) {
    final XYChart.Series<Number, Number> series = new XYChart.Series<>();
    for (int index = 0; index <= 2 * (upperBound-lowerBound) / PRECISION; index++) {
      double x = lowerBound + index * PRECISION;
      series.getData().add(new XYChart.Data<>(x, formula.eval(x)));
    }
    return series;
  }

  boolean isPlotted() {
    return isPlotted;
  }

  void setPlotted(boolean plotted) {
    isPlotted = plotted;
  }
}
