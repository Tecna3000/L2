package viewer;

import formula.Addition;
import formula.Constant;
import formula.VariableX;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FunctionList {
  private final List<PlottableFunction> functions = new ArrayList<>();

  private final FunctionChart functionChart;
  private final double lowerBound;
  private final double upperBound;

  FunctionList(FunctionChart functionChart) {
    this.functionChart = functionChart;
    this.lowerBound = functionChart.getLowerBound();
    this.upperBound = functionChart.getUpperBound();

//    PlottableFunction function = new PlottableFunction(new Constant(1), "f");
//    addFunctionAndItsDerivative(function);
//    PlottableFunction function = new PlottableFunction(new VariableX(), "g");
//    addFunctionAndItsDerivative(function);
    PlottableFunction function = new PlottableFunction(new Addition(new VariableX(), new Constant(5)), "h");
    addFunctionAndItsDerivative(function);
  }

  void toggleFunction(PlottableFunction function) {
    if (function.isPlotted()){
      unplot(function);
    }
    else{
      plot(function);
    }
  }

  private void unplot(PlottableFunction function) {
    functionChart.removeSeries(function.toString());
    function.setPlotted(false);
  }

  List<PlottableFunction> getFunctions(){
    return functions;
  }

  private void plot(PlottableFunction function){
    XYChart.Series<Number, Number> series = function.getData(lowerBound, upperBound);
    series.setName(function.toString());
    functionChart.getData().add(series);
    function.setPlotted(true);
  }

  private void addFunctionsAndTheirDerivative(Collection<PlottableFunction> functions){
    for(PlottableFunction function: functions){
      addFunctionAndItsDerivative(function);
    }
  }

  private void addFunctionAndItsDerivative(PlottableFunction function){
    add(function);
    add(function.derivative());
  }

  private void add(PlottableFunction function) {
    functions.add(function);
  }

  void clear() {
    functionChart.getData().clear();
    for(PlottableFunction function: functions){
      function.setPlotted(false);
    }
  }
}
