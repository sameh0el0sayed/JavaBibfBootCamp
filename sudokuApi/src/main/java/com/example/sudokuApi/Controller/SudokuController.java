package com.example.sudokuApi.Controller;

import com.example.sudokuApi.Exception.SudokuFileNotFoundException;
import com.example.sudokuApi.Model.Sudoku;
import com.example.sudokuApi.Solver.SudokuSolver;
import com.example.sudokuApi.SudokuFileParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SudokuController {

    @PostMapping("/SolveSodoku")
    public ResponseEntity<String> SolveSodoku(
            @RequestParam("file") MultipartFile file,
            @RequestParam("outputFile") String outputFile
    ) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new SudokuFileNotFoundException("Sudoku game file was not provided");
        }

        String content = new String(
                file.getBytes(),
                StandardCharsets.UTF_8
        );

        List<int[][]> puzzles = SudokuFileParser.parseSudokuFile(content);
        StringBuilder sb = new StringBuilder();

        for (int p = 0; p < puzzles.size(); p++) {

            Sudoku sudoku = new Sudoku(puzzles.get(p));
            SudokuSolver solver = new SudokuSolver();

            sb.append("\nPuzzle ").append(p + 1).append(":\n\n");
            sb.append(sudoku.printBoard());

            if (solver.solve(sudoku)) {
                sb.append("\nSolved Sudoku ").append(p + 1).append(":\n\n");
                sb.append(sudoku.printBoard());
            } else {
                sb.append("\nNo solution found.");
            }
        }

        Path outputPath = Path.of(outputFile);
        Files.createDirectories(outputPath.getParent());

        Files.writeString(
                outputPath,
                sb.toString(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

        return ResponseEntity.ok(sb.toString());
    }
}
