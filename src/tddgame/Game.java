package src.tddgame ;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Grid grid;

    public Game(Grid grid) {
        this.grid = grid;
    }

    public Game(int size) {
        this.grid = createRandomGrid(size);
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public boolean play(Movement direction, Player player) {
        if (player == null || direction == null || grid == null) return false;
        return player.move(direction, grid);
    }

    public Grid createRandomGrid(int size) {
        if (size < 3 || size > 7) return null;

        Random rand = new Random();
        Cell[][] gridArray = new Cell[size][size];

        // Initialize grid with WALLs
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridArray[i][j] = new Cell(CellComponents.WALL, CellComponents.WALL,
                        CellComponents.WALL, CellComponents.WALL);
            }
        }

        // Set EXIT at [0][0] on the left
        gridArray[0][0].setLeft(CellComponents.EXIT);

        // Carve a random path from bottom-right to top-left
        int i = size - 1;
        int j = size - 1;

        while (i > 0 || j > 0) {
            int dir = rand.nextInt((i > 0 && j > 0) ? 2 : 1); // 0 = up, 1 = left
            if (j > 0 && (dir == 1 || i == 0)) {
                // Move left
                gridArray[i][j].setLeft(CellComponents.APERTURE);
                gridArray[i][j - 1].setRight(CellComponents.APERTURE);
                j--;
            } else {
                // Move up
                gridArray[i][j].setUp(CellComponents.APERTURE);
                gridArray[i - 1][j].setDown(CellComponents.APERTURE);
                i--;
            }
        }

        // Randomly fill remaining directions with valid components
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                Cell cell = gridArray[i][j];

                // Ensure at least one APERTURE
                if (cell.getLeft() != CellComponents.APERTURE &&
                    cell.getRight() != CellComponents.APERTURE &&
                    cell.getUp() != CellComponents.APERTURE &&
                    cell.getDown() != CellComponents.APERTURE &&
                    !(i == 0 && j == 0)) {
                    cell.setRight(CellComponents.APERTURE); // add an opening
                }

                // Prevent any EXITs except [0][0]
                if (!(i == 0 && j == 0)) {
                    if (cell.getLeft() == CellComponents.EXIT) cell.setLeft(CellComponents.WALL);
                    if (cell.getRight() == CellComponents.EXIT) cell.setRight(CellComponents.WALL);
                    if (cell.getUp() == CellComponents.EXIT) cell.setUp(CellComponents.WALL);
                    if (cell.getDown() == CellComponents.EXIT) cell.setDown(CellComponents.WALL);
                }
            }
        }

        // Convert to List<Row>
        List<Row> rows = new ArrayList<>();
        for (i = 0; i < size; i++) {
            List<Cell> cellRow = new ArrayList<>();
            for (j = 0; j < size; j++) {
                cellRow.add(gridArray[i][j]);
            }
            rows.add(new Row(cellRow));
        }

        return new Grid(rows);
    }


    private CellComponents randomComponent(Random rand, boolean allowExit) {
        int choice = allowExit ? rand.nextInt(3) : rand.nextInt(2);
        return switch (choice) {
            case 0 -> CellComponents.WALL;
            case 1 -> CellComponents.APERTURE;
            case 2 -> CellComponents.EXIT;
            default -> CellComponents.WALL;
        };
    }

    @Override
    public String toString() {
        return "Game [grid=" + grid + "]";
    }
}
