package com.ga.deliverysystem.Seeder;

import com.ga.deliverysystem.Model.Enum.UserRole;
import com.ga.deliverysystem.Model.User;
import com.ga.deliverysystem.Model.UserProfile;
import com.ga.deliverysystem.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {
    private final UserService userService;
     public UserSeeder(UserService userService ) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        try {

            User admin = new User();
            admin.setUserName("admin");
            admin.setEmailAddress("admin@test.com");
            admin.setPassword("123456");
            admin.setRole(UserRole.ADMIN);
            UserProfile userProfile =new UserProfile();
            userProfile.setFirstName(admin.getUserName());
            userProfile.setLastName(admin.getUserName());
            admin.setUserProfile(userProfile);
            userService.createUser(admin);

        } catch (Exception e) {
            System.out.println("Admin already exists");
        }

        try {

            User user = new User();
            user.setUserName("user1");
            user.setEmailAddress("user1@test.com");
            user.setPassword("123456");
            user.setRole(UserRole.USER);
            UserProfile userProfile =new UserProfile();
            userProfile.setFirstName(user.getUserName());
            userProfile.setLastName(user.getUserName());
            user.setUserProfile(userProfile);
            userService.createUser(user);



        } catch (Exception e) {
            System.out.println("User already exists");
        }


    }
}

