package com.dev.clinic.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.exception.NotFoundException;
import com.dev.clinic.model.Regulation;
import com.dev.clinic.repository.RegulationRepository;
import com.dev.clinic.service.RegulationService;

@Service
public class RegulationServiceImpl implements RegulationService {
    @Autowired
    private RegulationRepository regulationRepository;

    @Override
    public List<Regulation> getRegulations() {
        List<Regulation> regulations = this.regulationRepository.findAll();

        return regulations;
    }

    @Override
    public Regulation createRegulation(Regulation regulation) {
        regulation.setCreatedDate(new Date());
        if (regulation.getActive() == true) {
            List<Regulation> regulations = getRegulations();
            for (Regulation item : regulations) {
                item.setActive(false);
            }

        }
        Regulation newRegulation = this.regulationRepository.save(regulation);

        return newRegulation;
    }

    @Override
    public Boolean deleteRegulation(Long id) {
        if (this.regulationRepository.existsById(id)) {
            Optional<Regulation> regulation = this.regulationRepository.findById(id);

            if (regulation.get().getActive() == true) {
                List<Regulation> regulations = getRegulations();
                regulations.get(0).setActive(true);
            }
            this.regulationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Regulation updateRegulation(Long id, Regulation regulation) {
        Optional<Regulation> cOptional = this.regulationRepository.findById(id);
        if (regulation.getActive() == true) {
            List<Regulation> regulations = getRegulations();
            for (Regulation item : regulations) {
                item.setActive(false);
            }
        }
        if (cOptional.isPresent()) {
            Regulation existedRegulation = cOptional.get();
            regulation.setId(existedRegulation.getId());
            Regulation updatedRegulation = this.regulationRepository.save(regulation);

            return updatedRegulation;
        }

        throw new NotFoundException("Regulation does not exist!");
    }

    @Override
    public Regulation getRegulationById(long id) {
        Optional<Regulation> rOptional = this.regulationRepository.findById(id);
        if (rOptional.isPresent()) {
            return rOptional.get();
        }
        throw new NotFoundException("Regulation does not exist!");
    }

}
