package com.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.entity.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    Optional<Farmer> findByMobileNumber(String mobileNumber);
}
