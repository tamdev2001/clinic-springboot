package com.dev.clinic.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.clinic.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    
    List<Prescription> findByCertificateId(long certificateId);

    @Transactional
    @Modifying
    @Query("delete from Prescription p where p.id =:preId")
    void deleteByPreId(@Param("preId") long preId);
} 
