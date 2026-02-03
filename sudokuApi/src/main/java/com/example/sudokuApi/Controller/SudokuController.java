package com.example.sudokuApi.Controller;

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
            @RequestParam("file") MultipartFile file
            ,@RequestParam("outputFile") String outputFile) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // Read file content
        String content = new String(
                file.getBytes(),
                StandardCharsets.UTF_8
        );

        List<int[][]> puzzles= SudokuFileParser.parseSudokuFile(content);
        StringBuilder sb = new StringBuilder();

        for (int p = 0; p < puzzles.size(); p++) {
            int[][] grid = puzzles.get(p);
            Sudoku sudoku = new Sudoku(grid);
            SudokuSolver solver = new SudokuSolver();

            sb.append("\nPuzzle "+(p+1));
            sb.append(sudoku.printBoard());

            if (solver.solve(sudoku)) {
                sb.append("\nSolved Sudoku "+(p+1)+":");
                sb.append(sudoku.printBoard());
            } else {
                sb.append("No solution found.");
            }
        }
        Path outputPath = Path.of( outputFile);
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
