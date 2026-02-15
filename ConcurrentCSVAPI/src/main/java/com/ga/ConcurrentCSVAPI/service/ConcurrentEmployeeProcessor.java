package com.ga.ConcurrentCSVAPI.service;

import com.ga.ConcurrentCSVAPI.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ConcurrentEmployeeProcessor {

    private static final int THREAD_POOL_SIZE = 4;
    private static final int MAX_CONCURRENT_UPDATES = 2;

    private final SalaryProcessorService salaryService;

    private final ExecutorService executor =
            Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    private final Semaphore semaphore =
            new Semaphore(MAX_CONCURRENT_UPDATES);

    public ConcurrentEmployeeProcessor(SalaryProcessorService salaryService) {
        this.salaryService = salaryService;
    }

    public void process(List<Employee> employees) {

        CountDownLatch latch = new CountDownLatch(employees.size());

        for (Employee employee : employees) {

            executor.submit(() -> {
                try {
                     semaphore.acquire();

                    ReentrantLock lock = employee.getLock();
                    lock.lock();
                    try {
                        salaryService.applyIncrement(employee);
                    } finally {
                        lock.unlock();
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release();
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}

