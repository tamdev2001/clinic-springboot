package com.dev.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Unit> findByNameContaining(String name);
}
