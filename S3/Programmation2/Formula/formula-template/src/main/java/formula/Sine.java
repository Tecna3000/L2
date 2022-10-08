package formula;

public class Sine implements Formula{
    private Formula formule;

    public Sine(Formula formule) {
        this.formule = formule;
    }

    @Override
    public double eval(double xValue) {
        return Math.sin(formule.eval(xValue));
    }

    @Override
    public Formula derivative() {
        return new Multiplication( formule.derivative(), new Cosine(formule));
    }
    @Override
    public String toString(){
        return "sin(" + formule + ")";
    }
}
