package dgol.priv.model;

public class Grid {

    private final Cell[][] cells;

    public Grid(int columns, int rows) {
        this.cells = new Cell[columns][rows];
    }

    public Cell getCell(int column, int row) {
        return cells[column][row];
    }

    public void setCell(int column, int row, boolean isAlive) {
        this.cells[column][row] = new Cell(row, column, isAlive);
    }

    public int getColumns() {
        return cells.length;
    }

    public int getRows() {
        return cells[0].length;
    }

    public Cell[][] getCells() {
        return cells;
    }

}
