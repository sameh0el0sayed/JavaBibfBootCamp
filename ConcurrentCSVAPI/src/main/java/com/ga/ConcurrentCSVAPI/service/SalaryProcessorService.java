package com.ga.ConcurrentCSVAPI.service;



import com.ga.ConcurrentCSVAPI.DateUtil;
import com.ga.ConcurrentCSVAPI.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class SalaryProcessorService {

    public void applyIncrement(Employee e) {

        if (e.getCompletedProjects() < 0.6)
            return;

        long years = DateUtil.yearsWorked(e.getJoinedDate());
        if (years < 1)
            return;

        double increment = years * 0.02;

        switch (e.getRole()) {
            case DIRECTOR -> increment += 0.05;
            case MANAGER -> increment += 0.02;
            case EMPLOYEE -> increment += 0.01;
        }

        double finalIncrement = increment;
        e.getSalary().updateAndGet(s -> s + (s * finalIncrement));
    }
}

