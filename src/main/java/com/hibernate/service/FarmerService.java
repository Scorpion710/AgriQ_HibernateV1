package com.hibernate.service;

import org.springframework.stereotype.Service;

import com.hibernate.entity.Farmer;
import com.hibernate.repository.FarmerRepository;

import java.util.List;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Farmer registerFarmer(Farmer farmer) {
        if (farmerRepository.findByMobileNumber(farmer.getMobileNumber()).isPresent()) {
            throw new RuntimeException("A farmer with this mobile number already exists.");
        }

        return farmerRepository.save(farmer);
    }

    public Farmer getFarmerById(Long id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + id));
    }

    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    public Farmer loginFarmer(String mobileNumber, String pin) {
        Farmer farmer = farmerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("No farmer found with this mobile number."));

        if (!farmer.getPin().equals(pin)) {
            throw new RuntimeException("Incorrect PIN.");
        }

        return farmer;
    }
}
