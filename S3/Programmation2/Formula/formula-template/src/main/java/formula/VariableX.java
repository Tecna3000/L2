package formula;

public class VariableX implements Formula{



    public VariableX(){

    }

    @Override
    public double eval(double xValue) {
        return xValue;
    }
    @Override
    public String toString(){
        return "x" ;
    }

    @Override
    public Formula derivative() {
        return new Constant(1);
    }
}
