package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    
}
