package com.dev.clinic.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.clinic.model.ReceiptPrescription;

public interface ReceiptPrescriptionRepository extends JpaRepository<ReceiptPrescription, Long> {

    boolean existsByPrescriptionId(long prescriptionId);

    @Transactional
    @Modifying
    @Query("delete from ReceiptPrescription r where r.id =:receiptId")
    void deleteByReceiptId(@Param("receiptId") long receiptId);
    
}
