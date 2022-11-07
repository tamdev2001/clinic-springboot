package com.dev.clinic.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.Register;
import com.dev.clinic.model.User;
import com.dev.clinic.repository.RegisterRepository;
import com.dev.clinic.service.RegisterService;
import com.dev.clinic.service.UserService;
import com.dev.clinic.util.CommonMethod;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private UserService userService;

    @Override
    public Register creatRegister(Register register) {
        register.setVerified(false);
        register.setCreatedDate(new Date());

        String currentUsername = CommonMethod.getCurrentUsername();
        if (currentUsername != null) {
            User user = this.userService.getCurrentUser();
            register.setUser(user);
        }

        return this.registerRepository.save(register);
    }

    @Override
    public List<Register> getRegisters(String name, String phone, Boolean verified, String examinationTime,
            String createdDate) {

        List<Register> registers;

        if (!examinationTime.equals("")) {
            try {
                registers = this.registerRepository
                        .findByExaminationTime(new SimpleDateFormat("yyyy-MM-dd").parse(examinationTime));
            } catch (ParseException e) {
                registers = this.registerRepository.findByNameContaining(name);
            }
        } else if (!createdDate.equals("")) {
            try {
                registers = this.registerRepository
                        .findByCreatedDateOnlyDate(new SimpleDateFormat("yyyy-MM-dd").parse(createdDate));
            } catch (ParseException e) {
                registers = this.registerRepository.findByNameContaining(name);
            }
        } else {
            registers = this.registerRepository.findByNameContaining(name);
        }

        if (!phone.equals("")) {
            registers = registers.stream().filter(r -> r.getPhone().contains(phone)).collect(Collectors.toList());
        }

        if (verified != null) {
            registers = registers.stream().filter(r -> r.getVerified() == verified).collect(Collectors.toList());
        }

        if (registers.isEmpty()) {
            throw new NotFoundException("Register does not exist!");
        }

        return registers;
    }

    @Override
    public Register getRegisterById(long id) {
        Optional<Register> rOptional = this.registerRepository.findById(id);
        if (rOptional.isPresent()) {
            Register register = rOptional.get();
            return register;
        }
        throw new NotFoundException("Not found register");
    }

    @Override
    public Register verifiedRegister(long id) {
        Optional<Register> rOptional = this.registerRepository.findById(id);
        if (rOptional.isPresent()) {
            Register register = rOptional.get();
            register.setVerified(true);
            return this.registerRepository.save(register);
        }

        throw new NotFoundException("Not found register");
    }

    @Override
    public Boolean deleteRegister(long id) {
        if (this.registerRepository.existsById(id)) {
            this.registerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Register updateRegister(long id, Register register) {
        Optional<Register> rOptional = this.registerRepository.findById(id);
        User currentUser = this.userService.getCurrentUserOrNull();
        if (rOptional.isPresent()) {
            register.setId(id);
            register.setUser(currentUser);
            return this.registerRepository.save(register);
        }
        return null;
    }

}
