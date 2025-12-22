package com.ga.DeliverablesHW.Controller;

import com.ga.DeliverablesHW.Entity.User;
import com.ga.DeliverablesHW.Service.UserService;
import com.ga.DeliverablesHW.ViewModel.CreateUserRequestViewModel;
import com.ga.DeliverablesHW.ViewModel.LoginRequestViewModel;
import com.ga.DeliverablesHW.ViewModel.RefreshTokenViewModel;
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
