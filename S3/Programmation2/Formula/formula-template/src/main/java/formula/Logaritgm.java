package formula;

public class Logaritgm implements Formula{

    private Formula formule;

    public Logaritgm(Formula formule) {
        this.formule = formule;
    }

    @Override
    public double eval(double xValue) {
        return Math.log(formule.eval(xValue));
    }

    @Override
    public Formula derivative() {
        return new Division(formule.derivative(), formule);
    }
    @Override
    public String toString(){
        return "log(" + formule + ")";
    }
}
