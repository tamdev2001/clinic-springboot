package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.model.Role;


public interface StatsService {
    List<Object[]> getStatsRevenueReMonthByYear(int year);

    List<Object[]> getStatsRevenueRpMonthByYear(int year);

    List<Object[]> getStatsRoles();

}
