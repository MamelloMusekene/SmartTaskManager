package com.smarttaskmanager.service;

import com.smarttaskmanager.dto.RegisterRequest;
import com.smarttaskmanager.entity.User;

public interface UserService {

    User register(RegisterRequest request);

}
