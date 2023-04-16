package sudoku.controller.command;

import sudoku.model.SudokuModel;

public class StopGame extends Command {
    public StopGame(SudokuModel model) {
        super(model);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
