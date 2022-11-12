package com.dev.clinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.response.ModelResponse;
import com.dev.clinic.service.StatsService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping(value = "/stats-roles")
    public ResponseEntity<ModelResponse> getStatsRoles(){
        String ms = "Get stats revenue successfully";
        String code = "200";
        List<Object[]> list = null;
        HttpStatus status = HttpStatus.OK;
        try {
            list = this.statsService.getStatsRoles();
        }catch (Exception ex){
            ms = ex.getMessage();
            code = "400";
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(
                new ModelResponse(code,ms,list)
        );
    }

    @GetMapping(value = "/stats-revenue-re-month-by-year/{year}")
    public ResponseEntity<ModelResponse> getStatsRevenueReMonthByYear(@PathVariable(value = "year") String year){
        String ms = "Get stats revenue successfully";
        String code = "200";
        List<Object[]> list = null;
        HttpStatus status = HttpStatus.OK;
        try {
            list = this.statsService.getStatsRevenueReMonthByYear(Integer.parseInt(year));
        }catch (Exception ex){
            ms = ex.getMessage();
            code = "400";
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(
                new ModelResponse(code,ms,list)
        );
    }

    @GetMapping(value = "/stats-revenue-rp-month-by-year/{year}")
    public ResponseEntity<ModelResponse> getStatsRevenueRpMonthByYear(@PathVariable(value = "year") String year){
        String ms = "Get stats revenue successfully";
        String code = "200";
        List<Object[]> list = null;
        HttpStatus status = HttpStatus.OK;
        try {
            list = this.statsService.getStatsRevenueRpMonthByYear(Integer.parseInt(year));
        }catch (Exception ex){
            ms = ex.getMessage();
            code = "400";
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(
                new ModelResponse(code,ms,list)
        );
    }
}
