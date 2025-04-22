package src.tddgame;



public class Cell {
    private CellComponents left, right, up, down;

    public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
        this.left = (left == null) ? CellComponents.WALL : left;
        this.right = (right == null) ? CellComponents.WALL : right;
        this.up = (up == null) ? CellComponents.WALL : up;
        this.down = (down == null) ? CellComponents.WALL : down;
    }

    public CellComponents getLeft() { return left; }
    public CellComponents getRight() { return right; }
    public CellComponents getUp() { return up; }
    public CellComponents getDown() { return down; }

    public void setLeft(CellComponents left) {
        this.left = (left == null) ? CellComponents.WALL : left;
    }

    public void setRight(CellComponents right) {
        this.right = (right == null) ? CellComponents.WALL : right;
    }

    public void setUp(CellComponents up) {
        this.up = (up == null) ? CellComponents.WALL : up;
    }

    public void setDown(CellComponents down) {
        this.down = (down == null) ? CellComponents.WALL : down;
    }

    @Override
    public String toString() {
        return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
    }
}
