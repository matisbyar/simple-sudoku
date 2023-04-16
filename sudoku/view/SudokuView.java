package sudoku.view;

import sudoku.controller.SudokuController;

import java.util.Scanner;

public class SudokuView {

    private final SudokuController controller;

    public SudokuView() {
        controller = new SudokuController(this);

        controller.startGame();
    }

    // User inputs

    /**
     * Ask the user for a game mode and execute it
     */
    public void askGameMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter game mode: (available modes: play, solve)");
        controller.executeCommand(scanner.nextLine());
    }

    /**
     * Ask the user for a command and execute it
     */
    public void askForAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter action: (available actions: place, undo, stop)");
        controller.executeCommand(scanner.nextLine());
    }

    /**
     * This method displays a message asking the user to enter a value between 1 and 9, then reads the user's entry
     * from the console using the Scanner class. It returns the entered value as an integer.
     */
    public int[] askForCoords() {
        try {
            int[] coords = new int[3];

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter row number (1-9):");
            coords[0] = Integer.parseInt(scanner.nextLine()) - 1; // Convert to 0-based indexing

            System.out.println("Enter column number (1-9):");
            coords[1] = Integer.parseInt(scanner.nextLine()) - 1; // Convert to 0-based indexing

            System.out.println("Enter value (1-9):");
            coords[2] = Integer.parseInt(scanner.nextLine());

            return coords;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return askForCoords();
        }
    }

    /**
     * Ask the user for a difficulty and return it.
     */
    public String askForSudokuBoard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter difficulty: (available sudoku boards: " + controller.getSudokuBoardsAvailable() + ")");
        return scanner.nextLine();
    }

    // Display methods

    /**
     * Display a welcome message.
     */
    public void displayWelcomeMessage() {
        System.out.println("Welcome to Sudoku game!");
    }

    // Display methods used by the Observer sudokuameliore

    /**
     * Display the game status.
     */
    public void displayGameStatus() {
        System.out.println(controller.isGameFinished() ? "Congrats!" : "");
    }

    /**
     * Display the board.
     *
     * @see sudoku.model.observer.CellObserver#update(SudokuView, int, int, int)
     */
    public void displayBoard() {
        for (int row = 0; row < controller.getBoardSize(); row++) {
            if (row % controller.getBlockSize() == 0) {
                System.out.println(" -----------------------");
            }
            for (int col = 0; col < controller.getBoardSize(); col++) {
                if (col % controller.getBlockSize() == 0) {
                    System.out.print("| ");
                }
                int value = controller.getValueAt(row, col);
                if (value == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(value + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    /**
     * Display the move made by the user.
     *
     * @param row   the row of the move
     * @param col   the column of the move
     * @param value the value of the move
     * @see sudoku.model.observer.CellObserver#update(SudokuView, int, int, int)
     */
    public void displayMove(int row, int col, int value) {
        System.out.println("Cell at row " + row + ", column " + col + " updated to " + value);
    }
}
