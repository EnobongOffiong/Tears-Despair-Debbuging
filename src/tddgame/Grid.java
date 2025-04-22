package src.tddgame;



import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Row> rows;

    public Grid(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Grid [rows=" + rows + "]";
    }
}
