package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
