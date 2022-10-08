import java.util.NoSuchElementException;

public class LinkedIntList implements IntList {
    private Cell first;


    public LinkedIntList() {
        this.first = null;
    }

    public Cell getFirst() {
        return this.first;
    }

    public void setFirst(Cell first) {
        this.first = first;
    }


    @Override
    public int getHead() {
        if (first == null) {
            throw new IllegalStateException("the list is empty");
        }
        return first.getDataCell();
    }

    @Override
    public IntList getTail() {
        LinkedIntList tail = new LinkedIntList();
        tail.setFirst(first.getNextCell());
        return tail;
    }

    public void setTail(LinkedIntList tail) {
        this.first.setNextCell(tail.getFirst());
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public IntList cons(int n) {
        LinkedIntList list2 = new LinkedIntList();
        list2.setFirst(new Cell(n, this.getFirst()));
        return list2;
    }

    @Override
    public int length() {
        if (this.isEmpty()) {
            return 0;
        }
        int length = 1 + this.getTail().length();
        return length;
    }

    public IntIterator iterator(LinkedIntList list) {
        return new IntListIterator(list);
    }

    void add(int n) {
        this.setFirst(new Cell(n, this.getFirst()));
    }

    void remove() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        this.setFirst(this.first.getNextCell());
    }


    void add(int n, int pos) {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (pos < 1) {
            add(n);
        }

        if (pos > this.length()) {
            Cell last = this.getLast();
            last.setNextCell(new Cell(n, null));
        } else {

            IntIterator iterator = this.iterator(this);
            Cell current = this.getFirst();
            while (iterator.next() != pos) {
                current = current.getNextCell();
            }
            current.setDataCell(n);
        }
    }

    private Cell getLast() {
        Cell last = this.getFirst();
        while (last.getNextCell() != null) {
            last = last.getNextCell();
        }
        return last;
    }

    public void remove(int pos) {
        if (pos < 1 || pos > this.length()) {
            throw new NoSuchElementException("The given position is out of range");
        }
        else if(pos == 1){
            remove();
        }
        else {
            Cell current = getFirst();
            Cell precedent = null;
            while (pos > 1) {
                precedent = current;
                current = current.getNextCell();
                pos--;
                precedent.setNextCell(current.getNextCell());
            }
        }
    }

}










