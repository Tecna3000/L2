public class Object {

    private int value;
    private int weight;



    public Object(int weight, int value) {

        this.value = value;
        this.weight = weight;

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Object{" +
                "weight=" + weight +
                " ,value=" + value +
                '}';
    }
}

