package com.hibernate.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.entity.Consumer;
import com.hibernate.service.ConsumerService;

@RestController
@RequestMapping("/api/consumers")
public class ConsumerController {

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerConsumer(@RequestBody Consumer consumer) {
        try {
            return ResponseEntity.ok(consumerService.registerConsumer(consumer));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginConsumer(@RequestBody Map<String, String> request) {
        try {
            String mobile = request.get("mobileNumber");
            String pin = request.get("pin");
            return ResponseEntity.ok(consumerService.loginConsumer(mobile, pin));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsumerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(consumerService.getConsumerById(id));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
        }
    }

    @GetMapping
    public List<Consumer> getAllConsumers() {
        return consumerService.getAllConsumers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateConsumerProfile(@PathVariable Long id, @RequestBody Consumer updatedData) {
        try {
            return ResponseEntity.ok(consumerService.updateConsumerProfile(id, updatedData));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
        }
    }
}
