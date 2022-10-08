package formula;

public class Exponential implements Formula{

    private Formula formule;

    public Exponential(Formula formule) {
        this.formule = formule;
    }

    @Override
    public double eval(double xValue) {
        return Math.exp(formule.eval(xValue));
    }

    @Override
    public Formula derivative() {
        return new Multiplication(formule.derivative(),formule);
    };
    @Override
    public String toString(){
        return "Expo(" + formule + ")";
    }

}
