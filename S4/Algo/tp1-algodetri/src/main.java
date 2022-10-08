import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class main {
    public static void main(String args[]) {
        ArrayList<Integer> myList;
        myList = randomGenerator(1000000,100, 0);//145899
        System.out.println(myList);
        //tri par bulle
        /*BubbleSort.bubbleSort(myList);
        System.out.println("list sorted with bubblesort: \n" + myList);*/

       //triParTas
        /*ArrayList<Integer> sorted =HeapSort.heapSort(myList);
        System.out.println("list sorted with heapsort: \n" + sorted);*/

        //triRapide
        QuickSort.quickSort(myList);
        System.out.println("list sorted with quicksort: \n" + myList);

        //tri par fusion
       /* LinkedList<Integer> linked = new LinkedList<>(myList);
        LinkedList<Integer> sorted = MergeSort.mergeSort(linked);
        System.out.println("list sorted with mergesort: \n" + sorted);*/
    }

    public static ArrayList<Integer> randomGenerator(int length, int max , int min ){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(i, (int) (Math.random() * (max-min)+1));
        }
        return list;
    }
}