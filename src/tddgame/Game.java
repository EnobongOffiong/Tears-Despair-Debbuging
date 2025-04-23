package src.tddgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Game class handles core game logic and manages the grid.
 * It can either use a provided grid or generate a random one.
 */
public class Game {
    private Grid grid;

    /**
     * Constructs a game using an existing grid.
     * 
     * @param grid the grid to be used in the game
     */
    public Game(Grid grid) {
        this.grid = grid;
    }

    /**
     * Constructs a game with a randomly generated grid of the given size.
     * 
     * @param size the size of the square grid (must be between 3 and 7)
     */
    public Game(int size) {
        this.grid = createRandomGrid(size);
    }

    /**
     * Returns the current grid.
     * 
     * @return the current grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Updates the game grid.
     * 
     * @param grid the new grid to use
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * Attempts to move the player in a given direction.
     * 
     * @param direction the direction to move in
     * @param player the player to move
     * @return true if the move was successful, false otherwise
     */
    public boolean play(Movement direction, Player player) {
        if (player == null || direction == null || grid == null) return false;
        return player.move(direction, grid);
    }

    /**
     * Generates a random grid with a path from the bottom-right to the top-left.
     * The top-left cell will have an EXIT component.
     * 
     * @param size the desired size of the grid (between 3 and 7)
     * @return a randomized Grid object or null if size is invalid
     */
    public Grid createRandomGrid(int size) {
        if (size < 3 || size > 7) return null;

        Random rand = new Random();
        Cell[][] gridArray = new Cell[size][size];

        // Fill the grid with walls
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                gridArray[row][col] = new Cell(
                        CellComponents.WALL, CellComponents.WALL,
                        CellComponents.WALL, CellComponents.WALL
                );
            }
        }

        // Set EXIT at top-left corner
        gridArray[0][0].setLeft(CellComponents.EXIT);

        // Random path from bottom-right to top-left
        int row = size - 1;
        int col = size - 1;

        while (row > 0 || col > 0) {
            int move = rand.nextInt((row > 0 && col > 0) ? 2 : 1);
            if (col > 0 && (move == 1 || row == 0)) {
                gridArray[row][col].setLeft(CellComponents.APERTURE);
                gridArray[row][col - 1].setRight(CellComponents.APERTURE);
                col--;
            } else {
                gridArray[row][col].setUp(CellComponents.APERTURE);
                gridArray[row - 1][col].setDown(CellComponents.APERTURE);
                row--;
            }
        }

        // Ensure each cell is accessible and fix EXITs
        for (row = 0; row < size; row++) {
            for (col = 0; col < size; col++) {
                Cell cell = gridArray[row][col];
                boolean isExitCell = (row == 0 && col == 0);
                boolean hasOpening = cell.getLeft() == CellComponents.APERTURE
                                  || cell.getRight() == CellComponents.APERTURE
                                  || cell.getUp() == CellComponents.APERTURE
                                  || cell.getDown() == CellComponents.APERTURE;

                if (!hasOpening && !isExitCell) {
                    cell.setRight(CellComponents.APERTURE);
                }

                if (!isExitCell) {
                    if (cell.getLeft() == CellComponents.EXIT) cell.setLeft(CellComponents.WALL);
                    if (cell.getRight() == CellComponents.EXIT) cell.setRight(CellComponents.WALL);
                    if (cell.getUp() == CellComponents.EXIT) cell.setUp(CellComponents.WALL);
                    if (cell.getDown() == CellComponents.EXIT) cell.setDown(CellComponents.WALL);
                }
            }
        }

        // Convert 2D array to list of rows
        List<Row> rows = new ArrayList<>();
        for (row = 0; row < size; row++) {
            ArrayList<Cell> cellRow = new ArrayList<>();
            for (col = 0; col < size; col++) {
                cellRow.add(gridArray[row][col]);
            }
            rows.add(new Row(cellRow));
        }

        // Make sure neighboring cells share consistent edge values
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Cell current = gridArray[r][c];

                if (c < size - 1) {
                    Cell right = gridArray[r][c + 1];
                    right.setLeft(current.getRight());
                }

                if (r < size - 1) {
                    Cell below = gridArray[r + 1][c];
                    below.setUp(current.getDown());
                }
            }
        }

        return new Grid(rows);
    }

    /**
     * Randomly selects a CellComponent. Can optionally include EXIT as a possible value.
     * 
     * @param rand the random generator
     * @param allowExit whether EXIT should be a valid return option
     * @return a randomly selected CellComponent
     */
    private CellComponents randomComponent(Random rand, boolean allowExit) {
        int choice = allowExit ? rand.nextInt(3) : rand.nextInt(2);
        return switch (choice) {
            case 0 -> CellComponents.WALL;
            case 1 -> CellComponents.APERTURE;
            case 2 -> CellComponents.EXIT;
            default -> CellComponents.WALL;
        };
    }

    /**
     * Returns a string representation of the game (mostly for debugging).
     * 
     * @return a string describing the game and its grid
     */
    @Override
    public String toString() {
        return "Game [grid=" + grid + "]";
    }
}
