package com.ga.ToDoHW.Controller;

import com.ga.ToDoHW.Entity.User;
import com.ga.ToDoHW.Service.UserService;
import com.ga.ToDoHW.ViewModel.CreateUserRequestViewModel;
import com.ga.ToDoHW.ViewModel.LoginRequestViewModel;
import com.ga.ToDoHW.ViewModel.RefreshTokenViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestViewModel viewModel) {
        return ResponseEntity.ok().body(userService.login(viewModel.getUsername(), viewModel.getPassword()));
    }

    // Refresh token endpoint
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenViewModel viewModel) {
        return ResponseEntity.ok().body(userService.refreshToken(viewModel.getRefreshToken()));
    }


    @PostMapping("/user/createUser")
    public User createUser(@RequestBody CreateUserRequestViewModel request) {
        return userService.createUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
    }
    @GetMapping("/user/getUser/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username);
    }
    @GetMapping("/user/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
