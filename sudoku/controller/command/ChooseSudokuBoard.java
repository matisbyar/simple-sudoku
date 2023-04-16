package sudoku.controller.command;

import sudoku.model.SudokuModel;
import sudoku.view.SudokuView;

public class ChooseSudokuBoard extends Command {

    SudokuView view;

    public ChooseSudokuBoard(SudokuModel model, SudokuView view) {
        super(model);
        this.view = view;
    }

    @Override
    public void execute() {
        model.setBoard("sudoku" + view.askForSudokuBoard().toLowerCase() + ".txt");
    }
}
