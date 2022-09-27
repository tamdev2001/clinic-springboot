package com.dev.clinic.util;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.dev.clinic.model.User;
import com.dev.clinic.repository.UserRepository;

@Configuration
public class CommonBean {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // @Autowired
    // private UserRepository userRepository;

    // // @Bean("currentUser")
    // public User getCurrentUser() {
    //     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    //     String username;

    //     if (principal instanceof UserDetails) {
    //         username = ((UserDetails) principal).getUsername();
    //         if (username != null) {
    //             Optional<User> uOptional = userRepository.findByUsername(username);
    //             if (uOptional.isPresent()) {
    //                 return uOptional.get();
    //             }
    //         }

    //     }

    //     return null;
    // }
}
