package com.services;

import java.time.LocalDate;

// Employee class
public class Employee {
    private String name;
    private double salary;
    private Gender gender;
    private LocalDate hireDate;

    public Employee(String name, double salary, Gender gender, LocalDate hireDate) {
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
    public Gender getGender() { return gender; }
    public LocalDate getHireDate() { return hireDate; }

    @Override
    public String toString() {
        return "Employee{name='" + name + '\'' +
                ", salary=" + salary +
                ", gender=" + gender +
                ", hireDate=" + hireDate + '}';
    }
}
