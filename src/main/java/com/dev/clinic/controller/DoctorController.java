package com.dev.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.model.Certificate;
import com.dev.clinic.service.CertificateService;
import com.dev.clinic.service.RegisterService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private RegisterService registerService;

    //#region certificate
    @PostMapping("/registers/{registerId}/certificates")
    public ResponseEntity<Certificate> createCertificate(@PathVariable long registerId,
            @RequestBody Certificate certificate) {
        Certificate newCertificate = this.certificateService.createCertificate(registerId, certificate);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCertificate);
    }

    @PutMapping("certificates/{certificateId}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable long certificateId,
            @RequestBody Certificate certificate) {
        Certificate updatedCertificate = this.certificateService.updateCertificate(certificateId, certificate);

        return ResponseEntity.ok(updatedCertificate);
    }

    @DeleteMapping("certificates/{certificateId}")
    public ResponseEntity<Boolean> deleteCertificate(@PathVariable long certificateId) {
        boolean result = this.certificateService.deleteCertifcate(certificateId);
        return ResponseEntity.ok(result);
    }
    //#endregion
    
    //#region precription
    
    //#endregion
}
