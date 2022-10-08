import java.util.NoSuchElementException;

public class IntListIterator implements IntIterator {


    public LinkedIntList list;

    public IntListIterator(LinkedIntList list) {
        this.list = list;

    }

    @Override
    public int next() {

        if (!this.hasNext()) {
            throw new NoSuchElementException("There is no next");
        }

        int current = this.list.getHead();
        this.list= (LinkedIntList) this.list.getTail();
        return current;
    }
    @Override
    public boolean hasNext() {
        return !this.list.isEmpty();
    }

}