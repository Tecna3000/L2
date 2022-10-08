import javafx.scene.paint.Color;

/**
 * Created by Arnaud Labourel on 02/10/2018.
 */
public interface Image {
    Color getPixelColor(int x, int y);
    int getWidth();
    int getHeight();
}
