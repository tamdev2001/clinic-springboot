package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Regulation;

public interface RegulationRepository extends JpaRepository<Regulation, Long> {
}
