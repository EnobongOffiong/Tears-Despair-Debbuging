package src.tddgame;

import java.util.ArrayList;
import java.util.List;

/**
 * The Grid class represents the full game grid, which is made up of a list of rows.
 * Each row contains a list of cells that form the maze or game layout.
 */
public class Grid {
    private List<Row> rows;

    /**
     * Creates a new Grid using the provided list of rows.
     *
     * @param rows the rows that make up the grid
     */
    public Grid(List<Row> rows) {
        this.rows = rows;
    }

    /**
     * Returns the list of rows in the grid.
     *
     * @return the rows in the grid
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Sets the rows that make up the grid.
     *
     * @param rows the new rows for the grid
     */
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    /**
     * Returns a simple string representation of the grid.
     *
     * @return a string showing the grid rows
     */
    @Override
    public String toString() {
        return "Grid [rows=" + rows + "]";
    }
}
