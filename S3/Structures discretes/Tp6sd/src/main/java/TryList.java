import static java.lang.System.out;
import static java.lang.System.out;
public class TryList {

    static int sum(IntList l) {
        if (l.isEmpty()) return 0;
        return l.getHead() + sum(l.getTail());
    }
    static int sumIterator (IntList l) {
        if (l.isEmpty()) return 0;
        int sum = l.getHead() + sumIterator(l.getTail());
        return sum;
    }

    static void printIntList(LinkedIntList l) {
       IntIterator iterator= l.iterator(l);
        System.out.print("[");
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+",");
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        LinkedIntList myList = (LinkedIntList) new LinkedIntList()
                .cons(3)
                .cons(2)
                .cons(1);
        printIntList(myList);
        myList.add(0);
        printIntList(myList);
        myList.remove();
        printIntList(myList);
        myList.add(4, 4);
        printIntList(myList);
        int sum = sumIterator(myList);
        System.out.println("the sum is: "+sum);
        System.out.println("the length is: " +myList.length());

        int result = sum(myList);
        System.out.println(result);
        LinkedIntList myLongerList = (LinkedIntList) myList.cons(8);
        result = sum(myLongerList);
        System.out.println(result);
        result = sum(myList);
        System.out.println(result);

        LinkedIntList myNewList = (LinkedIntList) myList.getTail();
        printIntList(myNewList);
        myNewList.remove(3);
        printIntList(myList);
        printIntList(myNewList);
    }
}

