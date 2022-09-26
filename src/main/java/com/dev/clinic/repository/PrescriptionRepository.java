package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    
}
