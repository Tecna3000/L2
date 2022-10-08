package formula;

public class Division implements Formula{
    private final Formula leftMember;
    private final Formula rightMember;

    public Division(Formula leftMember, Formula rightMember) {
        this.leftMember = leftMember;
        this.rightMember = rightMember;
    }

    @Override
    public double eval(double xValue) {
        return leftMember.eval(xValue) / rightMember.eval(xValue);
    }

    @Override
    public Formula derivative() {
        return new Division(leftMember.derivative(),rightMember.derivative());
    }
    @Override
    public String toString(){
        return leftMember + " / " + rightMember;
    }
}
