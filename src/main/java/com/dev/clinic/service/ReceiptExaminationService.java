package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.ReceiptExamination;

public interface ReceiptExaminationService {
    
    ReceiptExamination createReceiptExamination(ReceiptExamination receiptExamination, long registerId, String voucherCode);

    List<ReceiptExamination> getReceiptExaminations();

    ReceiptExamination updateReceiptExamination(long receiptId, ReceiptExamination receiptExamination, String voucherCode);

    Boolean deleteReceiptExamination(long receiptId);

    ReceiptExamination getReceiptById(long id);

}
