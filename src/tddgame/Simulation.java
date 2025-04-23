package src.tddgame;

import java.util.Scanner;

/**
 * This is the main class that runs the game .
 * you move a player around a grid and try to escape through the EXIT on the left side.
 * Watch out for walls  because they block your movement.
 */
public class Simulation {

    /**
     * Starts the game in the console.
     * You'll be asked to type in moves and the game will respond based on the grid layout.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start up a new game with a 5x5 grid
        Game game = new Game(5);
        Grid grid = game.getGrid();

       
        Player player = new Player(
                grid.getRows().get(grid.getRows().size() - 1),
                grid.getRows().get(grid.getRows().size() - 1).getCells().get(grid.getRows().size() - 1)
        );

        System.out.println("Welcome to Tears, Despair & Debugging!");

        // Loop until the player escapes or quits
        while (true) {
            displayGrid(grid, player);

            // Ask player for move
            System.out.print("Enter move (UP, DOWN, LEFT, RIGHT): ");
            String input = scanner.nextLine().trim().toUpperCase();
            Movement move = null;

            try {
                move = Movement.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Try to move the player . If it's a wall, say so
            boolean moved = game.play(move, player);
            if (!moved) {
                System.out.println("Movement blocked. That is a wall");
            }

            // Check if the player made it to the EXIT
            if (player.getCurrentRow().getCells().indexOf(player.getCurrentCell()) == 0 &&
                player.getCurrentCell().getLeft() == CellComponents.EXIT &&
                move == Movement.LEFT) {
                System.out.println("You escaped through the EXIT! Congrats!");
                break;
            }
        }

        scanner.close();
    }

    /**
     * Prints out the current state of the grid in the console.
     * 'A' means that's where the Agent is. 'E' shows where the EXIT is. 'S' is just a regular cell.
     */
    private static void displayGrid(Grid grid, Player player) {
        for (int i = 0; i < grid.getRows().size(); i++) {
            for (int j = 0; j < grid.getRows().get(i).getCells().size(); j++) {
                Cell cell = grid.getRows().get(i).getCells().get(j);

                if (player.getCurrentRow() == grid.getRows().get(i) && player.getCurrentCell() == cell) {
                    System.out.print("A "); // Agent is here
                } else if (j == 0 && cell.getLeft() == CellComponents.EXIT) {
                    System.out.print("E "); // This cell is the EXIT
                } else {
                    System.out.print("S "); // Just a regular space
                }
            }
            System.out.println();
        }
    }
}
