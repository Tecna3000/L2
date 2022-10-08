import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BackPack {


    private ArrayList<Object> objects ;
    private int capacity;
    private int numberOfObjects;


    public BackPack(File file) {
        readFile(file);
    }

    public void readFile (File file) {
        this.objects = new ArrayList<>();
        Scanner scanner = null;
        this.numberOfObjects = 0 ;

        try {
            scanner = new Scanner(file);
            capacity = scanner.nextInt();

            while (scanner.hasNextInt()) {
                objects.add(new Object(scanner.nextInt(), scanner.nextInt()));
                this.numberOfObjects++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert scanner != null;
        scanner.close();
    }

    public int getNumberOfObjects() {
        return numberOfObjects;
    }

    public int getCapacity() {
        return capacity;
    }
    public ArrayList<Object> getObjects() {
        return objects;
    }
    @Override
    public String toString() {
        String str ="" + capacity+ "\n";
        for(Object obj : this.objects){
            str+= obj.getWeight()+" " + obj.getValue()  +"\n";
        }
        return str;
    }

}
