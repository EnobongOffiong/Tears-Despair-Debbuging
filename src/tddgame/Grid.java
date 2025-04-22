package src.tddgame;

import java.util.List;

/**
 * Represents the game grid, consisting of rows of cells.
 */
public class Grid {
    private List<Row> rows;
    private int exitRow;

    /**
     * Constructs a new Grid with the specified rows and exit row.
     *
     * @param rows The rows of the grid
     * @param exitRow The row index where the exit is located
     * @throws IllegalArgumentException if rows is null or empty
     */
    public Grid(List<Row> rows, int exitRow) {
        if (rows == null || rows.isEmpty()) {
            throw new IllegalArgumentException("Rows cannot be null or empty");
        }
        
        this.rows = rows;
        this.exitRow = exitRow;
    }

    /**
     * Gets the rows of the grid.
     *
     * @return The list of rows
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Sets the rows of the grid.
     *
     * @param rows The new list of rows
     * @throws IllegalArgumentException if rows is null or empty
     */
    public void setRows(List<Row> rows) {
        if (rows == null || rows.isEmpty()) {
            throw new IllegalArgumentException("Rows cannot be null or empty");
        }
        this.rows = rows;
    }

    /**
     * Gets the number of rows in the grid.
     *
     * @return The number of rows
     */
    public int getNumberOfRows() {
        return rows.size();
    }

    /**
     * Gets the number of columns in the grid.
     *
     * @return The number of columns
     */
    public int getNumberOfColumns() {
        if (rows.isEmpty() || rows.get(0).getCells().isEmpty()) {
            return 0;
        }
        return rows.get(0).getCells().size();
    }

    /**
     * Gets the cell at the specified position.
     *
     * @param row The row index
     * @param column The column index
     * @return The cell at the specified position
     * @throws IndexOutOfBoundsException if the indices are out of bounds
     */
    public Cell getCell(int row, int column) {
        if (row < 0 || row >= getNumberOfRows() || column < 0 || column >= getNumberOfColumns()) {
            throw new IndexOutOfBoundsException("Cell indices out of bounds");
        }
        return rows.get(row).getCells().get(column);
    }

    /**
     * Gets the row index where the exit is located.
     *
     * @return The exit row index
     */
    public int getExitRow() {
        return exitRow;
    }

    /**
     * Returns a string representation of the grid.
     *
     * @return A string representing the grid
     */
    @Override
    public String toString() {
        return "Grid [rows=" + rows + "]";
    }
}