package com.dev.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.clinic.model.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, String> {
    
}
