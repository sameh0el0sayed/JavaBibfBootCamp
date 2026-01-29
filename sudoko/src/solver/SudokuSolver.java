package solver;

import model.Sudoku;

public class SudokuSolver {
    public boolean solve(Sudoku sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (sudoku.getValue(row, col) == 0) {
                    for (int num = 1; num <= 9; num++) {

                        if (sudoku.isValidMove(row, col, num)) {
                            sudoku.setValue(row, col, num);

                            if (solve(sudoku)) {
                                return true;
                            }

                            sudoku.setValue(row, col, 0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
