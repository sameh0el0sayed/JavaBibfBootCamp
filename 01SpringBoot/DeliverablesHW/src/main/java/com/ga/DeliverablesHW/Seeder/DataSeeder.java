package com.ga.DeliverablesHW.Seeder;

import com.ga.DeliverablesHW.Entity.User;
import com.ga.DeliverablesHW.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Seed superadmin
        if (!userRepository.existsByUsername("superadmin")) {
            User superadmin = new User();
            superadmin.setUsername("superadmin");
            superadmin.setPassword(passwordEncoder.encode("superadmin"));
            userRepository.save(superadmin);
        }

        System.out.println("Initial users seeded successfully!");
    }
}

