package sudoku.controller.command;

import sudoku.controller.SudokuController;
import sudoku.controller.strategy.BacktrackingSolver;
import sudoku.controller.strategy.SudokuSolver;
import sudoku.model.SudokuModel;

public class SolveCommand extends Command {

    SudokuController controller;
    SudokuSolver solver;

    public SolveCommand(SudokuModel model, SudokuController controller) {
        super(model);
        this.controller = controller;
    }

    @Override
    public void execute() {
        setSolvingStrategy(new BacktrackingSolver());
        if (!solve()) System.out.println("No luck!");
    }

    // Méthodes concernant le patron de conception Strategy

    private void setSolvingStrategy(SudokuSolver solver) {
        this.solver = solver;
    }

    private boolean solve() {
        if (solver != null) return solver.solve(model);
        return false;
    }
}
