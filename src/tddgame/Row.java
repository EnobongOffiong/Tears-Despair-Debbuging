package tddgame;

import java.util.List;

/**
 * Represents a row in the grid, w/ the list of cells
 */
public class Row {
    private List<Cell> cells;

    /**
     * Constructs a new Row with the specified cells
     * @param cells The list of cells in the row
     * @throws IllegalArgumentException if cells is null
     */
    public Row(List<Cell> cells) {
        if (cells == null) {
            throw new IllegalArgumentException("Cells cannot be null");
        }
        this.cells = cells;
    }

    /**
     * Gets the cells in the row
     * @return The list of cells
     */
    public List<Cell> getCells() {
        return cells;
    }

    /**
     * Sets the cells in the row
     *
     * @param cells The new list of cells
     * @throws IllegalArgumentException if cells is null
     */
    public void setCells(List<Cell> cells) {
        if (cells == null) {
            throw new IllegalArgumentException("Cells cannot be null");
        }
        this.cells = cells;
    }

    /**
     * Returns a string of the row
   
     * @return A string representing the row
     */
    @Override
    public String toString() {
        return "Row [cells=" + cells + "]";
    }
}