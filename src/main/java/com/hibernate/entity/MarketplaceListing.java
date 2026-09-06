package com.hibernate.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "marketplace_listings")
public class MarketplaceListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long farmerId;

    private String farmerName;

    private String farmerMobile;

    @Column(nullable = false)
    private String cropName;

    private String varietyGrade;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal quantityKg;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal pricePerKg;

    @Column(precision = 12, scale = 2)
    private BigDecimal minOrderQuantityKg;

    private String expectedAvailabilityDate;

    private String location;

    @Column(length = 1000)
    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String imageUrl;

    @Column(nullable = false)
    private String status = "AVAILABLE"; // AVAILABLE, SOLD, INACTIVE

    private String createdAt;

    public MarketplaceListing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmerMobile() {
        return farmerMobile;
    }

    public void setFarmerMobile(String farmerMobile) {
        this.farmerMobile = farmerMobile;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getVarietyGrade() {
        return varietyGrade;
    }

    public void setVarietyGrade(String varietyGrade) {
        this.varietyGrade = varietyGrade;
    }

    public BigDecimal getQuantityKg() {
        return quantityKg;
    }

    public void setQuantityKg(BigDecimal quantityKg) {
        this.quantityKg = quantityKg;
    }

    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public BigDecimal getMinOrderQuantityKg() {
        return minOrderQuantityKg;
    }

    public void setMinOrderQuantityKg(BigDecimal minOrderQuantityKg) {
        this.minOrderQuantityKg = minOrderQuantityKg;
    }

    public String getExpectedAvailabilityDate() {
        return expectedAvailabilityDate;
    }

    public void setExpectedAvailabilityDate(String expectedAvailabilityDate) {
        this.expectedAvailabilityDate = expectedAvailabilityDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
