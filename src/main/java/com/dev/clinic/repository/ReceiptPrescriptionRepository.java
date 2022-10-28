package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.ReceiptPrescription;

public interface ReceiptPrescriptionRepository extends JpaRepository<ReceiptPrescription, Long> {

    boolean existsByPrescriptionId(long prescriptionId);
    
}
