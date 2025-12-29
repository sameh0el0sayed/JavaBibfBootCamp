package com.ga.ToDoHW.Service;

import com.ga.ToDoHW.Entity.User;
import com.ga.ToDoHW.Repository.UserRepository;
import com.ga.ToDoHW.Security.JwtTokenProvider;
import com.ga.ToDoHW.ViewModel.LoginResponseViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${security.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User createUser(String username,String email, String password) {
        if (userRepository.existsByUsername(username)) {
             throw new RuntimeException("User already exists");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setCreatedAt(LocalDateTime.now());

        return userRepository.save(newUser);
    }
    // Get single user by username
    public User getUser(String username) {
        User userOpt = userRepository.findByUsername(username).orElse(null);
        if (userOpt!=null) {
             return userOpt;
        } else {
             throw new RuntimeException("User not found");
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
         return users;
    }

    // Login method
    public LoginResponseViewModel login(String username, String password) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!passwordEncoder.matches(password, user.getPassword())) {
                 throw new RuntimeException("Invalid credentials");
            }

            String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
            String refreshTokenString = jwtTokenProvider.generateRefreshToken(user.getUsername());


            return new LoginResponseViewModel(accessToken, refreshTokenString);

        } catch (Exception e) {
             throw e;
        }
    }

    // Refresh token method
    public LoginResponseViewModel refreshToken(String refreshToken) {
        try {
            if (!jwtTokenProvider.validateToken(refreshToken)) {
                 throw new RuntimeException("Invalid refresh token");
            }

            String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
            String accessToken = jwtTokenProvider.generateAccessToken(username);
            String refreshTokenString = jwtTokenProvider.generateRefreshToken(username);


            return new LoginResponseViewModel(accessToken, refreshTokenString);

        } catch (Exception e) {
            throw e;
        }
    }
}
