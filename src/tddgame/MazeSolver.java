//package src.tddgame;
//
//import java.util.*;
//
//public class MazeSolver {
//
//    private static class Position {
//        int row;
//        int column;
//
//        Position(int row, int column) {
//            this.row = row;
//            this.column = column;
//        }
//
//        // For use in HashMap/HashSet
//        @Override
//        public boolean equals(Object obj) {
//            if (!(obj instanceof Position)) return false;
//            Position other = (Position) obj;
//            return row == other.row && column == other.column;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(row, column);
//        }
//    }
//
//    public static List<Direction> findPath(Grid grid, int startRow, int startColumn) {
//        Queue<Position> queue = new LinkedList<>();
//        Map<Position, Position> cameFrom = new HashMap<>();
//        Map<Position, Direction> moveDirection = new HashMap<>();
//
//        Position start = new Position(startRow, startColumn);
//        queue.add(start);
//        cameFrom.put(start, null);
//
//        while (!queue.isEmpty()) {
//            Position current = queue.poll();
//
//            if (current.row == 0 && current.column == 0 &&
//                grid.getCell(current.row, current.column).getLeft() == CellComponents.EXIT) {
//                break;
//            }
//
//            for (Direction direction : Direction.values()) {
//                Position neighbor = move(current, direction, grid);
//                if (neighbor != null && !cameFrom.containsKey(neighbor)) {
//                    cameFrom.put(neighbor, current);
//                    moveDirection.put(neighbor, direction);
//                    queue.add(neighbor);
//                }
//            }
//        }
//
//        // Reconstruct path
//        List<Direction> path = new ArrayList<>();
//        Position current = new Position(0, 0);  // The exit cell is always (0, 0)
//
//        if (!cameFrom.containsKey(current)) {
//            return Collections.emptyList(); // No path found
//        }
//
//        while (cameFrom.get(current) != null) {
//            Direction dir = moveDirection.get(current);
//            path.add(dir);
//            current = cameFrom.get(current);
//        }
//
//        Collections.reverse(path); // because we built the path backwards
//        return path;
//    }
//
//    private static Position move(Position current, Direction direction, Grid grid) {
//        int row = current.row;
//        int column = current.column;
//        Cell cell = grid.getCell(row, column);
//
//        switch (direction) {
//            case UP:
//                if (row > 0 && cell.getUp() == CellComponents.APERTURE) {
//                    return new Position(row - 1, column);
//                }
//                break;
//            case DOWN:
//                if (row < grid.getNumberOfRows() - 1 && cell.getDown() == CellComponents.APERTURE) {
//                    return new Position(row + 1, column);
//                }
//                break;
//            case LEFT:
//                if (column > 0 && cell.getLeft() == CellComponents.APERTURE) {
//                    return new Position(row, column - 1);
//                } else if (column == 0 && cell.getLeft() == CellComponents.EXIT) {
//                    return new Position(0, 0); // special case for exit
//                }
//                break;
//            case RIGHT:
//                if (column < grid.getNumberOfColumns() - 1 && cell.getRight() == CellComponents.APERTURE) {
//                    return new Position(row, column + 1);
//                }
//                break;
//        }
//        return null;
//    }
//
//}