package formula;

import com.sun.javafx.scene.paint.MaterialHelper;

public class Cosine implements Formula{
    private final Formula formule;

    public Cosine(Formula formule){
        this.formule = formule;
    }
    @Override
    public double eval(double xValue) {
        return Math.cos(formule.eval(xValue));
    }

    @Override
    public Formula derivative() {

        return new Multiplication(new Opposite(formule.derivative()), new Sine (formule));
    }
    @Override
    public String toString(){
        return "cos(" + formule + ")";
    }
}
