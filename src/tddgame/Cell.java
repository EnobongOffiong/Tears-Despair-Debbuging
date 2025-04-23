package src.tddgame;

/**
 * This class represents a single square or "cell" in the game grid
 * Each cell has four sides: left, right, up, and down
 * Each side can be a wall, an aperture, or something else defined in CellComponents
 */
public class Cell {

    private CellComponents left, right, up, down;

    /**
     * Creates a new cell with components on all four sides.
     * If you leave any side empty (null), it'll be set to a wall by default.
     *
     * @param left  What’s on the left side of the cell
     * @param right What’s on the right side
     * @param up    What’s on the top side
     * @param down  What’s on the bottom side
     */
    public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
        this.left = (left == null) ? CellComponents.WALL : left;
        this.right = (right == null) ? CellComponents.WALL : right;
        this.up = (up == null) ? CellComponents.WALL : up;
        this.down = (down == null) ? CellComponents.WALL : down;
    }

    /**
     * Returns whatever is on the left side of the cell.
     */
    public CellComponents getLeft() { return left; }

    /**
     * Returns whatever is on the right side of the cell.
     */
    public CellComponents getRight() { return right; }

    /**
     * Returns whatever is on the top side of the cell.
     */
    public CellComponents getUp() { return up; }

    /**
     * Returns whatever is on the bottom side of the cell.
     */
    public CellComponents getDown() { return down; }

    /**
     * Sets the left side of the cell. If you pass in nothing, it sets it to a wall.
     */
    public void setLeft(CellComponents left) {
        this.left = (left == null) ? CellComponents.WALL : left;
    }

    /**
     * Sets the right side of the cell. If you pass in nothing, it sets it to a wall.
     */
    public void setRight(CellComponents right) {
        this.right = (right == null) ? CellComponents.WALL : right;
    }

    /**
     * Sets the top side of the cell. If you pass in nothing, it sets it to a wall.
     */
    public void setUp(CellComponents up) {
        this.up = (up == null) ? CellComponents.WALL : up;
    }

    /**
     * Sets the bottom side of the cell. If you pass in nothing, it sets it to a wall.
     */
    public void setDown(CellComponents down) {
        this.down = (down == null) ? CellComponents.WALL : down;
    }

    /**
     * Returns a simple summary of what’s on each side of the cell.
     */
    @Override
    public String toString() {
        return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
    }
}