package com.dev.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicApplication.class, args);
    }

}
