package dgol.priv;

import dgol.priv.model.Cell;
import dgol.priv.model.Grid;
import dgol.priv.service.GameOfLifeService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class GameOfLifeServiceTest {

    @Inject
    GameOfLifeService sut;

    @Test
    void when_initGame_isCalled_return_correctGrid() {
        //arrange

        //act
        Grid result = sut.initGame(10, 10, 0.25);

        //assert
        assertEquals(10, result.getRows());
        assertEquals(10, result.getColumns());
    }

    @Test
    void when_initGame_probabilityZero_allCellsDead() {
        //arrange

        //act
        Grid result = sut.initGame(10, 10, 0.0);

        //assert
        long livingCells = Arrays.stream(result.getCells())
                .flatMap(Arrays::stream)
                .filter(Cell::isAlive)
                .count();
        assertEquals(0, livingCells);
    }

    @Test
    void when_initGame_probabilityOne_allCellsAlive() {
        //arrange

        //act
        Grid result = sut.initGame(10, 10, 1.0);

        //assert
        long livingCells = Arrays.stream(result.getCells())
                .flatMap(Arrays::stream)
                .filter(Cell::isAlive)
                .count();
        assertEquals(100, livingCells);
    }

    @Test
    void when_next_isCalled_onEmptyGrid_shouldReturn_emptyGrid() {
        //arrange
        Grid initialGrid = new Grid(3, 3);
        initialGrid.setCell(0, 0, false);
        initialGrid.setCell(0, 1, false);
        initialGrid.setCell(0, 2, false);
        initialGrid.setCell(1, 0, false);
        initialGrid.setCell(1, 1, false);
        initialGrid.setCell(1, 2, false);
        initialGrid.setCell(2, 0, false);
        initialGrid.setCell(2, 1, false);
        initialGrid.setCell(2, 2, false);
        sut = new GameOfLifeService(initialGrid);

        //act
        Grid result = sut.next();

        //assert
        long livingCells = Arrays.stream(result.getCells())
                .flatMap(Arrays::stream)
                .filter(Cell::isAlive)
                .count();
        assertEquals(0, livingCells);
    }

    @Test
    void when_next_isCalled_onFullGrid_shouldReturn_onlyCornersAlive() {
        Grid initialGrid = new Grid(3, 3);
        initialGrid.setCell(0, 0, true);
        initialGrid.setCell(0, 1, true);
        initialGrid.setCell(0, 2, true);
        initialGrid.setCell(1, 0, true);
        initialGrid.setCell(1, 1, true);
        initialGrid.setCell(1, 2, true);
        initialGrid.setCell(2, 0, true);
        initialGrid.setCell(2, 1, true);
        initialGrid.setCell(2, 2, true);
        sut = new GameOfLifeService(initialGrid);

        //act
        Grid result = sut.next();

        //assert
        long livingCells = Arrays.stream(result.getCells())
                .flatMap(Arrays::stream)
                .filter(Cell::isAlive)
                .count();
        assertEquals(4, livingCells);
    }

    @Test
    void when_next_isCalled_withRegularBoard_return_nextGrid() {
        //arrange
        Grid initialGrid = new Grid(3, 3);
        initialGrid.setCell(0, 0, true);
        initialGrid.setCell(0, 1, true);
        initialGrid.setCell(0, 2, false);
        initialGrid.setCell(1, 0, true);
        initialGrid.setCell(1, 1, false);
        initialGrid.setCell(1, 2, true);
        initialGrid.setCell(2, 0, true);
        initialGrid.setCell(2, 1, true);
        initialGrid.setCell(2, 2, false);
        sut = new GameOfLifeService(initialGrid);

        //act
        Grid result = sut.next();

        //assert
        long livingCells = Arrays.stream(result.getCells())
                .flatMap(Arrays::stream)
                .filter(Cell::isAlive)
                .count();
        assertTrue(livingCells > 0);
    }

    @Test
    void when_next_isCalled_onSingleRowGrid_return_nextGrid_onlyMiddleAlive() {
        //arrange
        Grid initialGrid = new Grid(1, 3);
        initialGrid.setCell(0, 0, true);
        initialGrid.setCell(0, 1, true);
        initialGrid.setCell(0, 2, true);
        sut = new GameOfLifeService(initialGrid);

        //act
        Grid result = sut.next();

        //assert
        assertFalse(result.getCell(0, 0).isAlive());
        assertTrue(result.getCell(0, 1).isAlive());
        assertFalse(result.getCell(0, 2).isAlive());
    }

    @Test
    void when_next_isCalled_onSingleColumnGrid_return_nextGrid_onlyMiddleAlive() {
        //arrange
        Grid initialGrid = new Grid(3, 1);
        initialGrid.setCell(0, 0, true);
        initialGrid.setCell(1, 0, true);
        initialGrid.setCell(2, 0, true);
        sut = new GameOfLifeService(initialGrid);

        //act
        Grid result = sut.next();

        //assert
        assertFalse(result.getCell(0, 0).isAlive());
        assertTrue(result.getCell(1, 0).isAlive());
        assertFalse(result.getCell(2, 0).isAlive());
    }

    @Test
    void when_next_isCalled_withCornerAlive_return_nextGrid() {
        //arrange
        Grid initialGrid = new Grid(3, 3);
        initialGrid.setCell(0, 0, true);
        initialGrid.setCell(0, 1, true);
        initialGrid.setCell(0, 2, false);
        initialGrid.setCell(1, 0, true);
        initialGrid.setCell(1, 1, false);
        initialGrid.setCell(1, 2, false);
        initialGrid.setCell(2, 0, false);
        initialGrid.setCell(2, 1, false);
        initialGrid.setCell(2, 2, false);
        sut = new GameOfLifeService(initialGrid);

        //act
        Grid result = sut.next();

        //assert
        assertTrue(result.getCell(0, 0).isAlive());
        assertTrue(result.getCell(0, 1).isAlive());
        assertTrue(result.getCell(1, 0).isAlive());
        assertTrue(result.getCell(1, 1).isAlive());
    }

}
