package com.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.entity.ProcurementCentre;

public interface ProcurementCentreRepository extends JpaRepository<ProcurementCentre, Long> {
    Optional<ProcurementCentre> findByName(String name);
}
