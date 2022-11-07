package com.dev.clinic.service;

import java.util.List;


public interface StatsService {
    List<Object[]> getStatsRevenueReMonthByYear(int year);

    List<Object[]> getStatsRevenueRpMonthByYear(int year);

}
