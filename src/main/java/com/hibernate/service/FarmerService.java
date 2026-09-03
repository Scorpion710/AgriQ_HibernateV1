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
