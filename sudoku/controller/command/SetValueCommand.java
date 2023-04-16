package sudoku.controller.command;

import sudoku.model.SudokuModel;
import sudoku.view.SudokuView;

public class SetValueCommand extends UndoableCommand {

    SudokuView view;
    int row, col, newValue, oldValue;

    public SetValueCommand(SudokuModel model, SudokuView view) {
        super(model);
        this.view = view;
    }

    @Override
    public void execute() {
        int[] coords = view.askForCoords();

        this.row = coords[0];
        this.col = coords[1];
        this.newValue = coords[2];
        this.oldValue = model.getValueAt(row, col);

        model.setValueAt(row, col, newValue);
    }

    @Override
    public void undo() {
        model.setValueAt(row, col, oldValue);
    }
}
