package com.dev.clinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Regulation;

public interface RegulationRepository extends JpaRepository<Regulation, Long> {
}
