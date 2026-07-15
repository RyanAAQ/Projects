package turtle;

public class TurtlePosition {
    private int row;
    private int column;

    public int getRowPosition() {
        return row;
    }
    public int getColumnPosition() {
        return column;
    }

    public void increaseColumnBy(int steps){
        this.column += steps;
    }

    public String toString(){
        return "(" + row + ", " + column + ")";
    }

    public void increaseRowBy(int steps) {
        this.row += steps;
    }
}
