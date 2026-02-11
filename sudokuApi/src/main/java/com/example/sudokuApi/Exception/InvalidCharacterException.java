package com.example.sudokuApi.Exception;

public class InvalidCharacterException extends RuntimeException {

    public InvalidCharacterException(char ch, int lineNumber) {
        super("Invalid character '" + ch + "' found at line " + lineNumber);
    }

    public InvalidCharacterException(String message) {
        super(message);
    }
}
