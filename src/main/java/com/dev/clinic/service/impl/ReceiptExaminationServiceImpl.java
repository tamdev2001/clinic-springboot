package com.dev.clinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.BadRequestException;
import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.ReceiptExamination;
import com.dev.clinic.model.Register;
import com.dev.clinic.model.User;
import com.dev.clinic.repository.ReceiptExaminationRepository;
import com.dev.clinic.service.ReceiptExaminationService;
import com.dev.clinic.service.RegisterService;
import com.dev.clinic.service.UserService;

@Service
public class ReceiptExaminationServiceImpl implements ReceiptExaminationService {

    @Autowired
    private ReceiptExaminationRepository receiptExaminationRepository;

    @Autowired
    private RegisterService registerService;

    @Autowired 
    private UserService userService;

    @Override
    public ReceiptExamination createReceiptExamination(ReceiptExamination receiptExamination, long registerId) {
        if (this.receiptExaminationRepository.existsByRegisterId(registerId)) {
            throw new BadRequestException("The register has paid for examination");
        }

        User user = this.userService.getCurrentUser();
        Register register = this.registerService.getRegisterById(registerId);

        receiptExamination.setRegister(register);
        receiptExamination.setUser(user);
        receiptExamination.setPriceTotal(register.getRegulation().getExaminationPrice());

        return this.receiptExaminationRepository.save(receiptExamination);
    }

    @Override
    public List<ReceiptExamination> getReceiptExaminations() {
        List<ReceiptExamination> receiptExaminations = this.receiptExaminationRepository.findAll();

        if (receiptExaminations.isEmpty()) {
            throw new NotFoundException("Does not any ReceiptExaminations!");
        }

        return receiptExaminations;
    }
    
}
