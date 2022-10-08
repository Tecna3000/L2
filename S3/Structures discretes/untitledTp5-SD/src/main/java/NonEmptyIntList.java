import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NonEmptyIntList implements IntList{

    private int head;
    private IntList tile;
    private List<Integer> list;
    @Override
    public int getHead() {
        return head;
    }

    @Override
    public IntList getTail() {
        return tile;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public IntList cons(int n) {
      list.add(n);
      for(int element =0; element<=tile.length();element++){
          list.set(element,this.tile[element]);
      }




    }

    @Override
    public int length() {
        return tile.length()+1;
    }
}
