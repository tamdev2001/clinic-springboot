package com.dev.clinic.repository;

import java.util.List;

import com.dev.clinic.model.Role;

public interface StatsRepository{
    List<Object[]> getStatsRevenueReMonthByYear(int year);

    List<Object[]> getStatsRevenueRpMonthByYear(int year);

    List<Object[]> getStatsRoles();
    

}
