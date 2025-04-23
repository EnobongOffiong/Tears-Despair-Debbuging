package src.tddgame;

import java.util.List;

/**
 * Represents the player navigating through the grid.
 * The player has a current position defined by a row and a cell.
 */
public class Player {
    private Row currentRow;
    private Cell currentCell;

    /**
     * Creates a new player at a specific row and cell.
     *
     * @param currentRow the row the player starts in
     * @param currentCell the cell the player starts in
     * @throws IllegalArgumentException if either row or cell is null
     */
    public Player(Row currentRow, Cell currentCell) {
        if (currentRow == null || currentCell == null) {
            throw new IllegalArgumentException("Row and Cell cannot be null.");
        }
        this.currentRow = currentRow;
        this.currentCell = currentCell;
    }

    /**
     * Gets the current row the player is in.
     *
     * @return the current row
     */
    public Row getCurrentRow() {
        return currentRow;
    }

    /**
     * Gets the current cell the player is in.
     *
     * @return the current cell
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     * Tries to move the player in the specified direction based on the grid layout.
     * Movement is only possible through apertures or the exit.
     *
     * @param direction the direction the player wants to move
     * @param grid the grid the player is navigating
     * @return true if the move was successful, false otherwise
     */
    public boolean move(Movement direction, Grid grid) {
        if (direction == null || grid == null) return false;

        List<Row> rows = grid.getRows();
        int rowIndex = rows.indexOf(currentRow);
        int colIndex = currentRow.getCells().indexOf(currentCell);

        switch (direction) {
            case UP:
                if (rowIndex > 0 && currentCell.getUp() == CellComponents.APERTURE) {
                    Cell above = rows.get(rowIndex - 1).getCells().get(colIndex);
                    if (above.getDown() == CellComponents.APERTURE) {
                        currentRow = rows.get(rowIndex - 1);
                        currentCell = above;
                        return true;
                    }
                }
                break;

            case DOWN:
                if (rowIndex < rows.size() - 1 && currentCell.getDown() == CellComponents.APERTURE) {
                    Cell below = rows.get(rowIndex + 1).getCells().get(colIndex);
                    if (below.getUp() == CellComponents.APERTURE) {
                        currentRow = rows.get(rowIndex + 1);
                        currentCell = below;
                        return true;
                    }
                }
                break;

            case LEFT:
                if (colIndex > 0 && currentCell.getLeft() == CellComponents.APERTURE) {
                    Cell left = currentRow.getCells().get(colIndex - 1);
                    if (left.getRight() == CellComponents.APERTURE || left.getRight() == CellComponents.EXIT) {
                        currentCell = left;
                        return true;
                    }
                }

                // Check for left-edge EXIT
                if (colIndex == 0 && currentCell.getLeft() == CellComponents.EXIT) {
                    return true; // Player escapes!
                }
                break;

            case RIGHT:
                if (colIndex < currentRow.getCells().size() - 1 && currentCell.getRight() == CellComponents.APERTURE) {
                    Cell right = currentRow.getCells().get(colIndex + 1);
                    if (right.getLeft() == CellComponents.APERTURE) {
                        currentCell = right;
                        return true;
                    }
                }
                break;
        }

        return false; // Movement failed
    }

    /**
     * Returns a simple string version of the player’s current position.
     *
     * @return a string showing the current cell and row
     */
    @Override
    public String toString() {
        return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
    }
}
