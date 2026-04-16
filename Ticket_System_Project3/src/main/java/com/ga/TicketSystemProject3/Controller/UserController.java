package com.ga.TicketSystemProject3.Controller;


import com.ga.TicketSystemProject3.Model.Image;
import com.ga.TicketSystemProject3.Model.User;
import com.ga.TicketSystemProject3.Model.request.ChangePasswordRequest;
import com.ga.TicketSystemProject3.Model.request.ImageRequest;
import com.ga.TicketSystemProject3.Model.request.LoginRequest;
import com.ga.TicketSystemProject3.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User userObj){
        System.out.println("Calling createUser=>>");
        return userService.createUser(userObj);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("calling loginUser ==>");

        return userService.loginUser(loginRequest);
    }
    @PutMapping("/set-image")
    public User setImage(@ModelAttribute ImageRequest image){
        System.out.println("calling set image in controller ========>");
        return userService.setUserImage(image);
    }
    @DeleteMapping("/softDelete")
    public void softDelete(){
        System.out.println("calling soft delete user in user controller ========>");
        userService.softDelete();
    }
    @GetMapping("/reset-password")
    public void passwordReset(@RequestBody User user){
        System.out.println("calling reset in controller ========>");
        userService.resetPassword(user.getEmailAddress());
    }
    @PostMapping("/reset-password-activator")
    public void passwordResetActivator(@RequestBody User user ,@RequestParam String token){
        System.out.println("calling reset activator in controller ========>");
        userService.resetPasswordActivator(token,user);
    }
    @PutMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequest request){
        System.out.println("calling change password in controller ========>");
        userService.changePassword(request.getOldPass(), request.getNewPass() );
    }


}
