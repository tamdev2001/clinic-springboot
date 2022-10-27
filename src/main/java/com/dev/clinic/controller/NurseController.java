package com.dev.clinic.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.model.Register;
import com.dev.clinic.service.EmailService;
import com.dev.clinic.service.RegisterService;
import com.dev.clinic.util.CommonMethod;

@CrossOrigin
@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/registers")
    public ResponseEntity<List<Register>> getRegisters(@RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String phone,
            @RequestParam(required = false, defaultValue = "") Boolean verified,
            @RequestParam(required = false, defaultValue = "") String examinationTime,
            @RequestParam(required = false, defaultValue = "") String createdDate) {
        List<Register> registers = registerService.getRegisters(name, phone, verified, examinationTime, createdDate);
        return ResponseEntity.ok(registers);
    }

    @PostMapping("/registers/{id}/verified")
    public ResponseEntity<Register> verifiedRegister(@PathVariable long id)
            throws AddressException, MessagingException, IOException {
        Register register = this.registerService.verifiedRegister(id);

        if (!register.getEmail().isEmpty()) {
            this.emailService.sendEmail(register.getEmail(), "Appointment at Clinica",
                    "Hello guys! You have made an appointment at Clinica on " + register.getExaminationTime()
                            + " please arrive on time!");
        }

        return ResponseEntity.ok(register);
    }

}
