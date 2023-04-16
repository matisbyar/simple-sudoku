package sudoku.controller;

import sudoku.controller.command.*;
import sudoku.model.SudokuModel;
import sudoku.view.SudokuView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SudokuController {

    private final SudokuModel model;
    private final SudokuView view;

    private final Map<String, Command> commands;
    private final Stack<Command> history;

    public SudokuController(SudokuView view) {
        this.model = new SudokuModel(view);
        this.view = view;
        this.commands = new HashMap<>();
        this.history = new Stack<>();

        setCommand("play", new PlayCommand(model, this));
        setCommand("solve", new SolveCommand(model, this));

        setCommand("place", new PlaceCommand(model, this, view));
        setCommand("undo", new UndoCommand(model, this));
        setCommand("stop", new StopGame(model));
    }

    // Moteur du jeu

    public void startGame() {
        view.displayWelcomeMessage();
        while (!model.isBoardSet()) {
            new ChooseSudokuBoard(model, view).execute();
        }
        System.out.println("Starting game...");
        view.displayBoard();
        view.askGameMode();
    }

    /**
     * Play a game of sudoku
     */
    public void play() {
        view.displayBoard();
        while (!isGameFinished()) {
            view.askForAction();
        }
    }

    // Méthodes concernant le patron de conception Command

    /**
     * Set the command corresponding to the given call.
     *
     * @param call    the command to execute
     * @param command the command to execute
     */
    public void setCommand(String call, Command command) {
        commands.put(call, command);
    }

    /**
     * Execute the command corresponding to the given call.
     *
     * @param command the command to execute
     */
    public void executeCommand(Command command) {
        command.execute();
        if (command instanceof UndoableCommand) history.push(command);
    }

    /**
     * Execute the command corresponding to the given call.
     *
     * @param call the command to execute
     * @see SudokuController#executeCommand(Command)
     */
    public void executeCommand(String call) {
        if (commands.containsKey(call)) {
            executeCommand(commands.get(call));
        }
    }

    /**
     * Cancel the previous <code>UndoableCommand</code>-type command
     */
    public void undoCommand() {
        if (!history.isEmpty() && history.lastElement() instanceof UndoableCommand command) {
            System.out.println("Undoing last action... ");
            command.undo();
            history.pop();
        } else {
            System.out.println("No action to undo!");
        }
    }

    // Méthodes concernant l'affichage d'informations utilisées par la vue

    /**
     * Get the list of available boards
     *
     * @return the list of available boards
     */
    public String getSudokuBoardsAvailable() {
        File[] boards = new File("dataset").listFiles();
        StringBuilder sb = new StringBuilder();
        assert boards != null;
        for (File board : boards) {
            if (board.isFile() && board.getName().endsWith(".txt")) {
                sb.append(board.getName(), 6, board.getName().length() - 4).append(", ");
            }
        }
        return sb.substring(0, sb.length() - 2);
    }

    // Valeurs récupérées depuis le modèle

    public void setValueAt(int row, int col, int value) {
        model.setValueAt(row, col, value);
    }

    public boolean isValueValid(int row, int col, int value) {
        return model.isValueValid(row, col, value);
    }

    public boolean isGameFinished() {
        return model.isGameFinished();
    }

    public int getBoardSize() {
        return model.getBoardSize();
    }

    public int getValueAt(int row, int col) {
        return model.getValueAt(row, col);
    }

    public int getBlockSize() {
        return model.getBlockSize();
    }
}
