package formula;

public class Addition implements Formula{
    private final Formula leftMember;
    private final Formula rightMember;

    public Addition(Formula leftMember, Formula rightMember){
        this.leftMember = leftMember;
        this.rightMember = rightMember;

    }
    @Override
    public double eval(double xValue) {
        return leftMember.eval(xValue) + rightMember.eval(xValue);
    }

    @Override
    public Formula derivative() {
        return new Addition(leftMember.derivative(),rightMember.derivative() );
    }
    @Override
    public String toString(){
        return leftMember + " + " + rightMember;
    }
}
