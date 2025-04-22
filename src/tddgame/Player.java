package src.tddgame;

/**
 * Represents a player 
 * The player can move within the grid according to apertures
 */
public class Player {
    private int row;
    private int column;

    /**
     * Constructs a new Player 
     * @param row The initial row position
     * @param column The initial column position
     * @throws IllegalArgumentException if position is negative
     */
    public Player(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("Player position cannot be negative");
        }
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the player's current row position
     * @return The row position
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the player's current col position
     * @return The column position
     */
    public int getColumn() {
        return column;
    }

    /**
     * Moves the player in the specified direction if possible
     * Movement is only allowed through apertures or the exit
     *
     * @param direction The direction to move in
     * @param grid The game grid
     * @throws IllegalArgumentException if direction or grid is null
     */
    public void move(Direction direction, Grid grid) {
        if (direction == null || grid == null) {
            throw new IllegalArgumentException("Direction and grid cannot be null");
        }
        
        int newRow = row;
        int newColumn = column;

        Cell current = grid.getCell(row, column);
        switch (direction) {
            case UP:
                if (row > 0 && current.getUp() == CellComponents.APERTURE) newRow--;
                break;
            case DOWN:
                if (row < grid.getNumberOfRows() - 1 && current.getDown() == CellComponents.APERTURE) newRow++;
                break;
            case LEFT:
                if (column > 0 && current.getLeft() == CellComponents.APERTURE) newColumn--;
                else if (column == 0 && current.getLeft() == CellComponents.EXIT)
                    System.out.println("Player has escaped!");
                break;
            case RIGHT:
                if (column < grid.getNumberOfColumns() - 1 && current.getRight() == CellComponents.APERTURE) newColumn++;
                break;
        }
        row = newRow;
        column = newColumn;
    }

    /**
     * Checks if the player has reached the exit
     *
     * @param grid The game grid
     * @return true if the player has reached the exit, false otherwise
     * @throws IllegalArgumentException if grid is null
     */
    public boolean hasReachedExit(Grid grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid cannot be null");
        }
        return column == 0 && grid.getCell(row, column).getLeft() == CellComponents.EXIT;
    }

    /**
     * Returns a string the player
     * @return A string representing the player's position
     */
    @Override
    public String toString() {
        return String.format("Player [row=%d, column=%d]", row, column);
    }
}