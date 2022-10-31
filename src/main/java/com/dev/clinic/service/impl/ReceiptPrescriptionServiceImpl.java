package com.dev.clinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.BadRequestException;
import com.dev.clinic.exception.InternalException;
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
        if (this.receiptPrescriptionRepository.existsByPrescriptionId(prescriptionId)) {
            throw new BadRequestException("Preciption has paid!");
        }

        Prescription prescription = this.prescriptionService.getPrescriptionById(prescriptionId);
        User user = this.userService.getCurrentUser();
        double totalPrice = this.prescriptionService.totalMedicinePriceOfPrescription(prescriptionId);

        receiptPrescription.setPrescription(prescription);
        receiptPrescription.setUser(user);
        receiptPrescription.setPriceTotal(totalPrice);

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

    @Override
    public ReceiptPrescription updateReceiptPrescription(long receiptId, ReceiptPrescription receipt) {
        User user = this.userService.getCurrentUser();
        Optional<ReceiptPrescription> rOptional = this.receiptPrescriptionRepository.findById(receiptId);

        if (rOptional.isPresent()) {
            ReceiptPrescription ReceiptPrescription = rOptional.get();

            ReceiptPrescription.setIsPayment(receipt.getIsPayment());
            ReceiptPrescription.setPriceTotal(receipt.getPriceTotal());
            ReceiptPrescription.setUser(user);

            return this.receiptPrescriptionRepository.save(ReceiptPrescription);
        }

        throw new NotFoundException("ReceiptPrescription does not exist!");
    }

    @Override
    public Boolean deleteReceiptPrescription(long receiptId) {
        if (this.receiptPrescriptionRepository.existsById(receiptId)) {
            try {
                this.receiptPrescriptionRepository.deleteByReceiptId(receiptId);
                return true;
            } catch (Exception e) {
                throw new InternalException("Could not delete receipt!");
            }
            
        }

        throw new NotFoundException("Receipt does not exists!");
    }

    @Override
    public ReceiptPrescription getReceiptById(long id) {
        Optional<ReceiptPrescription> rOptional = this.receiptPrescriptionRepository.findById(id);
        if (rOptional.isPresent()) {
            return rOptional.get();
        }
        
        throw new NotFoundException("Receipt does not exist!");
    }

}
