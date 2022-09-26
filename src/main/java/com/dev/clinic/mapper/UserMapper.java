package com.dev.clinic.mapper;

import com.dev.clinic.dto.UserDto;
import com.dev.clinic.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setPhone(user.getPhone());
        userDto.setSex(user.getSex());
        userDto.setUsername(user.getUsername());
        userDto.setAvatar(user.getAvatar());

        return userDto;
    }
}
