package com.example.sudokuApi.Model;


import com.example.sudokuApi.Exception.InvalidMoveException;
import com.example.sudokuApi.Exception.InvalidSudokuException;

public class Sudoku {

    private static final int SIZE = 9;
    private final SudokuCell[][] board = new SudokuCell[SIZE][SIZE];

    public Sudoku(int[][] input) {
        if (input.length != SIZE) {
            throw new InvalidSudokuException("Sudoku must be 9x9");
        }

        for (int row = 0; row < SIZE; row++) {
            if (input[row].length != SIZE) {
                throw new InvalidSudokuException("Sudoku must be 9x9");
            }

            for (int col = 0; col < SIZE; col++) {
                int value = input[row][col];
                if (value < 0 || value > 9) {
                    throw new InvalidSudokuException("Values must be between 0 and 9");
                }
                board[row][col] = new SudokuCell(value);
            }
        }
    }

    public int getValue(int row, int col) {
        return board[row][col].getValue();
    }

    public void setValue(int row, int col, int value) {

        // Allow clearing during backtracking
        if (value == 0) {
            board[row][col].setValue(0);
            return;
        }

        if (!isValidMove(row, col, value)) {
            throw new InvalidMoveException("Invalid move at [" + row + "," + col + "]");
        }

        board[row][col].setValue(value);
    }


    public boolean isValidMove(int row, int col, int value) {
        if (value < 1 || value > 9) return false;
        if (!board[row][col].isEmpty()) return false;

        for (int c = 0; c < SIZE; c++) {
            if (board[row][c].getValue() == value) return false;
        }

        for (int r = 0; r < SIZE; r++) {
            if (board[r][col].getValue() == value) return false;
        }


        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c].getValue() == value) return false;
            }
        }

        return true;
    }

    public StringBuilder printBoard() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < SIZE; row++) {

             if (row % 3 == 0) {
                result.append("\n+-------+-------+-------+\n");
            }

            for (int col = 0; col < SIZE; col++) {

                 if (col % 3 == 0) {
                    result.append("| ");
                }

                int value = board[row][col].getValue();
                result.append(value == 0 ? ". " : value + " ");
            }

             result.append("|\n");
        }

         result.append("+-------+-------+-------+");

        return result;
    }

}


