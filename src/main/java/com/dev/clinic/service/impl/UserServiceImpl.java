package com.dev.clinic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.clinic.dto.UserDto;
import com.dev.clinic.model.User;
import com.dev.clinic.repository.UserRepository;
import com.dev.clinic.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (User user : users) {
            userDtos.add(mapper.map(user, UserDto.class));
        }
        
        return userDtos;
    }

    @Override
    public UserDto getUserById(long id) {
        Optional<User> uOptional = this.userRepository.findById(id);
        
        if (uOptional.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            User user = uOptional.get();

            return mapper.map(user, UserDto.class);
        }

        return null;
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(long id, User user) {
        Optional<User> uOptional = this.userRepository.findById(id);
        if (uOptional.isPresent()) {
            user.setId(id);
            return this.userRepository.save(user);
        }

        return null;
    }

    @Override
    public Boolean deleteUser(long id) {
        Optional<User> uOptional = this.userRepository.findById(id);
        if (uOptional.isPresent()) {
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
