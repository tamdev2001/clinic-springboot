package com.dev.clinic.repository;

import java.util.List;

public interface StatsRepository{
    List<Object[]> getStatsRevenueReMonthByYear(int year);

    List<Object[]> getStatsRevenueRpMonthByYear(int year);

}
