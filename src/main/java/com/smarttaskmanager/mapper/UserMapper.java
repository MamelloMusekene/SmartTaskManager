package com.smarttaskmanager.mapper;

import com.smarttaskmanager.dto.RegisterRequest;
import com.smarttaskmanager.entity.User;
import com.smarttaskmanager.enums.Role;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(RegisterRequest request) {

        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .enabled(true)
                .build();
    }
}
