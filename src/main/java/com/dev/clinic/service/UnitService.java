package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.Unit;

public interface UnitService {
    List<Unit> getUnits(String name);

    Unit createUnit(Unit unit);

    Boolean deleteUnit(int id);

    Unit updateUnit(int id, Unit unit);
}
