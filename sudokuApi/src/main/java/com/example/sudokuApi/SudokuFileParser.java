package com.example.sudokuApi;

import java.util.ArrayList;
import java.util.List;

public class SudokuFileParser {

    public static List<int[][]> parseSudokuFile(String fileContent) {

        List<int[][]> puzzles = new ArrayList<>();
        List<int[]> currentRows = new ArrayList<>();

        String[] lines = fileContent.split("\\R"); // split by any newline

        for (String line : lines) {
            line = line.trim();

            // Skip empty lines
            if (line.isEmpty()) continue;

            // Skip headers and separators
            if (line.startsWith("Puzzle") || line.startsWith("-")) continue;

            // Remove pipes
            line = line.replace("|", "").trim();

            // Split numbers
            String[] numbers = line.split("\\s+");
            if (numbers.length != 9) continue;

            int[] row = new int[9];
            for (int i = 0; i < 9; i++) {
                row[i] = Integer.parseInt(numbers[i]);
            }

            currentRows.add(row);

            // When we have 9 rows → one puzzle completed
            if (currentRows.size() == 9) {
                int[][] puzzle = new int[9][9];
                for (int i = 0; i < 9; i++) {
                    puzzle[i] = currentRows.get(i);
                }
                puzzles.add(puzzle);
                currentRows.clear();
            }
        }

        return puzzles;
    }
}

