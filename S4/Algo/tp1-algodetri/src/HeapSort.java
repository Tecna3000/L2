import java.util.ArrayList;
import java.util.PriorityQueue;

public class HeapSort {
     static ArrayList<Integer> heapSort(ArrayList<Integer> list){
         PriorityQueue<Integer> heap = new PriorityQueue<>(list);// convertir la liste en file de priorité
         ArrayList<Integer> sortedList= new ArrayList<>();
         for (int i = 0; i < list.size(); i++) {
             sortedList.add(heap.poll()); //retirer le plus petit element(celui qui a la plus grande clé) de la file et l'ajouter à la nouvelle liste
             System.out.println(sortedList);
         }
         return sortedList;
     }
}
//O(n log n)