package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.ReceiptPrescription;

public interface ReceiptPrescriptionService {

    ReceiptPrescription createReceiptPrescription(ReceiptPrescription receiptPrescription, long prescriptionId,
            String voucherCode);

    List<ReceiptPrescription> getReceiptPrescriptions();

    ReceiptPrescription updateReceiptPrescription(long receiptId, ReceiptPrescription ReceiptPrescription,
            String voucherCode);

    Boolean deleteReceiptPrescription(long receiptId);

    ReceiptPrescription getReceiptById(long id);

}
