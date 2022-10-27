package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.ReceiptExamination;

public interface ReceiptExaminationService {
    
    ReceiptExamination createReceiptExamination(ReceiptExamination receiptExamination, long registerId);

    List<ReceiptExamination> getReceiptExaminations();

}
