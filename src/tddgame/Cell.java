package src.tddgame;

/**
 * Represents a cell in the game grid.
 * Each cell has four components: up, down, left, and right.
 * Each component can be a WALL, APERTURE, or EXIT.
 */
public class Cell {
    private CellComponents up;
    private CellComponents down;
    private CellComponents left;
    private CellComponents right;

    /**
     * Constructs a new Cell with the specified components.
     *
     * @param up The upper component of the cell
     * @param down The lower component of the cell
     * @param left The left component of the cell
     * @param right The right component of the cell
     */
    public Cell(CellComponents up, CellComponents down, CellComponents left, CellComponents right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    /**
     * Checks if the cell has at least one aperture
     * @return true if the cell has at least one aperture, false otherwise
     */
    public boolean hasAperture() {
        return up == CellComponents.APERTURE || down == CellComponents.APERTURE ||
               left == CellComponents.APERTURE || right == CellComponents.APERTURE;
    }

    /**
     * Gets the upper component of the cell.
     * @return The upper component
     */
    public CellComponents getUp() { return up; }
    
    /**
     * Gets the lower component of the cell
     * @return The lower component
     */
    public CellComponents getDown() { return down; }
    
    /**
     * Gets the left component of the cell.
     * @return The left component
     */
    public CellComponents getLeft() { return left; }
    
    /**
     * Gets the right component of the cell.
     * @return The right component
     */
    public CellComponents getRight() { return right; }

    /**
     * Sets the upper component of the cell.
     * @param component The new upper component
     */
    public void setUp(CellComponents component) { this.up = component; }
    
    /**
     * Sets the lower component of the cell.
     * @param component The new lower component
     */
    public void setDown(CellComponents component) { this.down = component; }
    
    /**
     * Sets the left component of the cell.
     * @param component The new left component
     */
    public void setLeft(CellComponents component) { this.left = component; }
    
    /**
     * Sets the right component of the cell.
     * @param component The new right component
     */
    public void setRight(CellComponents component) { this.right = component; }

    /**
     * Returns a string representation of the cell.
     * @return A string representing the cell's components
     */
    @Override
    public String toString() {
        return "Cell[up=" + up + ", down=" + down + ", left=" + left + ", right=" + right + "]";
    }
}
