package com.hibernate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hibernate.entity.Procurement;
import com.hibernate.repository.ProcurementRepository;

@Service
public class ProcurementService {

    private final ProcurementRepository procurementRepository;
    private final com.hibernate.repository.PaymentRepository paymentRepository;

    public ProcurementService(ProcurementRepository procurementRepository,
                              com.hibernate.repository.PaymentRepository paymentRepository) {
        this.procurementRepository = procurementRepository;
        this.paymentRepository = paymentRepository;
    }

    public Procurement recordProcurement(Procurement procurement) {
        procurement.setTotalAmount(procurement.getQuantityKg().multiply(procurement.getMspPerKg()));
        Procurement saved = procurementRepository.save(procurement);

        // Auto-create matching payment record
        com.hibernate.entity.Payment payment = new com.hibernate.entity.Payment();
        payment.setFarmerId(saved.getFarmerId());
        payment.setProcurementId(saved.getId());
        payment.setAmount(saved.getTotalAmount());
        payment.setStatus("PROCESSING");
        payment.setPaymentDate(saved.getProcurementDate());
        payment.setTransactionReference("TXN-" + saved.getId() + "-" + (System.currentTimeMillis() % 1000000));
        paymentRepository.save(payment);

        return saved;
    }

    public List<Procurement> getFarmerProcurements(Long farmerId) {
        return procurementRepository.findByFarmerIdOrderByIdDesc(farmerId);
    }

    public List<Procurement> getAllProcurements() {
        return procurementRepository.findAll();
    }
}
