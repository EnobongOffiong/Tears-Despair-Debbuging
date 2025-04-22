package src.tddgame;


import java.util.List;

public class Player {
    private Row currentRow;
    private Cell currentCell;

    public Player(Row currentRow, Cell currentCell) {
        if (currentRow == null || currentCell == null) {
            throw new IllegalArgumentException("Row and Cell cannot be null.");
        }
        this.currentRow = currentRow;
        this.currentCell = currentCell;
    }

    public Row getCurrentRow() {
        return currentRow;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

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
                // check for EXIT on left side
                if (colIndex == 0 && currentCell.getLeft() == CellComponents.EXIT) {
                    return true; // agent has escaped!
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

        return false;
    }

    @Override
    public String toString() {
        return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
    }
}
