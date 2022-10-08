import javafx.scene.paint.Color;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Arnaud Labourel on 02/10/2018.
 */
public class MatrixGrayImage implements GrayImage {

    private final GrayColor[][] pixels;
    private final int width;
    private final int height;


    @Override
    public void setPixel(GrayColor gray, int x, int y) {
        // TODO : Compléter la méthode pour modifier le pixel.
    }

    @Override
    public GrayColor getPixelGrayColor(int x, int y) {
        // TODO : Changer les instructions pour retourner le bon pixel.
        return new ByteGrayColor();
    }

    @Override
    public Color getPixelColor(int x, int y) {
        // TODO : Changer les instructions pour retourner la couleur du pixel.
        return Color.WHITE;
    }

    @Override
    public int getWidth() {
        // TODO : Changer les instructions pour retourner la largeur de l'image.
        return 600;
    }

    @Override
    public int getHeight() {
        // TODO : Changer les instructions pour retourner la hauteur de l'image.
        return 400;
    }

    public MatrixGrayImage(int width, int height){
        /* TODO : Modifier les instructions pour initialiser correctement
            les propriétés de l'instance.
         */
        this.width = 0;
        this.height = 0;
        this.pixels = null;
    }


    public static MatrixGrayImage createImageFromPGMFile(String fileName) {
        // NE PAS MODIFIER !
        InputStream file = ClassLoader.getSystemResourceAsStream(fileName);
        Scanner scan = new Scanner(file);
        scan.nextLine();
        scan.nextLine();

        int width = scan.nextInt();
        int height = scan.nextInt();

        MatrixGrayImage result = new MatrixGrayImage(width, height);

        scan.nextInt();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {
                GrayColor color = new ByteGrayColor(scan.nextInt());
                result.setPixel(color, x, y);
            }
        }

        return result;
    }

    public void writeIntoPGMFormat(String fileName){
        // NE PAS MODIFIER !
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("P2");
            printWriter.println("# CREATOR: TP3 Version 1.0");
            printWriter.printf("%d %d\n",this.width, this.height);

            printWriter.println(pgmCodeOfGrayColor(pixels[0][0]));

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++) {
                    printWriter.println(pgmCodeOfGrayColor(getPixelGrayColor(x,y)));
                }
            }
            printWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static final int PGM_MAXIMUM_CODE = 255;

    private int pgmCodeOfGrayColor(GrayColor pixelGrayColor) {
        return (int) (pixelGrayColor.getLuminosity() * (double) PGM_MAXIMUM_CODE);
    }
}
