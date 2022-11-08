package com.dev.clinic.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.BadRequestException;
import com.dev.clinic.exception.InternalException;
import com.dev.clinic.exception.BadRequestException;
import com.dev.clinic.exception.InternalException;
import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.ReceiptExamination;
import com.dev.clinic.model.Register;
import com.dev.clinic.model.User;
import com.dev.clinic.model.Voucher;
import com.dev.clinic.repository.ReceiptExaminationRepository;
import com.dev.clinic.repository.VoucherRepository;
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

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public ReceiptExamination createReceiptExamination(ReceiptExamination receiptExamination, long registerId,
            String voucherCode) {
        if (this.receiptExaminationRepository.existsByRegisterId(registerId)) {
            throw new BadRequestException("The register has paid for examination");
        }

        User user = this.userService.getCurrentUser();
        Register register = this.registerService.getRegisterById(registerId);

        receiptExamination.setRegister(register);
        receiptExamination.setUser(user);
        receiptExamination.setPriceTotal(register.getRegulation().getExaminationPrice());

        if (voucherCode.equals("")) {
            receiptExamination.setPriceTotal(register.getRegulation().getExaminationPrice());
        } else {
            Optional<Voucher> vOptional = this.voucherRepository.findById(voucherCode);
            if (vOptional.isPresent()) {
                Voucher voucher = vOptional.get();
                receiptExamination.setVoucher(voucher);

                if (voucher.getPercentReduction() != null) {
                    receiptExamination.setPriceTotal(
                            register.getRegulation().getExaminationPrice() * voucher.getPercentReduction() / 100);
                } else if (voucher.getReducedPrice() != null) {
                    receiptExamination
                            .setPriceTotal(register.getRegulation().getExaminationPrice() - voucher.getReducedPrice());
                } else {
                    receiptExamination.setPriceTotal(register.getRegulation().getExaminationPrice());
                }
            } else {
                receiptExamination.setPriceTotal(register.getRegulation().getExaminationPrice());
            }
        }

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

    @Override
    public ReceiptExamination updateReceiptExamination(long receiptId, ReceiptExamination receipt, String voucherCode) {
        User user = this.userService.getCurrentUser();
        Optional<ReceiptExamination> rOptional = this.receiptExaminationRepository.findById(receiptId);

        if (rOptional.isPresent()) {
            ReceiptExamination receiptExamination = rOptional.get();

            receiptExamination.setIsPayment(receipt.getIsPayment());
            receiptExamination.setUser(user);
            if (voucherCode.equals("")) {
                receiptExamination
                        .setPriceTotal(receiptExamination.getRegister().getRegulation().getExaminationPrice());
            } else {
                Optional<Voucher> vOptional = this.voucherRepository.findById(voucherCode);
                if (vOptional.isPresent()) {
                    Voucher voucher = vOptional.get();
                    receiptExamination.setVoucher(voucher);

                    if (voucher.getPercentReduction() != null) {
                        receiptExamination
                                .setPriceTotal(receiptExamination.getRegister().getRegulation().getExaminationPrice()
                                        * voucher.getPercentReduction() / 100);
                    } else if (voucher.getReducedPrice() != null) {
                        receiptExamination
                                .setPriceTotal(receiptExamination.getRegister().getRegulation().getExaminationPrice()
                                        - voucher.getReducedPrice());
                    } else {
                        receiptExamination
                                .setPriceTotal(receiptExamination.getRegister().getRegulation().getExaminationPrice());
                    }
                } else {
                    receiptExamination
                            .setPriceTotal(receiptExamination.getRegister().getRegulation().getExaminationPrice());
                }
            }

            return this.receiptExaminationRepository.save(receiptExamination);
        }

        throw new NotFoundException("ReceiptExamination does not exist!");
    }

    @Override
    public Boolean deleteReceiptExamination(long receiptId) {
        if (this.receiptExaminationRepository.existsById(receiptId)) {
            try {
                this.receiptExaminationRepository.deleteByReceiptId(receiptId);
            } catch (Exception e) {
                throw new InternalException("Could not delete receipt!");
            }
            return true;
        }
        throw new NotFoundException("Receipt does not exist!");
    }

    @Override
    public ReceiptExamination getReceiptById(long id) {
        Optional<ReceiptExamination> rOptional = this.receiptExaminationRepository.findById(id);
        if (rOptional.isPresent()) {
            return rOptional.get();
        }

        throw new NotFoundException("Receipt does not exist!");
    }

}
