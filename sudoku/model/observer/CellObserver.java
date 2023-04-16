package sudoku.model.observer;

import sudoku.view.SudokuView;

public class CellObserver implements SudokuObserver {
    @Override
    public void update(SudokuView view, int row, int col, int value) {
        view.displayMove(row, col, value);
        view.displayBoard();
        view.displayGameStatus();
    }
}
