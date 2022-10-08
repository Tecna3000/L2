package viewer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {

  private static final int BUTTON_WIDTH = 500;
  @FXML
  private AnchorPane anchorPane;

  @FXML
  private VBox vBox;

  private FunctionList functionList;

  @Override
  public void initialize(final URL url, final ResourceBundle rb) {
    FunctionChart functionChart = new FunctionChart();
    functionList = new FunctionList(functionChart);
    anchorPane.getChildren().add(functionChart);
    addFunctionButtons();
    addClearButton();
  }

  private void addClearButton() {
    Button clearButton = new Button("Clear");
    clearButton.setOnAction(event -> functionList.clear());
    addButton(clearButton);
  }

  private void addFunctionButtons() {
    for(PlottableFunction function : functionList.getFunctions()){
      addFunctionButton(function);
    }
  }

  private void addFunctionButton(PlottableFunction function) {
    Button button = new Button(function.toString());
    addButton(button);
    button.setOnAction(event -> toggleFunction(function));
  }

  private void toggleFunction(PlottableFunction function){
    functionList.toggleFunction(function);
  }

  private void addButton(Button button) {
    button.setPrefWidth(BUTTON_WIDTH);
    vBox.getChildren().add(button);
  }
}
