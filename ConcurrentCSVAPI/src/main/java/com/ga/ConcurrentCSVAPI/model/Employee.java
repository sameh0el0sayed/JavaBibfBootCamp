package com.ga.ConcurrentCSVAPI.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class Employee {

    private final Long id;
    private final String name;
    private final AtomicReference<Double> salary;
    private final LocalDate joinedDate;
    private final Role role;
    private final double completedProjects;

    private final ReentrantLock lock = new ReentrantLock();

    public Employee(Long id, String name, double salary,
                    LocalDate joinedDate, Role role, double completedProjects) {
        this.id = id;
        this.name = name;
        this.salary = new AtomicReference<>(salary);
        this.joinedDate = joinedDate;
        this.role = role;
        this.completedProjects = completedProjects;
    }

    public AtomicReference<Double> getSalary() {
        return salary;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public Role getRole() {
        return role;
    }

    public double getCompletedProjects() {
        return completedProjects;
    }

    public String getName() {
        return name;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}

