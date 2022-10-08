import java.util.ArrayList;
import java.util.Objects;

public class QuickSort {
    public static void quickSort(ArrayList<Integer> list){
        quickSortMain(list,0,list.size());
    }
    public static void quickSortMain(ArrayList<Integer> list, int start, int end){
        if (start<=end) {
            int pivotPosition = partition(list, end, start);
            quickSortMain(list, start, pivotPosition - 1);
            quickSortMain(list, pivotPosition + 1, end);
        }
    }
    public static int partition (ArrayList<Integer> list,int end, int pivot){

        int  smallerPos = pivot;
        int cmp = pivot+1;
        int biggerPos = end;
        while(cmp<biggerPos){

            if (list.get(cmp)< list.get(pivot)){
                permute(list,cmp,smallerPos);
                smallerPos++;
                cmp++;
                pivot++;
            }
            else if(list.get(cmp) >list.get(pivot)){
                permute(list,cmp, biggerPos-1);
                biggerPos--;
            }
            else if((list.get(cmp).equals(list.get(pivot)))){
                pivot++;
                cmp++;

            }
        }
        return pivot;
    }

    // méthode pour permuter deux éléments dans une liste
    static void permute(ArrayList<Integer> list,int i,int j){
        int first = list.get(i);
        list.set(i,list.get(j));
        list.set(j,first);
    }
}
//O(n log n)