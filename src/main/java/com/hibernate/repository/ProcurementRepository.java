package com.hibernate.repository;

import com.hibernate.entity.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcurementRepository extends JpaRepository<Procurement, Long> {

    List<Procurement> findByFarmerIdOrderByIdDesc(Long farmerId);
}
