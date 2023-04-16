package sudoku.controller.command;

import sudoku.controller.SudokuController;
import sudoku.model.SudokuModel;

public class PlayCommand extends Command {

    SudokuController controller;

    public PlayCommand(SudokuModel model, SudokuController controller) {
        super(model);
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.play();
    }
}
