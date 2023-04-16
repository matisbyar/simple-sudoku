package sudoku.controller.strategy;

import sudoku.model.SudokuModel;

public interface SudokuSolver {
    boolean solve(SudokuModel model);
}
