package com.dev.clinic.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.clinic.model.ReceiptExamination;

public interface ReceiptExaminationRepository extends JpaRepository<ReceiptExamination, Long> {

    boolean existsByRegisterId(long registerId);

    @Transactional
    @Modifying
    @Query("delete from ReceiptExamination r where r.id =:receiptId")
    void deleteByReceiptId(@Param("receiptId") long receiptId);

}
