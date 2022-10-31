package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.ReceiptPrescription;

public interface ReceiptPrescriptionService {

    ReceiptPrescription createReceiptPrescription(ReceiptPrescription receiptPrescription, long prescriptionId);

    List<ReceiptPrescription> getReceiptPrescriptions();

    
    ReceiptPrescription updateReceiptPrescription(long receiptId, ReceiptPrescription ReceiptPrescription);

    Boolean deleteReceiptPrescription(long receiptId);
    
    ReceiptPrescription getReceiptById(long id);

}
