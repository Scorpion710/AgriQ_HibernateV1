package com.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByFarmerIdOrderByIdDesc(Long farmerId);

    java.util.Optional<Payment> findByProcurementId(Long procurementId);
}
