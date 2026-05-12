package com.ga.deliverysystem.Seeder;

import com.ga.deliverysystem.Model.Enum.UserRoleEnum;
import com.ga.deliverysystem.Model.UserModel;
import com.ga.deliverysystem.Model.UserProfileModel;
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

            UserModel admin = new UserModel();
            admin.setUserName("admin");
            admin.setEmailAddress("admin@test.com");
            admin.setPassword("123456");
            admin.setRole(UserRoleEnum.ADMIN);
            UserProfileModel userProfileModel =new UserProfileModel();
            userProfileModel.setFirstName(admin.getUserName());
            userProfileModel.setLastName(admin.getUserName());
            admin.setUserProfile(userProfileModel);
            userService.createUser(admin);

        } catch (Exception e) {
            System.out.println("Admin already exists");
        }

        try {

            UserModel userModel = new UserModel();
            userModel.setUserName("user1");
            userModel.setEmailAddress("user1@test.com");
            userModel.setPassword("123456");
            userModel.setRole(UserRoleEnum.USER);
            UserProfileModel userProfileModel =new UserProfileModel();
            userProfileModel.setFirstName(userModel.getUserName());
            userProfileModel.setLastName(userModel.getUserName());
            userModel.setUserProfile(userProfileModel);
            userService.createUser(userModel);



        } catch (Exception e) {
            System.out.println("User already exists");
        }


    }
}

