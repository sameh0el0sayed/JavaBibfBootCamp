package com.example.sudokuApi.Exception;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(String message) {
        super(message);
    }
}
