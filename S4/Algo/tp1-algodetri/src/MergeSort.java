import java.util.LinkedList;

public class MergeSort {

    public static LinkedList<Integer> mergeSort(LinkedList<Integer> list) {
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        LinkedList<Integer> leftSorted ;
        LinkedList<Integer> rightSorted ;
        LinkedList<Integer> listMerged ;
        if (list.size()>1) {
            //diviser en deux sous-listes
            for (int i = 0; i < list.size() / 2; i++) {
                left.add(list.get(i));
            }
            for (int j = list.size() / 2; j < list.size(); j++) {
                right.add(list.get(j));
            }
            leftSorted = mergeSort(left); // trier la liste de gauche
            rightSorted = mergeSort(right);
            listMerged = merge(leftSorted, rightSorted); // fusionner les deux sous listes triés
        }
        else{
            return list;
        }
        return listMerged;
    }
 // methode pour fusionner deux listes
    public static LinkedList <Integer> merge(LinkedList<Integer> left, LinkedList<Integer> right){
        LinkedList<Integer> mergedList = new LinkedList<>();
        int i = 0;
        int j = 0;
        while (left.size()!=0 && right.size()!= 0 ) {
            if (left.getFirst() <= right.getFirst()) {
                mergedList.add(left.getFirst());
                left.remove();
            } else {
                mergedList.add(right.getFirst());
                right.remove();
            }
        }
        // ajouter les élements restants
        while (i < left.size()){
            mergedList.add(left.get(i));
            i++;
        }
        while (j < right.size()){
            mergedList.add(right.get(j));
            j++;
        }
        return mergedList;
    }
}
//O(n log n)

