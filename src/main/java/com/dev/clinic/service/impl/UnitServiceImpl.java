package com.dev.clinic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.Unit;
import com.dev.clinic.repository.UnitRepository;
import com.dev.clinic.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService{
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Unit> getUnits(String name) {
        List<Unit> units = this.unitRepository.findByNameContaining(name);
        if (units.isEmpty()) {
            throw new NotFoundException("Unit does not exist!");
        }
        return units;
    }

    @Override
    public Unit createUnit(Unit unit) {
        Unit newUnit = this.unitRepository.save(unit);

        return newUnit;
    }

    @Override
    public Boolean deleteUnit(int id) {
        if (this.unitRepository.existsById(id)) {
            this.unitRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Unit updateUnit(int id, Unit unit) {
        Optional<Unit> cOptional = this.unitRepository.findById(id);
        if (cOptional.isPresent()) {
            Unit existedUnit = cOptional.get();
            unit.setId(existedUnit.getId());

            Unit updatedUnit = this.unitRepository.save(unit);

            return updatedUnit;
        }

        throw new NotFoundException("Unit does not exist!");
    }
    
}
