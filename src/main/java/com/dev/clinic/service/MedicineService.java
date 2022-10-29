package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.Medicine;

public interface MedicineService {

    Medicine getMedicineById(long medicineId);

    List<Medicine> getMedicines(String name);

    Medicine createMedicine(Medicine medicine);

    Boolean deleteMedicine(long id);

    Medicine updateMedicine(long id, Medicine medicine);
}
