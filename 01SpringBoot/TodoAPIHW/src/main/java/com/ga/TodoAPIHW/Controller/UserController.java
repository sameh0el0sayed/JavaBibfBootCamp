package com.ga.TodoAPIHW.Controller;

import com.ga.TodoAPIHW.Entity.User;
import com.ga.TodoAPIHW.Entity.request.LoginRequest;
import com.ga.TodoAPIHW.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
