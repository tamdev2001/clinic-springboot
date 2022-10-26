package com.dev.clinic.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.clinic.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    List<Register> findByNameOrPhoneContaining(String name, String phone);

    List<Register> findByNameContaining(String name);

    List<Register> findByPhoneContaining(String phone);

    List<Register> findByExaminationTime(Date examinationTime);

    List<Register> findByCreatedDate(Date createdDate);
    
    @Transactional
    @Modifying
    @Query("select r from Register r where r.createdDate <= :creationDateTime AND r.createdDate >= :creationDateTime")
    List<Register> findByCreatedDateOnlyDate(@Param("creationDateTime") Date creationDateTime);
}
