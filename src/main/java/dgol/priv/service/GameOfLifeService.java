package dgol.priv.service;

import dgol.priv.model.Grid;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;

import java.util.Random;
import java.util.stream.IntStream;

@ApplicationScoped
public class GameOfLifeService {
    @Inject
    Logger logger;

    private Grid grid;
    private final Random random = new Random();

    public GameOfLifeService() {
        this.grid = new Grid(0, 0);
    }

    public GameOfLifeService(Grid grid) {
        this.grid = grid;
    }

    /**
     * This method initiates a game board with the given width and height. It "randomly" creates living cells with the given alive probability.
     *
     * @param columns          number of columns on the game board
     * @param rows             number of rows on the game board
     * @param aliveProbability the probability of creating a living cell
     * @return Grid with a number of columns and rows and a aliveProbability.
     */
    //TODO: Add columns, rows and probability as input parameters from GUI to let the user set the game board.
    public Grid initGame(final int columns, final int rows, final double aliveProbability) {
        grid = new Grid(columns, rows);
        IntStream.range(0, columns).forEach(column ->
                IntStream.range(0, rows).forEach(row -> {
                    boolean isAlive = random.nextDouble() < aliveProbability;
                    grid.setCell(column, row, isAlive);
                })
        );
        logger.info("Created a new board with columns: {}, rows: {}, and aliveProbability: {}", columns, rows, aliveProbability);
        return grid;
    }

    /**
     * Performs the "next generation" according to the rules. This creates a new Cell[][] and returns the updated state
     *
     * @return Grid with the "next generation"
     */

    public Grid next() {
        Grid nextGrid = new Grid(grid.getColumns(), grid.getRows());
        IntStream.range(0, grid.getColumns()).forEach(column ->
                IntStream.range(0, grid.getRows()).forEach(row ->
                        nextGrid.setCell(column, row, shouldBeAlive(row, column))
                )
        );
        grid = nextGrid;
        return grid;
    }

    /**
     * Sets if the specific cell should be alive or dead, according to the ruleset of the game.
     * We calculate living neighbours. We then check if the given cell we are currently looking at is alive or dead.
     * - If the cell is currently alive and has 2 or 3 living neighbours, it lives on to the next generation (rule 2 on wiki)
     * - If the cell is currently dead and has exactly 3 living neighbours, it becomes alive (rule 4)
     * The opposite of these checks means that the cell should be dead (rule 1 and 3) or if translated to the 2nd row of rules
     * First check is for rule 1, second check is for rule 2 and rule 3 is for all "dead cell scenarios"
     *
     * @param row    the row on the grid in which the cell occurs
     * @param column the column on the grid in which the cell occurs
     * @return boolean true/false depending on the outcome of the check according to the rules.
     */

    private boolean shouldBeAlive(final int row, final int column) {
        long aliveNeighbours = countAliveNeighbours(row, column);
        return (grid.getCell(column, row).isAlive() && (aliveNeighbours == 2 || aliveNeighbours == 3)) || (!grid.getCell(column, row).isAlive() && aliveNeighbours == 3);
    }

    /**
     * Method to count alive neighbours for a specific cell (designated row/column). It checks all surrounding cells and filters out the living ones
     *
     * @param row    the row of the cell
     * @param column the column of the cell
     * @return a long representing the amount of living neighbours.
     */
    private long countAliveNeighbours(final int row, final int column) {
        return IntStream.rangeClosed(-1, 1).flatMap(neighbourColumn ->
                IntStream.rangeClosed(-1, 1).filter(neighbourRow ->
                        !(neighbourRow == 0 && neighbourColumn == 0) && isAlive(row + neighbourRow, column + neighbourColumn)
                )
        ).count();
    }

    /**
     * Returns true/false if the cell is alive. It also checks that the row and column are within the grid size since corner cases
     * will check out of bounds when looking for alive/dead neighbours.
     *
     * @param row    the row of the cell
     * @param column the column of the cell
     * @return true/false depending on the cells alive/dead status
     */
    private boolean isAlive(final int row, final int column) {
        return !cellOutOfBounds(row, column) && grid.getCell(column, row).isAlive();
    }

    /**
     * Util method to check cell out of bounds
     *
     * @param row    the row of the cell
     * @param column the column of the cell
     * @return true/false if the cell is out of bounds of the grid
     */
    private boolean cellOutOfBounds(final int row, final int column) {
        return (row < 0 || column < 0 || column >= grid.getColumns() || row >= grid.getRows());
    }


}
