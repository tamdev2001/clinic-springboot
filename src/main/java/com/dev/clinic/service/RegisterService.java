package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.Register;

public interface RegisterService {

    Register creatRegister(Register register);

    List<Register> getRegisters(String name, String phone, Boolean verified, String examinationTime, String createdDate);

    Register getRegisterById(long id);

    Register verifiedRegister(long id);

    Boolean deleteRegister(long id);

    Register updateRegister(long id, Register register);
}
