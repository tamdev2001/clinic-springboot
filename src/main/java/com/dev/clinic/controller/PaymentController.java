package com.dev.clinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.model.ReceiptExamination;
import com.dev.clinic.model.ReceiptPrescription;
import com.dev.clinic.service.ReceiptExaminationService;
import com.dev.clinic.service.ReceiptPrescriptionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private ReceiptExaminationService receiptExaminationService;

    @Autowired
    private ReceiptPrescriptionService receiptPrescriptionService;

    @GetMapping("/receipt-examinations/{id}")
    public ResponseEntity<ReceiptExamination> getReceiptExaminationById(@PathVariable long id) {
        ReceiptExamination receipt = this.receiptExaminationService.getReceiptById(id);
        return ResponseEntity.ok(receipt);
    }

    @GetMapping("/receipt-prescriptions/{id}")
    public ResponseEntity<ReceiptPrescription> getReceiptPrescriptionById(@PathVariable long id) {
        ReceiptPrescription receipt = this.receiptPrescriptionService.getReceiptById(id);
        return ResponseEntity.ok(receipt);
    }
    
    @GetMapping("/receipt-examinations")
    public ResponseEntity<List<ReceiptExamination>> getReceiptExaminations() {
        List<ReceiptExamination> receiptExaminations = this.receiptExaminationService.getReceiptExaminations();

        return ResponseEntity.ok(receiptExaminations);
    }

    @PostMapping("/registers/{registerId}/receipt-examinations") // Requiring authentication to get current user
    public ResponseEntity<ReceiptExamination> createReceiptExamination(@PathVariable long registerId, @RequestBody ReceiptExamination receiptExamination) {
        ReceiptExamination newReceiptExamination = this.receiptExaminationService.createReceiptExamination(receiptExamination, registerId);

        return ResponseEntity.status(HttpStatus.CREATED).body(newReceiptExamination);
    }

    @GetMapping("/receipt-prescriptions")
    public ResponseEntity<List<ReceiptPrescription>> getReceiptPrescriptions() {
        List<ReceiptPrescription> receiptPrescriptions = this.receiptPrescriptionService.getReceiptPrescriptions();

        return ResponseEntity.status(HttpStatus.CREATED).body(receiptPrescriptions);
    }

    @PostMapping("/prescriptions/{prescriptionId}/receipt-prescriptions") // Requiring authentication to get current user
    public ResponseEntity<ReceiptPrescription> createReceiptPrescription(@PathVariable long prescriptionId, @RequestBody ReceiptPrescription receiptPrescription) {
        ReceiptPrescription newReceiptPrescription = this.receiptPrescriptionService.createReceiptPrescription(receiptPrescription, prescriptionId);

        return ResponseEntity.ok(newReceiptPrescription);
    }

    @DeleteMapping("/receipt-examinations/{id}")
    public ResponseEntity<Boolean> deleteReceiptExamination(@PathVariable long id) {
        return ResponseEntity.ok(this.receiptExaminationService.deleteReceiptExamination(id));
    }

    @PutMapping("/receipt-examinations/{id}")
    public ResponseEntity<ReceiptExamination> updateReceiptExamination(@PathVariable long id, @RequestBody ReceiptExamination receipt) {
        ReceiptExamination newReceipt = this.receiptExaminationService.updateReceiptExamination(id, receipt);

        return ResponseEntity.ok(newReceipt);
    }

    @DeleteMapping("/receipt-prescriptions/{id}")
    public ResponseEntity<Boolean> deleteReceiptPrescription(@PathVariable long id) {
        return ResponseEntity.ok(this.receiptPrescriptionService.deleteReceiptPrescription(id));
    }

    @PutMapping("/receipt-prescriptions/{id}")
    public ResponseEntity<ReceiptPrescription> updateReceiptPrescription(@PathVariable long id, @RequestBody ReceiptPrescription receipt) {
        ReceiptPrescription newReceipt = this.receiptPrescriptionService.updateReceiptPrescription(id, receipt);

        return ResponseEntity.ok(newReceipt);
    }
    
}
