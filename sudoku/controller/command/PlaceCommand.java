package sudoku.controller.command;

import sudoku.controller.SudokuController;
import sudoku.model.SudokuModel;
import sudoku.view.SudokuView;

public class PlaceCommand extends Command {

    SudokuController controller;
    SudokuView view;

    public PlaceCommand(SudokuModel model, SudokuController controller, SudokuView view) {
        super(model);
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void execute() {
        controller.executeCommand(new SetValueCommand(model, view));
    }
}
