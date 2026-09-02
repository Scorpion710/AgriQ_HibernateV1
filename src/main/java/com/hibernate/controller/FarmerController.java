package com.hibernate.controller;

import com.hibernate.entity.Farmer;
import com.hibernate.service.FarmerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;

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
    public Map<String, Object> getFarmerById(@PathVariable Long id) {
        Farmer farmer = farmerService.getFarmerById(id);
        return Map.of(
                "id", farmer.getId(),
                "name", farmer.getName(),
                "mobileNumber", farmer.getMobileNumber()
        );
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
