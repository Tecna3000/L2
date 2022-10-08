package formula;

public class Opposite implements  Formula{

    private Formula value;
    public Opposite(Formula value) {
    this.value = value;
    }

    @Override
    public double eval(double xValue) {
        return -xValue;
    }

    @Override
    public Formula derivative() {
        return new Constant(-1);
    }
    @Override
    public String toString(){
        return "-x" ;
    }
}
