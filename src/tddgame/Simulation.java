package src.tddgame;


import java.util.Scanner;

public class Simulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(5); // Grid size (between 3 and 7)
        Grid grid = game.getGrid();
        Player player = new Player(
                grid.getRows().get(grid.getRows().size() - 1),
                grid.getRows().get(grid.getRows().size() - 1).getCells().get(grid.getRows().size() - 1)
        );

        System.out.println("Welcome to Tears, Despair & Debugging!");
        while (true) {
            displayGrid(grid, player);

            System.out.print("Enter move (UP, DOWN, LEFT, RIGHT): ");
            String input = scanner.nextLine().trim().toUpperCase();
            Movement move = null;

            try {
                move = Movement.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            boolean moved = game.play(move, player);
            if (!moved) {
                System.out.println("Movement blocked. That is a wall");
            }

            if (player.getCurrentRow().getCells().indexOf(player.getCurrentCell()) == 0 &&
                player.getCurrentCell().getLeft() == CellComponents.EXIT &&
                move == Movement.LEFT) {
                System.out.println("You escaped through the EXIT! Congrats!");
                break;
            }
        }
        scanner.close();
    }

    private static void displayGrid(Grid grid, Player player) {
        for (int i = 0; i < grid.getRows().size(); i++) {
            for (int j = 0; j < grid.getRows().get(i).getCells().size(); j++) {
                Cell cell = grid.getRows().get(i).getCells().get(j);
                if (player.getCurrentRow() == grid.getRows().get(i) && player.getCurrentCell() == cell) {
                    System.out.print("A ");
                } else if (j == 0 && cell.getLeft() == CellComponents.EXIT) {
                    System.out.print("E ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }
}
