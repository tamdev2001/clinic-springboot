package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    
}
