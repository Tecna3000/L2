/**
 * Created by Arnaud Labourel on 04/10/2018.
 */

public interface GrayImage extends Image {
    void setPixel(GrayColor gray, int x, int y);
    GrayColor getPixelGrayColor(int x, int y);
}
