import java.lang.reflect.Array;

public class Main {

    static int[] arrayOfInts = {0,1,2,3,4};


    static void change (int[] t){
        t[0] = 5;
    }

    public static void main(String[] args) {
        change(arrayOfInts);
        System.out.println(arrayOfInts[0]);

        IntTab tableauDeLaChieuse = new IntTab(5,2);
        System.out.println(tableauDeLaChieuse);
        tableauDeLaChieuse.insert(5,2);
        System.out.println(tableauDeLaChieuse);
    }
}