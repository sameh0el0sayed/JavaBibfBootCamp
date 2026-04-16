package com.ga.TicketSystemProject3.Seeder;

import com.ga.TicketSystemProject3.Model.ServiceType;
import com.ga.TicketSystemProject3.Model.User;
import com.ga.TicketSystemProject3.Model.UserProfile;
import com.ga.TicketSystemProject3.Model.UserRole;
import com.ga.TicketSystemProject3.Repository.ServiceTypeRepository;
import com.ga.TicketSystemProject3.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class UserSeeder implements CommandLineRunner {
    private final UserService userService;
    private final ServiceTypeRepository serviceTypeRepository;
    public UserSeeder(UserService userService, ServiceTypeRepository serviceTypeRepository) {
        this.userService = userService;
        this.serviceTypeRepository = serviceTypeRepository;
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

        if (serviceTypeRepository.count() == 0) {

            ServiceType s1 = new ServiceType();
            s1.setName("QUEUE_SERVICE");
            s1.setAvg_service_time(LocalTime.of(0, 10));

            ServiceType s2 = new ServiceType();
            s2.setName("FAST_SERVICE");
            s2.setAvg_service_time(LocalTime.of(0, 5));

            ServiceType s3 = new ServiceType();
            s3.setName("VIP_SERVICE");
            s3.setAvg_service_time(LocalTime.of(0, 2));

            serviceTypeRepository.saveAll(List.of(s1, s2, s3));

         }

    }
}
