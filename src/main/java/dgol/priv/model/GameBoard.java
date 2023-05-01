package dgol.priv.model;

public class GameBoard {

    private int columns;
    private int rows;

    private double aliveProbability;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public double getAliveProbability() {
        return aliveProbability;
    }

    public void setAliveProbability(double aliveProbability) {
        this.aliveProbability = aliveProbability;
    }


}
