package main;

import controller.GridController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
  private Stage primaryStage;

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Wang tiles");
    initRootLayout();
  }

  public void initRootLayout() {
    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GridCanvas.fxml"));
      AnchorPane rootLayout = loader.load();


      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);


      GridController controller = loader.getController();
      controller.initialize();
      controller.drawGrid();
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}