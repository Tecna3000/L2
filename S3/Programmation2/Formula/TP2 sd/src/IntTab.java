public class IntTab {

    private final int emptyElement;
    private static final int MAX_SIZE = 10000;
    int[] tab = new int[MAX_SIZE];
    int size;

    public IntTab() {
        size = 0;
        emptyElement = 0;
    }

    public IntTab(int s) {
        if (s > MAX_SIZE) {
            System.out.println("Taille limite 10000");
            s = MAX_SIZE;
        }
        size = s;
        emptyElement = 0;
        for (int i = 0; i < s; i++) {
            tab[i] = emptyElement;
        }

    }

    public IntTab(int s, int e) {
        if (s > MAX_SIZE) {
            System.out.println("Taille limite 10000");
            s = MAX_SIZE;
        }
        else{size =s;}
        emptyElement = e;
        for (int i = 0; i < s; i++) {
            tab[i] = emptyElement;
        }

    }

    public int getSize() {
        return size;
    }

    public int get(int i) {
        if (i < size && i >= 0) {
            return tab[i];
        }
        return emptyElement;
    }

    public boolean set(int data, int i) {
        if (i < size && i >= 0) {
            tab[i] = data;
            return true;
        }
        return false;

    }

    public boolean addLast(int e){
        if( size == MAX_SIZE){
            return  false;
        }
        tab[size]= e;
        size++;
        return true;
    }

    public boolean resize(int s){
        if (s>=0 && s< MAX_SIZE) {

            if (s > size) {
                for (int i = size; i < s; i++) {
                    tab[i] = emptyElement;
                }
                size = s;
                return true;
            }
        }
        return false;

    }
@Override
    public String toString(){
        String tabElement = " ";
        for (int i = 0; i < size; i++){
            tabElement += tab[i] + ",";

        }
        return tabElement;
    }
    public boolean insert(int data, int i){

        if (i<0 || i> size || getSize()== MAX_SIZE){
            return false;
        }
        size++;
        for(int j = size ; j>=i ; j--){
            tab[i] = data;
            tab[j] = tab[j-1];
        }
        return true;
    }
}

