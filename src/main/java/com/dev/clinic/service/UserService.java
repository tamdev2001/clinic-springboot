package com.dev.clinic.service;

import java.util.List;

import com.dev.clinic.dto.UserDto;
import com.dev.clinic.model.User;

public interface UserService {
    
    List<UserDto> getAllUsers();

    UserDto getUserById(long id);

    User createUser(User user);

    User updateUser(long id, User user);

    Boolean deleteUser(long id);
}
