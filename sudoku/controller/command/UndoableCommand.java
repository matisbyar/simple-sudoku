package sudoku.controller.command;

import sudoku.model.SudokuModel;

public abstract class UndoableCommand extends Command {
    public UndoableCommand(SudokuModel model) {
        super(model);
    }

    public abstract void undo();
}
