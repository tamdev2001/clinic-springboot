package com.dev.clinic.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.model.Certificate;
import com.dev.clinic.model.Prescription;
import com.dev.clinic.model.PrescriptionMedicine;
import com.dev.clinic.model.Specialty;
import com.dev.clinic.service.CertificateService;
import com.dev.clinic.service.PrescriptionService;
import com.dev.clinic.service.SpecialtyService;

@CrossOrigin
@RestController
@RequestMapping("/api/commons")
public class CommonController {

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private CertificateService certificateService;
    
    @GetMapping("/specialties")
    public ResponseEntity<List<Specialty>> getSpecialties(@RequestParam(required = false, defaultValue = "") String name) {
        List<Specialty> specialties = this.specialtyService.getSpecialties(name);

        return ResponseEntity.ok(specialties);
    }

    @GetMapping("/certificates/{certificateId}/prescriptions")
    public ResponseEntity<List<Prescription>> getPrescriptionsByCertificateId(@PathVariable long certificateId) {
        List<Prescription> prescriptions = this.prescriptionService.getPrescriptionsByCertificateId(certificateId);

        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/prescriptions/{prescriptionId}/details")
    public ResponseEntity<?> getPrescriptionDetailsByPrescriptionId(@PathVariable long prescriptionId) {
        List<PrescriptionMedicine> pds = this.prescriptionService.getPrescriptionDetails(prescriptionId);

        return ResponseEntity.ok(pds);
    }

    @GetMapping("/registers/{registerId}/certificates")
    public ResponseEntity<Set<Certificate>> getCertificatesByRegisterId(@PathVariable long registerId) {
        Set<Certificate> certificates = this.certificateService.getCertificatesByRegisterId(registerId);

        return ResponseEntity.ok(certificates);
    }

}
