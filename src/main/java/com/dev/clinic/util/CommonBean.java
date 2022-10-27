package com.dev.clinic.util;

import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class CommonBean {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
