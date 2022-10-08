public class Cell {
    int data;
    Cell nextCell;

    public Cell(int data, Cell nextCell){
        this.data = data;
        this.nextCell = nextCell;
    }

    public int getDataCell(){
        return this.data;
    }
    public Cell getNextCell(){
        return this.nextCell;
    }
    public void setDataCell(int data){
        this.data = data;
    }
    public void setNextCell(Cell nextCell){
        this.nextCell = nextCell;
    }
}
