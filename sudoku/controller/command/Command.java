package sudoku.controller.command;

import sudoku.model.SudokuModel;

public abstract class Command {

    SudokuModel model;

    public Command(SudokuModel model) {
        this.model = model;
    }

    public abstract void execute();
}
