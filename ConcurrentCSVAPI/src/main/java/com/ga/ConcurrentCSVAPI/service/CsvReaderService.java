package com.ga.ConcurrentCSVAPI.service;

import com.ga.ConcurrentCSVAPI.model.Employee;
import com.ga.ConcurrentCSVAPI.model.Role;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CsvReaderService {
public List<Employee> readEmployees(){
    List<Employee> employees=new ArrayList<>();

    try{
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("test_employees.csv"))));
        String line;
        while ((line=reader.readLine())!=null){
            String[] data=line.split(",");
            employees.add(new Employee(
                    Long.parseLong(data[0]),
                    data[1],
                    Double.parseDouble(data[2]),
                    LocalDate.parse(data[3]),
                    Role.valueOf(data[4].toUpperCase()),
                    Double.parseDouble(data[5])
            ));
        }

    }catch (Exception e){
        throw new RuntimeException("CSV Read Error",e);
    }

    return employees;
}


}
