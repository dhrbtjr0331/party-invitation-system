package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());
        
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            // Assuming you generate a token or session after login (for simplicity, using a placeholder here)
            return ResponseEntity.ok().body("{ \"token\": \"your-jwt-token\" }");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
