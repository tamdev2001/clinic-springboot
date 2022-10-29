package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.Regulation;

public interface RegulationService {

    List<Regulation> getRegulations();

    Regulation createRegulation(Regulation regulation);

    Boolean deleteRegulation(Long id);

    Regulation updateRegulation(Long id, Regulation regulation);
}
