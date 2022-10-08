import static java.lang.System.out;
public class TryList
{
    static int sum(IntList l)
    {if (l.isEmpty()) return 0;
        return l.getHead() + sum(l.getTail());
    }

    public static void main(String [] args)
    {
        NonEmptyIntList myList = new EmptyIntList()
                        .cons(3)
                        .cons(2)
                        .cons(1);
        int result = sum(myList);
        System.out.println(result);
        NonEmptyIntList myLongerList = myList.cons(8);
        result = sum(myLongerList);
        System.out.println(result);
        result = sum(myList);
        System.out.println(result);
    }
}
