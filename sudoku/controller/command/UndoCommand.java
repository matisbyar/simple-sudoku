package sudoku.controller.command;

import sudoku.controller.SudokuController;
import sudoku.model.SudokuModel;

public class UndoCommand extends Command {

    SudokuController controller;

    public UndoCommand(SudokuModel model, SudokuController controller) {
        super(model);
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.undoCommand();
    }
}
