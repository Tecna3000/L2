package viewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainAppLauncher extends Application {

  public static void main(String[] args) {
    Application.launch(MainAppLauncher.class, args);
  }

  @Override
  public void start(Stage stage) {
    try {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader()
              .getResource("MainApp.fxml")));
      stage.setScene(new Scene(root));
      stage.setTitle("Formulas");
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
