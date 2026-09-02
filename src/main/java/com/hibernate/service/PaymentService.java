package com.hibernate.service;

import com.hibernate.entity.Payment;
import com.hibernate.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        payment.setStatus(normalizeStatus(payment.getStatus()));
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment record not found with ID: " + id));
        payment.setStatus(normalizeStatus(status));
        return paymentRepository.save(payment);
    }

    public List<Payment> getFarmerPayments(Long farmerId) {
        return paymentRepository.findByFarmerIdOrderByIdDesc(farmerId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    private String normalizeStatus(String status) {
        if (status == null || status.isBlank()) {
            return "PENDING";
        }

        String normalizedStatus = status.trim().toUpperCase();
        if (!normalizedStatus.equals("PENDING")
                && !normalizedStatus.equals("PROCESSING")
                && !normalizedStatus.equals("PAID")) {
            throw new RuntimeException("Payment status must be PENDING, PROCESSING, or PAID.");
        }
        return normalizedStatus;
    }
}
