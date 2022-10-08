import javafx.scene.paint.Color;

/**
 * Created by Arnaud Labourel on 02/10/2018.
 */

public class ByteGrayColor implements GrayColor {

    private static final int MINIMUM_GRAY_LEVEL = 0;
    private static final int MAXIMUM_GRAY_LEVEL = 255;
    private static final int OPACITY = 1;

    private final int grayLevel;
    public static final int BlACK = 0;
    public static final int WHITE = 255;


    public ByteGrayColor(){
       this.grayLevel = MINIMUM_GRAY_LEVEL;
    }

    public ByteGrayColor(int grayLevel) {
        this.grayLevel = grayLevel;
    }

    public ByteGrayColor(double luminosity) {
        // TODO : Corriger l'initialisation de la propriété grayLevel de l'instance.
        this.grayLevel = luminosity;
    }

    @Override
    public double getLuminosity() {

        return ;
    }

    @Override
    public Color getColor(){
        double component = grayLevel / (double) MAXIMUM_GRAY_LEVEL;
        return new Color(component, component, component, OPACITY);
    }


    @Override
    public int compareTo(GrayColor o) {
        // TODO : Retourner la différence de niveau de gris.
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        ByteGrayColor color = (ByteGrayColor) o;
        return this.compareTo(color) == 0;
    }

}
