package sudoku.model.observer;

import sudoku.view.SudokuView;

public interface SudokuObserver {
    void update(SudokuView view, int row, int col, int value);
}
