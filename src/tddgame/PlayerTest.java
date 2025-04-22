package src.tddgame;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Test suite for the Player class.
 * Tests player movement and interaction with the grid.
 */
@DisplayName("Player Movement Test Suite")
public class PlayerTest {

    private Grid grid;
    private Player player;

    /**
     * Sets up the test environment before each test.
     * Creates a grid and places the player at the bottom-right corner
     */
    @BeforeEach
    void setup() {

        List<Row> rows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Cell> cells = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                cells.add(new Cell(CellComponents.WALL, CellComponents.WALL, CellComponents.WALL, CellComponents.WALL));
            }
            rows.add(new Row(cells));
        }
        

        int exitRowIndex = 0;
        rows.get(exitRowIndex).getCells().get(0).setLeft(CellComponents.EXIT);
        
        grid = new Grid(rows, exitRowIndex);
        player = new Player(grid.getNumberOfRows() - 1, grid.getNumberOfColumns() - 1);
    }

    /**
     * Tests that the player starts at the bottom-right corner of the grid.
     */
    @Test
    @DisplayName("Should start at bottom-right corner")
    void shouldStartAtBottomRightCorner() {
        assertEquals(grid.getNumberOfRows() - 1, player.getRow());
        assertEquals(grid.getNumberOfColumns() - 1, player.getColumn());
    }

    /**
     * Tests that the player can move up through an aperture.
     */
    @Test
    @DisplayName("Should move up if there is an aperture")
    void shouldMoveUpThroughAperture() {
        int row = player.getRow();
        int column = player.getColumn();

        if (row > 0) {
            grid.getCell(row, column).setUp(CellComponents.APERTURE);
            grid.getCell(row - 1, column).setDown(CellComponents.APERTURE);
        }

        player.move(Direction.UP, grid);
        assertEquals(row - 1, player.getRow());
        assertEquals(column, player.getColumn());
    }

    /**
     * Tests that the player cannot move up through a wall.
     */
    @Test
    @DisplayName("Should not move up if blocked by a wall")
    void shouldNotMoveUpIfBlockedByWall() {
        int row = player.getRow();
        int column = player.getColumn();

        grid.getCell(row, column).setUp(CellComponents.WALL);
        if (row > 0) {
            grid.getCell(row - 1, column).setDown(CellComponents.WALL);
        }

        player.move(Direction.UP, grid);
        assertEquals(row, player.getRow());
        assertEquals(column, player.getColumn());
    }

    /**
     * Tests that the player can escape the grid when moving left into the exit.
     */
    @Test
    @DisplayName("Should escape the grid when moving into the exit")
    void shouldEscapeGridWhenMovingIntoExit() {
        int exitRow = grid.getExitRow();
        player = new Player(exitRow, 0);
        grid.getCell(exitRow, 0).setLeft(CellComponents.EXIT);

        assertFalse(player.hasReachedExit(grid));
        player.move(Direction.LEFT, grid);
        assertTrue(player.hasReachedExit(grid));
    }
}