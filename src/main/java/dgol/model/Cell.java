package dgol.model;

public class Cell {

    private boolean alive;
    private int row;
    private int column;

    public Cell(int row, int column, boolean alive) {
        this.row = row;
        this.column = column;
        this.alive = alive;
    }

    public int getRow() {
        return row;
    }

    public void setX(int x) {
        this.row = x;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
