package com.dev.clinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.Prescription;
import com.dev.clinic.model.ReceiptPrescription;
import com.dev.clinic.model.User;
import com.dev.clinic.repository.ReceiptPrescriptionRepository;
import com.dev.clinic.service.PrescriptionService;
import com.dev.clinic.service.ReceiptPrescriptionService;
import com.dev.clinic.service.UserService;

@Service
public class ReceiptPrescriptionServiceImpl implements ReceiptPrescriptionService {
    
    @Autowired
    private ReceiptPrescriptionRepository receiptPrescriptionRepository;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired 
    private UserService userService;

    @Override
    public ReceiptPrescription createReceiptPrescription(ReceiptPrescription receiptPrescription, long prescriptionId) {
        Prescription prescription = this.prescriptionService.getPrescriptionById(prescriptionId);
        User user = this.userService.getCurrentUser();

        receiptPrescription.setPrescription(prescription);
        receiptPrescription.setUser(user);

        return this.receiptPrescriptionRepository.save(receiptPrescription);
    }

    @Override
    public List<ReceiptPrescription> getReceiptPrescriptions() {
        List<ReceiptPrescription> receiptPrescriptions = this.receiptPrescriptionRepository.findAll();

        if (receiptPrescriptions.isEmpty()) {
            throw new NotFoundException("Does not have any ReceiptPrescription!");
        }

        return receiptPrescriptions;
    }
    
}
