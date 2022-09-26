package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
