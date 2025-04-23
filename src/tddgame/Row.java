package src.tddgame;

import java.util.*;

/**
 * Represents a row in the game grid.
 * Each row is made up of a list of cells.
 */
public class Row {

    private ArrayList<Cell> cells;

    /**
     * Constructs a new Row with the given list of cells.
     *
     * @param cells the list of cells that make up this row
     */
    public Row(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    /**
     * Returns the list of cells in this row.
     *
     * @return the cells in this row
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * Updates the list of cells in this row.
     *
     * @param cells the new list of cells
     */
    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    /**
     * Returns a string representation of the row.
     *
     * @return a string showing the row's cells
     */
    @Override
    public String toString() {
        return "Row [cells=" + cells + "]";
    }
}
