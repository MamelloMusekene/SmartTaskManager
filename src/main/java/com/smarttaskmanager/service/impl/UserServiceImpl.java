package com.smarttaskmanager.service.impl;

import com.smarttaskmanager.dto.RegisterRequest;
import com.smarttaskmanager.entity.User;
import com.smarttaskmanager.exception.EmailAlreadyExistsException;
import com.smarttaskmanager.mapper.UserMapper;
import com.smarttaskmanager.repository.UserRepository;
import com.smarttaskmanager.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository,
                           PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "An account with this email already exists."
            );
        }

        User user = UserMapper.toEntity(request);

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repository.save(user);
    }
}