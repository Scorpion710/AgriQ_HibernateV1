package com.hibernate.controller;

import com.hibernate.entity.Procurement;
import com.hibernate.service.ProcurementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/procurements")
public class ProcurementController {

    private final ProcurementService procurementService;

    public ProcurementController(ProcurementService procurementService) {
        this.procurementService = procurementService;
    }

    @PostMapping
    public Procurement recordProcurement(@RequestBody Procurement procurement) {
        return procurementService.recordProcurement(procurement);
    }

    @GetMapping("/farmer/{farmerId}")
    public List<Procurement> getFarmerProcurements(@PathVariable Long farmerId) {
        return procurementService.getFarmerProcurements(farmerId);
    }

    @GetMapping
    public List<Procurement> getAllProcurements() {
        return procurementService.getAllProcurements();
    }
}
