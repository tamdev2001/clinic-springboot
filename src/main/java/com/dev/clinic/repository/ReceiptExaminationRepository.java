package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.ReceiptExamination;

public interface ReceiptExaminationRepository extends JpaRepository<ReceiptExamination, Long> {

}
