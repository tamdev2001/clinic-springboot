package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    
}
