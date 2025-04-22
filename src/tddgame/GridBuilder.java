package tddgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for building game grids.
 */
public class GridBuilder {

    private static final int minSize = 3;
    private static final int maxSize = 7;

    /**
     * Builds a random grid with size between minSize and maxSize.
     *
     * @return A randomly generated grid
     */
    public static Grid buildRandomGrid() {
        Random random = new Random();
        int numberOfRows = random.nextInt(maxSize - minSize + 1) + minSize;
        int numberOfColumns = random.nextInt(maxSize - minSize + 1) + minSize;
        return buildGrid(numberOfRows, numberOfColumns, random);
    }

    /**
     * Builds a grid with the specified dimensions.
     *
     * @param numberOfRows The number of rows
     * @param numberOfColumns The number of columns
     * @param random The random number generator
     * @return A grid with the specified dimensions
     * @throws IllegalArgumentException if dimensions are invalid
     */
    private static Grid buildGrid(int numberOfRows, int numberOfColumns, Random random) {
        if (numberOfRows < minSize || numberOfRows > maxSize || numberOfColumns < minSize || numberOfColumns > maxSize) {
            throw new IllegalArgumentException("Grid dimensions must be between " + minSize + " and " + maxSize);
        }
        
        List<Row> rows = new ArrayList<>();


        for (int i = 0; i < numberOfRows; i++) {
            List<Cell> cellRow = new ArrayList<>();
            for (int j = 0; j < numberOfColumns; j++) {
                cellRow.add(new Cell(CellComponents.WALL, CellComponents.WALL, CellComponents.WALL, CellComponents.WALL));
            }
            rows.add(new Row(cellRow));
        }


        int exitRow = random.nextInt(numberOfRows);
        rows.get(exitRow).getCells().get(0).setLeft(CellComponents.EXIT);


        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                Cell cell = rows.get(i).getCells().get(j);
                if (!cell.hasAperture()) {
                    Direction direction = getRandomValidDirection(i, j, numberOfRows, numberOfColumns, random);
                    switch (direction) {
                        case UP: cell.setUp(CellComponents.APERTURE); break;
                        case DOWN: cell.setDown(CellComponents.APERTURE); break;
                        case LEFT: 
                            if (!(j == 0 && i == exitRow)) {
                                cell.setLeft(CellComponents.APERTURE);
                            }
                            break;
                        case RIGHT: cell.setRight(CellComponents.APERTURE); break;
                    }
                }
            }
        }


        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                Cell current = rows.get(i).getCells().get(j);

                if (i > 0 && random.nextBoolean()) {
                    current.setUp(CellComponents.APERTURE);
                    rows.get(i - 1).getCells().get(j).setDown(CellComponents.APERTURE);
                }

                if (j < numberOfColumns - 1 && random.nextBoolean()) {
                    current.setRight(CellComponents.APERTURE);
                    rows.get(i).getCells().get(j + 1).setLeft(CellComponents.APERTURE);
                }
            }
        }


        Cell exitCell = rows.get(exitRow).getCells().get(0);
        if (!exitCell.hasAperture()) {
            exitCell.setRight(CellComponents.APERTURE);
            if (numberOfColumns > 1) {
                rows.get(exitRow).getCells().get(1).setLeft(CellComponents.APERTURE);
            }
        }

        return new Grid(rows, exitRow);
    }

    /**
     * Gets a random valid direction for the specified position.
     *
     * @param row The row index
     * @param column The column index
     * @param numberOfRows The total number of rows
     * @param numberOfColumns The total number of columns
     * @param random The random number generator
     * @return A random valid direction
     */
    private static Direction getRandomValidDirection(int row, int column, int numberOfRows, int numberOfColumns, Random random) {
        List<Direction> valid = new ArrayList<>();
        if (row > 0) valid.add(Direction.UP);
        if (row < numberOfRows - 1) valid.add(Direction.DOWN);
        if (column > 0) valid.add(Direction.LEFT);
        if (column < numberOfColumns - 1) valid.add(Direction.RIGHT);
        
        if (valid.isEmpty()) {
            // This shouldn't happen with proper grid sizing, but handling it anyway
            throw new IllegalStateException("No valid directions available for cell at position (" + row + ", " + column + ")");
        }
        
        return valid.get(random.nextInt(valid.size()));
    }
}