package com.example.sudokuApi.Exception;

public class SudokuFileNotFoundException extends RuntimeException {

    public SudokuFileNotFoundException() {
        super("Sudoku game file not found");
    }

    public SudokuFileNotFoundException(String message) {
        super(message);
    }
}
