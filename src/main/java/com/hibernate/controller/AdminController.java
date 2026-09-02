package com.hibernate.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Value("${agriq.admin.username:admin}") private String username;
    @Value("${agriq.admin.password:Admin@123}") private String password;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        boolean valid = username.equals(credentials.get("username")) && password.equals(credentials.get("password"));
        return valid ? ResponseEntity.ok(Map.of("role", "ADMIN")) : ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
    }
}
