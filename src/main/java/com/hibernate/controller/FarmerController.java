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

import com.hibernate.entity.Farmer;
import com.hibernate.service.FarmerService;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/register")
    public Farmer registerFarmer(@RequestBody Farmer farmer) {
        return farmerService.registerFarmer(farmer);
    }

    @GetMapping
    public List<Farmer> getAllFarmers() {
        return farmerService.getAllFarmers();
    }

    @GetMapping("/{id}")
    public Farmer getFarmerById(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFarmerProfile(@PathVariable Long id, @RequestBody Farmer updatedData) {
        try {
            return ResponseEntity.ok(farmerService.updateFarmerProfile(id, updatedData));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginFarmer(@RequestBody Farmer farmer) {
        try {
            return ResponseEntity.ok(farmerService.loginFarmer(farmer.getMobileNumber(), farmer.getPin()));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
        }
    }
}
