package com.dev.clinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.repository.StatsRepository;
import com.dev.clinic.service.StatsService;
import com.dev.clinic.util.Utils;

@Service
public class StatsServiceImpl implements StatsService{
    @Autowired
    private StatsRepository statsRepository;
    @Autowired
    private Utils utils;

    @Override
    public List<Object[]> getStatsRevenueReMonthByYear(int year) throws RuntimeException {
        try {
            return this.utils.customListStatsMonth(this.statsRepository.getStatsRevenueReMonthByYear(year));
        } catch (Exception ex) {
            String error_ms = ex.getMessage();
            throw new RuntimeException(error_ms);
        }
    }

    @Override
    public List<Object[]> getStatsRevenueRpMonthByYear(int year) throws RuntimeException {
        try {
            return this.utils.customListStatsMonth(this.statsRepository.getStatsRevenueRpMonthByYear(year));
        } catch (Exception ex) {
            String error_ms = ex.getMessage();
            throw new RuntimeException(error_ms);
        }
    }
    
}
