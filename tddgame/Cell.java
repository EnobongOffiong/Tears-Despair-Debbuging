package tddgame;

public class Cell {
	private Side up, down, left, right;

    public Cell(Side up, Side down, Side left, Side right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public boolean hasAperture() {
        return up == Side.APERTURE || down == Side.APERTURE ||
               left == Side.APERTURE || right == Side.APERTURE;
    }

    public Side getUp() { return up; }
    public Side getDown() { return down; }
    public Side getLeft() { return left; }
    public Side getRight() { return right; }

    public void setUp(Side up) { this.up = up; }
    public void setDown(Side down) { this.down = down; }
    public void setLeft(Side left) { this.left = left; }
    public void setRight(Side right) { this.right = right; }
}
