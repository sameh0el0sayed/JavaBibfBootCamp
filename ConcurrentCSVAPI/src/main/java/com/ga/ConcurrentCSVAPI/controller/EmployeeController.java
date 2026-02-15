package com.ga.ConcurrentCSVAPI.controller;

import com.ga.ConcurrentCSVAPI.model.Employee;
import com.ga.ConcurrentCSVAPI.service.ConcurrentEmployeeProcessor;
import com.ga.ConcurrentCSVAPI.service.CsvReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final CsvReaderService csvReader;
    private final ConcurrentEmployeeProcessor processor;

    public EmployeeController(CsvReaderService csvReader, ConcurrentEmployeeProcessor processor) {
        this.csvReader = csvReader;
        this.processor = processor;
    }
    @GetMapping("/process")
    public List<Employee> processEmployees() {
        List<Employee> employees = csvReader.readEmployees();
        processor.process(employees);
        return employees;
    }
}
