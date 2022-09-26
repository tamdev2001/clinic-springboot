package com.dev.clinic.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.model.Register;
import com.dev.clinic.repository.RegisterRepository;
import com.dev.clinic.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public Register creatRegister(Register register) {
        register.setVerified(false);
        register.setCreatedDate(new Date());
        return this.registerRepository.save(register);
    }

    @Override
    public List<Register> getAllRegisters() {
        return this.registerRepository.findAll();
    }

    @Override
    public Register getRegisterById(long id) {
        Optional<Register> rOptional = this.registerRepository.findById(id);
        if (rOptional.isPresent()) {
            Register register = rOptional.get();
            return register;
        }
        return null;
    }

    @Override
    public Register verifiedRegister(long id) {
        Optional<Register> rOptional = this.registerRepository.findById(id);
        if (rOptional.isPresent()) {
            Register register = rOptional.get();
            register.setVerified(true);
            return this.registerRepository.save(register);
        }

        return null;
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
        if (rOptional.isPresent()) {
            register.setId(id);
            return this.registerRepository.save(register);
        }
        return null;
    }

}
