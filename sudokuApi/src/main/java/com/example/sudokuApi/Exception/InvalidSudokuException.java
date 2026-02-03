package com.example.sudokuApi.Exception;

public class InvalidSudokuException extends RuntimeException {

    public InvalidSudokuException(String message) {
        super(message);
    }
}
