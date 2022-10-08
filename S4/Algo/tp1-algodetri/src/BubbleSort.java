import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    static void bubbleSort(ArrayList<Integer> myList){
            int length =  myList.size();
            for(int i=0; i < length; i++) {
                for(int j=1; j < (length-i); j++) {
                    if (myList.get(j - 1) > myList.get(j)) {
                        permute(myList,j,j-1);
                    }
                }
            }
    }
    // méthode pour permuter deux éléments dans une liste
    static void permute(ArrayList<Integer> list,int i,int j){
        int first = list.get(i);
        list.set(i,list.get(j));
        list.set(j,first);
    }
}
 //O(n²)
