package com.hibernate.repository;

import com.hibernate.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByFarmerIdOrderByIdDesc(Long farmerId);
}
