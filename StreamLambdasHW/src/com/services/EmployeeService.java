package com.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Service class with Stream operations
public class EmployeeService {

    private List<Employee> employees;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    private <E> void printList(List<E> list) {
        if (list != null) list.forEach(System.out::println);
        else {
            System.out.println("List is empty or null");
        }
    }

    public void getEmployeesOver50k() {
        List<Employee> over50k = employees.stream()
                .filter(e -> e.getSalary() >= 50000)
                .toList();
        System.out.println("\nEmployees earning $50k or more:");
        printList(over50k);
    }

    public void getEmployeeNamesHiredAfter2012() {
        List<String> names = employees.stream()
                .filter(e -> !e.getHireDate().isBefore(LocalDate.of(2012, 1, 1)))
                .map(Employee::getName)
                .toList();
        System.out.println("\nEmployees hired on or after Jan 1, 2012:");
        printList(names);
    }

    public void getMaxSalary() {
        double max = employees.stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0);
        System.out.println("\nMax Salary: " + max);
    }

    public void getMinSalary() {
        double min = employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);
        System.out.println("Min Salary: " + min);
    }

    public void getAverageSalaries() {
        double averageMale = employees.stream()
                .filter(e -> e.getGender() == Gender.MALE)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        double averageFemale = employees.stream()
                .filter(e -> e.getGender() == Gender.FEMALE)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        System.out.println("Average Salaries -> Male: " + averageMale + ", Female: " + averageFemale);
    }

    public void getMaximumPaidEmployee() {
        Optional<Employee> highest = employees.stream()
                .reduce((e1, e2) -> e1.getSalary() >= e2.getSalary() ? e1 : e2);
        System.out.println("Highest Paid Employee: " + highest.orElse(null));
    }
}