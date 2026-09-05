package com.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, length = 12)
    private String aadhaarNumber;

    private String mobileNumber;

    @Column(nullable = false)
    private String pin;

    public Farmer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    private String registeredCrops;

    private String village;

    private String procurementCentre;

    public String getRegisteredCrops() {
        return registeredCrops;
    }

    public void setRegisteredCrops(String registeredCrops) {
        this.registeredCrops = registeredCrops;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getProcurementCentre() {
        return procurementCentre;
    }

    public void setProcurementCentre(String procurementCentre) {
        this.procurementCentre = procurementCentre;
    }
}
