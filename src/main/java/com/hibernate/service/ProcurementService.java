package com.hibernate.service;

import com.hibernate.entity.Procurement;
import com.hibernate.repository.ProcurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcurementService {

    private final ProcurementRepository procurementRepository;

    public ProcurementService(ProcurementRepository procurementRepository) {
        this.procurementRepository = procurementRepository;
    }

    public Procurement recordProcurement(Procurement procurement) {
        procurement.setTotalAmount(procurement.getQuantityKg().multiply(procurement.getMspPerKg()));
        return procurementRepository.save(procurement);
    }

    public List<Procurement> getFarmerProcurements(Long farmerId) {
        return procurementRepository.findByFarmerIdOrderByIdDesc(farmerId);
    }

    public List<Procurement> getAllProcurements() {
        return procurementRepository.findAll();
    }
}
