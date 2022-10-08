import java.util.Objects;

public class Arc implements Comparable<Arc>{

    private final int start;
    private final int end;


    private final int weight;


    public Arc(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    public int getStart() {
        return start;
    }
    public int getEnd() {
        return end;
    }
    public int getWeight() {
        return weight;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arc arc = (Arc) o;
        return start == arc.start && end == arc.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "(" +
                start +
                "," + end +
                " /" + weight +
                ')';
    }

    @Override
    public int compareTo(Arc o) {
        if (this.getWeight() < o.getWeight())
            return -1;
        else if (this.getWeight() > o.getWeight())
            return 1;
        else {
            return 0;
        }
    }
}


