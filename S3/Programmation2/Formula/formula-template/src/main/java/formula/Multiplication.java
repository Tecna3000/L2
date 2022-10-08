package formula;

public class Multiplication implements Formula{

    private final Formula leftMember;
    private final Formula rightMember;

    public Multiplication(Formula leftMember, Formula rightMember){

        this.leftMember = leftMember;
        this.rightMember = rightMember;
    }
    @Override
    public double eval(double xValue) {
        return leftMember.eval(xValue) * rightMember.eval(xValue);
    }

    @Override
    public Formula derivative() {
        return new Multiplication(leftMember.derivative(),rightMember.derivative() );
    }

    @Override
    public String toString(){
        return leftMember + " * " + rightMember;
    }

}
