package tddgame ;

import java.util.List;

public class Game {
    private Grid grid;
    private Player player;

    /**
     * Constructs a new game with the specified grid.
     * Places the player at the bottom-right corner of the grid.
     * @param grid The grid for the game
     */
    public Game(Grid grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid cannot be null");
        }
        
        this.grid = grid;
        this.player = new Player(grid.getNumberOfRows() - 1, grid.getNumberOfColumns() - 1);


        System.out.println("Initial Grid:");
        printGridWithPlayer();

        List<Direction> moves = MazeSolver.findPath(grid, player.getRow(), player.getColumn());


        for (Direction direction : moves) {
            simulateMove(direction);
            if (player.hasReachedExit(grid)) {
                System.out.println("The player has escaped successfully!");
                return;
            }
        }

        System.out.println("The player did not reach the EXIT. They are still trapped!");
    }

    /**
     * Simulates a player move in the specified direction and prints the updated grid.
     *
     * @param direction The direction to move in
     */
    private void simulateMove(Direction direction) {
        System.out.println(" Moving: " + direction);
        player.move(direction, grid);
        printGridWithPlayer();
    }

    /**
     * Prints the current state of the grid with the player's position.
     * 'E' denotes EXIT, 'A' denotes AGENT, 'W' denotes WALL, and 'S' denotes SPACE with APERTURE.
     */
    private void printGridWithPlayer() {
        int playerRow = player.getRow();
        int playerColumn = player.getColumn();

        for (int i = 0; i < grid.getNumberOfRows(); i++) {
            for (int j = 0; j < grid.getNumberOfColumns(); j++) {
                Cell cell = grid.getCell(i, j);

                if (i == playerRow && j == playerColumn) {
                    System.out.print("A ");
                } else if (cell.getLeft() == CellComponents.EXIT && j == 0) {
                    System.out.print("E ");
                } else {
                    // Check if this cell has any apertures to determine see if its a space or wall
                    if (cell.hasAperture()) {
                        System.out.print("S ");
                    } else {
                        System.out.print("W ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Grid grid = GridBuilder.buildRandomGrid();
        new Game(grid);  
    }
}