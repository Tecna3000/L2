package formula;

public class Substraction implements Formula{
    private final Formula leftMember;
    private final Formula rightMember;

    public Substraction(Formula leftMember, Formula rightMember) {
        this.leftMember = leftMember;
        this.rightMember = rightMember;
    }


    @Override
    public double eval(double xValue) {
        return leftMember.eval(xValue) - rightMember.eval(xValue);
    }

    @Override
    public Formula derivative() {
        return new Substraction (leftMember.derivative(),rightMember.derivative() );
    }
    @Override
    public String toString(){
        return leftMember + " - " + rightMember;
    }
}
