
import java.util.ArrayList;

public class Dynamic {
    private final BackPack backPack;
    private int [][] table;
    private int numberOfRows;
    private int numberOfColumns;

    public Dynamic(BackPack backPack) {
        this.backPack = backPack;
        initialize();
        fillTable(backPack,numberOfRows,numberOfColumns);
    }

    public void initialize(){
        this.numberOfRows = backPack.getCapacity()+1;
        this.numberOfColumns = backPack.getNumberOfObjects()+ 1;
        this.table = new int[numberOfRows][numberOfColumns];
    }

    public void fillTable(BackPack backPack, int numberOfRows, int numberOfColumns){
        ArrayList<Object> objects = backPack.getObjects();
        ArrayList<Object> toTake = new ArrayList<>();
        for(int row =0; row<numberOfRows; row++){

            this.table[row][0]= 0;

            int line=0;
            for (int column = 1; column<numberOfColumns; column++){
                int maxCapacity =  row;
                int currentWeight = objects.get(line).getWeight();
                int currentVal = objects.get(line).getValue();
                int precedentVal = this.table[row][column-1];
                if(currentWeight > maxCapacity){
                    this.table[row][column] = precedentVal;
                }
                else if( currentWeight < maxCapacity && currentVal> precedentVal){
                    this.table[row][column] = currentVal;

                }
                else if(currentWeight < maxCapacity && currentVal < precedentVal){
                    this.table[row][column] = precedentVal;
                }
                line++;

            }
        }
    }

    public ArrayList<Object> OptimalCharging() {
        final ArrayList<Object> charge = new ArrayList<>();
        int capacity = this.backPack.getCapacity();
        for (int row = this.backPack.getNumberOfObjects(); row > 0; row--) {
            if (table[capacity][row] > table[capacity][row-1]) {
                charge.add(backPack.getObjects().get(row-1));
                capacity = capacity - backPack.getObjects().get(row-1).getWeight();
            }
        }
        return charge;
    }


    public void setNumberOfRows(int numberOfRows){
        this.numberOfRows =  numberOfRows;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int[][] getTable() {
        return table;
    }


    public String toString() {
        String table = "";
        for(int row = 0; row < numberOfRows; row++ ){
            table += row + "| " ;
            for(int column = 0; column < numberOfColumns; column++){
                table += this.table [row][column]+ " ";
                // System.out.println(this.table [row][column]);
            }
            table += "\n";

        }
        return table;
    }
}