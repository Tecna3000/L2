public class EmptyIntList implements IntList{
    @Override
    public int getHead() {
        return 0;
    }

    @Override
    public IntList getTail() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public IntList cons(int n) {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }
}
