package com.dev.clinic.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dev.clinic.model.PrescriptionMedicine;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine, Long> {

    @Transactional
    @Modifying
    @Query("delete from PrescriptionMedicine p where p.id =:pmId")
    void deleteByPMId(@Param("pmId") long pmId);
}
