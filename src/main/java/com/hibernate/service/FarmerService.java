package com.hibernate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hibernate.entity.Farmer;
import com.hibernate.repository.FarmerRepository;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Farmer registerFarmer(Farmer farmer) {
        if (farmer.getName() == null || farmer.getName().isBlank()) {
            throw new RuntimeException("Farmer name is required.");
        }
        if (farmer.getAadhaarNumber() == null || !farmer.getAadhaarNumber().trim().matches("^[0-9]{12}$")) {
            throw new RuntimeException("Please enter a valid 12-digit Aadhaar number.");
        }
        if (farmer.getMobileNumber() == null || !farmer.getMobileNumber().trim().matches("^[0-9]{10}$")) {
            throw new RuntimeException("Please enter a valid 10-digit mobile number.");
        }
        if (farmer.getPin() == null || !farmer.getPin().trim().matches("^[0-9]{4}$")) {
            throw new RuntimeException("PIN must be a 4-digit numeric code.");
        }

        farmer.setName(farmer.getName().trim());
        farmer.setAadhaarNumber(farmer.getAadhaarNumber().trim());
        farmer.setMobileNumber(farmer.getMobileNumber().trim());
        farmer.setPin(farmer.getPin().trim());

        if (farmerRepository.findByAadhaarNumber(farmer.getAadhaarNumber()).isPresent()) {
            throw new RuntimeException("A farmer with this Aadhaar number already exists.");
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

    public Farmer loginFarmer(String aadhaarNumber, String pin) {
        if (aadhaarNumber == null || !aadhaarNumber.trim().matches("^[0-9]{12}$")) {
            throw new RuntimeException("Please enter a valid 12-digit Aadhaar number.");
        }

        Farmer farmer = farmerRepository.findByAadhaarNumber(aadhaarNumber.trim())
                .orElseThrow(() -> new RuntimeException("No farmer found with this Aadhaar number."));

        if (!farmer.getPin().equals(pin)) {
            throw new RuntimeException("Incorrect PIN.");
        }

        return farmer;
    }

    public Farmer updateFarmerProfile(Long id, Farmer updatedData) {
        Farmer farmer = getFarmerById(id);
        if (updatedData.getName() != null && !updatedData.getName().isBlank()) {
            farmer.setName(updatedData.getName().trim());
        }
        if (updatedData.getMobileNumber() != null && !updatedData.getMobileNumber().isBlank()) {
            farmer.setMobileNumber(updatedData.getMobileNumber().trim());
        }
        if (updatedData.getRegisteredCrops() != null) {
            farmer.setRegisteredCrops(updatedData.getRegisteredCrops().trim());
        }
        if (updatedData.getVillage() != null) {
            farmer.setVillage(updatedData.getVillage().trim());
        }
        if (updatedData.getProcurementCentre() != null) {
            farmer.setProcurementCentre(updatedData.getProcurementCentre().trim());
        }
        return farmerRepository.save(farmer);
    }
}
